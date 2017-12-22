package com.zng.common.entity;

/**
 * Created by John.Zhang on 2017/12/22.
 */
public enum ResponseCode {

    Sucess(200),Error(500),NotFound(404),NoAuth(401);

    private Integer code;

    ResponseCode(Integer code){
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

}
