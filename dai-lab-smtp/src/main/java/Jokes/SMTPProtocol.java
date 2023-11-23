package Jokes;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Random;


public class SMTPProtocol {
    private final int SERVER_PORT = 1025;
    private final String SERVER_ADDRESS = "0.0.0.0";

    public void sendEmail(List<Group> groups, List<List<String>> messages) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             var in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
             var out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8))) {

            init(in);
            for (Group group : groups) {
                System.out.println("---------------------------------------------");
                System.out.println("Group " + group.getId());
                System.out.println("Sender  : " + group.getSender());
                List<String> message = getRandomMessage(messages);
                for(String reciever : group.getRecievers()){
                    String[] commands = sendHello(out, in);
                    sendCommand(out, in, mailFrom(group.getSender()));
                    sendCommand(out, in, rcptTo(reciever));
                    sendCommand(out, in, "data");
                    sendCommand(out, in, content("Shakira@gmail.com", reciever, new Date(2020,12,2), message.get(0), message.get(1)));
                    System.out.println("Mail sent to : " + reciever);

                }

            }
           quit(out, in);

        } catch (IOException e) {
            throw new RuntimeException("Error while sending email" + " : ", e);
        }
    }

    private void init(BufferedReader in) throws IOException {
        if (!in.readLine().contains("220")) {
            throw new RuntimeException("Error while initializing connection");
        }
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

    private void sendCommand(BufferedWriter out, BufferedReader in, String command) {
        try {
            out.write(command + "\r\n");
            out.flush();
            String response = in.readLine();
            if (!response.contains("250") && !response.contains("220") && !response.contains("354") && !response.contains("221")) {
                throw new RuntimeException("Error in command: " + command);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error while sending command: " + command, e);
        }
    }

    private void quit(BufferedWriter out, BufferedReader in) {
        try {
            out.write("quit\r\n");
            out.flush();
            String response = in.readLine();
            if (!response.contains("221")) {
                throw new RuntimeException("Error in command: quit");
            }
        } catch (IOException e) {
            throw new RuntimeException("Error while sending command: quit", e);
        }
    }

    private String mailFrom(String sender) {
        return "mail from:<" + sender + ">";
    }

    private String rcptTo(String receiver) {
        return "rcpt to:<" + receiver + ">";
    }

    private String content(String from, String to, Date date, String subject, String body) {
        String encodedSubject = "=?UTF-8?B?" + Base64.getEncoder().encodeToString(subject.getBytes(StandardCharsets.UTF_8)) + "?=";
        return "From: <" + from + ">\r\nTo: <" + to + ">\r\nDate: " + date + "\r\nSubject: " + encodedSubject + "\r\nContent-Type: text/plain; charset=UTF-8\r\n\r\n" + body + "\r\n.";
    }
    /**
     * Returns a random message from the list of messages.
     * @return A random message.
     */
    private List<String> getRandomMessage(List<List<String>> messages) {
        Random random = new Random();
        int index = random.nextInt(messages.size());
        return messages.get(index);
    }
}

