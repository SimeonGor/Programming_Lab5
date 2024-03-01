package Lab5;

import Lab5.Collection.CollectionManager;
import Lab5.Collection.MyCollection;
import Lab5.Commands.ICommand;
import Lab5.Exceptions.UnknownCommandException;
import Lab5.utils.CommandParser;

import java.util.Collection;
import java.util.HashMap;

public class Server {
    private final HashMap<String, ICommand> commands = new HashMap<>();
    private final CollectionManager collectionManager;

    public Server(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public void setCommand(ICommand command) {
        this.commands.put(command.getName(), command);
    }

    public Collection<ICommand> getCommands() {
        return commands.values();
    }

    public void executeRequest(Request request) {
        try {
            executeCommand(request.getCommandName(), request.getParameters(), request.getClient());
        } catch (UnknownCommandException e) {
            request.getClient().receiveResponse(new Response(false,
                    e.getMessage() + "\nPrint \"help\" to see available commands"));
        }
    }

    private void executeCommand(String commandName, String parameters, Client client) throws UnknownCommandException {
        if (commands.containsKey(commandName)) {
            commands.get(commandName).execute(parameters, client);
        }
        else {
            throw new UnknownCommandException(commandName);
        }
    }

    public CollectionManager getCollectionManager() {
        return collectionManager;
    }
}
