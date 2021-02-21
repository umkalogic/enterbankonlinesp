package ua.svitl.enterbank.servletproject.controller.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {

    private static final Logger LOG = LogManager.getLogger(ContextListener.class);

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        LOG.debug("Servlet context destruction starts");
        LOG.debug("Servlet context destruction ends");
    }

    @Override
    public void contextInitialized(ServletContextEvent event) {
        LOG.debug("Servlet context initialization starts");

        LOG.trace(event.getServletContext().getServletContextName());
        initCommandContainer();

        LOG.debug("Servlet context initialization ends");
    }

    /**
     * Initializes CommandContainer.
     */
    private void initCommandContainer() {
        // initialize commands container
        // just load class to JVM
        try {
            LOG.debug("Try load CommandContainer");
            Class.forName("ua.svitl.enterbank.servletproject.controller.command.CommandContainer");
        } catch (ClassNotFoundException ex) {
            LOG.error("Cannot initialize Command Container ==> {}", ex.getMessage());
            throw new IllegalStateException("Cannot initialize Command Container");
        }
    }

}
