package au.com.lonsec.singampk.fundutil.utils;

import au.com.lonsec.singampk.fundutil.model.IndexKey;
import au.com.lonsec.singampk.fundutil.model.OutPerformanceObject;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;


/**
 * This class is to test the Sort Utils methods
 *
 * Created by ksingamp on 19/02/2017.
 */

public class SortUtilsTest {

    static Map<IndexKey,OutPerformanceObject>  outPerformanceObjectMap = null;

    //Loading required objects
    @BeforeClass
    public static void loadMapAndContext(){

        outPerformanceObjectMap = new HashMap<>();
        outPerformanceObjectMap.put(new IndexKey("Fund1", LocalDate.now()),new OutPerformanceObject("Fund Name1", LocalDate.now(),0.332f,"",1.1323f,2,new IndexKey("Fund1", LocalDate.now())));
        outPerformanceObjectMap.put(new IndexKey("Fund2", LocalDate.now()),new OutPerformanceObject("Fund Name2", LocalDate.now(),1.332f,"",0.1323f,2,new IndexKey("Fund2", LocalDate.now())));
        outPerformanceObjectMap.put(new IndexKey("Fund3", LocalDate.now()),new OutPerformanceObject("Fund Name3", LocalDate.now(),2.332f,"",2.1323f,2,new IndexKey("Fund3", LocalDate.now())));
        outPerformanceObjectMap.put(new IndexKey("Fund4", LocalDate.now()),new OutPerformanceObject("Fund Name4", LocalDate.now(),-0.332f,"",3.1323f,2,new IndexKey("Fund4", LocalDate.now())));
        outPerformanceObjectMap.put(new IndexKey("Fund5", LocalDate.now()),new OutPerformanceObject("Fund Name5", LocalDate.now(),-4.232f,"",5.1323f,2,new IndexKey("Fund5", LocalDate.now())));
        outPerformanceObjectMap.put(new IndexKey("Fund1", LocalDate.of(2016,2,21)),new OutPerformanceObject("Fund Name1", LocalDate.of(2016,2,21),0.231f,"",2.1323f,2,new IndexKey("Fund1", LocalDate.of(2016,2,21))));
        outPerformanceObjectMap.put(new IndexKey("Fund2", LocalDate.of(2016,2,21)),new OutPerformanceObject("Fund Name2", LocalDate.of(2016,2,21),1.332f,"",-2.1323f,2,new IndexKey("Fund2", LocalDate.of(2016,2,21))));
        outPerformanceObjectMap.put(new IndexKey("Fund3", LocalDate.of(2016,2,21)),new OutPerformanceObject("Fund Name3", LocalDate.of(2016,2,21),4.332f,"",2.1323f,2,new IndexKey("Fund3", LocalDate.of(2016,2,21))));
        outPerformanceObjectMap.put(new IndexKey("Fund4", LocalDate.of(2016,2,21)),new OutPerformanceObject("Fund Name4", LocalDate.of(2016,2,21),3.332f,"",-4.1323f,2,new IndexKey("Fund4", LocalDate.of(2016,2,21))));
        outPerformanceObjectMap.put(new IndexKey("Fund5", LocalDate.of(2016,2,21)),new OutPerformanceObject("Fund Name5", LocalDate.of(2016,2,21),-2.332f,"",2.1323f,2,new IndexKey("Fund5", LocalDate.of(2016,2,21))));
        outPerformanceObjectMap.put(new IndexKey("Fund1", LocalDate.of(2016,4,21)),new OutPerformanceObject("Fund Name1", LocalDate.of(2016,4,21),3.332f,"",0.1323f,2,new IndexKey("Fund1", LocalDate.of(2016,4,21))));
        outPerformanceObjectMap.put(new IndexKey("Fund2", LocalDate.of(2016,4,21)),new OutPerformanceObject("Fund Name2", LocalDate.of(2016,4,21),4.332f,"",2.1323f,2,new IndexKey("Fund2", LocalDate.of(2016,4,21))));
        outPerformanceObjectMap.put(new IndexKey("Fund3", LocalDate.of(2016,4,21)),new OutPerformanceObject("Fund Name3", LocalDate.of(2016,4,21),-2.332f,"",5.1323f,2,new IndexKey("Fund3", LocalDate.of(2016,4,21))));
        outPerformanceObjectMap.put(new IndexKey("Fund4", LocalDate.of(2016,4,21)),new OutPerformanceObject("Fund Name4", LocalDate.of(2016,4,21),1.332f,"",2.1323f,2,new IndexKey("Fund4", LocalDate.of(2016,4,21))));
        outPerformanceObjectMap.put(new IndexKey("Fund5", LocalDate.of(2016,4,21)),new OutPerformanceObject("Fund Name5", LocalDate.of(2016,4,21),0.332f,"",2.1423f,2,new IndexKey("Fund5", LocalDate.of(2016,4,21))));
    }

    @Test
    public void convertSortedListToStringTest(){
        List<String> sortedList = SortUtils.sortMapBasedOnValues(outPerformanceObjectMap);
        //Highest eligible value
        assertEquals(sortedList.get(0),"Fund Name3,20/02/2017,2.33,,2.13,1");
        //Start of second sequence
        assertEquals(sortedList.get(5),"Fund Name2,21/04/2016,4.33,,2.13,1");
        //End of second sequence
        assertEquals(sortedList.get(9),"Fund Name3,21/04/2016,-2.33,,5.13,5");
        //Least eligible value
        assertEquals(sortedList.get(14),"Fund Name5,21/02/2016,-2.33,,2.13,5");
    }
}
