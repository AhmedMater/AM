package am;

import am.main.common.ConfigParam;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.naming.InitialContext;

import static am.common.NotificationParam.SOURCE;

/**
 * Created by ahmed.motair on 1/6/2018.
 */
@Singleton
@Startup
public class AMInitializer {
    @PostConstruct
    public void load() {
        try {
            InitialContext ctx = new InitialContext();
            ConfigParam.setConfiguration((String) ctx.lookup("AMTConfigPath"), SOURCE);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
