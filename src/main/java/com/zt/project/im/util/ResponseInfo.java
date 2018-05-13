package com.zt.project.im.util;

import com.zt.project.im.enumpack.ErrorCodeEnum;

/**
 * ZhangTao
 * 2018/5/10 22:30
 * Description:
 */
public class ResponseInfo<T> {

    private Integer code = ErrorCodeEnum.SUCCESS.getCode();

    private String desc = ErrorCodeEnum.SUCCESS.getDesc();

    private T result;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
