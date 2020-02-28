package com.imooc.controller;

import com.imooc.service.BgmService;
import com.imooc.utils.IMoocJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Api(value = "背景音乐业务的接口", tags = {"背景音乐业务的Controller"})
@RequestMapping("/bgm")
public class BgmController {

    @Resource
    private BgmService bgmService;

    @ApiOperation(value = "获取背景音乐列表", notes = "获取背景音乐列表的接口")
    @PostMapping("/list")
    public IMoocJSONResult bgmList() {
        return IMoocJSONResult.ok(bgmService.queryBgmList());
    }
}
