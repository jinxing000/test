package com.ef.golf.mapper;

import com.ef.golf.pojo.Dynamic_comment;
import com.ef.golf.vo.QuanziCommentVo;

import java.util.List;

public interface Dynamic_commentMapper {
    int deleteByPrimaryKey(Integer dynamicComId);

    int insert(Dynamic_comment record);

    int insertSelective(Dynamic_comment record);

    Dynamic_comment selectByPrimaryKey(Integer dynamicComId);

    int updateByPrimaryKeySelective(Dynamic_comment record);

    int updateByPrimaryKey(Dynamic_comment record);

    //获取动态评论
    List<QuanziCommentVo> getComments(Integer dynamicId);
    //删除评论
    int delCommentBydynamicComId(Integer dynamicComId);
}