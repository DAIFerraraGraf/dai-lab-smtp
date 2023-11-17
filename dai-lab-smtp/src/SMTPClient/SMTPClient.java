package SMTPClient;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SMTPClient {
    private FileManagement fileManagement = new FileManagement();
    private SMTPProtocol smtpProtocol = new SMTPProtocol();
    private List<String> emailAdresses;
    private List<String> messages;
    private List<Group> groups;
    public void readEmailAdressFiles( String path) {
        emailAdresses = fileManagement.readFile(path, true);
        this.formGroups();
    }
    private List<Group> formGroups() {
        List<Group> groups = new ArrayList<>();
        List<String> emailGroup = new ArrayList<>();
        for (int i = 0; i < emailAdresses.size(); i++) {
            emailGroup.add(emailAdresses.get(i));
            if (i % 5 == 0 && i != 0) {
                Group group = new Group(emailGroup);
                groups.add(group);
                emailGroup.clear();
            }
        }
        return groups;
    }
    public void selectMessages(String path) {
        messages = fileManagement.readFile(path, false);

    }
    public void sendEmails() {

        for (Group group : groups) {
            Message message = new Message(group.getSender(), "", " Shakra","Justin", new Date(2020,12,2),"Iphone","I'm shakra"  );
            smtpProtocol.sendEmail(group, message);
        }
    }
}
