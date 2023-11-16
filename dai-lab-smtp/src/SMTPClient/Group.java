package SMTPClient;

import java.util.List;

public class Group {
    private String sender;
    private List<String> recievers;

    public Group(List<String> recievers) {
        this.recievers = recievers;
        if (!recievers.isEmpty())
            this.sender = recievers.get(0);
    }
    public String getSender() {
        return sender;
    }
    public List<String> getRecievers() {
        return recievers;
    }
    public void setSender(String sender) {
        this.sender = sender;
    }
}
