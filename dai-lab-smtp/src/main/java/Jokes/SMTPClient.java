package Jokes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * SMTPClient class is responsible for managing email groups and sending emails.
 */
public class SMTPClient {
    private FileManagement fileManagement = new FileManagement();
    private SMTPProtocol smtpProtocol = new SMTPProtocol();
    private List<String> emailAdresses;
    private List<List<String>> messages;
    private List<Group> groups = new ArrayList<>();

    /**
     * Reads email addresses from a file and forms groups.
     * @param path The path to the file containing email addresses.
     */
    public void readEmailAdressFiles(String path) {
        emailAdresses = fileManagement.readFileTXT(path, true);
        this.formGroups();
    }

    /**
     * Forms groups of email addresses.
     * Each group contains 5 email addresses.
     */
    private void formGroups() {
        List<String> emailGroup = new ArrayList<>();
        for (int i = 0; i < emailAdresses.size(); i++) {
            emailGroup.add(emailAdresses.get(i));
            if (i % 5 == 0 && i != 0) {
                Group group = new Group(emailGroup);
                groups.add(group);
                emailGroup.clear();
            }
        }
    }

    /**
     * Selects messages from a file.
     * @param path The path to the file containing messages.
     */
    public void readMessagesFiles(String path) {
        messages = fileManagement.readFileJSON(path);
    }

    /**
     * Sends emails to the groups.
     * Each group receives a random message.
     */
    public void sendEmails() {
        for (Group group : groups) {
            List<String> messageText = getRandomMessage();
            System.out.println("---------------------------------------------");
            System.out.println("Group " + group.getId());
            System.out.println("Sender  : " + group.getSender());
            for (String email : group.getRecievers()) {
                Message message = new Message("Shakira@gmail.com",email, new Date(2020,12,2),messageText.get(0), messageText.get(1));
                smtpProtocol.sendEmail(group.getSender(),email, message);
            }
        }
    }

    /**
     * Returns a random message from the list of messages.
     * @return A random message.
     */
    private List<String> getRandomMessage() {
        Random random = new Random();
        int index = random.nextInt(messages.size());
        return messages.get(index);
    }
}