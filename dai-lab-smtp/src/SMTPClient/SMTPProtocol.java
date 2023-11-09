package SMTPClient;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class SMTPProtocol {
    private final int SERVER_PORT = 1025;
    private final String SERVER_ADDRESS = "0.0.0.0";

    public void send() {

        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             var in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
             var out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8))) {

            init(out, in);

            String[] commands = sendHello(out, in);
            for(String command : commands){
                System.out.println(command);
            }

            quit(out, in);
        } catch(IOException e){
            System.out.println("Client: exc.: " + e);
        }
    }

    private void init(BufferedWriter out, BufferedReader in) throws IOException {
        if(in.readLine().contains("220"))
            System.out.println("Client: connect successful");
    }

    private String[] sendHello(BufferedWriter out, BufferedReader in) throws IOException {
        out.write("ehlo " + SERVER_ADDRESS + "\r\n");
        out.flush();

        String line;
        String commands = "";
        do {
            line = in.readLine();
            commands += line + " ";
        } while (line.charAt(3) != ' ');
        return commands.split("250");
    }

    private void quit(BufferedWriter out, BufferedReader in) throws IOException {
        out.write("quit \r\n");
        out.flush();
        if(in.readLine().contains("221"))
            System.out.println("Client: quit successful");
    }


}

