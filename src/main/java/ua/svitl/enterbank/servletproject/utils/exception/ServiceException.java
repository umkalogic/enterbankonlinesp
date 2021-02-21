package ua.svitl.enterbank.servletproject.utils.exception;

public class ServiceException extends Exception {
    private static final long serialVersionUID = 2352441974767357010L;

    public ServiceException() {
        super();
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }
}
