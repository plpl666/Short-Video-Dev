package com.imooc.pojo.vo;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

public class CommentsVO {


    /**
     * 用户头像
     */
    private String faceImg;
    /**
     * 用户昵称
     */
    private String nickname;
    /**
     * 被回复用户昵称
     */
    private String toNickname;
    /**

     * 编号
     */
    private String id;

    /**
     * 视频编号
     */
    private String videoId;

    /**
     * 留言用户编号
     */
    private String fromUserId;
    /**
     * 父评论编号
     */
    private String fatherCommentId;

    /**
     * 被回复用户编号
     */
    private String toUserId;

    /**
     * 留言时间
     */
    private Date createTime;

    /**
     * 格式化留言时间
     */
    private String timeAgo;

    /**
     * 留言内容
     */
    private String comment;

    public String getFaceImg() {
        return faceImg;
    }

    public void setFaceImg(String faceImg) {
        this.faceImg = faceImg;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getToNickname() {
        return toNickname;
    }

    public void setToNickname(String toNickname) {
        this.toNickname = toNickname;
    }
    public String getTimeAgo() {
        return timeAgo;
    }

    public void setTimeAgo(String timeAgo) {
        this.timeAgo = timeAgo;
    }

    public String getFatherCommentId() {
        return fatherCommentId;
    }

    public void setFatherCommentId(String fatherCommentId) {
        this.fatherCommentId = fatherCommentId;
    }

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

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