package SMTPClient;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class SMTPProtocol {
    private final int SERVER_PORT = 1025;
    private final String SERVER_ADDRESS = "0.0.0.0";

    public void sendEmail(Group group, Message message) {

        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             var in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
             var out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8))) {


            init(out, in);

            String[] commands = sendHello(out, in);
            for (String command : commands) {
                System.out.println(command);
            }

            sendFromToSMTP(out, in, group);
            sendContent(out, in, message);

            quit(out, in);

        } catch (IOException e) {
            System.out.println("Client: exc.: " + e);
        }
    }

    private void init(BufferedWriter out, BufferedReader in) throws IOException {
        if (in.readLine().contains("220"))
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

    private void sendFromToSMTP(BufferedWriter out, BufferedReader in, Group group) throws IOException {
        String line;
        out.write("mail from:<" + group.getSender() + ">\r\n");
        out.flush();
        if (!in.readLine().contains("250")) {
//            throw "error";
            System.out.println("error in mailfrom");
            return;
        }

        for (String to : group.getRecievers()) {
            out.write("rcpt to:<" + to + ">\r\n");
        }
        out.flush();
        if (!in.readLine().contains("250")) {
//            throw "error";
            System.out.println("error in mailto");
            return;
        }
    }

    private void sendContent(BufferedWriter out, BufferedReader in, Message message) throws IOException {
        out.write("data\r\n");
        out.flush();
        if (!in.readLine().contains("354")) {
//            throw "error";
            System.out.println("error in data");
            return;
        }
        out.write("From: <" + message.getFrom() + ">\r\n");
        out.write("To: <" + message.getTo() + ">\r\n");
        out.write("Date: " + message.getDate() + "\r\n");
        out.write("Subject: " + message.getSubject() + "\r\n");
        out.write("\r\n" + message.getBody() + "\r\n");
        out.write("\r\n.\r\n");
        out.flush();
//        if (!in.readLine().contains("250")) {
////            throw "error";
//            System.out.println("error in sending content");
//            return;
//        }
    }

    private void quit(BufferedWriter out, BufferedReader in) throws IOException {
        out.write("quit \r\n");
        out.flush();
        if (in.readLine().contains("221"))
            System.out.println("Client: quit successful");
    }


}

