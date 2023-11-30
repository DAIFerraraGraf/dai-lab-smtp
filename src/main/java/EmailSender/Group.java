package EmailSender;

import java.util.List;

/**
 * The Group class represents a group of email recipients.
 * It contains a sender and a list of receivers.
 */
public class Group {
    private String sender;
    private List<String> recievers;
    private int id;
    private static int counterID = 0;

    /**
     * Constructs a new Group with the specified list of receivers.
     * The sender is set to the first receiver in the list.
     * @param recievers The list of receivers for this group.
     */
    public Group(List<String> recievers) {
        if (recievers.isEmpty() || recievers.size() < 2)
            throw new IllegalArgumentException("The list of receivers cannot be empty or contain only 1 person.");
        this.recievers = recievers;
        this.id = ++counterID;
        this.sender = recievers.get(0);
    }
    public int getId() {
        return id;
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
    public List<String> getReceivers() {
        return recievers;
    }
    public String getStringReceivers(){
        String result = "";
        for (String reciever : recievers) {
            result += reciever + ", ";
        }
        return result.substring(0, result.length() - 2);
    }

    /**
     * Sets the sender of this group.
     * @param sender The sender to set.
     */
    public void setSender(String sender) {
        this.sender = sender;
    }
}