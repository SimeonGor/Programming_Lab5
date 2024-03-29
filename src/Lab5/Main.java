package Lab5;

import Lab5.collection.CollectionManager;
import Lab5.collection.JsonCollectionLoader;
import Lab5.collection.MyCollection;
import Lab5.commands.*;

import java.io.IOException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        try {
            CollectionManager collectionManager = new CollectionManager(args[0], new JsonCollectionLoader<MyCollection>());
            Server server = new Server(collectionManager);
            server.setCommand(new HelpCommand(server));
            server.setCommand(new ExitCommand(server));
            server.setCommand(new ShowCommand(server));
            server.setCommand(new SaveCommand(server));
            server.setCommand(new ClearCommand(server));
            server.setCommand(new RemoveByIDCommand(server));
            server.setCommand(new RemoveAtCommand(server));
            server.setCommand(new InfoCommand(server));
            server.setCommand(new ReorderCommand(server));
            server.setCommand(new MinByPostalAddressCommand(server));
            server.setCommand(new FilterGreaterThanTypeCommand(server));
            server.setCommand(new PrintFieldDescendingPostalAddressCommand(server));
            server.setCommand(new AddCommand(server));
            server.setCommand(new UpdateCommand(server));
            server.setCommand(new AddIfMaxCommand(server));
            server.setCommand(new ExecuteScriptCommand(server));

            Client client = new Client(server);
            client.main_thread();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("No arguments");
        }
        finally {
            ;
        }
    }
}