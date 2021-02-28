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
import ua.svitl.enterbank.servletproject.utils.exception.ServiceException;
import ua.svitl.enterbank.servletproject.utils.resource.ResourcesBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.ResourceBundle;

import static ua.svitl.enterbank.servletproject.controller.command.utils.ParametersUtils.getIntOrOne;

public class PaymentsCommand implements Command {
    private static final long serialVersionUID = 931241697412670011L;
    private static final Logger LOG = LogManager.getLogger(PaymentsCommand.class);
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
        HttpSession session = request.getSession();
        ResourceBundle rb = ResourcesBundle.getResourceBundle(session);
        User user = (User) session.getAttribute("user");
        if (null == user) {
            LOG.error("Unauthorized request. Session has ended");
            throw new AppException(rb.getString("message.you.must.login"));
        }
        LOG.trace("Logged user: {}", user);

        try {
            LOG.debug("Start execute command=payments");

            String sortField = request.getParameter("sortfield") == null ? "payment_date" :
                    request.getParameter("sortfield");
            String sortDir = request.getParameter("sortdir") == null ? "desc" : request.getParameter("sortdir");

            int pageNo = getIntOrOne(request, "currentpage");
            int pageSize = ControllerConstants.PAGE_SIZE + ControllerConstants.PAGE_SIZE;
            List<Payment> payments =
                    paymentService.findAllPaymentsByUser(user, pageNo, pageSize + 1, sortField, sortDir);
            payments.forEach(e -> LOG.debug(e.toString()));

            request.setAttribute("payments", payments);
            ParametersUtils.setPaginationAttributes(request, sortField, sortDir, pageNo, pageSize, payments.size());

            if (request.getParameter("infomessage") != null) {
                request.setAttribute("infoMessage", request.getParameter("infomessage"));
            }

            if (request.getParameter("errormessage") != null) {
                request.setAttribute("errorMessage", request.getParameter("errormessage"));
            }

            if (request.getParameter("successmessage") != null) {
                request.setAttribute("successMessage", request.getParameter("successmessage"));
            }

            LOG.debug("End execute command=payments. Forwarding to... {}", ControllerConstants.PAGE_USER_PAYMENTS);

        } catch (ServiceException ex) {
            LOG.error("Command=payments: error loading user payments");
            request.setAttribute("errorMessage", "label.error.loading.data");
            request.setAttribute("infoMessage", ex.getMessage());
        }

        return CommandResult.forward(ControllerConstants.PAGE_USER_PAYMENTS);
    }

}
