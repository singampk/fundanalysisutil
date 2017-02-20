package au.com.lonsec.singampk.fundutil.dao;

import au.com.lonsec.singampk.fundutil.model.DataObject;
import au.com.lonsec.singampk.fundutil.model.IndexKey;
import au.com.lonsec.singampk.fundutil.model.ReturnSeries;

import java.util.Map;

/**
 * Interface to handle to load data Objects and return serires
 *
 * Created by ksingamp on 16/02/2017.
 */
public interface GetDataObjects {

    Map<String,DataObject> loadDataObject(String path);
    Map<IndexKey,ReturnSeries> loadReturnSeries(String path);

}
