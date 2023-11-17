package SMTPClient;

import java.util.List;

/**
 * The Group class represents a group of email recipients.
 * It contains a sender and a list of receivers.
 */
public class Group {
    private String sender;
    private List<String> recievers;

    /**
     * Constructs a new Group with the specified list of receivers.
     * The sender is set to the first receiver in the list.
     * @param recievers The list of receivers for this group.
     */
    public Group(List<String> recievers) {
        this.recievers = recievers;
        if (!recievers.isEmpty())
            this.sender = recievers.get(0);
    }

    /**
     * Returns the sender of this group.
     * @return The sender of this group.
     */
    public String getSender() {
        return sender;
    }

    /**
     * Returns the list of receivers of this group.
     * @return The list of receivers of this group.
     */
    public List<String> getRecievers() {
        return recievers;
    }

    /**
     * Sets the sender of this group.
     * @param sender The sender to set.
     */
    public void setSender(String sender) {
        this.sender = sender;
    }
}