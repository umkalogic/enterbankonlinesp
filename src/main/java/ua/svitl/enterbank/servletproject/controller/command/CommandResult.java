package ua.svitl.enterbank.servletproject.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommandResult {
    private static final Logger LOG = LogManager.getLogger(CommandResult.class);
    private final String page;
    private final boolean redirect;

    private CommandResult(String page, boolean redirect) {
        this.page = page;
        this.redirect = redirect;
    }

    public static CommandResult forward(String page) {
        LOG.debug("Forwarding... {}", page);
        return new CommandResult(page, false);
    }

    public static CommandResult redirect(String page) {
        LOG.debug("Redirecting... {}", page);
        return new CommandResult(page, true);
    }

    public String getPage() {
        return page;
    }

    public boolean isRedirect() {
        return redirect;
    }

    @Override
    public String toString() {
        return "CommandResult{" +
                "page='" + page + '\'' +
                ", redirect=" + redirect +
                '}';
    }
}