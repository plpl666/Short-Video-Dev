package com.imooc.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.imooc.mapper.*;
import com.imooc.pojo.Comments;
import com.imooc.pojo.SearchRecords;
import com.imooc.pojo.UsersLikeVideos;
import com.imooc.pojo.Videos;
import com.imooc.pojo.vo.CommentsVO;
import com.imooc.pojo.vo.VideosVO;
import com.imooc.service.VideoService;
import com.imooc.utils.PagedResult;
import com.imooc.utils.TimeAgoUtils;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {

    @Resource
    private VideosMapper videosMapper;

    @Resource
    private VideosExpandMapper videosExpandMapper;

    @Resource
    private SearchRecordsMapper searchRecordsMapper;

    @Resource
    private UsersLikeVideosMapper usersLikeVideosMapper;

    @Resource
    private UsersMapper usersMapper;

    @Resource
    private CommentsMapper commentsMapper;

    @Resource
    private  CommentsExpandMapper commentsExpandMapper;

    @Autowired
    private Sid sid;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void userLikeVideo(String userId, String videoId, String videoCreatorId) {
        //1.保存用户和视频的喜欢点赞关联关系表
        String likeId = sid.nextShort();
        UsersLikeVideos ulv = new UsersLikeVideos();
        ulv.setId(likeId);
        ulv.setUserId(userId);
        ulv.setVideoId(videoId);
        usersLikeVideosMapper.insert(ulv);
        //2.视频喜欢数量累加
        videosExpandMapper.addVideoLikeCount(videoId);
        //3.用户受喜欢数量累加
        usersMapper.addReceiveLikeCount(videoCreatorId);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void userUnLikeVideo(String userId, String videoId, String videoCreatorId) {
        //1.删除用户和视频的喜欢点赞关联关系表
        Example example = new Example(UsersLikeVideos.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId",userId);
        criteria.andEqualTo("videoId",videoId);
        usersLikeVideosMapper.deleteByExample(example);
        //2.视频喜欢数量累减
        videosExpandMapper.reduceVideoLikeCount(videoId);
        //3.用户受喜欢数量累减
        usersMapper.reduceReceiveLikeCount(videoCreatorId);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<String> getHotWords() {
        return searchRecordsMapper.selectHotWords();
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedResult<VideosVO> getAllUserVideos(String userId, Integer page, Integer pageSize) {
        Page<VideosVO> pages = PageHelper.startPage(page,pageSize);
        videosExpandMapper.queryAllVideos(null,userId);
        PageInfo<VideosVO> pageList = pages.toPageInfo();
        return new PagedResult<>(pageList.getPageNum(), pageList.getPages(), pageList.getTotal(), pageList.getList());
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedResult<Comments> getVideoComments(String videoId, Integer page, Integer pageSize) {

        Page<Comments> pages = PageHelper.startPage(page, pageSize);
        List<CommentsVO> list = commentsExpandMapper.queryComments(videoId);
        for (CommentsVO c : list) {
            c.setTimeAgo(TimeAgoUtils.format(c.getCreateTime()));
        }
        PageInfo<Comments> pageList = pages.toPageInfo();
        return new PagedResult<>(pageList.getPageNum(), pageList.getPages(), pageList.getTotal(), pageList.getList());
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Integer getVideoCommentCounts(String videoId) {
        Example example = new Example(Comments.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("videoId",videoId);
        commentsMapper.selectCountByExample(example);
        return commentsMapper.selectCountByExample(example);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void saveComment(Comments comment) {
        comment.setId(sid.nextShort());
        comment.setCreateTime(new Date());
        commentsMapper.insert(comment);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedResult<VideosVO> getAllLikeVideos(String userId, Integer page, Integer pageSize) {
        Page<VideosVO> pages = PageHelper.startPage(page,pageSize);
        videosExpandMapper.queryLikeVideos(userId);
        PageInfo<VideosVO> pageList = pages.toPageInfo();
        return new PagedResult<>(pageList.getPageNum(), pageList.getPages(), pageList.getTotal(), pageList.getList());
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public PagedResult<VideosVO> getAllVideos(Videos video, Integer isSave, Integer page, Integer pageSize) {
        String desc = video.getVideoDesc();
        //保存热搜词
        if (isSave != null && isSave == 1) {
            SearchRecords searchRecord = new SearchRecords();
            searchRecord.setId(sid.nextShort());
            searchRecord.setContent(desc);
            searchRecordsMapper.insert(searchRecord);
        }
        Page<VideosVO> pages = PageHelper.startPage(page,pageSize);
        videosExpandMapper.queryAllVideos(desc,null);
        PageInfo<VideosVO> pageList = pages.toPageInfo();
        return new PagedResult<>(pageList.getPageNum(), pageList.getPages(), pageList.getTotal(), pageList.getList());
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updateVideo(String videoId, String coverPath) {
        Videos video = new Videos();
        video.setId(videoId);
        System.out.println(coverPath);
        video.setCoverPath(coverPath);
        videosMapper.updateByPrimaryKeySelective(video);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public String saveVideo(Videos video) {
        String id = sid.nextShort();
        video.setId(id);
        videosMapper.insertSelective(video);
        return id;
    }
}
