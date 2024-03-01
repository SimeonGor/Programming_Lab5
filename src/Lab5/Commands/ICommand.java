package Lab5.Commands;

import Lab5.Client;

/**
 * Interface for command pattern.
 */
public interface ICommand {
    String getName();
    String getParameters();
    String getDescription();
    void execute(String parameters, Client client);
}
