package ua.svitl.enterbank.servletproject.utils.exception;

public class AppException extends Exception {

    private static final long serialVersionUID = 6609277490766405589L;

    public AppException() {
        super();
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppException(String message) {
        super(message);
    }

    public AppException(Throwable cause) {
        super(cause);
    }

}