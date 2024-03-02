package Lab5.commands;

import Lab5.Client;
import Lab5.Response;
import Lab5.Server;

/**
 * Command to clearing the collection
 */
public class ClearCommand extends Command {
    public ClearCommand(Server server) {
        super(server, "clear", "", "clear the collection");
    }

    @Override
    public void execute(String params, Client client) {
        server.getCollectionManager().clear();
        client.receiveResponse(new Response(true, "The collection is empty"));
    }
}
