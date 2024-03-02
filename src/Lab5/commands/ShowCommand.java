package Lab5.commands;

import Lab5.Client;
import Lab5.collection.CollectionManager;
import Lab5.Response;
import Lab5.Server;

/**
 * Command to print all the elements of the collection in a string representation to the standard output stream
 */
public class ShowCommand extends Command {
    public ShowCommand(Server server) {
        super(server, "show", "",
                "print all the elements of the collection in a string representation to the standard output stream");
    }

    @Override
    public void execute(String parameters, Client client) {
        CollectionManager collectionManager = server.getCollectionManager();
        if (collectionManager == null || collectionManager.isEmpty()) {
            client.receiveResponse(new Response(true, "The collection is empty"));
            return;
        }
        client.receiveResponse(new Response(true, collectionManager.getCollectionAsString()));
    }
}
