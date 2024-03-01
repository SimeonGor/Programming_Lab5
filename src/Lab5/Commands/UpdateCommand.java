package Lab5.Commands;

import Lab5.Client;
import Lab5.Collection.CollectionManager;
import Lab5.Collection.Organization;
import Lab5.Exceptions.InvalidArgument;
import Lab5.Response;
import Lab5.Server;

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
        } catch (NumberFormatException e) {
            client.receiveResponse(new Response(false, "The id must be an integer"));
        } catch (InvalidArgument e) {
            client.receiveResponse(new Response(false, e.getMessage()));
        }

    }
}