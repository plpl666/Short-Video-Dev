package com.imooc.service;

import com.imooc.pojo.Bgm;

import java.util.List;


public interface BgmService {

    /**
     * @Description: 查询背景音乐列表
     */
    List<Bgm> queryBgmList();

    /**
     * @Description: 根据ID查询背景音乐
     */
    Bgm queryBgmById(String bgmId);

}
