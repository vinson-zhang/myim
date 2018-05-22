package com.zt.project.im.enumpack;

/**
 * ZhangTao
 * 2018/5/22 21:02
 * Description:好友状态
 */
public enum  FriendStatusEnum {

    APPLYING(1,"申请中"),
    AGREED(2,"已同意"),
    BLACK_LIST(3,"黑名单"),
    DELETE(4,"已删除");

    private Integer status;

    private String desc;

    FriendStatusEnum(Integer status, String desc){
        this.status = status;
        this.desc = desc;
    }

    public Integer getStatus() {
        return status;
    }

    public String getDesc() {
        return desc;
    }
}
