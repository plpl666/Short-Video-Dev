package com.imooc.mapper;

import com.imooc.pojo.Users;
import com.imooc.utils.MyMapper;

import java.util.List;

public interface UsersMapper extends MyMapper<Users> {

    /**
     * @Description: 用户受喜欢数累加
     * @param userId 用户ID
     */
    void addReceiveLikeCount(String userId);

    /**
     * @Description: 用户受喜欢数累减
     * @param userId 用户ID
     */
    void reduceReceiveLikeCount(String userId);

    /**
     * @Description: 用户粉丝数累加
     * @param userId 用户ID
     */
    void addFansCount(String userId);

    /**
     * @Description: 用户粉丝数累减
     * @param userId 用户ID
     */
    void reduceFansCount(String userId);

    /**
     * @Description: 用户关注数累加
     * @param userId 用户ID
     */
    void addFollowCount(String userId);

    /**
     * @Description: 用户关注数累减
     * @param userId 用户ID
     */
    void reduceFollowCount(String userId);

    /**
     * @Description: 通过编号查询用户
     * @param userId 用户ID
     */
    List<Users> queryUsersByUserId(List<String> userId);

}