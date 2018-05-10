package com.zt.project.im.util;

/**
 * ZhangTao
 * 2018/5/10 22:30
 * Description:
 */
public class ResponseInfo<T> {

    private String code;

    private String desc;

    private T result;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
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
