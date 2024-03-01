package Lab5.Commands;

import Lab5.Client;
import Lab5.Collection.*;
import Lab5.Exceptions.InvalidArgument;
import Lab5.Response;

import java.time.LocalDate;

public class NewElement {
    public static Organization getNewElement(Client client) {
        Organization newEl = new Organization();
        while (true) {
            try {
                String name = client.getParameters("name");
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
                client.receiveResponse(new Response(false, "it must be an number"));
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
        newEl.setCreationDate(LocalDate.now());
        return newEl;
    }
}
