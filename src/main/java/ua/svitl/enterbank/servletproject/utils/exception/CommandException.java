package ua.svitl.enterbank.servletproject.utils.exception;

public class CommandException extends Exception {
    private static final long serialVersionUID = -8175820292710650130L;

    public CommandException() {
        super();
    }

    public CommandException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommandException(String message) {
        super(message);
    }

    public CommandException(Throwable cause) {
        super(cause);
    }

}
