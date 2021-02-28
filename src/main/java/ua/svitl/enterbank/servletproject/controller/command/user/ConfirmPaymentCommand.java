package ua.svitl.enterbank.servletproject.controller.command.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.svitl.enterbank.servletproject.controller.command.utils.ControllerConstants;
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
        ResourceBundle rb = ResourcesBundle.getResourceBundle(session);
        User user = (User) session.getAttribute("user");
        if (null == user) {
            LOG.error("Unauthorized request. Session has ended");
            throw new AppException(rb.getString("message.you.must.login"));
        }
        LOG.trace("Logged user: {}", user);

        try {
            LOG.trace("Confirm an existing payment");
            ParametersUtils.bankAccountAttributes(request);

            Payment payment = new Payment();
            payment.setBankAccountId(ParametersUtils.getId(request, "id", rb.getString("wrong.id")));
            payment.setPaymentId(ParametersUtils.getId(request, "paymentid", rb.getString("wrong.id")));
            payment.setToBankAccount(ParametersUtils.getString(request, "tobankaccount",
                    rb.getString("form.validation.message.bank.account.number")));
            payment.setPaymentAmount(ParametersUtils.getAmount(request));
            String bankAccountNumber = ParametersUtils.getString(request, "bankaccountfrom",
                    rb.getString("form.validation.message.bank.account.number"));
            payment.getBankAccount().setBankAccountNumber(bankAccountNumber);
            payment.getBankAccount().setCurrency(request.getParameter("currency"));
            request.setAttribute("payment", payment);

            LOG.trace("Payment to confirm: [ {} ]", payment);

            paymentService.updatePayment(user, payment);

            LOG.debug("Forwarding to... {}", ControllerConstants.COMMAND_USER_PAYMENTS);

            LOG.debug("End execute command");
            return CommandResult.redirect(ControllerConstants.COMMAND_USER_PAYMENTS);

        } catch (ServiceException ex) {
            LOG.error("Couldn't confirm payment ==> {}", ex.getMessage());
            request.setAttribute("errorMessage", rb.getString("label.error.confirm.payment"));
            request.setAttribute("infoMessage", ex.getMessage());

        } catch (CommandException ex) {
            LOG.error("Error in payment data ==> {}", ex.getMessage());
            request.setAttribute("errorMessage", rb.getString("label.error.data"));
            request.setAttribute("infoMessage", ex.getMessage());
        }
        //todo add params to to confirmpayment with params: id, paymentid, toba, bafrom
        return CommandResult.forward(ControllerConstants.PAGE_USER_SEND_PAYMENT);
    }

}
