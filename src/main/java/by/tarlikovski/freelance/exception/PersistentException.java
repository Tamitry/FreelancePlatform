package by.tarlikovski.freelance.exception;

public class PersistentException extends Exception {
    public PersistentException() {
        super();
    }

    public PersistentException(final String message) {
        super(message);
    }

    public PersistentException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public PersistentException(final Throwable cause) {
        super(cause);
    }

    public PersistentException(final Exception e) {
        super(e);
    }
}
