package ua.svitl.enterbank.servletproject.controller.command.user.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.svitl.enterbank.servletproject.controller.command.user.ConfirmPaymentCommand;
import ua.svitl.enterbank.servletproject.utils.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

public class PaymentUtils {
    private static final Logger LOG = LogManager.getLogger(PaymentUtils.class);

    public static String getString(HttpServletRequest request, String param) throws CommandException {
        String str = request.getParameter(param);
        if (str == null) {
            LOG.error("Couldn't parse to Double ==> {}", param);
            throw new CommandException("Input field is empty");
        }
        return str;
    }

    public static int setId(HttpServletRequest request) throws CommandException {
        try {
            return Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException ex) {
            LOG.error("Couldn't parse to Int ==> {}", ex.getMessage());
            throw new CommandException("Bank account id is wrong");
        }
    }

    public static double setAmount(HttpServletRequest request) throws CommandException {
        try {
            return Double.parseDouble(request.getParameter("paymentamount"));
        } catch (NumberFormatException ex) {
            LOG.error("Couldn't parse to Double ==> {}", ex.getMessage());
            throw new CommandException("Payment amount has wrong format");
        }
    }
}
