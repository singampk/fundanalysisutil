package au.com.lonsec.singampk.fundutil.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.AbstractEnvironment;

import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;

import java.util.*;

/**
 * This is utility class to get the application properties from outperformance.properties
 *
 * Created by ksingamp on 17/02/2017.
 */


@Configuration
@PropertySources({ @PropertySource(value = "outperformance.properties", ignoreResourceNotFound = true) })
public class ApplicationProperties {

    @Autowired
    private Environment env;

    public Map<String,Object> getAllProperties()
    {
        Map<String, Object> map = new HashMap();
        for(Iterator it = ((AbstractEnvironment) env).getPropertySources().iterator(); it.hasNext(); ) {
            MapPropertySource propertySource = (MapPropertySource) it.next();
            if (propertySource instanceof MapPropertySource) {
                map.putAll(((MapPropertySource) propertySource).getSource());
            }
        }
        return map;
    }

    public String getProperty(String propName) {
        return env.getProperty(propName);
    }


}
