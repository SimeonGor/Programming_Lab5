package Lab5;

import Lab5.collection.CollectionManager;
import Lab5.commands.ICommand;
import Lab5.exceptions.UnknownCommandException;

import java.util.Collection;
import java.util.HashMap;

/**
 * Class to server
 */
public class Server {
    private final HashMap<String, ICommand> commands = new HashMap<>();
    private final CollectionManager collectionManager;

    public Server(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * add new Command
     * @param command
     * @see Lab5.commands.Command
     */
    public void setCommand(ICommand command) {
        this.commands.put(command.getName(), command);
    }

    /**
     * Get list of commands
     * @return
     */
    public Collection<ICommand> getCommands() {
        return commands.values();
    }

    /**
     * process the request
     * @param request
     * @see Request
     * @see Client
     */
    public void executeRequest(Request request) {
        try {
            executeCommand(request.getCommandName(), request.getParameters(), request.getClient());
        } catch (UnknownCommandException e) {
            request.getClient().receiveResponse(new Response(false,
                    e.getMessage() + "\nPrint \"help\" to see available commands"));
        }
    }

    /**
     * Execute command by name
     * @param commandName
     * @param parameters
     * @param client
     * @throws UnknownCommandException
     */
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
