package Lab5.Commands;

import Lab5.Server;

public abstract class Command implements ICommand {
    protected String name, parameters, description;
    protected final Server server;

    public Command(Server server, String name, String parameters, String description) {
        this.server = server;
        this.name = name;
        this.parameters = parameters;
        this.description = description;
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getParameters() {
        return parameters;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
