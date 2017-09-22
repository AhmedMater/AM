package am.api.notification;

/**
 * Created by ahmed.motair on 5/28/2017.
 */
public class MailData {
    private String to;
    private String subject;
    private String body;

    public MailData() {
    }
    public MailData(String to, String subject, String body) {
        this.to = to;
        this.subject = subject;
        this.body = body;
    }

    public String getTo() {
        return to;
    }
    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MailData)) return false;

        MailData mailData = (MailData) o;

        if (getTo() != null ? !getTo().equals(mailData.getTo()) : mailData.getTo() != null) return false;
        if (getSubject() != null ? !getSubject().equals(mailData.getSubject()) : mailData.getSubject() != null) return false;
        return getBody() != null ? getBody().equals(mailData.getBody()) : mailData.getBody() == null;
    }

    @Override
    public int hashCode() {
        int result = getTo() != null ? getTo().hashCode() : 0;
        result = 31 * result + (getSubject() != null ? getSubject().hashCode() : 0);
        result = 31 * result + (getBody() != null ? getBody().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MailData: {dto = " + to + ", subject = " + subject + ", body = " + body +  "}\n";
    }
}
