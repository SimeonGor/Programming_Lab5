package Lab5.Exceptions;

public class UnknownCommandException extends Exception {
    private final String commandName;

    public UnknownCommandException(String commandName) {
        super("command not found: " + commandName);
        this.commandName = commandName;
    }

    public String getCommandName() {
        return commandName;
    }
}
