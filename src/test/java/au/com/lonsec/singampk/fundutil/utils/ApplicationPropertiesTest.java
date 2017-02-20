package au.com.lonsec.singampk.fundutil.utils;

import au.com.lonsec.singampk.fundutil.TestConstants;
import au.com.lonsec.singampk.fundutil.model.PropertyConstants;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.StringTokenizer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Test cases to test the methods in ApplicationPropertiesTest
 *
 *
 * Created by ksingamp on 18/02/2017.
 */

@ContextConfiguration("classpath*:/applicationContext.xml")
public class ApplicationPropertiesTest {


    static ConfigurableApplicationContext context = null;


    private ApplicationProperties applicationProperties;

    @BeforeClass
    public static void loadContext(){
        context = new ClassPathXmlApplicationContext(TestConstants.CONST_APPLICATIONCONTEXT_XML);
    }

    /**
     * Test case to load the properties
     */
    @Test
    public void getPropertyTest(){
        applicationProperties = context.getBean(PropertyConstants.CONST_APPLICATIONPROPERTIES, ApplicationProperties.class);
        assertNotNull(applicationProperties);
        if(applicationProperties!=null) {
                String delimiter = applicationProperties.getProperty(PropertyConstants.CONST_DELIMITER);
                String upperNLowerLimits = applicationProperties.getProperty(PropertyConstants.CONST_UPPERNLOWERLIMITS);
                StringTokenizer stnz = new StringTokenizer(upperNLowerLimits,delimiter);

                while(stnz.hasMoreTokens())
                {
                    String underperformance = applicationProperties.getProperty(stnz.nextToken());
                    String outperformance = applicationProperties.getProperty(stnz.nextToken());
                    assertNotNull(underperformance);
                    assertNotNull(outperformance);
                    assertEquals( underperformance,TestConstants.CONST_UNDERPERFORMED_VALUE);
                    assertEquals( outperformance,TestConstants.CONST_OUTPERFORMED_VALUE);
                }


                assertNotNull(delimiter);
                assertNotNull(upperNLowerLimits);

                assertEquals( delimiter,TestConstants.CONST_DELIMITER_VALUE);
                assertEquals( upperNLowerLimits,TestConstants.CONST_UPPERNLOWERLIMITS_VALUE);

            }
        }
}
