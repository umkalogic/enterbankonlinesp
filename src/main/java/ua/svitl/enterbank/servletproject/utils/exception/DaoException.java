package ua.svitl.enterbank.servletproject.utils.exception;

public class DaoException extends RuntimeException {
    private static final long serialVersionUID = 1435767695053005139L;

    public DaoException() {
        super();
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(Throwable cause) {
        super(cause);
    }
}
