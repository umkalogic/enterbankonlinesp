package ua.svitl.enterbank.servletproject.controller.command.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.svitl.enterbank.servletproject.controller.command.utils.ControllerConstants;
import ua.svitl.enterbank.servletproject.controller.command.Command;
import ua.svitl.enterbank.servletproject.controller.command.CommandResult;
import ua.svitl.enterbank.servletproject.controller.command.utils.ParametersUtils;
import ua.svitl.enterbank.servletproject.model.service.BankAccountService;
import ua.svitl.enterbank.servletproject.utils.exception.AppException;
import ua.svitl.enterbank.servletproject.utils.exception.CommandException;
import ua.svitl.enterbank.servletproject.utils.exception.ServiceException;
import ua.svitl.enterbank.servletproject.utils.resource.ResourcesBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ResourceBundle;

public class ChangeAccountStatusCommand implements Command {
    private static final long serialVersionUID = 4680815531346472625L;
    private final BankAccountService bankAccountService = new BankAccountService();
    private static final Logger LOG = LogManager.getLogger(ChangeAccountStatusCommand.class);
    /**
     * Execution method for command.
     *
     * @param request  http request
     * @param response http response
     * @return CommandResult with address and forward/redirect command to go once the command is executed.
     */
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        HttpSession session = request.getSession();
        ResourceBundle rb = ResourcesBundle.getResourceBundle(session);

        try {
            LOG.debug("Start account change status command: user id={}, ba_id={}",
                    request.getParameter("id"), request.getParameter("baid"));
            ParametersUtils.checkIfNull(request, "id", rb.getString("cannot.be.null"));
            ParametersUtils.checkIfNull(request, "baid", rb.getString("cannot.be.null"));

            int id = ParametersUtils.getId(request, "id", rb.getString("wrong.id"));
            int baid = ParametersUtils.getId(request, "baid",  rb.getString("wrong.id"));
            boolean status = "true".equalsIgnoreCase(request.getParameter("isactive"));

            bankAccountService.updateAccountActive(baid, status);

            LOG.debug("Redirecting to... {}", ControllerConstants.COMMAND_ADMIN_SHOW_USER_ACCOUNTS);
            return CommandResult.redirect(ControllerConstants.COMMAND_ADMIN_SHOW_USER_ACCOUNTS +
                    "&id=" + id);
        } catch (ServiceException ex) {
            LOG.error("Error loading user by id ==> {}", ex.getMessage());
            request.setAttribute("errorMessage", rb.getString("label.error.loading.data"));
            request.setAttribute("infoMessage", ex.getMessage());
        } catch (CommandException ex) {
            request.setAttribute("errorMessage", rb.getString("label.error.data"));
            request.setAttribute("infoMessage", ex.getMessage());

        }
        return CommandResult.redirect(ControllerConstants.COMMAND_ADMINHOME +
                "&errormessage=" + request.getAttribute("errorMessage") +
                "&infomessage=" + request.getAttribute("infoMessage"));

    }
}
