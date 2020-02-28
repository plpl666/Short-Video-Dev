package com.imooc.mapper;

import com.imooc.pojo.Comments;
import com.imooc.pojo.vo.CommentsVO;
import com.imooc.utils.MyMapper;

import java.util.List;

public interface CommentsExpandMapper extends MyMapper<Comments> {

    /**
     * @Description: 查询留言
     */
    List<CommentsVO> queryComments(String videoId);

}
