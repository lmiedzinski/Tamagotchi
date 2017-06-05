package pl.dbjllmjk.Model;

/**
 * Class representing {@link Pet} Operation. Extends {@link Action} class.
 */
public class Operation extends Action {

    /**
     * Constructor with parameters.
     *
     * @param name of operation
     * @param value of operation
     * @see Action#Action(String, int)
     */
    public Operation(String name, int value) {
        super(name, value);
    }

    public Operation() {
        super("", 0);
    }

}
