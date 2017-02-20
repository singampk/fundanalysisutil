package au.com.lonsec.singampk.fundutil.utils;


import au.com.lonsec.singampk.fundutil.TestConstants;
import au.com.lonsec.singampk.fundutil.model.DataObject;
import au.com.lonsec.singampk.fundutil.model.IndexKey;
import au.com.lonsec.singampk.fundutil.model.PropertyConstants;
import au.com.lonsec.singampk.fundutil.model.ReturnSeries;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * This class to test the LoadCSVFiles methods
 *
 * Created by ksingamp on 16/02/2017.
 */

@ContextConfiguration("classpath:applicationContext.xml")
public class LoadCSVFilesTest {

   static ConfigurableApplicationContext context = null;

   @BeforeClass
   public static void loadContext(){
       context = new ClassPathXmlApplicationContext("applicationContext.xml");
    }
    /**
     * Test case to verify the Fund populated properly
     */
    @Test
    public void processFundFileTest(){

        LoadCSVFiles loadCSVFiles = context.getBean(PropertyConstants.CONST_LOADCSVFILES, LoadCSVFiles.class);
        ClassLoader classLoader = getClass().getClassLoader();
        URL url =  classLoader.getResource(TestConstants.CONST_FUND_CSV);
        if(url != null){
            File file = new File(url.getFile());
            if(file != null) {
                assertNotNull(file.getAbsolutePath());
                Map<String,DataObject> mapFunds = loadCSVFiles.loadInputFile(file.getAbsolutePath());
                assertTrue(! mapFunds.isEmpty());
                assertEquals( mapFunds.size(), 6);
                assertEquals( mapFunds.get("MF-1-4220").getCode(),"MF-1-4220");
                assertEquals( mapFunds.get("MF-1-31705").getName(),"Pengana Global Small Companies Fund");
                assertEquals( mapFunds.get("EF-2-21254").getBenchmarkCode(),"BM-672");
            }
            else{
                assertNotNull(file);
            }
        }
        else {
            assertNotNull(url);
        }
      }
    /**
     * Test case to verify benchmark csv is loaded properly or not
     */
    @Test
    public void processBenchmarkFileTest(){

        LoadCSVFiles loadCSVFiles = context.getBean(PropertyConstants.CONST_LOADCSVFILES, LoadCSVFiles.class);
        ClassLoader classLoader = getClass().getClassLoader();
        URL url =  classLoader.getResource(TestConstants.CONST_BENCHMARK_CSV);
        if(url != null){
            File file = new File(url.getFile());
            if(file != null) {
                assertNotNull(file.getAbsolutePath());
                Map<String,DataObject> mapBenchmarks = loadCSVFiles.loadInputFile(file.getAbsolutePath());
                assertTrue(! mapBenchmarks.isEmpty());
                //Expected size
                assertEquals( mapBenchmarks.size(), 2);
                assertEquals( mapBenchmarks.get("BM-18").getCode(),"BM-18");
                assertEquals( mapBenchmarks.get("BM-672").getName(),"S&P/ASX Mid Small TR Index AUD");
            }
            else{
                assertNotNull(file);
            }
        }
        else {
            assertNotNull(url);
        }
    }
    /**
     * Test case to verify Fund return series csv is loaded properly or not
     */
    @Test
    public void processFundReturnSeriesTest(){

        LoadCSVFiles loadCSVFiles = context.getBean(PropertyConstants.CONST_LOADCSVFILES, LoadCSVFiles.class);
        ClassLoader classLoader = getClass().getClassLoader();
        URL url =  classLoader.getResource(TestConstants.CONST_FUND_RETURN_CSV);
        if(url != null){
            File file = new File(url.getFile());
            if(file != null) {
                assertNotNull(file.getAbsolutePath());
                Map<IndexKey,ReturnSeries> fundReturnSeries = loadCSVFiles.loadInputReturnFile(file.getAbsolutePath());
                assertTrue(! fundReturnSeries.isEmpty());
                assertEquals( fundReturnSeries.size(), 36);
            }
            else{
                assertNotNull(file);
            }
        }
        else {
            assertNotNull(url);
        }
    }
    /**
     * Test case to verify Benchmark return series csv is loaded properly or not
     */
    @Test
    public void processBenchmarkReturnSeriesTest(){

        LoadCSVFiles loadCSVFiles = context.getBean(PropertyConstants.CONST_LOADCSVFILES, LoadCSVFiles.class);
        ClassLoader classLoader = getClass().getClassLoader();
        URL url =  classLoader.getResource(TestConstants.CONST_BENCHMARK_RETURN_CSV);
        if(url != null){
            File file = new File(url.getFile());
            if(file != null) {
                assertNotNull(file.getAbsolutePath());
                Map<IndexKey,ReturnSeries> benchmarkReturnSeries = loadCSVFiles.loadInputReturnFile(file.getAbsolutePath());
                assertTrue(! benchmarkReturnSeries.isEmpty());
                assertEquals( benchmarkReturnSeries.size(), 14);
            }
            else{
                assertNotNull(file);
            }
        }
        else {
            assertNotNull(url);
        }
    }

    /**
     * Test case verify the date conversion is happening properly or not
     */
    @Test
    public void convertDateToStringTest(){

        String date = "2016-07-31";
        String date2 = "30/06/2016";
        String date3 = "2016-08-22";
        LoadCSVFiles loadCSVFiles = context.getBean(PropertyConstants.CONST_LOADCSVFILES, LoadCSVFiles.class);
        LocalDate localDate = loadCSVFiles.convertStringToDate(date);
        LocalDate localDate2 = loadCSVFiles.convertStringToDate(date2);
        LocalDate localDate3 = loadCSVFiles.convertStringToDate(date3);

        assertEquals(localDate.getDayOfMonth(),31);
        assertEquals(localDate2.getDayOfMonth(),30);
        assertEquals(localDate3.getDayOfMonth(),22);
    }
}
