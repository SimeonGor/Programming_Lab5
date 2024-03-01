package Lab5.Commands;

import Lab5.Client;
import Lab5.Collection.CollectionManager;
import Lab5.Collection.Organization;
import Lab5.Response;
import Lab5.Server;

import java.time.LocalDate;
import java.util.Comparator;

public class AddIfMaxCommand extends Command {
    public AddIfMaxCommand(Server server) {
        super(server, "add_if_max", "{element}",
                "add a new item to the collection if its value exceeds the value of the largest item in this collection");
    }

    @Override
    public void execute(String params, Client client) {
        Organization element = NewElement.getNewElement(client);
        CollectionManager collectionManager = server.getCollectionManager();
        element.setId(collectionManager.getCollection().genId());
        Comparator<Organization> cmp = collectionManager.getComparator();

        if (collectionManager.isEmpty() || cmp.compare(collectionManager.maxIf(cmp), element) < 0) {
            collectionManager.add(element);

            client.receiveResponse(new Response(true, "New item has been successful added to the collection"));
        }
        else {
            client.receiveResponse(new Response(true, "New item is less than the maximum in the collection"));
        }

    }
}
