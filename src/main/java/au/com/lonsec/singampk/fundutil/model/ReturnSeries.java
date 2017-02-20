package au.com.lonsec.singampk.fundutil.model;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.Date;

/**
 * This object holds Return series object
 *
 * Created by ksingamp on 16/02/2017.
 */
@Entity
public class ReturnSeries {

    private IndexKey indexKey;
    private String code;
    private LocalDate date;
    private float returnPercent;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public float getReturnPercent() {
        return returnPercent;
    }

    public void setReturnPercent(float returnPercent) {
        this.returnPercent = returnPercent;
    }

    public IndexKey getIndexKey() {
        return indexKey;
    }

    public void setIndexKey(IndexKey indexKey) {
        this.indexKey = indexKey;
    }
}
