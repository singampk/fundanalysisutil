package au.com.lonsec.singampk.fundutil.dao;

import au.com.lonsec.singampk.fundutil.businessunit.GenerateOutperformanceReport;
import au.com.lonsec.singampk.fundutil.model.DataObject;
import au.com.lonsec.singampk.fundutil.model.IndexKey;
import au.com.lonsec.singampk.fundutil.model.PropertyConstants;
import au.com.lonsec.singampk.fundutil.model.ReturnSeries;
import au.com.lonsec.singampk.fundutil.utils.LoadCSVFiles;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * To test Data object methods
 * Created by ksingamp on 20/02/2017.
 */
@ContextConfiguration("classpath:applicationContext.xml")
public class GetDataObjectImplTest {

    static ConfigurableApplicationContext context = null;

    GetDataObjects getDataObjects = null;
    //LoadCSVFiles class
    LoadCSVFiles loadCSVFiles = null;

    @BeforeClass
    public static void loadContext(){
        context = new ClassPathXmlApplicationContext("applicationContext.xml");}

    @Test
    public void loadReturnSeriesTest(){
        loadCSVFiles = context.getBean(PropertyConstants.CONST_LOADCSVFILES, LoadCSVFiles.class);
        getDataObjects = context.getBean(PropertyConstants.CONST_GETDATAOBJECTS, GetDataObjects.class);
        String fundReturnCSVpath = loadCSVFiles.getFilePath("FundReturnSeries.csv");
        Map<IndexKey, ReturnSeries> indexKeyReturnSeriesMap = getDataObjects.loadReturnSeries(fundReturnCSVpath);
        assertEquals(indexKeyReturnSeriesMap.values().size(),36);
    }

    @Test
    public void loadDataObjectTest(){
        loadCSVFiles = context.getBean(PropertyConstants.CONST_LOADCSVFILES, LoadCSVFiles.class);
        getDataObjects = context.getBean(PropertyConstants.CONST_GETDATAOBJECTS, GetDataObjects.class);
        String fundCSVpath = loadCSVFiles.getFilePath("fund.csv");
        Map<String, DataObject> indexKeyReturnSeriesMap = getDataObjects.loadDataObject(fundCSVpath);
        assertEquals(indexKeyReturnSeriesMap.values().size(),6);
    }

}
