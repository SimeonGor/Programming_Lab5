package Lab5.Commands;

import Lab5.Client;
import Lab5.Collection.CollectionManager;
import Lab5.Collection.Organization;
import Lab5.Response;
import Lab5.Server;

import java.util.Comparator;

public class MinByPostalAddressCommand extends Command {
    public MinByPostalAddressCommand(Server server) {
        super(server, "min_by_postal_address", "",
                "output any object from the collection whose postal Address field value is minimal");
    }

    @Override
    public void execute(String params, Client client) {
        CollectionManager collectionManager = server.getCollectionManager();

        if (collectionManager.isEmpty()) {
            client.receiveResponse(new Response(false, "The collection is empty"));
            return;
        }
        client.receiveResponse(new Response(true, collectionManager.minIf(new Comparator<Organization>() {
            @Override
            public int compare(Organization o1, Organization o2) {
                if (o1.getPostalAddress().getZipCode() == null && o2.getPostalAddress().getZipCode() == null) return 0;
                if (o2.getPostalAddress().getZipCode() == null) return -1;
                if (o1.getPostalAddress().getZipCode() == null) return 1;
                return o1.getPostalAddress().getZipCode().compareTo(
                        o2.getPostalAddress().getZipCode());
            }
        }).toString()));
    }
}
