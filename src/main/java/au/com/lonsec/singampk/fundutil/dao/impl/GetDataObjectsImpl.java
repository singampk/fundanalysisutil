package au.com.lonsec.singampk.fundutil.dao.impl;

import au.com.lonsec.singampk.fundutil.dao.GetDataObjects;
import au.com.lonsec.singampk.fundutil.model.DataObject;
import au.com.lonsec.singampk.fundutil.model.IndexKey;
import au.com.lonsec.singampk.fundutil.model.PropertyConstants;
import au.com.lonsec.singampk.fundutil.model.ReturnSeries;
import au.com.lonsec.singampk.fundutil.utils.ApplicationContextProvider;
import au.com.lonsec.singampk.fundutil.utils.LoadCSVFiles;

import java.util.Map;

/**
 * This is an implementation of GetDataObjects
 *
 * Created by ksingamp on 16/02/2017.
 */
public class GetDataObjectsImpl implements GetDataObjects{


    LoadCSVFiles loadCSVFiles = ApplicationContextProvider.getInstance().getApplicationContext().getBean(PropertyConstants.CONST_LOADCSVFILES, LoadCSVFiles.class);

    /**
     * To load the data Objects to map
     * @param path
     * @return Map
     */

    @Override
    public Map<String,DataObject> loadDataObject(String path) {
        return loadCSVFiles.loadInputFile(path);
    }

    /**
     * To load return series to map
     *
     * @param path
     * @return Map
     */
    @Override
    public Map<IndexKey,ReturnSeries> loadReturnSeries(String path) {
        return loadCSVFiles.loadInputReturnFile(path);
    }


}
