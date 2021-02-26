package ua.svitl.enterbank.servletproject.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.svitl.enterbank.servletproject.controller.command.utils.ControllerConstants;
import ua.svitl.enterbank.servletproject.model.entity.User;
import ua.svitl.enterbank.servletproject.utils.exception.AppException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DefaultCommand implements Command {
    private static final long serialVersionUID = -6936944036687961187L;
    private static final Logger LOG = LogManager.getLogger(DefaultCommand.class);
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
        User user = (User) session.getAttribute("user");
        LOG.trace("Logged user: {}", user);

        if ("ADMIN".equals(user.getRole().getRoleName())) {
            return CommandResult.redirect(ControllerConstants.COMMAND_ADMINHOME);
        } else {
            return CommandResult.redirect(ControllerConstants.COMMAND_USERHOME);
        }
    }
}
