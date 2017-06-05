package pl.dbjllmjk.Model;

/**
 * {@link Exception} thrown when performing operation on {@link Pet} fails.
 */
public class PetTransactionException extends Exception {

    /**
     * {@value}
     */
    private static final long serialVersionUID = 1L;

    /**
     * {@link Exception#Exception()}
     */
    public PetTransactionException() {
        super();
    }

    /**
     * {@link Exception#Exception(String)}
     */
    public PetTransactionException(String arg0) {
        super(arg0);
    }
}
