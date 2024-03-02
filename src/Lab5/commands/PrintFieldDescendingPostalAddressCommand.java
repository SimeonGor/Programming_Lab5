package Lab5.commands;

import Lab5.Client;
import Lab5.collection.Address;
import Lab5.collection.CollectionManager;
import Lab5.Response;
import Lab5.Server;

import java.util.ArrayList;

/**
 * Command to print the values of the PostalAddress field of all elements in descending order
 */
public class PrintFieldDescendingPostalAddressCommand extends Command {
    public PrintFieldDescendingPostalAddressCommand(Server server) {
        super(server, "print_field_descending_postal_address", "",
                "print the values of the PostalAddress field of all elements in descending order");
    }

    @Override
    public void execute(String params, Client  client) {
        CollectionManager collectionManager = server.getCollectionManager();
        if (collectionManager.isEmpty()) {
            client.receiveResponse(new Response(false, "The collection is empty"));
            return;
        }

        ArrayList<Address> addresses = new ArrayList<>();
        for (var e : collectionManager.getCollection().getCollection()) {
            if (e.getPostalAddress().getZipCode() != null) {
                addresses.add(e.getPostalAddress());
            }
        }
        addresses.sort((o1, o2) -> (-1) * o1.getZipCode().compareTo(o2.getZipCode()));

        if (addresses.isEmpty()) {
            client.receiveResponse(new Response(false, "There ar no postal addresses"));
            return;
        }

        StringBuilder result = new StringBuilder();
        for (var e : addresses) {
            result.append(e).append("\n");
        }

        client.receiveResponse(new Response(true, result.substring(0, result.length() - 1)));
    }
}
