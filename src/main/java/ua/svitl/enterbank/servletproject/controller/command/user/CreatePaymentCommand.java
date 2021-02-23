package ua.svitl.enterbank.servletproject.controller.command.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.svitl.enterbank.servletproject.controller.ControllerConstants;
import ua.svitl.enterbank.servletproject.controller.command.Command;
import ua.svitl.enterbank.servletproject.controller.command.CommandResult;
import ua.svitl.enterbank.servletproject.model.entity.Payment;
import ua.svitl.enterbank.servletproject.model.entity.User;
import ua.svitl.enterbank.servletproject.model.service.PaymentService;
import ua.svitl.enterbank.servletproject.utils.exception.AppException;
import ua.svitl.enterbank.servletproject.utils.exception.CommandException;
import ua.svitl.enterbank.servletproject.utils.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static ua.svitl.enterbank.servletproject.controller.command.user.utils.PaymentUtils.*;

public class CreatePaymentCommand implements Command {
    private static final long serialVersionUID = 4339457107093951826L;
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
            LOG.trace("Creating new payment");
            Payment payment = new Payment();
            payment.setBankAccountId(setId(request));
            payment.setToBankAccount(getString(request, "tobankaccount"));
            payment.setPaymentAmount(setAmount(request));
            String bankAccountNumber = getString(request, "bankaccountfrom");

            payment = paymentService.createPayment(user, payment, bankAccountNumber);

            LOG.trace("Created payment ==> {}", payment);
            request.setAttribute(ControllerConstants.ATTR_PAYMENT, payment);

            LOG.debug("Forwarding to... {}", ControllerConstants.PAGE_USER_SEND_PAYMENT);

            LOG.debug("End execute command");
            return CommandResult.forward(ControllerConstants.PAGE_USER_SEND_PAYMENT);
        } catch (ServiceException ex) {
            LOG.error("Exception in DB ==> ", ex);
            request.setAttribute("errorMessage", ex.getMessage());
            return CommandResult.forward(ControllerConstants.PAGE_USER_HOME);
        } catch (CommandException ex) {
            LOG.error("Exception in form data ==> {}", ex.getMessage());
            request.setAttribute("errorMessage", ex.getMessage());
            return CommandResult.forward(ControllerConstants.PAGE_USER_SEND_PAYMENT);
        }
    }

}
