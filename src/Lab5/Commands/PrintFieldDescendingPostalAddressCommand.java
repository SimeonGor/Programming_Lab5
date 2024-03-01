package Lab5.Commands;

import Lab5.Client;
import Lab5.Collection.Address;
import Lab5.Collection.CollectionManager;
import Lab5.Response;
import Lab5.Server;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class PrintFieldDescendingPostalAddressCommand extends Command {
    public PrintFieldDescendingPostalAddressCommand(Server server) {
        super(server, "print_descending_postal_address", "",
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
            addresses.add(e.getPostalAddress());
        }
        addresses.sort((o1, o2) -> (-1) * o1.getZipCode().compareTo(o2.getZipCode()));

        StringBuilder result = new StringBuilder();
        for (var e : addresses) {
            result.append(e).append("\n");
        }

        client.receiveResponse(new Response(true, result.substring(0, result.length() - 1)));
    }
}
