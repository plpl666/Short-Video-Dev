package com.imooc.pojo;

import javax.persistence.*;

@Table(name = "users_fans")
public class UsersFans {
    /**
     * 编号
     */
    @Id
    private String id;

    /**
     * 用户编号
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 粉丝编号
     */
    @Column(name = "fan_id")
    private String fanId;

    /**
     * 获取编号
     *
     * @return id - 编号
     */
    public String getId() {
        return id;
    }

    /**
     * 设置编号
     *
     * @param id 编号
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取用户编号
     *
     * @return user_id - 用户编号
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户编号
     *
     * @param userId 用户编号
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取粉丝编号
     *
     * @return fan_id - 粉丝编号
     */
    public String getFanId() {
        return fanId;
    }

    /**
     * 设置粉丝编号
     *
     * @param fanId 粉丝编号
     */
    public void setFanId(String fanId) {
        this.fanId = fanId;
    }
}