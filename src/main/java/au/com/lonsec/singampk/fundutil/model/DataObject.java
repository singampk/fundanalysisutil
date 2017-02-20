package au.com.lonsec.singampk.fundutil.model;

import javax.persistence.Entity;
/**
 * This model to hold the Data Object (Fund and Benchmark)
 *
 * Created by ksingamp on 16/02/2017.
 */
@Entity
public class DataObject {

    private String name;
    private String code;
    private String benchmarkCode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBenchmarkCode() {
        return benchmarkCode;
    }

    public void setBenchmarkCode(String benchmarkCode) {
        this.benchmarkCode = benchmarkCode;
    }
}
