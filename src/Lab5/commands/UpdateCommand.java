package Lab5.commands;

import Lab5.Client;
import Lab5.collection.CollectionManager;
import Lab5.collection.Organization;
import Lab5.exceptions.InvalidArgument;
import Lab5.Response;
import Lab5.Server;

/**
 * Command to update the value of a collection item whose id is equal to the specified one
 */
public class UpdateCommand extends Command {
    public UpdateCommand(Server server) {
        super(server, "update", "id {element}",
                "update the value of a collection item whose id is equal to the specified one");
    }

    @Override
    public void execute(String parameters, Client client) {
        CollectionManager collectionManager = server.getCollectionManager();

        try {
            long id = Long.parseLong(parameters);
            Organization newElement = NewElement.getNewElement(client);
            newElement.setId(id);

            collectionManager.replace(id, newElement);

            client.receiveResponse(new Response(true, String.format("The item with id %s has been successful updated", id)));
        } catch (NumberFormatException e) {
            client.receiveResponse(new Response(false, "The id must be an integer"));
        } catch (InvalidArgument e) {
            client.receiveResponse(new Response(false, e.getMessage()));
        }

    }
}
