package com.imooc.service;

import com.imooc.pojo.Comments;
import com.imooc.pojo.Videos;
import com.imooc.pojo.vo.VideosVO;
import com.imooc.utils.PagedResult;

import java.util.List;

public interface VideoService {

    /**
     * @Description: 分页查询视频列表
     */
    PagedResult<VideosVO> getAllVideos(Videos video, Integer isSave, Integer page, Integer pageSize);

    /**
     * @Description: 保存视频
     */
    String saveVideo(Videos video);

    /**
     * @Description: 修改视频封面
     */
    void updateVideo(String videoId, String coverPath);

    /**
     * @Description: 返回热搜词列表
     */
    List<String> getHotWords();

    /**
     * @Description: 用户喜欢视频
     */
    void userLikeVideo(String userId, String videoId, String videoCreatorId);

    /**
     * @Description: 用户取消喜欢视频
     */
    void userUnLikeVideo(String userId, String videoId, String videoCreatorId);

    /**
     * @Description: 分页查询用户的视频
     */
    PagedResult<VideosVO> getAllUserVideos(String userId, Integer page, Integer pageSize);

    /**
     * @Description: 分页查询用户喜欢的视频
     */
    PagedResult<VideosVO> getAllLikeVideos(String userId, Integer page, Integer pageSize);

    /**
     * @Description: 保存留言
     */
    void saveComment(Comments comment);

    /**
     * @Description: 获取视频留言
     */
    PagedResult<Comments> getVideoComments(String videoId, Integer page, Integer pageSize);

    /**
     * @Description: 获取视频留言数量
     */
    Integer getVideoCommentCounts(String videoId);
}
