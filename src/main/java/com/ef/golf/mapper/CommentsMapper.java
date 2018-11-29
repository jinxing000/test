package com.ef.golf.mapper;

import com.ef.golf.pojo.Comments;
import com.ef.golf.pojo.Praise;
import com.ef.golf.vo.CommentVo;
import com.ef.golf.vo.SiteCommentVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CommentsMapper {
    int deleteByPrimaryKey(Integer commentsId);

    int savePraise(Praise praise);

    int insert(Comments record);

    int insertSelective(Comments record);

    Comments selectByPrimaryKey(Integer commentsId);

    int getCommentCount(Comments comments);

    int updateByPrimaryKeySelective(Comments record);

    int updateByPrimaryKey(Comments record);

    //获取评论
    List<CommentVo> selectCommentsByProductIdAndCommentType(Map<String, Object> maps);
//获取评论记录
    int getCommentCountByProductIdAndCommentType(Map<String, Object> maps);
}