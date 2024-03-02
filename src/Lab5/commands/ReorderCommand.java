package Lab5.commands;

import Lab5.Client;
import Lab5.collection.CollectionManager;
import Lab5.Response;
import Lab5.Server;

/**
 * Command to reorder the collection
 */
public class ReorderCommand extends Command {
    public ReorderCommand(Server server) {
        super(server, "reorder", "", "reorder the collection");
    }

    @Override
    public void execute(String params, Client client) {
        CollectionManager collectionManager = server.getCollectionManager();

        if (collectionManager.isEmpty()) {
            client.receiveResponse(new Response(false, "The collection is empty"));
        } else {
            collectionManager.getCollection().reverse();
            client.receiveResponse(new Response(true, "The collection has been reordered"));
        }
    }
}
