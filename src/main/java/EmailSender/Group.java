package EmailSender;

import java.util.List;

/**
 * The Group class represents a group of email recipients.
 * It contains a sender and a list of receivers.
 */
public class Group {
    private String sender;
    private List<String> recievers;
    private final int id;
    private static int counterID = 0;

    /**
     * Constructs a new Group with the specified list of receivers.
     * The sender is set to the first receiver in the list.
     * If the list of receivers is empty or contains less than 2 email addresses,
     * an error message is printed and the Group.id == -1.
     *
     * @param recievers The list of receivers for this group.
     */
    public Group(List<String> recievers) {
        if (recievers.isEmpty() || recievers.size() < 2){
            System.out.println("A group must contain at least 2 email addresses.");
            System.out.println("If the group is not empty the last email address is not added to a group.");
            this.id = -1;
            return;
        }
        this.sender = recievers.get(0);
        recievers.remove(0);
        this.recievers = recievers;
        this.id = ++counterID;

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
    /**
     * Returns a string representation of the receivers of this group.
     * The email addresses are formatted as "Firstname Lastname <email>", and are separated by commas.
     * @return A string representation of the receivers of this group.
     */
    public String getStringReceivers(){
        StringBuilder result = new StringBuilder();

        for (String reciever : recievers) {
            result.append(getPersonInfo(reciever)).append(", ");
        }
        return result.substring(0, result.length() - 2);
    }
    /**
     * Returns a string representation of the sender of this group.
     * The email address is formatted as "Firstname Lastname <email>".
     * @return A string representation of the sender of this group.
     */
    public String getStringSender(){
        return getPersonInfo(sender);
    }
    /**
     * Formats an email address as "Firstname Lastname <email>".
     * The method extracts the first name and last name from the email address, capitalizes the first letter of each, and then concatenates them with the email address.
     * @param email The email address to format.
     * @return The formatted email address.
     */
    private String getPersonInfo(String email){
        int indexPoint = email.indexOf('.');
        String firstname = email.substring(0, indexPoint).substring(0, 1).toUpperCase() + email.substring(0, indexPoint).substring(1);
        String substring = email.substring(indexPoint + 1, email.indexOf('@'));
        String lastname = substring.substring(0, 1).toUpperCase() + substring.substring(1);
        return firstname + " " + lastname + " <" + email + ">";
    }

    /**
     * Sets the sender of this group.
     * @param sender The sender to set.
     */
    public void setSender(String sender) {
        this.sender = sender;
    }
}