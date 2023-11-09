package SMTPClient;

import java.util.Date;

public class Message {
    private String fromSMTP;
    private String toSMTP;
    private String from;
    private String to;
    private Date date;
    private String subject;
    private String body;

    public Message(String fromSMTP, String toSMTP, String from, String to, Date date, String subject, String body) {
        this.fromSMTP = fromSMTP;
        this.toSMTP = toSMTP;
        this.from = from;
        this.to = to;
        this.date = date;
        this.subject = subject;
        this.body = body;
    }

    public String getFromSMTP() {
        return fromSMTP;
    }

    public String getToSMTP() {
        return toSMTP;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public Date getDate() {
        return date;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }

}
