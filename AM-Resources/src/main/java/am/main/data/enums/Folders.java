package am.main.data.enums;

/**
 * Created by ahmed.motair on 1/14/2018.
 */
public enum Folders {
    EMAIL_TEMPLATES("EM", "Templates/Email"),
    SMS_TEMPLATES("SMS", "Templates/SMS"),
    WEB_NOTIFICATION_TEMPLATES("Web-Notification", "Templates/Web-Notification");

    private final String name;
    private final String path;
    Folders(String name, String path){
        this.name = name;
        this.path = path;
    }

    public String folderName() {
        return name;
    }
    public String folderPath() {
        return path;
    }

    @Override
    public String toString() {
        return "Folders{" +
                "name = " + name +
                ", path = " + path +
                "}\n";
    }
}
