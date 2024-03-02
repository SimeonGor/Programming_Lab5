package Lab5.commands;

import Lab5.Client;
import Lab5.Response;
import Lab5.Server;

import java.io.IOException;

/**
 * Command to save the collection to a file
 */
public class SaveCommand extends Command {
    public SaveCommand(Server server) {
        super(server, "save", "", "save the collection to a file");
    }

    @Override
    public void execute(String parameters, Client client) {
        try {
            server.getCollectionManager().saveCollection();
            client.receiveResponse(new Response(true, "The collection has been successfully saved"));
        }
        catch (IOException e) {
            client.receiveResponse(new Response(false, e.getMessage()));
        }
    }
}
