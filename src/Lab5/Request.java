package Lab5;

import Lab5.utils.CommandParser;

/**
 * Class for client's request
 */
public class Request {
    private final Client client;
    private final Server server;
    private final String command, parameters;

    public Request(Client client, Server server, String request) {
        this.client = client;
        this.server = server;
        CommandParser commandParser = new CommandParser(request);
        this.command = commandParser.getCommandName();
        this.parameters = commandParser.getParameters();
    }

    public String getCommandName() {
        return command;
    }

    public String getParameters() {
        return parameters;
    }

    public Client getClient() {
        return client;
    }

    public Server getServer() {
        return server;
    }
}
