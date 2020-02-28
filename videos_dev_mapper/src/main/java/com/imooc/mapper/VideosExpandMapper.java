package com.imooc.mapper;

import com.imooc.pojo.Videos;
import com.imooc.pojo.vo.VideosVO;
import com.imooc.utils.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VideosExpandMapper extends MyMapper<Videos>{

    /**
     * @Description: 根据视频描述查询视频(模糊查询)
     * @param videoDesc 视频描述
     * @return 关联查询的视频list
     */
    List<VideosVO> queryAllVideos(@Param("videoDesc") String videoDesc, @Param("userId") String userId);

    /**
     * @Description: 对视频喜欢的数量进行累加
     */
    void addVideoLikeCount(String videoId);

    /**
     * @Description: 对视频喜欢的数量进行累减
     */
    void reduceVideoLikeCount(String videoId);

    /**
     * @Description: 查询用户喜欢的视频
     */
    List<VideosVO> queryLikeVideos(String userId);

}
