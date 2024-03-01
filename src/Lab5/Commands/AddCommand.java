package Lab5.Commands;

import Lab5.Client;
import Lab5.Collection.*;
import Lab5.Exceptions.InvalidArgument;
import Lab5.Response;
import Lab5.Server;

import java.time.LocalDate;

public class AddCommand extends Command {
    public AddCommand(Server server) {
        super(server, "add", "{element}", "add new element to the collection");
    }

    @Override
    public void execute(String params, Client client) {
        CollectionManager collectionManager = server.getCollectionManager();

        Organization newEl = NewElement.getNewElement(client);
        newEl.setId(collectionManager.getCollection().genId());
        collectionManager.add(newEl);

        client.receiveResponse(new Response(true, "The item has been successful added to the collection"));
    }
}
