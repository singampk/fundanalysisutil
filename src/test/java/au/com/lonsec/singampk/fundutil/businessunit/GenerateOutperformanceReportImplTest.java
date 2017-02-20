package au.com.lonsec.singampk.fundutil.businessunit;

import au.com.lonsec.singampk.fundutil.model.IndexKey;
import au.com.lonsec.singampk.fundutil.model.InputParams;
import au.com.lonsec.singampk.fundutil.model.OutPerformanceObject;
import au.com.lonsec.singampk.fundutil.model.PropertyConstants;
import au.com.lonsec.singampk.fundutil.utils.ApplicationProperties;
import au.com.lonsec.singampk.fundutil.utils.LoadCSVFiles;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * To test generate out performance report impl
 * Created by ksingamp on 20/02/2017.
 */
@ContextConfiguration("classpath:applicationContext.xml")
public class GenerateOutperformanceReportImplTest {

    static ConfigurableApplicationContext context = null;

    GenerateOutperformanceReport generateOutperformanceReport = null;

    static LoadCSVFiles loadCSVFiles = null;

    static InputParams inputParams = null;

    @BeforeClass
    public static void loadContext(){
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
        inputParams = new InputParams();
        loadCSVFiles = context.getBean(PropertyConstants.CONST_LOADCSVFILES, LoadCSVFiles.class);
        inputParams.setFundCSVPath(loadCSVFiles.getFilePath("fund.csv"));
        inputParams.setBenchmarkCSVPath(loadCSVFiles.getFilePath("benchmark.csv"));
        inputParams.setReturnFundSeriesPath(loadCSVFiles.getFilePath("FundReturnSeries.csv"));
        inputParams.setReturnBenchmarkSeriesPath(loadCSVFiles.getFilePath("BenchReturnSeries.csv"));
        inputParams.setOutputFilePath(loadCSVFiles.getFilePath("testCaseOutputFile.csv"));

    }

    @Test
    public void getOutPerformanceReportTest(){
        generateOutperformanceReport = context.getBean(PropertyConstants.CONST_GENERATE_OUTPERFORMANCE_REPORT, GenerateOutperformanceReport.class);

        Map<IndexKey, OutPerformanceObject> outPerformanceReport = generateOutperformanceReport.getOutPerformanceReport(inputParams);
        assertNotNull(outPerformanceReport);
        assertEquals(outPerformanceReport.values().size(), 36);

    }
}
