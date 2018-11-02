package com.zng.system.auth.dto;

/**
 * @author John.Zhang
 * @version 1.0
 * @date 2018/11/2
 */
public class QRLoginModel {

    private Integer code;

    private String msg;

    private Object value;

    public QRLoginModel(){

    }

    public QRLoginModel(Integer code,String msg,Object value){
        this.code = code;
        this.msg = msg;
        this.value = value;
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
}
