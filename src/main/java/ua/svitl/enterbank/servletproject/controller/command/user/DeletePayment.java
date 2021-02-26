package ua.svitl.enterbank.servletproject.controller.command.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.svitl.enterbank.servletproject.controller.ControllerConstants;
import ua.svitl.enterbank.servletproject.controller.command.Command;
import ua.svitl.enterbank.servletproject.controller.command.CommandResult;
import ua.svitl.enterbank.servletproject.controller.command.utils.ParametersUtils;
import ua.svitl.enterbank.servletproject.model.entity.User;
import ua.svitl.enterbank.servletproject.model.service.PaymentService;
import ua.svitl.enterbank.servletproject.utils.exception.AppException;
import ua.svitl.enterbank.servletproject.utils.exception.CommandException;
import ua.svitl.enterbank.servletproject.utils.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialException;

public class DeletePayment implements Command {
    private static final long serialVersionUID = 4809067227975688398L;
    private static final Logger LOG = LogManager.getLogger(ConfirmPaymentCommand.class);
    private final PaymentService paymentService = new PaymentService();
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
        try {
            LOG.trace("Deleting payment [{}] for user ==> [ {} ]", request.getParameter("paymentid"), user);
            int id = ParametersUtils.getId(request, "paymentid");

            paymentService.deletePaymentForUserById(user, id);

        } catch (ServiceException ex) {
            LOG.error("Exception in DB ==> ", ex);
            request.setAttribute("errorMessage", ex.getMessage());
            LOG.debug("Redirecting to... {}", ControllerConstants.COMMAND_USER_PAYMENTS);
        } catch (CommandException ex) {

        }
        return CommandResult.redirect(ControllerConstants.COMMAND_USER_PAYMENTS);

    }
}
