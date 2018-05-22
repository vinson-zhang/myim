package com.zt.project.im.bean;

import java.util.Date;

/**
 * ZhangTao
 * 2018/5/22 20:34
 * Description:
 */
public class Friend {

    private Integer id;

    private Integer userId;

    private Integer friendId;

    //1：申请中 2：同意 3：拉黑 4：删除
    private Integer status;

    //创建时间
    private Date createTime;

    //修改时间
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getFriendId() {
        return friendId;
    }

    public void setFriendId(Integer friendId) {
        this.friendId = friendId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
