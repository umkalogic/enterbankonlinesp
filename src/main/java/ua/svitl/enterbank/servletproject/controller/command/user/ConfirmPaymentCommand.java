package ua.svitl.enterbank.servletproject.controller.command.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.svitl.enterbank.servletproject.controller.ControllerConstants;
import ua.svitl.enterbank.servletproject.controller.command.Command;
import ua.svitl.enterbank.servletproject.controller.command.CommandResult;
import ua.svitl.enterbank.servletproject.model.entity.Payment;
import ua.svitl.enterbank.servletproject.model.entity.User;
import ua.svitl.enterbank.servletproject.utils.exception.AppException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Class which is used to confirm payment upon user request.
 */
public class ConfirmPaymentCommand implements Command {
    private static final long serialVersionUID = 4339457107093951826L;
    private static final Logger LOG = LogManager.getLogger(ShowFormForPaymentCommand.class);
    /**
     * Execution method for command.
     *
     * @param request  http request
     * @param response http response
     * @return CommandResult with address and forward/redirect command to go once the command is executed.
     */
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        LOG.debug("Start execute command");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        LOG.trace("Logged user: {}", user);
        LOG.trace("Showing payment from account id: {}, {}",
                request.getParameter("id"), request.getParameter("bankaccountfrom"));

        Payment payment = (Payment) request.getAttribute("payment");
        LOG.debug("Forwarding to... {}", ControllerConstants.PAGE_USER_SEND_PAYMENT);

        LOG.debug("End execute command");
        return CommandResult.forward(ControllerConstants.PAGE_USER_SEND_PAYMENT);
    }
}
