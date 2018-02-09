package am.common;

import am.data.enums.ALS;
import am.main.spi.AMSource;

/**
 * Created by ahmed.motair on 2/9/2018.
 */
public class LoggerParam {
    public static final String FILE_LOG_QUEUE = "AM_File_Log_Q";
    public static final String BUS_LOG_QUEUE = "AM_Bus_Not_Q";
    public static final String FUN_LOG_QUEUE = "AM_Fun_Not_Q";

    public static final AMSource SOURCE = new ALS("AM-Logger");
}
