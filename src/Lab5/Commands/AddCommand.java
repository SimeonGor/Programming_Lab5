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

        Organization newEl = new Organization();
        while (true) {
            try {
                String name = client.getParameters("name");
                System.out.println(name);
                newEl.setName(name);
                break;
            } catch (InvalidArgument e) {
                client.receiveResponse(new Response(false, e.getMessage()));
            }
        }

        Coordinates coordinates = new Coordinates();
        while (true) {
            try {
                int x = Integer.parseInt(client.getParameters("coordinates.x"));
                coordinates.setX(x);
                break;
            } catch (InvalidArgument e) {
                client.receiveResponse(new Response(false, e.getMessage()));
            } catch (NumberFormatException e) {
                client.receiveResponse(new Response(false, "it must be an integer"));
            }
        }
        while (true) {
            try {
                long y = Long.parseLong(client.getParameters("coordinates.y"));
                coordinates.setY(y);
                break;
            } catch (NumberFormatException e) {
                client.receiveResponse(new Response(false, "it must be an integer"));
            }
        }

        newEl.setCoordinates(coordinates);

        while (true) {
            try {
                double t = Double.parseDouble(client.getParameters("annual turnover"));
                newEl.setAnnualTurnover(t);
                break;
            } catch (InvalidArgument e) {
                client.receiveResponse(new Response(false, e.getMessage()));
            } catch (NumberFormatException e) {
                client.receiveResponse(new Response(false, "it must be an integer"));
            }
        }

        while (true) {
            try {
                OrganizationType t = OrganizationType.getByName(
                        client.getParameters("type" + OrganizationType.listOfElementsPrettyView()));
                newEl.setType(t);
                break;
            } catch (InvalidArgument e) {
                client.receiveResponse(new Response(false, e.getMessage()));
            }
        }

        Address address = new Address();
        while (true) {
            try {
                String t = client.getParameters("zip code");
                address.setZipCode(t);
                break;
            } catch (InvalidArgument e) {
                client.receiveResponse(new Response(false, e.getMessage()));
            }
        }

        newEl.setPostalAddress(address);

        newEl.setId(collectionManager.getCollection().genId());
        newEl.setCreationDate(LocalDate.now());

        collectionManager.add(newEl);

        client.receiveResponse(new Response(true, "The element has been successful added to the collection"));
    }
}
