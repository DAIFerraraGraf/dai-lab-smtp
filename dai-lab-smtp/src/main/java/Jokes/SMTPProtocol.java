package Jokes;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * This class represents the SMTP protocol used to send emails.
 */
public class SMTPProtocol {
    private final int SERVER_PORT = 1025;
    private final String SERVER_ADDRESS = "0.0.0.0";

    /**
     * Sends an email to a list of groups with a random message.
     * @param groups The list of groups to send the email to.
     * @param messages The list of messages to choose from.
     */
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
                String[] commands = sendHello(out, in);
                sendCommand(out, in, mailFrom(group.getSender()));
                for (String receiver : group.getReceivers()) {
                    sendCommand(out, in, rcptTo(receiver));
                    System.out.println("Mail send to: " + receiver);
                }
                sendCommand(out, in, "data");
                sendCommand(out, in, content("Shakira@gmail.com", group.getStringReceivers(), new Date(2020, 12, 2), message.get(0), message.get(1)));


            }
            quit(out, in);

        } catch (IOException e) {
            throw new RuntimeException("Error while sending email" + " : ", e);
        }
    }

    /**
     * Initializes the connection with the server.
     * @param in The input stream from the server.
     * @throws IOException If an I/O error occurs.
     */
    private void init(BufferedReader in) throws IOException {
        if (!in.readLine().contains("220")) {
            throw new RuntimeException("Error while initializing connection");
        }
    }

    /**
     * Sends a hello message to the server.
     * @param out The output stream to the server.
     * @param in The input stream from the server.
     * @return The server's response to the hello message.
     * @throws IOException If an I/O error occurs.
     */
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

    /**
     * Sends a command to the server.
     * @param out The output stream to the server.
     * @param in The input stream from the server.
     * @param command The command to send.
     */
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

    /**
     * Sends a quit command to the server.
     * @param out The output stream to the server.
     * @param in The input stream from the server.
     */
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

    /**
     * Creates a mail from command.
     * @param sender The sender of the email.
     * @return The mail from command.
     */
    private String mailFrom(String sender) {
        return "mail from:<" + sender + ">";
    }

    /**
     * Creates a rcpt to command.
     * @param receiver The receiver of the email.
     * @return The rcpt to command.
     */
    private String rcptTo(String receiver) {

        return "rcpt to:<" + receiver + ">";

    }

    /**
     * Creates the content of the email.
     * @param from The sender of the email.
     * @param to The receiver of the email.
     * @param date The date of the email.
     * @param subject The subject of the email.
     * @param body The body of the email.
     * @return The content of the email.
     */
    private String content(String from, String to, Date date, String subject, String body) {
        String encodedSubject = "=?UTF-8?B?" + Base64.getEncoder().encodeToString(subject.getBytes(StandardCharsets.UTF_8)) + "?=";
        return "From: <" + from + ">\r\nTo: <" + to + ">\r\nDate: " + date + "\r\nSubject: " + encodedSubject + "\r\nContent-Type: text/plain; charset=UTF-8\r\n\r\n" + body + "\r\n.";
    }

    /**
     * Returns a random message from the list of messages.
     * @param messages The list of messages to choose from.
     * @return A random message.
     */
    private List<String> getRandomMessage(List<List<String>> messages) {
        Random random = new Random();
        int index = random.nextInt(messages.size());
        return messages.get(index);
    }
}