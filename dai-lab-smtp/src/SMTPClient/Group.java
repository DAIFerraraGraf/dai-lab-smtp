package SMTPClient;

import java.util.List;

public class Group {
    private String sender;
    private List<String> emails;

    public Group(List<String> emails) {
        this.emails = emails;
        if (!emails.isEmpty())
            this.sender = emails.get(0);
    }
    public String getSender() {
        return sender;
    }
    public List<String> getEmails() {
        return emails;
    }
    public void setSender(String sender) {
        this.sender = sender;
    }
}
