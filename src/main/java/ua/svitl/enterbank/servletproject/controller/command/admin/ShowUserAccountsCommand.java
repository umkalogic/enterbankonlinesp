package ua.svitl.enterbank.servletproject.controller.command.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.svitl.enterbank.servletproject.controller.command.utils.ControllerConstants;
import ua.svitl.enterbank.servletproject.controller.command.Command;
import ua.svitl.enterbank.servletproject.controller.command.CommandResult;
import ua.svitl.enterbank.servletproject.controller.command.utils.ParametersUtils;
import ua.svitl.enterbank.servletproject.utils.exception.CommandException;
import ua.svitl.enterbank.servletproject.utils.resource.ResourcesBundle;
import ua.svitl.enterbank.servletproject.model.dto.BankAccountDto;
import ua.svitl.enterbank.servletproject.model.service.BankAccountService;
import ua.svitl.enterbank.servletproject.utils.exception.AppException;
import ua.svitl.enterbank.servletproject.utils.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.ResourceBundle;

public class ShowUserAccountsCommand implements Command {
    private static final long serialVersionUID = -7162025736812054087L;
    private final BankAccountService bankAccountService = new BankAccountService();
    private static final Logger LOG = LogManager.getLogger(ShowUserAccountsCommand.class);
    /**
     * Execution method for command.
     *
     * @param request http request
     * @param response http response
     * @return CommandResult with address and forward/redirect command to go once the command is executed.
     */
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        HttpSession session = request.getSession();
        ResourceBundle rb = ResourcesBundle.getResourceBundle(session);
        try {
            LOG.debug("Start execute command");

            ParametersUtils.checkIfNull(request, "id", rb.getString("cannot.be.null"));
            int id = ParametersUtils.getId(request, "id", rb.getString("wrong.id"));

            LOG.trace("Looking accounts for user id={}", id);

            String sortField = request.getParameter("sortfield") == null ? "bank_account_number" :
                    request.getParameter("sortfield");

            String sortDir = request.getParameter("sortdir") == null ? "asc" : request.getParameter("sortdir");

            List<BankAccountDto> bankAccountList =
                    bankAccountService.getUserAccountsById(id, sortField, sortDir);

            request.setAttribute("listBankAccounts", bankAccountList);

            request.setAttribute("id", id);
            request.setAttribute("sortField", sortField);
            request.setAttribute("sortDir", sortDir);
            request.setAttribute("reverseSortDir", "asc".equalsIgnoreCase(sortDir) ? "desc" : "asc");


        } catch (ServiceException ex) {
            LOG.error("Error loading user accounts ==> {}", ex.getMessage());
            request.setAttribute("errorMessage", rb.getString("label.error.loading.data"));
            request.setAttribute("infoMessage", ex.getMessage());

        } catch (CommandException ex) {
            LOG.error("Catch command exception ==> {}", ex.getMessage());
            request.setAttribute("errorMessage", rb.getString("label.error.loading.data"));
            request.setAttribute("infoMessage", ex.getMessage());
        }

        LOG.debug("Forwarding to... {}", ControllerConstants.PAGE_ADMIN_USER_ACCOUNTS);
        LOG.debug("End execute command");
        return CommandResult.forward(ControllerConstants.PAGE_ADMIN_USER_ACCOUNTS);
    }

}
