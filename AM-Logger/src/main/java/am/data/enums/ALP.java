package am.data.enums;

import am.main.data.enums.logger.LoggerLevels;
import am.main.spi.AMPhase;

/**
 * Created by ahmed.motair on 2/9/2018.
 */
public class ALP extends AMPhase {
    private static final String AM_LOG = "AML";

    public static final ALP BUSINESS_LOG = new ALP(AM_LOG, "Business");
    public static final ALP FILE_LOG = new ALP(AM_LOG, "File");
    public static final ALP FUNCTION_LOG = new ALP(AM_LOG, "Function");

    public ALP(String CATEGORY, String NAME) {
        super(CATEGORY, NAME, LoggerLevels.ST_DEBUG);
    }
}
