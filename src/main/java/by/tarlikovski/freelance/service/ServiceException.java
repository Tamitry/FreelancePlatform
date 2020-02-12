package by.tarlikovski.freelance.service;

public class ServiceException extends Exception {
    public ServiceException() {
        super();
    }

    public ServiceException(final String message) {
        super(message);
    }

    public ServiceException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ServiceException(final Throwable cause) {
        super(cause);
    }

    public ServiceException(final Exception e) {
        super(e);
    }
}
