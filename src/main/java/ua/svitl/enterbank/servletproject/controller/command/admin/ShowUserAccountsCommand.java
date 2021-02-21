package ua.svitl.enterbank.servletproject.controller.command.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.svitl.enterbank.servletproject.controller.ControllerConstants;
import ua.svitl.enterbank.servletproject.controller.command.Command;
import ua.svitl.enterbank.servletproject.controller.command.CommandResult;
import ua.svitl.enterbank.servletproject.controller.resource.ResourcesBundle;
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
            int id = setId(request);
            LOG.trace("Looking accounts for user id={}", id);
            List<BankAccountDto> bankAccountList =
                    bankAccountService.getUserAccountsById(id, "bank_account_number", "asc");

            request.setAttribute("listBankAccounts", bankAccountList);

            request.setAttribute("sortField", "bank_account_number");
            request.setAttribute("sortDir", "asc");
            request.setAttribute("reverseSortDir", "desc");

            LOG.debug("Forwarding to... {}", ControllerConstants.PAGE_USER_HOME);
            LOG.debug("End execute command");
            return CommandResult.forward(ControllerConstants.PAGE_USER_HOME);
        } catch (ServiceException ex) {
            LOG.error("UserHome: error loading user home page");
            request.setAttribute("errorMessage", rb.getString("label.error.loading.data"));
            return CommandResult.forward(ControllerConstants.PAGE_USER_HOME);
        }
    }

    private static int setId(HttpServletRequest request) {
        try {
            return Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException ex) {
            return 1;
        }
    }
}
