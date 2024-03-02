package Lab5.commands;

import Lab5.Client;
import Lab5.Server;

/**
 * Class to exit the program (without saving to a file)
 */
public class ExitCommand extends Command {
    public ExitCommand(Server server) {
        super(server, "exit", "", "exit the program (without saving to a file)");
    }

    @Override
    public void execute(String parameters, Client client) {
        System.exit(0);
    }
}
