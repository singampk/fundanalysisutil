package au.com.lonsec.singampk.fundutil.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * A singleton application provider
 *
 * Created by ksingamp on 17/02/2017.
 */

public class ApplicationContextProvider implements ApplicationContextAware {

    private static ApplicationContextProvider applicationContextProvider;
    private static ApplicationContext context;

    private ApplicationContextProvider() {

    }

    public static ApplicationContextProvider getInstance(){
        if(applicationContextProvider == null) {
            applicationContextProvider = new ApplicationContextProvider();
        }
        return applicationContextProvider;
    }

    public ApplicationContext getApplicationContext() {
        return context;
    }

    @Override
    public void setApplicationContext(ApplicationContext ctx) {
        context = ctx;
    }
}

