package au.com.lonsec.singampk.fundutil.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

/**
 * This is used as key for hashmap
 *
 * Created by ksingamp on 18/02/2017.
 */
public class IndexKey {
    private String code;
    private LocalDate date;

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

    public IndexKey(){

    }
    public IndexKey(String code, LocalDate date){
        this.code =code;
        this.date = date;
    }

    /**
     * Overrided the equals to compare the keys
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof IndexKey)) {
            return false;
        }

        IndexKey indexKey = (IndexKey) o;
        if(indexKey.getCode()!=null) {
            if (!Objects.equals(indexKey.getCode(), this.code)) {
                return false;
            }
            if (indexKey.getDate() != null && this.date !=null) {
                if(indexKey.getDate().getMonth()== this.date.getMonth()
                        && indexKey.getDate().getDayOfMonth()== this.date.getDayOfMonth()
                        && indexKey.getDate().getYear()== this.date.getYear()
                        ){
                    return true;
                }

            }
        }
        return false;
    }

    /**
     * To get the hascode
     * @return int
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(code)
                .append(date)
                .toHashCode();
    }
    @Override
    public String toString(){
        return "KEY:: '"+code +","+date+"'";
    }
}
