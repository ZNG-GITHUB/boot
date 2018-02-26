package com.zng.common.entity;

/**
 * Created by John.Zhang on 2017/12/22.
 */
public class ResponseModel{

    /**
     *响应编码
     */
    private Integer code;

    /**
     * 响应状态
     */
    private String status;

    /**
     * 反馈信息
     */
    private String message;

    /**
     * 返回数据
     */
    private Object data;

    /**
     * 异常名称
     */
    private String exceptionName;


    public ResponseModel (){
        this(ResponseCode.Sucess,null,null,null);
    }

    public ResponseModel (Object data){
        this(ResponseCode.Sucess,null,data,null);
    }

    public ResponseModel (ResponseCode responseCode,String message){
        this(responseCode,message,null,null);
    }

    public ResponseModel (ResponseCode responseCode,String message,Object data){
        this(responseCode,message,data,null);
    }

    public ResponseModel (ResponseCode responseCode,String message,Object data,Exception e){
        this.code = responseCode.getCode();
        this.status = responseCode.name();
        this.message = message;
        this.data = data;
        if(e != null){
            this.exceptionName = e.getClass().getSimpleName();
        }
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getExceptionName() {
        return exceptionName;
    }

    public void setExceptionName(String exceptionName) {
        this.exceptionName = exceptionName;
    }
}
