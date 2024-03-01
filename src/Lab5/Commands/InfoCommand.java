package Lab5.Commands;

import Lab5.Client;
import Lab5.Collection.CollectionManager;
import Lab5.Response;
import Lab5.Server;

public class InfoCommand extends Command {
    public InfoCommand(Server server) {
        super(server, "info", "", "print information about the collection");
    }

    @Override
    public void execute(String string, Client client) {
        CollectionManager collectionManager = server.getCollectionManager();

        StringBuilder result = new StringBuilder();

        if (collectionManager.isEmpty()) {
            result.append("Collection is empty");
        }
        else {
            result.append("Type: ").append(collectionManager.getTypeOfElement())
                    .append("\nCreation date: ").append(collectionManager.getCollection().getCreationDate())
                    .append("\nModified date: ").append(collectionManager.getCollection().getModifiedDate())
                    .append("\nSize: ").append(collectionManager.getCollection().size());
        }

        client.receiveResponse(new Response(true, result.toString()));
    }
}
