package hr.fer.zemris.app.dao;

/**
 * This exception will be thrown when there's an exception in the {@link DAO}.
 * To see what each constructor creates, see {@link RuntimeException} constructors.
 * 
 * @author Filip Hrenić
 * @version 1.0
 */
@SuppressWarnings("serial")
public class DAOException extends RuntimeException {

    public DAOException() {
        super();
    }

    /**
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public DAOException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    /**
     * @param message
     * @param cause
     */
    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     */
    public DAOException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public DAOException(Throwable cause) {
        super(cause);
    }
}
