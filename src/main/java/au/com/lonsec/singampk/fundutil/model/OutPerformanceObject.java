package au.com.lonsec.singampk.fundutil.model;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.Date;

/**
 * This object is the object output to the file
 *
 * Created by ksingamp on 17/02/2017.
 */
@Entity
public class OutPerformanceObject {

    private IndexKey indexKey;
    private String fundName;
    private LocalDate date;
    private float excess;
    private String outPerformance;
    private float returnPercent;
    private Integer rank;

    public OutPerformanceObject(
            String fundName,
            LocalDate date,
            float excess,
            String outPerformance,
            float returnPercent,
            Integer rank,
            IndexKey indexKey){

        this.fundName = fundName;
        this.date = date;
        this.excess = excess;
        this.outPerformance = outPerformance;
        this.returnPercent = returnPercent;
        this.rank = rank;
        this.indexKey = indexKey;
    }

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public float getExcess() {
        return excess;
    }

    public void setExcess(float excess) {
        this.excess = excess;
    }

    public String getOutPerformance() {
        return outPerformance;
    }

    public void setOutPerformance(String outPerformance) {
        this.outPerformance = outPerformance;
    }

    public float getReturnPercent() {
        return returnPercent;
    }

    public void setReturnPercent(float returnPercent) {
        this.returnPercent = returnPercent;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public IndexKey getIndexKey() {
        return indexKey;
    }

    public void setIndexKey(IndexKey indexKey) {
        this.indexKey = indexKey;
    }

    /**
     * For better logging
     * @return
     */
    @Override
    public String toString(){
            StringBuffer returnString = new StringBuffer();
            returnString.append(" Index Key: "+ this.indexKey);
            if(rank != null){
                returnString.append(" FundName: "+ this.fundName);
            }

            returnString.append(" Date: "+ this.date);
            returnString.append(" ReturnPercent: "+ this.returnPercent );
            returnString.append(" Excess: "+ String.valueOf(this.excess));

            if(rank != null){
                returnString.append("Rank: " + this.rank);
            }
        return returnString.toString();
    }
}
