package com.imooc.controller;


import com.imooc.config.ResourceConfig;
import com.imooc.enums.VideoStatus;
import com.imooc.pojo.Bgm;
import com.imooc.pojo.Comments;
import com.imooc.pojo.Videos;
import com.imooc.pojo.vo.VideosVO;
import com.imooc.service.BgmService;
import com.imooc.service.VideoService;
import com.imooc.utils.IMoocJSONResult;
import com.imooc.utils.MergeVideoBgm;
import com.imooc.utils.PagedResult;
import io.swagger.annotations.*;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.util.Date;
import java.util.UUID;

@RestController
@Api(value = "视频相关业务的接口", tags = {"视频相关业务的Controller"})
@RequestMapping("/videos")
public class VideosController {

    @Autowired
    ResourceConfig resourceConfig;

    @Resource
    private BgmService bgmService;

    @Resource
    private VideoService videoService;

    @ApiOperation(value = "用户上传视频", notes = "用户上传视频的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true,
                    dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "bgmId", value = "背景音乐ID", required = false,
                    dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "desc", value = "视频描述", required = false,
                    dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "videoSeconds", value = "视频播放时间" , required = true,
                    dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "videoWidth", value = "视频宽度", required = true,
                    dataType = "int", paramType = "form"),
            @ApiImplicitParam(name = "videoHeight", value = "视频高度", required = true,
                    dataType = "int", paramType = "form")
    })
    @PostMapping(value = "/uploadVideo", headers = "content-type=multipart/form-data")
    public IMoocJSONResult uploadVideo(String userId,
                                       String bgmId, String desc,
                                       float videoSeconds, int videoWidth, int videoHeight,
                                       @ApiParam(value = "短视频", required = true)
                                       MultipartFile file) throws IOException {
        if (!StringUtils.isNoneBlank(userId)) {
            return IMoocJSONResult.errorMsg("用户ID不能为空!");
        }
        String fileSpace = resourceConfig.getFileSpace();
        String uploadVideoPath = "/" + userId + "/video/videos";
        String uploadCoverPath = "/" + userId + "/video/covers";
        if (file != null) {
//            String fileName = UUID.randomUUID().toString();
            String fileName = file.getOriginalFilename();
            String coverName = null;
            //文件上传的最终保存路径(绝对路径)
            String finalVideoPath = fileSpace + uploadVideoPath + "/" + fileName;
            File outFile = new File(finalVideoPath);
            if (outFile.getParentFile() == null || !outFile.getParentFile().isDirectory()) {
                outFile.getParentFile().mkdirs();
            }
            FileOutputStream fos = new FileOutputStream(outFile);
            InputStream is = file.getInputStream();
            IOUtils.copy(is, fos);
            fos.flush();
            fos.close();
            is.close();
            if (StringUtils.isNoneBlank(bgmId)) {
                Bgm bgm = bgmService.queryBgmById(bgmId);
                String bgmInputPath = fileSpace + bgm.getPath();
//                String videoOutputPath = finalVideoPath;
                String videoOutputName = UUID.randomUUID().toString() + ".mp4";
                //数据库保存路径
                uploadVideoPath += ("/" + videoOutputName);
                String videoOutputPath = fileSpace + uploadVideoPath;
                MergeVideoBgm.converter(finalVideoPath, bgmInputPath, videoSeconds, videoOutputPath);
                coverName = videoOutputName.substring(0,videoOutputName.lastIndexOf(".")) + ".jpg";
                FileUtils.forceDelete(new File(finalVideoPath));
                finalVideoPath = videoOutputPath;
            } else {
                //数据库保存路径
                uploadVideoPath += ("/" + fileName);
                coverName = fileName.substring(0,fileName.lastIndexOf(".")) + ".jpg";
            }
            String finalCoverPath = fileSpace + uploadCoverPath + "/" + coverName;
            outFile = new File(finalCoverPath);
            if (outFile.getParentFile() == null || !outFile.getParentFile().isDirectory()) {
                outFile.getParentFile().mkdirs();
            }
            MergeVideoBgm.getCover(finalVideoPath,finalCoverPath);
            uploadCoverPath += ("/" + coverName);
            Videos video = new Videos();
            video.setAudioId(bgmId);
            video.setUserId(userId);
            video.setVideoSeconds(videoSeconds);
            video.setVideoWidth(videoWidth);
            video.setVideoHeight(videoHeight);
            video.setCoverPath(uploadCoverPath);
            video.setVideoPath(uploadVideoPath);
            video.setCreateTime(new Date());
            video.setVideoDesc(desc);
            video.setStatus(VideoStatus.SUCCESS.value);
            video.setLikeCounts(0);
            String videoId = videoService.saveVideo(video);
            return IMoocJSONResult.ok(videoId);
        } else {
            return IMoocJSONResult.errorMsg("上传出错...");
        }
    }

    /**
     * @Description: 分页和搜索查询视频列表
     * @param video 视频对象
     * @param isSave 是否保存: 1-需要保存 2-不需要保存,或者为空
     * @param page 当前页
     * @return 分页视频列表
     */
    @PostMapping(value = "/showVideos")
    public IMoocJSONResult showVideos(@RequestBody Videos video, Integer isSave, Integer page) {
        if (page == null) {
            page = 1;
        }
        PagedResult<VideosVO> result = videoService.getAllVideos(video, isSave, page, 6);
        return IMoocJSONResult.ok(result);
    }

    @PostMapping(value = "/hotWords")
    public IMoocJSONResult hotWords() {
        return IMoocJSONResult.ok(videoService.getHotWords());
    }

    @PostMapping(value = "/userLike")
    public IMoocJSONResult userLike(String userId, String videoId, String videoCreatorId) {
        videoService.userLikeVideo(userId,videoId,videoCreatorId);
        return IMoocJSONResult.ok();
    }

    @PostMapping(value = "/userUnLike")
    public IMoocJSONResult userUnLike(String userId, String videoId, String videoCreatorId) {
        videoService.userUnLikeVideo(userId, videoId, videoCreatorId);
        return IMoocJSONResult.ok();
    }

    @PostMapping("/showUserVideos")
    public IMoocJSONResult showUserVideos(String userId, Integer page) {
        if (StringUtils.isBlank(userId)) {
            return IMoocJSONResult.errorMsg("");
        }
        if (page == null) {
            page = 1;
        }
        PagedResult<VideosVO> result = videoService.getAllUserVideos(userId, page, 6);
        return IMoocJSONResult.ok(result);
    }

    @PostMapping("/showLikeVideos")
    public IMoocJSONResult showLikeVideos(String userId, Integer page) {
        if (StringUtils.isBlank(userId)) {
            return IMoocJSONResult.errorMsg("");
        }
        if (page == null) {
            page = 1;
        }
        PagedResult<VideosVO> result = videoService.getAllLikeVideos(userId, page, 6);
        return IMoocJSONResult.ok(result);
    }

    @PostMapping("/saveComment")
    public IMoocJSONResult saveComment(@RequestBody Comments comment, String fatherCommentId, String toUserId) {
        if (StringUtils.isNotBlank(fatherCommentId) && StringUtils.isNotBlank(toUserId)) {
            comment.setFatherCommentId(fatherCommentId);
            comment.setToUserId(toUserId);
        }
        videoService.saveComment(comment);
        return IMoocJSONResult.ok();
    }

    @PostMapping("/getVideoComments")
    public IMoocJSONResult getVideoComments(String videoId, Integer page) {
        if (StringUtils.isBlank(videoId)) {
            return IMoocJSONResult.errorMsg("");
        }
        if (page == null) {
            page = 1;
        }
        PagedResult<Comments> result = videoService.getVideoComments(videoId, page, 6);
        return IMoocJSONResult.ok(result);
    }
    @PostMapping("/getVideoCommentCounts")
    public IMoocJSONResult getVideoCommentCounts(String videoId) {
        if (StringUtils.isBlank(videoId)) {
            return IMoocJSONResult.errorMsg("");
        }
        return IMoocJSONResult.ok(videoService.getVideoCommentCounts(videoId));
    }


