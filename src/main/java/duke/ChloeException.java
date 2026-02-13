package duke;
/**
 * Represents a custom exception used in the Chloe application.
 */
public class ChloeException extends Exception {

    /**
     * Constructs a ChloeException with the specified detail message.
     *
     * @param message The detail message.
     */
    public ChloeException(String message) {
        super(message);
    }
}
