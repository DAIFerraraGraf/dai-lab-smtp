package SMTPClient;

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
    private List<String> messages;
    private List<Group> groups = new ArrayList<>();

    /**
     * Reads email addresses from a file and forms groups.
     * @param path The path to the file containing email addresses.
     */
    public void readEmailAdressFiles(String path) {
        emailAdresses = fileManagement.readFile(path, true);
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
    public void selectMessages(String path) {
        messages = fileManagement.readFile(path, false);
    }

    /**
     * Sends emails to the groups.
     * Each group receives a random message.
     */
    public void sendEmails() {
        for (Group group : groups) {
            Message message = new Message(group.getSender(), "", " Shakra","Justin", new Date(2020,12,2),"Iphone", getRandomMessage());
            smtpProtocol.sendEmail(group, message);
        }
    }

    /**
     * Returns a random message from the list of messages.
     * @return A random message.
     */
    private String getRandomMessage() {
        Random rand = new Random();
        int randomIndex = rand.nextInt(messages.size());
        return messages.get(randomIndex);
    }
}