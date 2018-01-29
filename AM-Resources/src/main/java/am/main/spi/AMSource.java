package am.main.spi;

/**
 * Created by ahmed.motair on 1/27/2018.
 */
public abstract class AMSource {
    private final String name;

    protected AMSource(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
