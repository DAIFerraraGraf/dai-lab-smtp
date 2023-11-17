package SMTPClient;

import java.util.Date;

/**
 * The Message class represents an email message.
 * It contains the SMTP from and to addresses, the from and to addresses, the date, the subject, and the body of the message.
 */
public class Message {
    private String fromSMTP;
    private String toSMTP;
    private String from;
    private String to;
    private Date date;
    private String subject;
    private String body;

    /**
     * Constructs a new Message with the specified parameters.
     * @param fromSMTP The SMTP from address.
     * @param toSMTP The SMTP to address.
     * @param from The from address.
     * @param to The to address.
     * @param date The date of the message.
     * @param subject The subject of the message.
     * @param body The body of the message.
     */
    public Message(String fromSMTP, String toSMTP, String from, String to, Date date, String subject, String body) {
        this.fromSMTP = fromSMTP;
        this.toSMTP = toSMTP;
        this.from = from;
        this.to = to;
        this.date = date;
        this.subject = subject;
        this.body = body;
    }

    /**
     * Returns the SMTP from address of this message.
     * @return The SMTP from address of this message.
     */
    public String getFromSMTP() {
        return fromSMTP;
    }

    /**
     * Returns the SMTP to address of this message.
     * @return The SMTP to address of this message.
     */
    public String getToSMTP() {
        return toSMTP;
    }

    /**
     * Returns the from address of this message.
     * @return The from address of this message.
     */
    public String getFrom() {
        return from;
    }

    /**
     * Returns the to address of this message.
     * @return The to address of this message.
     */
    public String getTo() {
        return to;
    }

    /**
     * Returns the date of this message.
     * @return The date of this message.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Returns the subject of this message.
     * @return The subject of this message.
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Returns the body of this message.
     * @return The body of this message.
     */
    public String getBody() {
        return body;
    }
}