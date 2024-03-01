package Lab5.Commands;

import Lab5.Client;
import Lab5.Response;
import Lab5.Server;

public class HelpCommand extends Command {
    public HelpCommand(Server server) {
        super(server, "help", "", "print this help message to the output stream");
    }

    @Override
    public void execute(String params, Client client) {
        StringBuilder t = new StringBuilder();
        for (var i : server.getCommands()) {
            t.append(String.format("\t\u001B[1m%-40s\u001B[0m \u001B[3m%-10s\u001B[0m %s\n",
                    i.getName(), i.getParameters(), i.getDescription()));
        }

        client.receiveResponse(new Response(true, t.substring(0, t.length() - 1)));
    }
}
