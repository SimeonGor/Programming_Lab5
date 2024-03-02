package Lab5.Commands;

import Lab5.Client;
import Lab5.Request;
import Lab5.Response;
import Lab5.Server;

import java.io.*;
import java.util.Scanner;

public class ExecuteScriptCommand extends Command {
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
        File file = new File(path);
        if (!file.exists()) {
            client.receiveResponse(new Response(false, String.format("%s : no such file", path)));
        }
        if (!file.canRead()) {
            client.receiveResponse(new Response(false, String.format("%s : permission to read denied", path)));
        }

        try {
            Client surrogate = new SurrogateClient(server, new FileInputStream(file));
            surrogate.main_thread();
        } catch (FileNotFoundException e) {
            client.receiveResponse(new Response(false, String.format("%s : no such file", path)));
        }
    }
}
