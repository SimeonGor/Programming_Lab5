package Lab5;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Class for client that which interacts with the user
 * @see Server
 */
public class Client{
    private final Server server;
    protected Scanner scanner;

    public Client(Server server) {
        this.server = server;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Start main thread
     */
    public void main_thread() {
        printResult("Print \"help\" to see available commands");
        while (true) {
            action(enterCommand());
        }
    }

    protected String enterCommand() {
        System.out.print("\u001B[34m>>> \u001B[0m");
        try {
            return scanner.nextLine();
        } catch (NoSuchElementException e) {
            printException("the program was interrupted");
            System.exit(0);
        }
        return null;
    }

    protected void action(String params) {
        server.executeRequest(new Request(this, server, params));
    }

    /**
     * the method of processing the server response
     * @param response
     */
    public void receiveResponse(Response response) {
        if (response.getStatus()) {
            printResult(response.getMessage());
        }
        else {
            printException(response.getMessage());
        }
    }

    /**
     * method to get item parameters
     * @param parameters_name name of parameters
     * @return parameters in string form
     */
    public String getParameters(String parameters_name) {
        System.out.printf("\u001B[34mEnter %s: \u001B[0m", parameters_name);
        try {
            return scanner.nextLine();
        } catch (NoSuchElementException e) {
            printException("the program was interrupted");
            System.exit(0);
        }
        return null;
    }

    protected void printException(String message) {
        System.out.println(message);
    }

    protected void printResult(String result) {
        System.out.println(result);
    }
}
