package am.main.spi.notification;

/**
 * Created by ahmed.motair on 1/31/2018.
 */
public abstract class AMEvent {
    private final String event;

    protected AMEvent(String event) {
        this.event = event;
    }

    public String getEvent() {
        return event;
    }

    @Override
    public String toString() {
        return event;
    }
}
