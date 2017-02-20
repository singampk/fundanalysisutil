package au.com.lonsec.singampk.fundutil.businessunit;

import au.com.lonsec.singampk.fundutil.model.IndexKey;
import au.com.lonsec.singampk.fundutil.model.InputParams;
import au.com.lonsec.singampk.fundutil.model.OutPerformanceObject;

import java.util.Map;

/**
 * Interface to handle GenerateOutperformance report
 *
 * Created by ksingamp on 17/02/2017.
 */
public interface GenerateOutperformanceReport {

   Map<IndexKey, OutPerformanceObject> getOutPerformanceReport(InputParams inputParams);

   void writeFile(InputParams inputParams, Map<IndexKey, OutPerformanceObject> outPerformanceObjectMap);


}
