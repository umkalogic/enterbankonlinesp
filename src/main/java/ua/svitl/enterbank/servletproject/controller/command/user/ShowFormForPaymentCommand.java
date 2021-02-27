package ua.svitl.enterbank.servletproject.controller.command.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.svitl.enterbank.servletproject.controller.command.Command;
import ua.svitl.enterbank.servletproject.controller.command.CommandResult;
import ua.svitl.enterbank.servletproject.controller.command.utils.ControllerConstants;
import ua.svitl.enterbank.servletproject.controller.command.utils.ParametersUtils;
import ua.svitl.enterbank.servletproject.model.entity.User;
import ua.svitl.enterbank.servletproject.utils.exception.AppException;
import ua.svitl.enterbank.servletproject.utils.exception.CommandException;
import ua.svitl.enterbank.servletproject.utils.resource.ResourcesBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ResourceBundle;


/**
 * Class which is used for creating new payment upon user request.
 */
public class ShowFormForPaymentCommand implements Command {
    private static final long serialVersionUID = 5222081051064775272L;
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
        ResourceBundle rb = ResourcesBundle.getResourceBundle(session);

        LOG.trace("Logged user: {}", user);

        try {
            ParametersUtils.checkIfNull(request, "id", rb.getString("cannot.be.null"));
        } catch (CommandException ex) {
            //todo add infomessage/errormessage
            //todo forward page_user_make_payment or redirect to previous page
        }

        LOG.trace("Making payment from account id: {}; account number: {}",
                request.getParameter("id"), request.getParameter("bankaccountfrom"));

        request.setAttribute("id", request.getParameter("id"));
        request.setAttribute("bankaccountfrom", request.getParameter("bankaccountfrom"));
        request.setAttribute("currency", request.getParameter("currency"));


        LOG.debug("Forwarding to... {}", ControllerConstants.PAGE_USER_MAKE_PAYMENT);

        LOG.debug("End execute command");
        return CommandResult.forward(ControllerConstants.PAGE_USER_MAKE_PAYMENT);
    }

}
