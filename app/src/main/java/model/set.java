package model;

import java.io.Serializable;

/**
 * Created by Moein on 7/7/2017.
 */

public class set implements Serializable {

    private static final long SerialUI=10L;
    private String date;
    private int Times;
    private int setId;

//    public set(String date, int times,int Id) {
//        this.date = date;
//        Times = times;
//        setId=Id;
//    }

    public int getSetId() {
        return setId;
    }

    public void setSetId(int setId) {
        this.setId = setId;
    }

    public static long getSerialUI() {
        return SerialUI;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTimes() {
        return Times;
    }

    public void setTimes(int times) {
        Times = times;
    }
}
