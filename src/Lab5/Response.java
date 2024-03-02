package Lab5;


/**
 * Class to server's response
 */
public class Response {
    private final boolean status;
    private final String message;

    public Response(boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    public boolean getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
