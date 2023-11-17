package SMTPClient;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
public class SMTPClient {
    private FileManagement fileManagement = new FileManagement();
    private SMTPProtocol smtpProtocol = new SMTPProtocol();
    private List<String> emailAdresses;
    private List<String> messages;
    private List<Group> groups = new ArrayList<>();
    public void readEmailAdressFiles( String path) {
        emailAdresses = fileManagement.readFile(path, true);
        this.formGroups();
    }
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
    public void selectMessages(String path) {
        messages = fileManagement.readFile(path, false);

    }
    public void sendEmails() {

        for (Group group : groups) {
            Message message = new Message(group.getSender(), "", " Shakra","Justin", new Date(2020,12,2),"Iphone", getRandomMessage());
            smtpProtocol.sendEmail(group, message);
        }
    }
private String getRandomMessage() {
    Random rand = new Random();
    int randomIndex = rand.nextInt(messages.size());
    return messages.get(randomIndex);
}
}
