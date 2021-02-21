package ua.svitl.enterbank.servletproject.controller.resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSession;
import java.util.Locale;
import java.util.ResourceBundle;

public class ResourcesBundle {
    private static final Logger LOG = LogManager.getLogger(ResourcesBundle.class);

    public static ResourceBundle getResourceBundle(HttpSession session) {
        Object langParameter = session.getAttribute("lang");
        LOG.debug("ResourcesBundle lang: {}", langParameter);
        Locale currentLang;
        if (langParameter != null) {
            currentLang = new Locale(String.valueOf(langParameter));
        } else {
            currentLang = new Locale("");
        }
        return ResourceBundle.getBundle("messages", currentLang);
    }

    private ResourcesBundle() {}

}
