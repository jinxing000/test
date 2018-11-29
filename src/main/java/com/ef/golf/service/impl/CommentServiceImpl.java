package com.ef.golf.service.impl;

import com.ef.golf.mapper.CommentsMapper;
import com.ef.golf.mapper.ScoreMapper;
import com.ef.golf.mapper.PraiseMapper;
import com.ef.golf.pojo.Comments;
import com.ef.golf.pojo.Praise;
import com.ef.golf.service.CommentService;
import com.ef.golf.vo.CommentVo;
import com.ef.golf.vo.SiteCommentVo;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * for golf
 * Created by Administrator on 2017/10/16.
 * Date: 2017/10/16 14:56
 */

@Repository
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentsMapper commentsMapper;

    @Resource
    private ScoreMapper scoreMapper;


    @Resource
    private PraiseMapper praiseMapper;

    public long saveComment(Comments comments) {
        commentsMapper.insertSelective(comments);
        return 0;
    }

    public int getCommentCount(Comments comments) {
        return commentsMapper.getCommentCount(comments);
    }

    @Override
    public int insertSelective(Comments record) {
        return commentsMapper.insertSelective(record);
    }

    public List<CommentVo> selectCommentsByProductIdAndCommentType(Map<String, Object> maps) {
        return commentsMapper.selectCommentsByProductIdAndCommentType(maps);
    }



    public int addPraise(Praise praise) {
        if(praise.getPraiseNum()>0)
            praiseMapper.insertSelective(praise);
        else
            praiseMapper.removePraise(praise);
        return commentsMapper.savePraise(praise);
    }

    @Override
    public int getCommentCountByProductIdAndCommentType(Map<String, Object> maps) {
        return commentsMapper.getCommentCountByProductIdAndCommentType(maps);
    }

}
