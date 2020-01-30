package by.tarlikovski.freelance.exception;

public class ControlException extends Exception {
    public ControlException() {
        super();
    }

    public ControlException(final String message) {
        super(message);
    }

    public ControlException(final String message,
                            final Throwable cause) {
        super(message, cause);
    }

    public ControlException(final Throwable cause) {
        super(cause);
    }

    protected ControlException(final String message,
                               final Throwable cause,
                               final boolean enableSuppression,
                               final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
