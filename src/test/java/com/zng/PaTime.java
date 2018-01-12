package com.zng;

import java.util.Date;

/**
 * Created by John.Zhang on 2018/1/8.
 */
public class PaTime {

    private Date startTime;
    private Date endTime;
    private String type;

    public PaTime (Date startTime,Date endTime,String type){
        this.startTime = startTime;
        this.endTime = endTime;
        this.type = type;
    }


    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
