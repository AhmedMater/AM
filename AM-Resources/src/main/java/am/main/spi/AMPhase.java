package am.main.spi;

import am.main.data.enums.logger.LoggerLevels;

import java.util.HashMap;

/**
 * Created by ahmed.motair on 1/21/2018.
 */
public abstract class AMPhase {
    private final String CATEGORY;
    private final String NAME;
    private final String DEFAULT_LOG_LEVEL;

    private HashMap<String, AMPhase> ALL_PHASES = new HashMap<>();

    public AMPhase(String CATEGORY, String NAME, LoggerLevels DEFAULT_LOG_LEVEL) {
        this.CATEGORY = CATEGORY;
        this.NAME = NAME;
        this.DEFAULT_LOG_LEVEL = DEFAULT_LOG_LEVEL.level();

        this.ALL_PHASES.put(NAME, this);
    }

    public String getCategory() {
        return CATEGORY;
    }
    public String getName() {
        return NAME;
    }
    public String getDefaultLogLevel() {
        return DEFAULT_LOG_LEVEL;
    }

    public HashMap<String, AMPhase> getALL_PHASES() {
        return ALL_PHASES;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AMPhase)) return false;

        AMPhase amPhase = (AMPhase) o;

        if (CATEGORY != null ? !CATEGORY.equals(amPhase.CATEGORY) : amPhase.CATEGORY != null) return false;
        return NAME != null ? NAME.equals(amPhase.NAME) : amPhase.NAME == null;
    }

    @Override
    public int hashCode() {
        int result = CATEGORY != null ? CATEGORY.hashCode() : 0;
        result = 31 * result + (NAME != null ? NAME.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AMPhase{" +
                "CATEGORY = " + CATEGORY +
                ", NAME = " + NAME +
                "}\n";
    }
}
