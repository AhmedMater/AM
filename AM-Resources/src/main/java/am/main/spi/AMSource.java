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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AMSource)) return false;

        AMSource amSource = (AMSource) o;

        return getName() != null ? getName().equals(amSource.getName()) : amSource.getName() == null;
    }

    @Override
    public int hashCode() {
        return getName() != null ? getName().hashCode() : 0;
    }

    @Override
    public String toString() {
        return name;
    }
}
