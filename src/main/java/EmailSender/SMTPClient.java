package EmailSender;

import java.util.ArrayList;
import java.util.List;

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

                if (emailGroup.size() % 5 == 0) {
                    Group group = new Group(emailGroup);
                    groups.add(group);
                    emailGroup.clear();
                }else if (i == emailAdresses.size() - 1) {
                    if(emailGroup.size() > 1) {
                        Group group = new Group(emailGroup);
                        groups.add(group);
                    }else {
                        System.out.println("The last email address is not added to a group. \n" +
                                "Because a group must contain at least 2 email addresses.");
                    }
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
        smtpProtocol.sendEmail(groups, messages);
    }

}