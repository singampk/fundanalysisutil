package au.com.lonsec.singampk.fundutil.utils;

import au.com.lonsec.singampk.fundutil.model.IndexKey;
import au.com.lonsec.singampk.fundutil.model.OutPerformanceObject;
import au.com.lonsec.singampk.fundutil.model.PropertyConstants;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This is an util class to sort the map and update the Rank for each record
 *
 * Created by ksingamp on 19/02/2017.
 */

public class SortUtils {



    /**
     * This method is the sort the map based on the date and Rank of the Fund
     * Rank will be calculated based on Excess gained in current month
     *
     *
     * @param outPOMap
     * @return List<String>
     */

    public static List<String> sortMapBasedOnValues(Map<IndexKey, OutPerformanceObject> outPOMap){


        List<OutPerformanceObject> outPOList= new ArrayList(outPOMap.values());
        //Sorting based on date and excess value
        outPOList.sort((x,y) -> {
            if(y.getDate().compareTo(x.getDate()) == 0){
                if(y.getExcess() < x.getExcess()){

                    return -1;
                }else
                    return 1;
            }else
                return y.getDate().compareTo(x.getDate());
        });

         return convertSortedListToString(outPOList);
    }

    /**
     * This method is to convert the list of OutPerformanceObject to List of
     * String that can write to files.
     *
     *
     * @param outPOList
     * @return List of String to wirte
     */
    public static List<String> convertSortedListToString(List<OutPerformanceObject> outPOList){
        ApplicationProperties applicationProperties= ApplicationContextProvider.getInstance().getApplicationContext().getBean(PropertyConstants.CONST_APPLICATIONPROPERTIES, ApplicationProperties.class);

        ArrayList<String> returnList = new ArrayList();
        returnList.add(applicationProperties.getProperty(PropertyConstants.CONST_DEFAULT_FIRST_LINE));
        int i = 0;
        LocalDate tempDate = null;
        for(OutPerformanceObject opo:outPOList ){
            if(!opo.getDate().equals(tempDate)){
                i =1;
            }
            DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            returnList.add(opo.getFundName()+","
                    + opo.getDate().format(df)+","
                    +String.format("%.02f", opo.getExcess())+","
                    +opo.getOutPerformance()+","
                    +String.format("%.02f", opo.getReturnPercent())+","+
                    i++);
            tempDate =opo.getDate();
        }
        return returnList;
    }


}
