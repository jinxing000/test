package com.ef.golf.service;


import com.ef.golf.pojo.Comments;
import com.ef.golf.pojo.Praise;
import com.ef.golf.vo.CommentVo;
import com.ef.golf.vo.SiteCommentVo;

import java.util.List;
import java.util.Map;

/**
 * for efgolf
 * Created by Bart on 2017/9/21.
 * Date: 2017/9/21 16:02
 */
public interface CommentService {

    long saveComment(Comments comments);

    int getCommentCount(Comments comments);

    int insertSelective(Comments record);

    //根据评论
    List<CommentVo> selectCommentsByProductIdAndCommentType(Map<String, Object> maps);

    int addPraise(Praise praise);
//查询评论的记录数
    int getCommentCountByProductIdAndCommentType(Map<String, Object> maps);
}
