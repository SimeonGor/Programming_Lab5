package Lab5.commands;

import Lab5.Client;
import Lab5.collection.CollectionManager;
import Lab5.collection.MyCollection;
import Lab5.collection.Organization;
import Lab5.collection.OrganizationType;
import Lab5.exceptions.InvalidArgument;
import Lab5.Response;
import Lab5.Server;

import java.util.function.Predicate;

/**
 * Command to output elements whose type field value is greater than the specified one
 */
public class FilterGreaterThanTypeCommand extends Command {
    public FilterGreaterThanTypeCommand(Server server) {
        super(server, "filter_greater_than_type", "type",
                "output elements whose type field value is greater than the specified one");
    }

    @Override
    public void execute(String params, Client client) {
        OrganizationType type = null;
        boolean fl = true;
        while (fl) {
            String typeName = client.getParameters(OrganizationType.listOfElementsPrettyView());
            try {
                type = OrganizationType.getByName(typeName);
                fl = false;
            } catch (InvalidArgument e) {
                client.receiveResponse(new Response(false, e.getMessage()));
            }
        }

        CollectionManager collectionManager = server.getCollectionManager();
        if (collectionManager.isEmpty()) {
            client.receiveResponse(new Response(false, "The collection is empty"));
            return;
        }
        OrganizationType finalType = type;
        MyCollection collection = collectionManager.greaterThan(new Predicate<Organization>() {
            @Override
            public boolean test(Organization organization) {
                return organization.getType().compareTo(finalType) > 0;
            }
        });

        if (collection.isEmpty()) {
            client.receiveResponse(new Response(false,
                    "There are no elements whose type field value is greater than the specified one"));
            return;
        }

        client.receiveResponse(new Response(true, collection.toString()));

    }
}
