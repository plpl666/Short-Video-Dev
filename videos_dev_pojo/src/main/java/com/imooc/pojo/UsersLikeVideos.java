package com.imooc.pojo;

import javax.persistence.*;

@Table(name = "users_like_videos")
public class UsersLikeVideos {
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
     * 视频编号
     */
    @Column(name = "video_id")
    private String videoId;

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
}