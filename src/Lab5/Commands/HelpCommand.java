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
            t.append("\t\u001B[1m").append(i.getName()).append("\u001B[0m ")
                    .append("\u001B[4m").append(i.getParameters()).append("\u001B[0m ").append("\t")
                    .append(i.getDescription()).append("\n");
        }

        client.receiveResponse(new Response(true, t.substring(0, t.length() - 1)));
    }
}
