package com.zng.system.auth.dto;

import java.util.Date;

/**
 * @author John.Zhang
 * @version 1.0
 * @date 2018/11/2
 */
public class QRLoginModel {

    private Integer code;

    private String msg;

    private Object value;

    private Date buildTime = new Date();

    private Integer active = 10;

    public QRLoginModel(){

    }

    public QRLoginModel(Integer code,String msg,Object value){
        this.code = code;
        this.msg = msg;
        this.value = value;
    }

    public QRLoginModel(Integer code,String msg,Object value,Integer active){
        this.code = code;
        this.msg = msg;
        this.value = value;
        this.active = active;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Date getBuildTime() {
        return buildTime;
    }

    public void setBuildTime(Date buildTime) {
        this.buildTime = buildTime;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }
}
