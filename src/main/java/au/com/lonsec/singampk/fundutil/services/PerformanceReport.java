package au.com.lonsec.singampk.fundutil.services;

import au.com.lonsec.singampk.fundutil.businessunit.GenerateOutperformanceReport;
import au.com.lonsec.singampk.fundutil.businessunit.impl.GenerateOutperformanceReportImpl;
import au.com.lonsec.singampk.fundutil.dao.impl.GetDataObjectsImpl;
import au.com.lonsec.singampk.fundutil.model.IndexKey;
import au.com.lonsec.singampk.fundutil.model.InputParams;
import au.com.lonsec.singampk.fundutil.model.OutPerformanceObject;
import au.com.lonsec.singampk.fundutil.utils.CommandLineUtil;
import au.com.lonsec.singampk.fundutil.utils.LoadCSVFiles;
import au.com.lonsec.singampk.fundutil.utils.SortUtils;
import org.apache.log4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.text.DateFormatter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * This class is the mail class to trigger when the util is used
 * This class is responsible for generating the monthlyOutPerformance.csv
 *
 *
 * Created by ksingamp on 16/02/2017.
 */
public class PerformanceReport {

    /**
     * Main method responsible for generating the monthly
     * Performance Data sheet
     *
     * @arguments Fund csv path, Benchmark csv path, Fund return series csv, Benchmark return series csv
     * @rerurn void
     * @outputs monthlyOutperformance.csv
     *
     * **/
    public static void main(String... args){

        // initializing console logger
        final Logger logger = Logger.getLogger(PerformanceReport.class);
        //To load the application context
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        InputParams inputParams = new CommandLineUtil(args).parse();

        GenerateOutperformanceReport gor = context.getBean("generateOutperformanceReport", GenerateOutperformanceReportImpl.class);
        Map<IndexKey,OutPerformanceObject> outPerformanceObjectMap = gor.getOutPerformanceReport(inputParams);
        gor.writeFile(inputParams,outPerformanceObjectMap);
        //outPOList.forEach(System.out::println);

        logger.info("File has been successfully written to " + inputParams.getOutputFilePath());
        logger.info("********* End of execution **************");
    }

}
