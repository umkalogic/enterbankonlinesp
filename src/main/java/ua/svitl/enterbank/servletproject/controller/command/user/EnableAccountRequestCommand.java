package ua.svitl.enterbank.servletproject.controller.command.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.svitl.enterbank.servletproject.controller.command.utils.ControllerConstants;
import ua.svitl.enterbank.servletproject.controller.command.Command;
import ua.svitl.enterbank.servletproject.controller.command.CommandResult;
import ua.svitl.enterbank.servletproject.controller.command.utils.ParametersUtils;
import ua.svitl.enterbank.servletproject.model.entity.User;
import ua.svitl.enterbank.servletproject.model.service.BankAccountService;
import ua.svitl.enterbank.servletproject.utils.exception.AppException;
import ua.svitl.enterbank.servletproject.utils.exception.CommandException;
import ua.svitl.enterbank.servletproject.utils.exception.ServiceException;
import ua.svitl.enterbank.servletproject.utils.resource.ResourcesBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ResourceBundle;

public class EnableAccountRequestCommand implements Command {
    private static final long serialVersionUID = 8888993852329358342L;
    private final BankAccountService bankAccountService = new BankAccountService();
    private static final Logger LOG = LogManager.getLogger(EnableAccountRequestCommand.class);
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
        User user = (User) session.getAttribute("user");
        if (null == user) {
            LOG.error("Unauthorized request. Session has ended");
            throw new AppException(rb.getString("message.you.must.login"));
        }
        LOG.trace("Logged user: {}", user);
        try {
            LOG.debug("Start enable account request command: account id={}", request.getParameter("id"));

            ParametersUtils.checkIfNull(request, "id", "cannot.be.null");
            int id = ParametersUtils.getId(request, "id", "wrong.id");

            bankAccountService.updateAccountEnableRequest(user, id);

            LOG.debug("Redirecting to... {}", ControllerConstants.COMMAND_USERHOME);
            return CommandResult.redirect(ControllerConstants.COMMAND_USERHOME);

        } catch (ServiceException ex) {
            LOG.error("Error loading account by id");
            request.setAttribute("errorMessage", rb.getString("label.error.loading.data"));
            request.setAttribute("infoMessage", ex.getMessage());

        } catch (CommandException ex) {
            request.setAttribute("errorMessage", rb.getString("label.error.data"));
            request.setAttribute("infoMessage", ex.getMessage());
        }

        LOG.debug("Redirecting to ==> {}", ControllerConstants.COMMAND_USERHOME);
        return CommandResult.redirect(ControllerConstants.COMMAND_USERHOME +
                "&errormessage=" + request.getAttribute("errorMessage") +
                "&infomessage=" + request.getAttribute("infoMessage"));
    }
}