/*
    @ApiOperation(value = "用户上传视频(封面上传)", notes = "用户上传视频封面的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "videoId", value = "视频ID", required = true,
                    dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true,
                    dataType = "String", paramType = "form")
    })
    @PostMapping(value = "/uploadCover", headers = "content-type=multipart/form-data")
    public IMoocJSONResult uploadCover(String videoId, String userId,
                                       @ApiParam(value = "视频封面", required = true)
                                       MultipartFile file) throws IOException {
        if (!StringUtils.isNoneBlank(videoId)) {
            return IMoocJSONResult.errorMsg("视频ID不能为空!");
        }
        String fileSpace = "D:/_videos_dev";
        String uploadPath = "/" + userId + "/video/covers";
        if (file != null) {
            String fileName = file.getOriginalFilename();
            System.out.println(fileName);
            String finalVideoPath = fileSpace + uploadPath + "/" + fileName;
            File outFile = new File(finalVideoPath);
            if (outFile.getParentFile() != null || !outFile.getParentFile().isDirectory()) {
                outFile.getParentFile().mkdirs();
            }
            FileOutputStream fos = new FileOutputStream(outFile);
            InputStream is = file.getInputStream();
            IOUtils.copy(is, fos);
            fos.flush();
            fos.close();
            is.close();
            uploadPath += ("/" + fileName);
            videoService.updateVideo(videoId,uploadPath);
            return IMoocJSONResult.ok();
        } else {
            return IMoocJSONResult.errorMsg("上传出错...");
        }
    }
*/

}
