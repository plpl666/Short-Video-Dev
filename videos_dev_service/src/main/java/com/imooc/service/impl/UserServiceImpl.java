package com.imooc.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.imooc.mapper.*;
import com.imooc.pojo.Users;
import com.imooc.pojo.UsersFans;
import com.imooc.pojo.UsersLikeVideos;
import com.imooc.pojo.UsersReport;
import com.imooc.service.UserService;
import com.imooc.utils.PagedResult;
import io.swagger.models.auth.In;
import org.apache.commons.lang3.StringUtils;
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
public class UserServiceImpl implements UserService {

    @Resource
    private UsersMapper usersMapper;

    @Resource
    private UsersLikeVideosMapper usersLikeVideosMapper;

    @Resource
    private UsersFansMapper usersFansMapper;

    @Resource
    private UsersReportMapper usersReportMapper;

    @Autowired
    private Sid sid;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean queryUsernameIsExist(String username) {

        Users user = new Users();
        user.setUsername(username);

        Users result = usersMapper.selectOne(user);

        return result == null ? false : true;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void saveUser(Users user) {
        user.setId(sid.nextShort());
        usersMapper.insert(user);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void reportUser(UsersReport usersReport) {
        usersReport.setId(sid.nextShort());
        usersReport.setCreateTime(new Date());
        usersReportMapper.insert(usersReport);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean queryIfFollow(String fansId, String userId) {
        Example example = new Example(UsersFans.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("fanId",fansId);
        criteria.andEqualTo("userId",userId);
        List<UsersFans> list = usersFansMapper.selectByExample(example);
        if (list != null && !list.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Users queryUserForLogin(String username, String password) {
        Example userExample = new Example(Users.class);
        Example.Criteria criteria = userExample.createCriteria();
        criteria.andEqualTo("username",username);
        criteria.andEqualTo("password",password);
        return usersMapper.selectOneByExample(userExample);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void userFollowUser(String fansId, String userId) {
        UsersFans usersFans = new UsersFans();
        usersFans.setId(sid.nextShort());
        usersFans.setFanId(fansId);
        usersFans.setUserId(userId);
        usersFansMapper.insert(usersFans);
        usersMapper.addFansCount(userId);
        usersMapper.addFollowCount(fansId);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void userUnFollowUser(String fansId, String userId) {
        Example example = new Example(UsersFans.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("fanId",fansId);
        criteria.andEqualTo("userId",userId);
        usersFansMapper.deleteByExample(example);
        usersMapper.reduceFansCount(userId);
        usersMapper.reduceFollowCount(fansId);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean isUserLikeVideo(String userId, String videoId) {
        if (StringUtils.isBlank(userId) || StringUtils.isBlank(videoId)) {
            return false;
        }
        Example example = new Example(UsersLikeVideos.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId",userId);
        criteria.andEqualTo("videoId",videoId);
        List<UsersLikeVideos> list = usersLikeVideosMapper.selectByExample(example);
        return !list.isEmpty();
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Users queryUserInfo(String userId) {
        Example userExample = new Example(Users.class);
        Example.Criteria criteria = userExample.createCriteria();
        criteria.andEqualTo("id", userId);
        return usersMapper.selectOneByExample(userExample);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updateUserInfo(Users user) {
        Example userExample = new Example(Users.class);
        Example.Criteria criteria = userExample.createCriteria();
        criteria.andEqualTo("id", user.getId());
        usersMapper.updateByExampleSelective(user,userExample);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedResult<Users> queryFollow(String fanId, Integer page, Integer pageSize) {
        List<String> list = usersFansMapper.queryUserId(fanId);
        if (!list.isEmpty()) {
            Page<Users> pages = PageHelper.startPage(page,pageSize);
            usersMapper.queryUsersByUserId(list);
            PageInfo<Users> pageList = pages.toPageInfo();
            return new PagedResult<>(pageList.getPageNum(), pageList.getPages(), pageList.getTotal(), pageList.getList());
        } else {
            return null;
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedResult<Users> queryFans(String userId, Integer page, Integer pageSize) {
        List<String> list = usersFansMapper.queryFanId(userId);
        if (!list.isEmpty()) {
            Page<Users> pages = PageHelper.startPage(page,pageSize);
            usersMapper.queryUsersByUserId(list);
            PageInfo<Users> pageList = pages.toPageInfo();
            return new PagedResult<>(pageList.getPageNum(), pageList.getPages(), pageList.getTotal(), pageList.getList());
        } else {
            return null;
        }
    }
}
