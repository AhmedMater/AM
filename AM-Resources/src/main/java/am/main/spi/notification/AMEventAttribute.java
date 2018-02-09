package am.main.spi.notification;

/**
 * Created by ahmed.motair on 1/31/2018.
 */
public abstract class AMEventAttribute {
    private final AMEvent event;
    private final String attribute;

    public AMEventAttribute(AMEvent event, String attribute) {
        this.event = event;
        this.attribute = attribute;
    }

    public AMEvent getEvent() {
        return event;
    }
    public String getAttribute() {
        return attribute;
    }

    @Override
    public String toString() {
        return "AMEventAttribute{" +
                "event = " + event +
                ", attribute = " + attribute +
                "}\n";
    }
}
