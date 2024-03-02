package Lab5.exceptions;


/**
 * Indicates that the method argument does not fit the range
 */
public class InvalidArgument extends Exception {
    public InvalidArgument(String message) {
        super(message);
    }
}
