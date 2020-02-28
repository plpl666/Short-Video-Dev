package com.imooc.pojo;

import java.util.Date;
import javax.persistence.*;

public class Comments {
    /**
     * 编号
     */
    @Id
    private String id;

    /**
     * 视频编号
     */
    @Column(name = "video_id")
    private String videoId;

    /**
     * 留言用户编号
     */
    @Column(name = "from_user_id")
    private String fromUserId;

    /**
     * 父评论编号
     */
    @Column(name = "father_comment_id")
    private String fatherCommentId;

    /**
     * 被回复用户编号
     */
    @Column(name = "to_user_id")
    private String toUserId;

    /**
     * 留言时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 留言内容
     */
    private String comment;

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
     * 获取视频编号
     *
     * @return video_id - 视频编号
     */
    public String getVideoId() {
        return videoId;
    }

    /**
     * 设置视频编号
     *
     * @param videoId 视频编号
     */
    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    /**
     * 获取留言用户编号
     *
     * @return from_user_id - 留言用户编号
     */
    public String getFromUserId() {
        return fromUserId;
    }

    /**
     * 设置留言用户编号
     *
     * @param fromUserId 留言用户编号
     */
    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    /**
     * 获取父评论编号
     *
     * @return father_comment_id - 父评论编号
     */
    public String getFatherCommentId() {
        return fatherCommentId;
    }

    /**
     * 设置父评论编号
     *
     * @param fatherCommentId 父评论编号
     */
    public void setFatherCommentId(String fatherCommentId) {
        this.fatherCommentId = fatherCommentId;
    }

    /**
     * 获取被回复用户编号
     *
     * @return to_user_id - 被回复用户编号
     */
    public String getToUserId() {
        return toUserId;
    }

    /**
     * 设置被回复用户编号
     *
     * @param toUserId 被回复用户编号
     */
    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    /**
     * 获取留言时间
     *
     * @return create_time - 留言时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置留言时间
     *
     * @param createTime 留言时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取留言内容
     *
     * @return comment - 留言内容
     */
    public String getComment() {
        return comment;
    }

    /**
     * 设置留言内容
     *
     * @param comment 留言内容
     */
    public void setComment(String comment) {
        this.comment = comment;
    }
}