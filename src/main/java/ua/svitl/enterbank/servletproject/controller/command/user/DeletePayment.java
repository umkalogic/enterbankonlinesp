package ua.svitl.enterbank.servletproject.controller.command.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.svitl.enterbank.servletproject.controller.command.utils.ControllerConstants;
import ua.svitl.enterbank.servletproject.controller.command.Command;
import ua.svitl.enterbank.servletproject.controller.command.CommandResult;
import ua.svitl.enterbank.servletproject.controller.command.utils.ParametersUtils;
import ua.svitl.enterbank.servletproject.model.entity.User;
import ua.svitl.enterbank.servletproject.model.service.PaymentService;
import ua.svitl.enterbank.servletproject.utils.exception.AppException;
import ua.svitl.enterbank.servletproject.utils.exception.CommandException;
import ua.svitl.enterbank.servletproject.utils.exception.ServiceException;
import ua.svitl.enterbank.servletproject.utils.resource.ResourcesBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.RescaleOp;
import java.util.ResourceBundle;

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
        ResourceBundle rb = ResourcesBundle.getResourceBundle(session);
        User user = (User) session.getAttribute("user");
        LOG.trace("Logged user: {}", user);

        try {
            LOG.trace("Deleting payment [{}] for user ==> [ {} ]", request.getParameter("paymentid"), user);
            ParametersUtils.checkIfNull(request, "paymentid", "cannot.be.null");

            int id = ParametersUtils.getId(request, "paymentid", rb.getString("wrong.id"));

            paymentService.deletePaymentForUserById(user, id);

        } catch (ServiceException ex) {
            LOG.error("Exception in DB ==> ", ex);
            request.setAttribute("errorMessage", rb.getString("label.error.loading.data"));
            request.setAttribute("infoMessage", ex.getMessage());

        } catch (CommandException ex) {
            request.setAttribute("errorMessage", rb.getString("label.error.data"));
            request.setAttribute("infoMessage", ex.getMessage());

        }
        //todo add errormessage/infomessage params to redirect request
        LOG.debug("Redirecting to... {}", ControllerConstants.COMMAND_USER_PAYMENTS);
        return CommandResult.redirect(ControllerConstants.COMMAND_USER_PAYMENTS);

    }
}
