package ua.svitl.enterbank.servletproject.controller.command.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.svitl.enterbank.servletproject.utils.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParametersUtils {
    private static final Logger LOG = LogManager.getLogger(ParametersUtils.class);

    private ParametersUtils() {}

    public static String getString(HttpServletRequest request, String param, String errorMessage) throws CommandException {
        String str = request.getParameter(param);
        if (str == null) {
            LOG.error("Couldn't get String parameter ==> {}", param);
            throw new CommandException(errorMessage);
        }
        return str;
    }

    public static int getId(HttpServletRequest request, String param, String errorMessage) throws CommandException {
        try {
            return Integer.parseInt(request.getParameter(param));
        } catch (NumberFormatException ex) {
            LOG.error("Couldn't parse to Int ==> {}", ex.getMessage());
            throw new CommandException(errorMessage);
        }
    }

    public static double getAmount(HttpServletRequest request) throws CommandException {
        try {
            return Double.parseDouble(request.getParameter("paymentamount"));
        } catch (NumberFormatException ex) {
            LOG.error("Couldn't parse to Double ==> {}", ex.getMessage());
            throw new CommandException("Payment amount has wrong format: e.g. 0.01, 250.1, 25.05");
        }
    }

    public static int getIntOrOne(HttpServletRequest request, String param) {
        try {
            return Integer.parseInt(request.getParameter(param));
        } catch (NumberFormatException ex) {
            LOG.error("Couldn't parse to Int ==> {}", ex.getMessage());
            LOG.trace("Setting {} to 1", param);
            return 1;
        }
    }

    public static void setPaginationAttributes(HttpServletRequest request, String sortField, String sortDir,
                                               int pageNo, int pageSize, int size) {
        request.setAttribute("currentPage", pageNo);
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("prev", pageNo > 1);
        request.setAttribute("next", size == pageSize + 1);

        request.setAttribute("sortField", sortField);
        request.setAttribute("sortDir", sortDir);
        request.setAttribute("reverseSortDir", "asc".equalsIgnoreCase(sortDir) ? "desc" : "asc");
    }

    public static void bankAccountAttributes(HttpServletRequest request) {
        request.setAttribute("currency", request.getParameter("currency"));
        request.setAttribute("id", request.getParameter("id"));
        request.setAttribute("bankaccountfrom", request.getParameter("bankaccountfrom"));
        request.setAttribute("tobankaccount", request.getParameter("tobankaccount"));
        request.setAttribute("paymentamount", request.getParameter("paymentamount"));
    }

    public static boolean checkIfNull(HttpServletRequest request, String param, String errorMessage) throws CommandException {
        if (request.getParameter(param) == null) {
            throw new CommandException(errorMessage);
        } else {
            return false;
        }
    }

    public static boolean checkUserName(String userName, String errorMessage) throws CommandException {
        Pattern pt = Pattern.compile("[a-zA-Z_][a-zA-Z0-9_]{4,}");
        Matcher mt = pt.matcher(userName);
        if (!mt.find()) {
            throw new CommandException(errorMessage);
        }
        return true;
    }

    public static boolean checkEmail(String email, String errorMessage) throws CommandException {
        Pattern pt = Pattern.compile("^[a-zA-Z][a-zA-Z0-9_.]{0,50}[@][a-zA-Z][a-zA-Z0-9_]{0,50}[.][a-z]{2,4}");
        Matcher mt = pt.matcher(email);
        if (!mt.find()) {
            throw new CommandException(errorMessage);
        }
        return true;
    }

}
