package Lab5.commands;

import Lab5.Client;
import Lab5.Response;
import Lab5.Server;

import java.io.*;
import java.util.Scanner;
import java.util.TreeSet;


/**
 * Command to execute the script from the specified file
 */
public class ExecuteScriptCommand extends Command {

    static private TreeSet<String> file_names = new TreeSet<>();
    public ExecuteScriptCommand(Server server) {
        super(server, "execute_script", "file_name", "execute the script from the specified file");
    }

    @Override
    public void execute(String params, Client client) {
        class SurrogateClient extends Client {
            public SurrogateClient(Server server, InputStream input) {
                super(server);
                this.scanner = new Scanner(input);
            }
            @Override
            public void receiveResponse(Response response) {
                client.receiveResponse(response);
            }
            @Override
            public void main_thread() {
                while (scanner.hasNext()) {
                    action(enterCommand());
                }
                scanner.close();
            }

            @Override
            protected String enterCommand() {
                return scanner.nextLine();
            }


            @Override
            public String getParameters(String parameters_name) {
                return scanner.nextLine();
            }
        }

        String path = params;
        if (path.isBlank()) {
            client.receiveResponse(new Response(false, "print filename"));
            return;
        }

        File file = new File(path);
        if (!file.exists()) {
            client.receiveResponse(new Response(false, String.format("%s : no such file", path)));
            return;
        }
        if (!file.canRead()) {
            client.receiveResponse(new Response(false, String.format("%s : permission to read denied", path)));
            return;
        }

        try {
            Client surrogate = new SurrogateClient(server, new FileInputStream(file));
            if (!file_names.contains(path)) {
                file_names.add(path);
                surrogate.main_thread();
                file_names.remove(path);
            }
            else {
                client.receiveResponse(new Response(false, "recursive call"));
            }
        } catch (FileNotFoundException e) {
            client.receiveResponse(new Response(false, String.format("%s : no such file", path)));
        }

    }
}
