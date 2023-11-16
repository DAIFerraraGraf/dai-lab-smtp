package SMTPClient;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class SMTPProtocol {
    private final int SERVER_PORT = 1025;
    private final String SERVER_ADDRESS = "0.0.0.0";

    public void sendEmail(List<Group> groups, List<Message> messages) {

        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             var in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
             var out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8))) {

            init(out, in);

            String[] commands = sendHello(out, in);
            for (String command : commands) {
                System.out.println(command);
            }

            sendFromToSMTP(out, in, "imposteur@gmail.com", "juju@gmail.com");
            sendContent(out, in, "shakira@gmail.com", "juju@gmail.com", "April 1st, 2021", "shasha", "hello im shakira");

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

    private void sendFromToSMTP(BufferedWriter out, BufferedReader in, String from, String to) throws IOException {
        String line;
        out.write("mail from:<" + from + ">\r\n");
        out.flush();
        if (!in.readLine().contains("250")) {
//            throw "error";
            System.out.println("error in mailfrom");
            return;
        }

        out.write("rcpt to:<" + to + ">\r\n");
        out.flush();
        if (!in.readLine().contains("250")) {
//            throw "error";
            System.out.println("error in mailto");
            return;
        }
    }

    private void sendContent(BufferedWriter out, BufferedReader in, String from, String to, String date, String subject,
                             String content) throws IOException {
        out.write("data\r\n");
        out.flush();
        if (!in.readLine().contains("354")) {
//            throw "error";
            System.out.println("error in data");
            return;
        }
        out.write("From: <" + from + ">\r\n");
        out.write("To: <" + to + ">\r\n");
        out.write("Date: " + date + "\r\n");
        out.write("Subject: " + subject + "\r\n");
        out.write("\r\n" + content + "\r\n");
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

