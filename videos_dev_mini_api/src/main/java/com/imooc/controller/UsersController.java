package com.imooc.controller;


import com.imooc.config.ResourceConfig;
import com.imooc.pojo.Users;
import com.imooc.pojo.UsersReport;
import com.imooc.pojo.vo.UsersVO;
import com.imooc.service.UserService;
import com.imooc.utils.IMoocJSONResult;
import com.imooc.utils.PagedResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
@Api(value = "用户实现需求的接口", tags = {"用户实现需求的Controller"})
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private ResourceConfig resourceConfig;

    @Resource
    private UserService userService;

    @ApiOperation(value = "用户上传头像", notes = "用户上传头像的接口")
    @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "String", paramType = "query")
    @PostMapping(value = "/uploadFace", headers = "content-type=multipart/form-data")
    public IMoocJSONResult uploadFace(String userId,
                                       @ApiParam(value = "头像", required = true)
                                               MultipartFile file) throws IOException {
        if (StringUtils.isBlank(userId)) {
            return IMoocJSONResult.errorMsg("用户ID不能为空!");
        }
        String fileSpace = resourceConfig.getFileSpace();
        String uploadPath = "/" + userId + "/" + "face";
//        String uploadPath = File.separator + userId + File.separator + "face";
        if (file != null) {
            FileOutputStream fos = null;
            InputStream is = null;
            String fileName = file.getOriginalFilename();
            if (StringUtils.isNoneBlank(fileName)) {
                //文件上传的最终保存路径(绝对路径)
                String finalFacePath = fileSpace + uploadPath + "/" + fileName;
//                String finalFacePath = fileSpace + uploadPath + File.separator + fileName;
                File outFile = new File(finalFacePath);
                if (outFile.getParentFile() == null || !outFile.getParentFile().isDirectory()) {
                    outFile.getParentFile().mkdirs();
                }
                fos = new FileOutputStream(outFile);
                is = file.getInputStream();
                IOUtils.copy(is, fos);
                fos.flush();
                fos.close();
                is.close();
            } else {
                return IMoocJSONResult.errorMsg("上传出错...");
            }
            //数据库保存路径
//            uploadPath += (File.separator + fileName);
            uploadPath += ("/" + fileName);
            Users user = new Users();
            user.setId(userId);
            user.setFaceImg(uploadPath);
            userService.updateUserInfo(user);
        } else {
            return IMoocJSONResult.errorMsg("上传出错...");
        }
        return IMoocJSONResult.ok(uploadPath);
    }

    @ApiOperation(value = "查询用户信息", notes = "查询用户信息的接口")
    @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "String", paramType = "query")
    @PostMapping("/query")
    public IMoocJSONResult query(String userId, String fansId) {
        if (!StringUtils.isNotBlank(userId)) {
            return IMoocJSONResult.errorMsg("用户ID不能为空!");
        }
        Users userInfo = userService.queryUserInfo(userId);
        UsersVO userVO = new UsersVO();
        BeanUtils.copyProperties(userInfo, userVO);
        userVO.setFollow(userService.queryIfFollow(fansId,userId));
        return IMoocJSONResult.ok(userVO);
    }

    @PostMapping("/queryPublisher")
    public IMoocJSONResult queryPublisher(String publisherUserId) {
        if (StringUtils.isBlank(publisherUserId)) {
            return IMoocJSONResult.errorMsg("");
        }
        Users userInfo = userService.queryUserInfo(publisherUserId);
        return IMoocJSONResult.ok(userInfo);
    }

    @PostMapping("/userLikeOrNot")
    public IMoocJSONResult userLikeOrNot(String loginUserId, String videoId) {
        if (StringUtils.isBlank(loginUserId)) {
            return IMoocJSONResult.ok(false);
        }
        if (StringUtils.isBlank(videoId)) {
            return IMoocJSONResult.errorMsg("");
        }
        boolean userLikeOrNot = userService.isUserLikeVideo(loginUserId, videoId);
        return IMoocJSONResult.ok(userLikeOrNot);
    }


    @PostMapping("/fansFollowUser")
    public IMoocJSONResult fansFollowUser(String fansId, String userId) {
        if (StringUtils.isBlank(fansId) || StringUtils.isBlank(userId)) {
            return IMoocJSONResult.errorMsg("");
        }
        userService.userFollowUser(fansId,userId);
        return IMoocJSONResult.ok("已关注>_<");
    }

    @PostMapping("/fansUnFollowUser")
    public IMoocJSONResult fansUnFollowUser(String fansId, String userId) {
        if (StringUtils.isBlank(fansId) || StringUtils.isBlank(userId)) {
            return IMoocJSONResult.errorMsg("");
        }
        userService.userUnFollowUser(fansId, userId);
        return IMoocJSONResult.ok("已取关>_<");
    }

    @PostMapping("/reportUser")
    public IMoocJSONResult reportUser(@RequestBody UsersReport usersReport) {
        userService.reportUser(usersReport);
        return IMoocJSONResult.errorMsg("举报成功!");
    }

    @PostMapping("/queryFollow")
    public IMoocJSONResult queryFollow(String fanId, Integer page) {
        if (!StringUtils.isNotBlank(fanId)) {
            return IMoocJSONResult.errorMsg("用户ID不能为空!");
        }
        PagedResult<Users> result = userService.queryFollow(fanId,page,8);
        if (result != null) {
            return IMoocJSONResult.ok(result);
        } else {
            return IMoocJSONResult.errorMsg("您还没有关注!");
        }
    }

    @PostMapping("/queryFans")
    public IMoocJSONResult queryFans(String userId, Integer page) {
        if (!StringUtils.isNotBlank(userId)) {
            return IMoocJSONResult.errorMsg("用户ID不能为空!");
        }
        PagedResult<Users> result = userService.queryFans(userId,page,8);
        if (result != null) {
            return IMoocJSONResult.ok(result);
        } else {
            return IMoocJSONResult.errorMsg("您还没有粉丝!");
        }
    }


//    @PostMapping("/queryPublisher")
//    public IMoocJSONResult queryPublisher(String loginUserId, String videoId, String publisherUserId) {
//        if (StringUtils.isBlank(publisherUserId)) {
//            return IMoocJSONResult.errorMsg("");
//        }
//        //1.查询视频发布者信息
//        Users userInfo = userService.queryUserInfo(publisherUserId);
//
//        //2.查询当前登录者和视频的关系
//        boolean userLikeVideo = userService.isUserLikeVideo(loginUserId,videoId);
//        PublisherVideo publisherVideo = new PublisherVideo();
//        publisherVideo.setUser(userInfo);
//        publisherVideo.setUserLikeVideo(userLikeVideo);
//        return IMoocJSONResult.ok(publisherVideo);
//    }

}
