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
        try {
            LOG.debug("Start execute command=payments");
            User user = (User) session.getAttribute("user");
            LOG.trace("Logged user: {}", user);
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

            LOG.debug("End execute command=payments. Forwarding to... {}", ControllerConstants.PAGE_USER_PAYMENTS);
            return CommandResult.forward(ControllerConstants.PAGE_USER_PAYMENTS);
        } catch (ServiceException ex) {
            LOG.error("Command=payments: error loading user payments");
            request.setAttribute("errorMessage", rb.getString("label.error.loading.data"));
            return CommandResult.forward(ControllerConstants.PAGE_USER_PAYMENTS);
        }
    }

}
