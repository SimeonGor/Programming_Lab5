package Lab5.commands;

import Lab5.Client;
import Lab5.collection.CollectionManager;
import Lab5.Response;
import Lab5.Server;


/**
 * Command to delete an item from the collection by index
 */
public class RemoveAtCommand extends Command {
    public RemoveAtCommand(Server server) {
        super(server, "remove_at", "index", "delete an item from the collection by index");
    }

    public int getParameters(String param) throws NumberFormatException{
        return Integer.parseInt(param);
    }

    @Override
    public void execute(String parameters, Client client) {
        CollectionManager collectionManager = server.getCollectionManager();
        if (collectionManager.isEmpty()) {
            client.receiveResponse(new Response(false, "The collection is empty"));
            return;
        }

        try {
            int index = getParameters(parameters);
            collectionManager.removeAt(index - 1);
            client.receiveResponse(new Response(true, String.format("The item by index %d has been successfully deleted", index)));
        }
        catch (NumberFormatException e) {
            client.receiveResponse(new Response(false, "The index must be an integer"));
        }
        catch (IndexOutOfBoundsException e) {
            client.receiveResponse(
                    new Response(false,
                            String.format("The index must be in the range from 1 to %s",
                                    server.getCollectionManager().getCollection().size())));
        }
        catch (NullPointerException e) {
            client.receiveResponse(new Response(false, "The collection is empty"));
        }
    }
}
