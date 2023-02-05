package com.xyz.store.service.util;

import lombok.Data;

import java.io.Serializable;

/**
 *json状态，响应结果，响应码
 */
@Data
public class JsonResult<E> implements Serializable {
    /** 状态码 **/
    private Integer state;
    /** 描述信息**/
    private String message;

    /** 数据*/
    private E data;

    public JsonResult(Throwable e) {
        this.message = e.getMessage();
    }

    public JsonResult(Integer state,E data) {
        this.state = state;
        this.data=data;
    }


    public JsonResult() {

    }
}
