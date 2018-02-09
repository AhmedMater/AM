package am.data.enums.am.impl;

import am.main.spi.AMForm;

/**
 * Created by ahmed.motair on 2/5/2018.
 */
public class ANF extends AMForm {

    public static final ANF NOTIFICATION_DTO = new ANF("AM Notification");
    public static final ANF DESTINATION_DTO = new ANF("AM Notification Destination");

    private ANF(String form) {
        super(form);
    }
}
