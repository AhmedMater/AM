package am.main.spi.notification;

/**
 * Created by ahmed.motair on 1/31/2018.
 */
public abstract class AMEventNotification {
    private final AMEvent event;
    private final String notification;

    public AMEventNotification(AMEvent event, String notification) {
        this.event = event;
        this.notification = notification;
    }

    public AMEvent getEvent() {
        return event;
    }
    public String getNotification() {
        return notification;
    }

    @Override
    public String toString() {
        return "AMEventNotification{" +
                "event = " + event +
                ", notification = " + notification +
                "}\n";
    }
}
