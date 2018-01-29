package am.main.data.enums.impl;

import am.main.spi.AMSource;

/**
 * Created by ahmed.motair on 1/27/2018.
 */
public class AMS extends AMSource {

    public static AMS AM = new AMS("AM");
    public static AMS AM_LOGGER = new AMS("AM-Logger");
    public static AMS AM_FILE_MANAGER = new AMS("AM-File-Manager");

    private AMS(String name) {
        super(name);
    }
}
