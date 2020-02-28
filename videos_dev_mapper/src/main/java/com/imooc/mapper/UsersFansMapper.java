package com.imooc.mapper;

import com.imooc.pojo.UsersFans;
import com.imooc.utils.MyMapper;

import java.util.List;

public interface UsersFansMapper extends MyMapper<UsersFans> {

    /**
     * @Description: 查询关注用户编号
     * @param fanId 粉丝ID
     */
    List<String> queryUserId(String fanId);

    /**
     * @Description: 查询粉丝用户编号
     * @param userId 用户ID
     */
    List<String> queryFanId(String userId);
}