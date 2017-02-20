package au.com.lonsec.singampk.fundutil.utils;

import au.com.lonsec.singampk.fundutil.model.DataObject;
import au.com.lonsec.singampk.fundutil.model.IndexKey;
import au.com.lonsec.singampk.fundutil.model.PropertyConstants;
import au.com.lonsec.singampk.fundutil.model.ReturnSeries;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


/**
 * This class is an util class to which contains all the util methods that used
 * to load data and generate data
 *
 *
 * Created by ksingamp on 16/02/2017.
 */
@Component
public class LoadCSVFiles {

    final Logger logger = Logger.getLogger(LoadCSVFiles.class);



    @Autowired
    private ApplicationProperties applicationProperties;
    @Value(",")
    String delimiter = "";

    /**
     * This method is to load files that are in classpath only
     *
     *
     * @param filename
     * @return String absolute file path.
     */

    public String getFilePath(String filename){
        ClassLoader classLoader = getClass().getClassLoader();
        URL url =  classLoader.getResource(filename);
        if(url != null) {
            File file = new File(url.getFile());
            if (file != null) {
                return file.getAbsolutePath();
            }
        }
        return null;
    }

    public Map<String,DataObject> loadInputFile(String inputFilePath) {
       delimiter = applicationProperties.getProperty(PropertyConstants.CONST_DELIMITER);
        Map<String,DataObject> map = new HashMap<>();

        try{
            File inputF = new File(inputFilePath);
            InputStream inputFS = new FileInputStream(inputF);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));
            // skip the header of the csv
            map = br.lines()
                    .skip(1)
                    .map(mapToFund)
                    .distinct()
                    .collect(Collectors.toMap(DataObject::getCode,Function.identity(),(code1,code2) -> code1));

            br.close();
        } catch (IOException | NullPointerException e ) {
          logger.error("Exception in processing the input file>>>"+e);
        }
        return map ;

    }
    public Map<IndexKey,ReturnSeries> loadInputReturnFile(String absolutePath) {
        delimiter = applicationProperties.getProperty(PropertyConstants.CONST_DELIMITER);
        Map<IndexKey,ReturnSeries> map = new HashMap<>();

        try{
            File inputF = new File(absolutePath);
            InputStream inputFS = new FileInputStream(inputF);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));
            // skip the header of the csv
            map = br.lines()
                    .skip(1)
                    .map(mapToReturn)
                    .distinct().filter((x) -> x.getDate()!=null && x.getCode()!=null)
                    .collect(Collectors.toMap(ReturnSeries::getIndexKey,Function.identity(),(index1, index2) -> index1));

            br.close();
        } catch (IOException | NullPointerException  e ) {
            logger.error("Exception in processing the input file>>>"+e);
        }
        return map ;
    }
    private Function<String, DataObject> mapToFund = (line) -> {
        String[] token = line.split(delimiter);
        DataObject dataObject = new DataObject();

        if (token[0] != null && token[0].trim().length() > 0) {
            dataObject.setCode(token[0].trim());
        }
        if (token[1] != null && token[1].trim().length() > 0) {
            dataObject.setName(token[1].trim());
        }
        if (token.length> 2 && token[2] != null && token[2].trim().length() > 0) {
            dataObject.setBenchmarkCode(token[2].trim());
        }
        return dataObject;
    };
    private Function<String, ReturnSeries> mapToReturn = (line) -> {
        String[] token = line.split(delimiter);

        ReturnSeries returnSeries = new ReturnSeries();
        IndexKey indexKey = new IndexKey();
        if(token.length>2) {
            if (token[0] != null && token[0].trim().length() > 0) {
                returnSeries.setCode(token[0].trim());
                indexKey.setCode(returnSeries.getCode());
            }
            if (token[1] != null && token[1].trim().length() > 0) {
                returnSeries.setDate(convertStringToDate(token[1].trim()));
                indexKey.setDate(returnSeries.getDate());
            }

            if (token.length > 2 && token[2] != null && token[2].trim().length() > 0) {
                if (NumberUtils.isNumber(token[2].trim())) {
                    returnSeries.setReturnPercent(Float.parseFloat(token[2].trim()));
                }
            }
        }
        returnSeries.setIndexKey(indexKey);

        return returnSeries;
    };

    public LocalDate convertStringToDate(String seriesDate){

        DateTimeFormatter[] formats =
                    new DateTimeFormatter[] {DateTimeFormatter.ofPattern("yyyy-MM-dd"),
                            DateTimeFormatter.ofPattern("dd/MM/yyyy")
                            };
            LocalDate returnDate = null;
            for (DateTimeFormatter dateFormat :formats )
            {
                try {
                    returnDate = LocalDate.parse(seriesDate, dateFormat);

                }catch (DateTimeParseException dtpe){
                     continue;
                }
            }
        return returnDate;
    }

    public ApplicationProperties getApplicationProperties() {
        return applicationProperties;
    }

    public void setApplicationProperties(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }


}
