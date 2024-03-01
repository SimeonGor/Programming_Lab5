package Lab5.Commands;

import Lab5.Client;
import Lab5.Response;
import Lab5.Server;

public class RemoveByIDCommand extends Command {
    public RemoveByIDCommand(Server server) {
        super(server, "remove_by_id", "id", "delete an item from the collection by its id");
    }

    public int getParameters(String param) throws NumberFormatException{
        return Integer.parseInt(param);
    }

    @Override
    public void execute(String parameters, Client client) {
        try {
            long id = getParameters(parameters);
            server.getCollectionManager().removeByID(id);
            client.receiveResponse(new Response(true, String.format("The item with id %d has been successfully deleted", id)));
        }
        catch (NumberFormatException e) {
            client.receiveResponse(new Response(false, "The id must be an integer"));
        }
        catch (NullPointerException e) {
            client.receiveResponse(new Response(false, "The collection is empty"));
        }
    }
}
