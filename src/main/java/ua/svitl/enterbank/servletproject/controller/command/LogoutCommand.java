package ua.svitl.enterbank.servletproject.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.svitl.enterbank.servletproject.controller.ControllerConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command {
    private static final long serialVersionUID = 5851626878917197418L;

    private static final Logger LOG = LogManager.getLogger(LogoutCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("Command starts");

        HttpSession session = request.getSession(false);

        if (session != null) {
            session.removeAttribute("user");
            session.removeAttribute("role");
            LOG.debug("Session invalidates");
            session.invalidate();
        }

        response.setHeader("Cache-control", "no-cache");
        response.setHeader("Cache-control", "no-store");
        response.setHeader("Pragma", "no-cache");
        LOG.debug("Command ends. Go to {}", ControllerConstants.PAGE_INDEX);
        return CommandResult.redirect(ControllerConstants.PAGE_INDEX);
    }

    @Override
    public final String toString() {
        return getClass().getSimpleName();
    }
}
