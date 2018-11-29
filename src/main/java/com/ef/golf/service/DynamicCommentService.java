package com.ef.golf.service;

import com.ef.golf.pojo.Dynamic_comment;
import com.ef.golf.vo.QuanziCommentVo;

import java.util.List;

public interface DynamicCommentService {

    int insertSelective(Dynamic_comment record);
    Dynamic_comment selectByPrimaryKey(Integer dynamicComId);
    //获取动态评论
    List<QuanziCommentVo> getComments(Integer dynamicId);
    //删除评论
    int delCommentBydynamicComId(Integer dynamicComId);
}
