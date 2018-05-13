package com.zt.project.im.enumpack;

/**
 * 错误码
 */
public enum ErrorCodeEnum {

    SUCCESS(30100,"成功"),
    OTHER_ERROR(30101,"其他错误"),
    NOT_LOGIN(30104,"请登录"),
    UNKNOWN_ACCOUNT(30105,"未知账户"),
    PASSWORD_WROMG(30106,"密码错误")
    ;

    private Integer code;

    private String desc;

    ErrorCodeEnum(Integer code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
