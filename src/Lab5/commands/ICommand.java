package Lab5.commands;

import Lab5.Client;

/**
 * Interface for command pattern.
 */
public interface ICommand {
    String getName();
    String getParameters();
    String getDescription();

    /**
     * Execute the command
     * @param parameters
     * @param client
     */
    void execute(String parameters, Client client);
}
