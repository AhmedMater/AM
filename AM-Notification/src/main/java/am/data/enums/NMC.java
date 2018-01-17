package am.data.enums;

/**
 * Created by ahmed.motair on 16/09/2017.
 */
public enum NMC {
    MAIL_SMTP_AUTH("mail.smtp.auth"),
    MAIL_SMTP_START_TLS_EN("mail.smtp.starttls.enable"),
//    MAIL_SMTP_EN_SSL("mail.smtp.EnableSSL.enable"),
//    MAIL_SMTP_SSL_TRUST("mail.smtp.ssl.trust"),
//    MAIL_SMTP_HOST("mail.smtp.host"),
    MAIL_SMTP_PORT("mail.smtp.port"),
//    MAIL_FROM("mail.from"),
//    MAIL_PASSWORD("mail.password"),
    MAIL_DEBUG("mail.debug");

    private String value;

    NMC(String value){
        this.value = value;
    }
    NMC(){
    }
    public String value(){
        return value;
    }
    @Override public String toString(){
        return value;
    }
}
