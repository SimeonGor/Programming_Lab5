package Lab5.Commands;

import Lab5.Client;
import Lab5.Collection.CollectionManager;
import Lab5.Response;
import Lab5.Server;

import java.util.Collections;

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
