package com.imooc.service;

import com.imooc.pojo.Users;
import com.imooc.pojo.UsersReport;
import com.imooc.utils.PagedResult;
import io.swagger.models.auth.In;


public interface UserService {

    /**
     * @Description: 判断用户名是否存在
     */
    boolean queryUsernameIsExist(String username);

    /**
     * @Description: 保存用户(注册)
     */
    void saveUser(Users users);

    /**
     * @Description: 用户登陆,根据用户名和密码查询用户
     */
    Users queryUserForLogin(String username, String md5Str);

    /**
     * @Description: 用户修改信息
     */
    void updateUserInfo(Users user);

    /**
     * @Description: 查询用户信息
     */
    Users queryUserInfo(String userId);

    /**
     * @Description: 查询用户是否喜欢视频
     */
    boolean isUserLikeVideo(String userId, String videoId);

    /**
     * @Description: 用户关注用户
     */
    void userFollowUser(String fansId, String userId);

    /**
     * @Description: 用户取消关注用户
     */
    void userUnFollowUser(String fansId, String userId);

    /**
     * @Description: 用户是否关注
     */
    boolean queryIfFollow(String fansId, String userId);

    /**
     * @Description: 举报用户
     */
    void reportUser(UsersReport usersReport);

    /**
     * @Description: 查询用户关注
     */
    PagedResult<Users> queryFollow(String fanId, Integer page, Integer pageSize);

    /**
     * @Description: 查询用户粉丝
     */
    PagedResult<Users> queryFans(String userId, Integer page, Integer pageSize);
}
