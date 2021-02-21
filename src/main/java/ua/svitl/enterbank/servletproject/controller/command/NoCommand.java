package ua.svitl.enterbank.servletproject.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.svitl.enterbank.servletproject.controller.ControllerConstants;
import ua.svitl.enterbank.servletproject.controller.resource.ResourcesBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ResourceBundle;

public class NoCommand implements Command {

    private static final long serialVersionUID = -4993912539078408010L;

    private static final Logger LOG = LogManager.getLogger(NoCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("Command starts");
        HttpSession session = request.getSession();
        ResourceBundle rb = ResourcesBundle.getResourceBundle(session);
        request.setAttribute("errorMessage", rb.getString("label.no.such.command"));
        LOG.debug("Set request attribute errorMessage: no such command");
        LOG.debug("Command finished");
        return CommandResult.forward(ControllerConstants.PAGE_ERROR);
    }

    @Override
    public final String toString() {
        return getClass().getSimpleName();
    }
}
