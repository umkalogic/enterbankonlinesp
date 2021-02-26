package ua.svitl.enterbank.servletproject.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.svitl.enterbank.servletproject.controller.command.utils.ControllerConstants;
import ua.svitl.enterbank.servletproject.utils.resource.Language;
import ua.svitl.enterbank.servletproject.utils.resource.ResourcesBundle;
import ua.svitl.enterbank.servletproject.utils.exception.AppException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ResourceBundle;

public class ChangeLangCommand implements Command {
    private static final Logger LOG = LogManager.getLogger(ChangeLangCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws AppException {

        LOG.debug("Command changelang starts");
        String lang = req.getParameter("lang");
        String page = req.getParameter("page");

        HttpSession session = req.getSession();

        ResourceBundle rb = ResourcesBundle.getResourceBundle(session);
        if (lang == null || page == null) {
            session.setAttribute("errorMessage", rb.getString("wrong.operation"));
            LOG.error("Unknown operation: {}", rb.getString("wrong.operation"));
            return CommandResult.forward(ControllerConstants.PAGE_ERROR);
        }

        Language languageName = new Language();
        if (languageName.isLangExist(lang)) {
            session.setAttribute("lang", lang);
        } else {
            LOG.error("Unknown language: {}", lang);
            throw new AppException("Unknown language: " + lang);
        }
        LOG.debug("Command changelang ends");
        switch (page) {
            case "login":
                return CommandResult.forward(ControllerConstants.PAGE_LOGIN);
            case "error":
                return CommandResult.forward(ControllerConstants.PAGE_ERROR);
            case "adminhome":
                return CommandResult.redirect(ControllerConstants.COMMAND_ADMINHOME);
            case "userhome":
                return CommandResult.redirect(ControllerConstants.COMMAND_USERHOME);
            case "useraccounts":
                return CommandResult.redirect(ControllerConstants.COMMAND_ADMIN_SHOW_USER_ACCOUNTS +
                        "&id=" + req.getParameter("id"));
            case "showformforuserupdate":
                return CommandResult.redirect(ControllerConstants.COMMAND_ADMIN_SHOW_FORM_FOR_USER_UPDATE +
                        "&id=" + req.getParameter("id"));
            default:
                LOG.error("Command changelang: unknown page: {}", page);
                req.setAttribute("errorMessage", rb.getString("label.no.such.command"));
                return CommandResult.forward(ControllerConstants.PAGE_ERROR);
        }

    }

}
