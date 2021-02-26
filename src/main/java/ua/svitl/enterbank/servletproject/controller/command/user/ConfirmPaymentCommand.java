package ua.svitl.enterbank.servletproject.controller.command.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.svitl.enterbank.servletproject.controller.ControllerConstants;
import ua.svitl.enterbank.servletproject.controller.command.Command;
import ua.svitl.enterbank.servletproject.controller.command.CommandResult;
import ua.svitl.enterbank.servletproject.controller.command.utils.ParametersUtils;
import ua.svitl.enterbank.servletproject.model.entity.Payment;
import ua.svitl.enterbank.servletproject.model.entity.User;
import ua.svitl.enterbank.servletproject.model.service.PaymentService;
import ua.svitl.enterbank.servletproject.utils.exception.AppException;
import ua.svitl.enterbank.servletproject.utils.exception.CommandException;
import ua.svitl.enterbank.servletproject.utils.exception.ServiceException;
import ua.svitl.enterbank.servletproject.utils.resource.ResourcesBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ResourceBundle;

/**
 * Class which is used to confirm payment upon user request.
 */
public class ConfirmPaymentCommand implements Command {
    private static final long serialVersionUID = 4339457107093951826L;
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
            LOG.trace("Confirm an existing payment");
            ParametersUtils.bankAccountAttributes(request);

            Payment payment = new Payment();
            payment.setBankAccountId(ParametersUtils.getId(request, "id"));
            payment.setPaymentId(ParametersUtils.getId(request, "paymentid"));
            payment.setToBankAccount(ParametersUtils.getString(request, "tobankaccount", "Bank account number must consist of 14 digits"));
            payment.setPaymentAmount(ParametersUtils.getAmount(request));
            String bankAccountNumber = ParametersUtils.getString(request, "bankaccountfrom", "Bank account number must consist of 14 digits");
            payment.getBankAccount().setBankAccountNumber(bankAccountNumber);
            payment.getBankAccount().setCurrency(request.getParameter("currency"));
            request.setAttribute("payment", payment);

            LOG.trace("Payment to confirm: [ {} ]", payment);

            paymentService.updatePayment(user, payment);

            LOG.debug("Forwarding to... {}", ControllerConstants.PAGE_USER_PAYMENTS);

            LOG.debug("End execute command");
            return CommandResult.forward(ControllerConstants.PAGE_USER_PAYMENTS);

        } catch (ServiceException ex) {
            LOG.error("Couldn't confirm payment");
            ResourceBundle rb = ResourcesBundle.getResourceBundle(session);
            request.setAttribute("errorMessage", rb.getString("label.error.confirm.payment"));
            request.setAttribute("infoMessage", ex.getMessage());
            return CommandResult.forward(ControllerConstants.PAGE_USER_SEND_PAYMENT);
        } catch (CommandException ex) {
            LOG.error("Error in payment data ==> {}", ex.getMessage());
            request.setAttribute("errorMessage", ex.getMessage());

            return CommandResult.forward(ControllerConstants.PAGE_USER_SEND_PAYMENT);
        }

    }

}
