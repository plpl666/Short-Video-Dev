package com.imooc.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Table(name = "users")
@ApiModel(value = "用户对象", description = "这是用户对象")
public class Users {
    /**
     * 编号
     */
    @ApiModelProperty(hidden = true)
    @Id
    private String id;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名", name = "username", example = "plpluser", required = true)
    private String username;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码", name = "password", example = "123456", required = true)
    private String password;

    /**
     * 头像
     */
    @ApiModelProperty(hidden = true)
    @Column(name = "face_img")
    private String faceImg;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 粉丝数
     */
    @ApiModelProperty(hidden = true)
    @Column(name = "fans_counts")
    private Integer fansCounts;

    /**
     * 关注数
     */
    @ApiModelProperty(hidden = true)
    @Column(name = "follow_counts")
    private Integer followCounts;

    /**
     * 点赞数
     */
    @ApiModelProperty(hidden = true)
    @Column(name = "receive_like_counts")
    private Integer receiveLikeCounts;

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
     * 获取用户名
     *
     * @return username - 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名
     *
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取头像
     *
     * @return face_img - 头像
     */
    public String getFaceImg() {
        return faceImg;
    }

    /**
     * 设置头像
     *
     * @param faceImg 头像
     */
    public void setFaceImg(String faceImg) {
        this.faceImg = faceImg;
    }

    /**
     * 获取昵称
     *
     * @return nickname - 昵称
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 设置昵称
     *
     * @param nickname 昵称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 获取粉丝数
     *
     * @return fans_counts - 粉丝数
     */
    public Integer getFansCounts() {
        return fansCounts;
    }

    /**
     * 设置粉丝数
     *
     * @param fansCounts 粉丝数
     */
    public void setFansCounts(Integer fansCounts) {
        this.fansCounts = fansCounts;
    }

    /**
     * 获取关注数
     *
     * @return follow_counts - 关注数
     */
    public Integer getFollowCounts() {
        return followCounts;
    }

    /**
     * 设置关注数
     *
     * @param followCounts 关注数
     */
    public void setFollowCounts(Integer followCounts) {
        this.followCounts = followCounts;
    }

    /**
     * 获取点赞数
     *
     * @return receive_like_counts - 点赞数
     */
    public Integer getReceiveLikeCounts() {
        return receiveLikeCounts;
    }

    /**
     * 设置点赞数
     *
     * @param receiveLikeCounts 点赞数
     */
    public void setReceiveLikeCounts(Integer receiveLikeCounts) {
        this.receiveLikeCounts = receiveLikeCounts;
    }
}