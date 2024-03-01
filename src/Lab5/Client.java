package Lab5;

import Lab5.Exceptions.UnknownCommandException;

import java.util.Scanner;

public class Client{
    private final Server server;
    private final Scanner scanner;

    public Client(Server server) {
        this.server = server;
        this.scanner = new Scanner(System.in);
    }

    public void main_thread() {
        while (true) {
            action(enterCommand());
        }
    }

    private String enterCommand() {
        System.out.print("\u001B[34m>>> \u001B[0m");
        return scanner.nextLine();
    }

    private void action(String params) {
        server.executeRequest(new Request(this, server, params));
    }

    public void receiveResponse(Response response) {
        if (response.getStatus()) {
            printResult(response.getMessage());
        }
        else {
            printException(response.getMessage());
        }
    }

    public String getParameters(String parameters_name) {
        System.out.printf("\u001B[34mEnter %s: \u001B[0m", parameters_name);
        return scanner.nextLine();
    }

    public void printException(String message) {
        System.out.println(message);
    }

    public void printResult(String result) {
        System.out.println(result);
    }
}
