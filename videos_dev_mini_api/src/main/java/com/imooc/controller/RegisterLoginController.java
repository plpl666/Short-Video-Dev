package com.imooc.controller;

import com.imooc.pojo.Users;
import com.imooc.pojo.vo.UsersVO;
import com.imooc.service.UserService;
import com.imooc.utils.IMoocJSONResult;
import com.imooc.utils.MD5Utils;
import com.imooc.utils.RedisOperator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;

@RestController
@Api(value = "用户注册登陆的接口", tags = {"注册和登陆的Controller"})
public class RegisterLoginController {

    @Autowired
    public RedisOperator redis;

    private static final String USER_REDIS_SESSION = "user-redis-session";

    @Resource
    private UserService userService;

    @ApiOperation(value = "用户注册", notes = "用户注册的接口")
    @PostMapping("/register")
    public IMoocJSONResult register(@RequestBody Users user) throws Exception {
        if (StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword())) {
            return IMoocJSONResult.errorMsg("用户名和密码不能为空!");
        }
        boolean usernameIsExist = userService.queryUsernameIsExist(user.getUsername());
        if (!usernameIsExist) {
            user.setNickname(user.getUsername());
            user.setPassword(MD5Utils.getMD5Str(user.getPassword()));
            user.setFansCounts(0);
            user.setReceiveLikeCounts(0);
            user.setFollowCounts(0);
            userService.saveUser(user);
        } else {
            return IMoocJSONResult.errorMsg("用户名被注册,请重新输入!");
        }
        user.setPassword("");
//        String uniqueToken = UUID.randomUUID().toString();
//        redis.set(USER_RDEIS_SESSION + ":" + user.getId(), uniqueToken, 1000 * 60 * 30);
//        UsersVO userVO = new UsersVO();
//        BeanUtils.copyProperties(user, userVO);
//        userVO.setUserToken(uniqueToken);
        UsersVO userVO = setUserRedisSessionToken(user);
        return IMoocJSONResult.ok(userVO);
    }

    @ApiOperation(value = "用户登陆", notes = "用户登陆的接口")
    @PostMapping("/login")
    public IMoocJSONResult login(@RequestBody Users user) throws Exception {
        if (StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword())) {
            return IMoocJSONResult.errorMsg("用户名和密码不能为空!");
        }
        Users userResult = userService.queryUserForLogin(user.getUsername(),MD5Utils.getMD5Str(user.getPassword()));
        if (userResult != null) {
            userResult.setPassword("");
            UsersVO userVO = setUserRedisSessionToken(userResult);
            return IMoocJSONResult.ok(userVO);
        } else {
            return IMoocJSONResult.errorMsg("用户名或密码不正确,请重试...");
        }
    }

    @ApiOperation(value = "用户注销", notes = "用户注销的接口")
    @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "String", paramType = "query")
    @PostMapping("/logout")
    public IMoocJSONResult logout(String userId) throws Exception {
       redis.del(USER_REDIS_SESSION + ":" + userId);
       return IMoocJSONResult.ok();
    }

    private UsersVO setUserRedisSessionToken(Users user) {
        String uniqueToken = UUID.randomUUID().toString();
        redis.set(USER_REDIS_SESSION + ":" + user.getId(), uniqueToken, 1000 * 60 * 30);
        UsersVO userVO = new UsersVO();
        BeanUtils.copyProperties(user, userVO);
        userVO.setUserToken(uniqueToken);
        return userVO;
    }

}
