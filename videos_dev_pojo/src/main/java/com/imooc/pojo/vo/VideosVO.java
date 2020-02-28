package com.imooc.pojo.vo;

import java.util.Date;

public class VideosVO {

    /**
     * 用户头像
     */
    private String faceImg;
    /**
     * 用户昵称
     */
    private String nickname;
    /**
     * 编号
     */
    private String id;

    /**
     * 用户编号
     */
    private String userId;

    /**
     * 音频编号
     */
    private String audioId;

    /**
     * 视频描述
     */
    private String videoDesc;

    /**
     * 视频路径
     */
    private String videoPath;

    /**
     * 视频时长(秒)
     */
    private Float videoSeconds;

    /**
     * 视频宽
     */
    private Integer videoWidth;

    /**
     * 视频高
     */
    private Integer videoHeight;

    /**
     * 视频封面
     */
    private String coverPath;

    /**
     * 视频点赞数
     */
    private Integer likeCounts;

    /**
     * 视频状态：1 : 发布。2 : 禁播。
     */
    private Integer status;

    /**
     * 发布时间
     */
    private Date createTime;

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
     * 获取音频编号
     *
     * @return audio_id - 音频编号
     */
    public String getAudioId() {
        return audioId;
    }

    /**
     * 设置音频编号
     *
     * @param audioId 音频编号
     */
    public void setAudioId(String audioId) {
        this.audioId = audioId;
    }

    /**
     * 获取视频描述
     *
     * @return video_desc - 视频描述
     */
    public String getVideoDesc() {
        return videoDesc;
    }

    /**
     * 设置视频描述
     *
     * @param videoDesc 视频描述
     */
    public void setVideoDesc(String videoDesc) {
        this.videoDesc = videoDesc;
    }

    /**
     * 获取视频路径
     *
     * @return video_path - 视频路径
     */
    public String getVideoPath() {
        return videoPath;
    }

    /**
     * 设置视频路径
     *
     * @param videoPath 视频路径
     */
    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    /**
     * 获取视频时长(秒)
     *
     * @return video_seconds - 视频时长(秒)
     */
    public Float getVideoSeconds() {
        return videoSeconds;
    }

    /**
     * 设置视频时长(秒)
     *
     * @param videoSeconds 视频时长(秒)
     */
    public void setVideoSeconds(Float videoSeconds) {
        this.videoSeconds = videoSeconds;
    }

    /**
     * 获取视频宽
     *
     * @return video_width - 视频宽
     */
    public Integer getVideoWidth() {
        return videoWidth;
    }

    /**
     * 设置视频宽
     *
     * @param videoWidth 视频宽
     */
    public void setVideoWidth(Integer videoWidth) {
        this.videoWidth = videoWidth;
    }

    /**
     * 获取视频高
     *
     * @return video_height - 视频高
     */
    public Integer getVideoHeight() {
        return videoHeight;
    }

    /**
     * 设置视频高
     *
     * @param videoHeight 视频高
     */
    public void setVideoHeight(Integer videoHeight) {
        this.videoHeight = videoHeight;
    }

    /**
     * 获取视频封面
     *
     * @return cover_path - 视频封面
     */
    public String getCoverPath() {
        return coverPath;
    }

    /**
     * 设置视频封面
     *
     * @param coverPath 视频封面
     */
    public void setCoverPath(String coverPath) {
        this.coverPath = coverPath;
    }

    /**
     * 获取视频点赞数
     *
     * @return like_counts - 视频点赞数
     */
    public Integer getLikeCounts() {
        return likeCounts;
    }

    /**
     * 设置视频点赞数
     *
     * @param likeCounts 视频点赞数
     */
    public void setLikeCounts(Integer likeCounts) {
        this.likeCounts = likeCounts;
    }

    /**
     * 获取视频状态：1 : 发布。2 : 禁播。
     *
     * @return status - 视频状态：1 : 发布。2 : 禁播。
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置视频状态：1 : 发布。2 : 禁播。
     *
     * @param status 视频状态：1 : 发布。2 : 禁播。
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取发布时间
     *
     * @return create_time - 发布时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置发布时间
     *
     * @param createTime 发布时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}