package au.com.lonsec.singampk.fundutil.businessunit.impl;

import au.com.lonsec.singampk.fundutil.businessunit.GenerateOutperformanceReport;
import au.com.lonsec.singampk.fundutil.dao.impl.GetDataObjectsImpl;
import au.com.lonsec.singampk.fundutil.model.*;
import au.com.lonsec.singampk.fundutil.utils.ApplicationProperties;
import au.com.lonsec.singampk.fundutil.utils.LoadCSVFiles;
import au.com.lonsec.singampk.fundutil.utils.SortUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * This class is responsible for business logic implementation,
 * This class is an implementation of GenerateOutperformanceReport
 *
 * Created by ksingamp on 17/02/2017.
 */
public class GenerateOutperformanceReportImpl implements GenerateOutperformanceReport {

    final Logger logger = Logger.getLogger(GenerateOutperformanceReportImpl.class);
    @Autowired
    private ApplicationProperties applicationProperties;

    @Autowired
    private GetDataObjectsImpl getDataObjects;

    /**
     * This method is responsible for calling DAO to load the required objects
     * and populate the OutPerformanceObject based on the values
     *
     * @param inputParams
     * @return Map of IndexKey as key and OutPerformanceObject as values
     */
    @Override
    public Map<IndexKey,OutPerformanceObject> getOutPerformanceReport(InputParams inputParams) {
        Map<IndexKey,OutPerformanceObject> outPerformanceObjectMap = new HashMap<>();
        Map<String, DataObject> fundMap = getDataObjects.loadDataObject(inputParams.getFundCSVPath());
        Map<String, DataObject> benchmarkMap = getDataObjects.loadDataObject(inputParams.getBenchmarkCSVPath());
        Map<IndexKey, ReturnSeries> fundReturnSeriesMap = getDataObjects.loadReturnSeries(inputParams.getReturnFundSeriesPath());
        Map<IndexKey, ReturnSeries> benchmarkReturnSeriesMap = getDataObjects.loadReturnSeries(inputParams.getReturnBenchmarkSeriesPath());
        populateOutPerformanceObjects(fundMap,benchmarkMap,fundReturnSeriesMap,benchmarkReturnSeriesMap,outPerformanceObjectMap);
        return outPerformanceObjectMap;
    }

    /**
     * This method is responsible to write File to disk
     * based on inputParams (OutputPath) and Map of out Performance Objects
     *
     * @param inputParams
     * @param outPerformanceObjectMap
     */
    @Override
    public void writeFile(InputParams inputParams, Map<IndexKey, OutPerformanceObject> outPerformanceObjectMap) {
        List<String> finalListToWrite = SortUtils.sortMapBasedOnValues(outPerformanceObjectMap);

        try {
            Files.write(Paths.get(!StringUtils.isEmpty(inputParams.getOutputFilePath())?inputParams.getOutputFilePath():applicationProperties.getProperty(PropertyConstants.CONST_OUTPUTFILE))
                    , finalListToWrite);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method will perform all the business logic and load the OutPerformanceObject
     *
     * @param fundMap
     * @param benchmarkMap
     * @param fundReturnSeriesMap
     * @param benchmarkReturnSeriesMap
     * @param outPOMap
     */
    private void populateOutPerformanceObjects(Map<String, DataObject> fundMap, Map<String, DataObject> benchmarkMap, Map<IndexKey, ReturnSeries> fundReturnSeriesMap, Map<IndexKey, ReturnSeries> benchmarkReturnSeriesMap, Map<IndexKey, OutPerformanceObject> outPOMap) {

        fundReturnSeriesMap.forEach((k,v)-> {
            IndexKey indexKey = (IndexKey) k;
            ReturnSeries fundReturnSeries = (ReturnSeries) v;
            if (benchmarkReturnSeriesMap.containsKey(new IndexKey(fundMap.get(fundReturnSeries.getCode())
                    .getBenchmarkCode(),fundReturnSeries.getDate()))){
                float benchmarkPercent = benchmarkReturnSeriesMap.get(new IndexKey(fundMap.get(fundReturnSeries.getCode())
                        .getBenchmarkCode(), fundReturnSeries.getDate())).getReturnPercent();
            float excess = fundReturnSeries.getReturnPercent() - benchmarkPercent;

            String outPerformed = getOutPerformed(excess);
            outPOMap.put(indexKey, (new OutPerformanceObject(fundMap.get(fundReturnSeries.getCode()).getName(),
                    fundReturnSeries.getDate(),
                    excess,
                    outPerformed,
                    fundReturnSeries.getReturnPercent(),
                    0,
                    indexKey)));
        }else{
            logger.error("Looks like some of benchmark or fund return series values aren't right, please verify the files :: Fund and date:: " +indexKey.toString());
            }
        });

    }

    /**
     * This method is to calculate the outperformed or underperformed value
     * This method will do some basic calculations that are required that the
     * fund is outperformed or underperformed
     *
     * @param excess
     * @return String based value that is configured in the property file
     */
    private String getOutPerformed(float excess) {
        String upperNlowerLimits  = applicationProperties.getProperty(PropertyConstants.CONST_UPPERNLOWERLIMITS);
        StringTokenizer stringTokenizer = new StringTokenizer(upperNlowerLimits,
                applicationProperties.getProperty(PropertyConstants.CONST_DELIMITER));
        int upperLimit = 0;
        int lowerLimit = 0;
        while(stringTokenizer.countTokens()==2 && stringTokenizer.hasMoreTokens()){
            int limit1 = Integer.parseInt(stringTokenizer.nextToken());
            int limit2 = Integer.parseInt(stringTokenizer.nextToken());
            upperLimit = limit1 > limit2? limit1 : limit2;
            lowerLimit = limit1 < limit2? limit1 : limit2;

        }
        if(excess > upperLimit){
            return applicationProperties.getProperty(String.valueOf(upperLimit));
        }else if(excess < lowerLimit){
            return applicationProperties.getProperty(String.valueOf(lowerLimit));
        }else {
            return applicationProperties.getProperty(PropertyConstants.CONST_DEFAULT_PERFORMANCE);
        }
    }
}
