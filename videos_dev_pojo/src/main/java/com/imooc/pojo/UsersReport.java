package com.imooc.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "users_report")
public class UsersReport {
    @Id
    private String id;

    /**
     * 被举报用户编号
     */
    @Column(name = "deal_user_id")
    private String dealUserId;

    /**
     * 被举报视频编号
     */
    @Column(name = "deal_video_id")
    private String dealVideoId;

    /**
     * 举报标题
     */
    private String title;

    /**
     * 具体描述
     */
    private String content;

    /**
     * 举报用户编号
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 举报时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取被举报用户编号
     *
     * @return deal_user_id - 被举报用户编号
     */
    public String getDealUserId() {
        return dealUserId;
    }

    /**
     * 设置被举报用户编号
     *
     * @param dealUserId 被举报用户编号
     */
    public void setDealUserId(String dealUserId) {
        this.dealUserId = dealUserId;
    }

    /**
     * 获取被举报视频编号
     *
     * @return deal_video_id - 被举报视频编号
     */
    public String getDealVideoId() {
        return dealVideoId;
    }

    /**
     * 设置被举报视频编号
     *
     * @param dealVideoId 被举报视频编号
     */
    public void setDealVideoId(String dealVideoId) {
        this.dealVideoId = dealVideoId;
    }

    /**
     * 获取举报标题
     *
     * @return title - 举报标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置举报标题
     *
     * @param title 举报标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取具体描述
     *
     * @return content - 具体描述
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置具体描述
     *
     * @param content 具体描述
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取举报用户编号
     *
     * @return user_id - 举报用户编号
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置举报用户编号
     *
     * @param userId 举报用户编号
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取举报时间
     *
     * @return create_time - 举报时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置举报时间
     *
     * @param createTime 举报时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}