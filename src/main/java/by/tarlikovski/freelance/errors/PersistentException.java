package by.tarlikovski.freelance.errors;

public class PersistentException extends Throwable {
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
