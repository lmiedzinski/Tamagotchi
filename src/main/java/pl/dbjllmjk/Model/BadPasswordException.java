package pl.dbjllmjk.Model;

/**
 * {@link Exception} thrown in case of incorrect password given while logging.
 */
public class BadPasswordException extends Exception {

    /**
     * {@value}
     */
    private static final long serialVersionUID = 1L;

    /**
     * {@link Exception#Exception()}
     */
    public BadPasswordException() {
        super();
    }

    /**
     * {@link Exception#Exception(String)}
     */
    public BadPasswordException(String message) {
        super(message);
    }
}
