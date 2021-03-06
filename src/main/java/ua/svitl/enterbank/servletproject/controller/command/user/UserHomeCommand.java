package ua.svitl.enterbank.servletproject.controller.command.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.svitl.enterbank.servletproject.controller.command.utils.ControllerConstants;
import ua.svitl.enterbank.servletproject.controller.command.Command;
import ua.svitl.enterbank.servletproject.controller.command.CommandResult;
import ua.svitl.enterbank.servletproject.utils.resource.ResourcesBundle;
import ua.svitl.enterbank.servletproject.model.dto.BankAccountDto;
import ua.svitl.enterbank.servletproject.model.entity.User;
import ua.svitl.enterbank.servletproject.model.service.BankAccountService;
import ua.svitl.enterbank.servletproject.utils.exception.AppException;
import ua.svitl.enterbank.servletproject.utils.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.ResourceBundle;

public class UserHomeCommand implements Command {
    private static final long serialVersionUID = 5222081051064775272L;
    private final BankAccountService bankAccountService = new BankAccountService();
    private static final Logger LOG = LogManager.getLogger(UserHomeCommand.class);
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
        User user = (User) session.getAttribute("user");
        if (null == user) {
            ResourceBundle rb = ResourcesBundle.getResourceBundle(session);
            LOG.error("Unauthorized request. Session has ended");
            throw new AppException(rb.getString("message.you.must.login"));
        }
        LOG.trace("Logged user: {}", user);

        try {
            LOG.debug("Start execute command");

            String sortField = request.getParameter("sortfield") == null ? "bank_account_number" :
                    request.getParameter("sortfield");
            String sortDir = request.getParameter("sortdir") == null ? "asc" : request.getParameter("sortdir");

            List<BankAccountDto> bankAccountDtoList =
                    bankAccountService.getUserAccounts(user, sortField, sortDir);

            request.setAttribute("listBankAccounts", bankAccountDtoList);

            request.setAttribute("sortField", sortField);
            request.setAttribute("sortDir", sortDir);
            request.setAttribute("reverseSortDir", "asc".equalsIgnoreCase(sortDir) ? "desc" : "asc");

            if (request.getParameter("infomessage") != null) {
                request.setAttribute("infoMessage", request.getParameter("infomessage"));
            }

            if (request.getParameter("errormessage") != null) {
                request.setAttribute("errorMessage", request.getParameter("errormessage"));
            }

            LOG.debug("Forwarding to... {}", ControllerConstants.PAGE_USER_HOME);
            LOG.debug("End execute command");
            return CommandResult.forward(ControllerConstants.PAGE_USER_HOME);

        } catch (ServiceException ex) {
            LOG.error("UserHome: error loading user home page");
            request.setAttribute("errorMessage", "label.error.loading.data");
            request.setAttribute("infoMessage", ex.getMessage());
            LOG.debug("Redirecting to ==> {}", ControllerConstants.COMMAND_USERHOME);
            return CommandResult.redirect(ControllerConstants.COMMAND_USERHOME +
                    "&errormessage=" + request.getAttribute("errorMessage") +
                    "&infomessage=" + request.getAttribute("infoMessage"));
        }
    }
}
