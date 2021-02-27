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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CreatePaymentCommand implements Command {
    private static final long serialVersionUID = 4420654600091410248L;
    private static final Logger LOG = LogManager.getLogger(CreatePaymentCommand.class);
    private final PaymentService paymentService = new PaymentService();

    /**
     * Execution method for createpayment command.
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
            LOG.trace("Creating new payment: id={}", request.getParameter("id"));

            ParametersUtils.bankAccountAttributes(request);
            Payment payment = new Payment();
            payment.setToBankAccount(ParametersUtils.getString(request, "tobankaccount",
                    "Bank account must consist of 14 digits"));
            //todo add backend validation for bank account numbers: 14 digits

            payment.setPaymentAmount(ParametersUtils.getAmount(request));
            String bankAccountNumber = ParametersUtils.getString(request, "bankaccountfrom",
                    "Bank account must consist of 14 digits");
            payment.getBankAccount().setBankAccountNumber(bankAccountNumber);
            payment.getBankAccount().setCurrency(request.getParameter("currency"));

            payment = paymentService.createPayment(user, payment, bankAccountNumber);

            LOG.trace("Created payment ==> {}", payment);
            request.setAttribute(ControllerConstants.ATTR_PAYMENT, payment);

            LOG.debug("Forwarding to... {}", ControllerConstants.PAGE_USER_SEND_PAYMENT);

            LOG.debug("End execute command");
            return CommandResult.forward(ControllerConstants.PAGE_USER_SEND_PAYMENT);

        } catch (ServiceException ex) {
            LOG.error("Exception in DB ==> ", ex);
            request.setAttribute("errorMessage", ex.getMessage());
            //todo add infoMessage attribute
            LOG.debug("Redirecting to... {}", ControllerConstants.COMMAND_USERHOME);

            //todo add infomessage/errormessage parameters
            return CommandResult.redirect(ControllerConstants.COMMAND_USERHOME);
        } catch (CommandException ex) {
            LOG.error("Exception in form data ==> {}", ex.getMessage());
            request.setAttribute("errorMessage", ex.getMessage());
            //todo add infoMessage attribute

            LOG.debug("Forwarding to... {}", ControllerConstants.PAGE_USER_MAKE_PAYMENT);

            //todo add infomessage/errormessage parameters
            return CommandResult.forward(ControllerConstants.PAGE_USER_MAKE_PAYMENT);
        }
    }

}
