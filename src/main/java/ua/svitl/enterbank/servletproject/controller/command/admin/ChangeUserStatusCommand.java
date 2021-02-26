package ua.svitl.enterbank.servletproject.controller.command.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.svitl.enterbank.servletproject.controller.ControllerConstants;
import ua.svitl.enterbank.servletproject.controller.command.Command;
import ua.svitl.enterbank.servletproject.controller.command.CommandResult;
import ua.svitl.enterbank.servletproject.controller.command.utils.ParametersUtils;
import ua.svitl.enterbank.servletproject.model.service.UserService;
import ua.svitl.enterbank.servletproject.utils.exception.AppException;
import ua.svitl.enterbank.servletproject.utils.exception.CommandException;
import ua.svitl.enterbank.servletproject.utils.exception.ServiceException;
import ua.svitl.enterbank.servletproject.utils.resource.ResourcesBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ResourceBundle;

public class ChangeUserStatusCommand implements Command {
    private static final long serialVersionUID = 5002523440278557319L;
    private final UserService userService = new UserService();
    private static final Logger LOG = LogManager.getLogger(ChangeUserStatusCommand.class);

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
            LOG.debug("Start user change status command: user id={}", request.getParameter("id"));

            int id = ParametersUtils.getId(request, "id");
            boolean status = "true".equalsIgnoreCase(request.getParameter("isactive"));

            userService.updateUserActive(id, status);

        } catch (ServiceException ex) {
            LOG.error("Error loading user by id");
            request.setAttribute("errorMessage", rb.getString("label.error.loading.data"));
            request.setAttribute("infoMessage", ex.getMessage());
        } catch (CommandException ex) {
            request.setAttribute("errorMessage", rb.getString("label.error.loading.data"));
            request.setAttribute("infoMessage", ex.getMessage());
        }

        LOG.debug("Redirecting to... {}", ControllerConstants.COMMAND_ADMINHOME);
        //todo add infoMessage at admin home page
        return CommandResult.redirect(ControllerConstants.COMMAND_ADMINHOME);
    }
}
