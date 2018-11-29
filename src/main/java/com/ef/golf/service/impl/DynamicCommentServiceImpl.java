package com.ef.golf.service.impl;

import com.ef.golf.mapper.DynamicMapper;
import com.ef.golf.mapper.Dynamic_commentMapper;
import com.ef.golf.mapper.WorkTypeMapper;
import com.ef.golf.pojo.Dynamic;
import com.ef.golf.pojo.Dynamic_comment;
import com.ef.golf.pojo.WorkType;
import com.ef.golf.service.DynamicCommentService;
import com.ef.golf.vo.QuanziCommentVo;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


@Repository
public class DynamicCommentServiceImpl implements DynamicCommentService {
    @Resource
    private Dynamic_commentMapper dynamicCommentMapper;
    @Resource
    private WorkTypeMapper workTypeMapper;
    @Resource
    private DynamicMapper dynamicMapper;
    @Override
    public int insertSelective(Dynamic_comment record) {

        if(null == record.getReplyUserId()){//评论
            WorkType workType = new WorkType
                    (null,Long.valueOf(record.getCommentsUserId()),"2",Long.valueOf(record.getDynamicId()),"0",new Date(),"1",null);
            workTypeMapper.insertSelective(workType);
        }else{//回复
            /*WorkType workType = new WorkType
                    (null,Long.valueOf(record.getReplyUserId()),"3",
                            Long.valueOf(record.getDynamicId()),"0",new Date(),"1",null);*/

            WorkType workType = new WorkType
                    (null,Long.valueOf(record.getCommentsUserId()),"3",
                            Long.valueOf(record.getDynamicId()),"0",new Date(),"1",null);

            workTypeMapper.insertSelective(workType);
        }
        Dynamic dynamic = dynamicMapper.selectByPrimaryKey(record.getDynamicId());
        dynamic.setForwardNum(dynamic.getForwardNum()+1);
        dynamicMapper.updateByPrimaryKey(dynamic);
        return dynamicCommentMapper.insertSelective(record);
    }

    @Override
    public Dynamic_comment selectByPrimaryKey(Integer dynamicComId) {
        return dynamicCommentMapper.selectByPrimaryKey(dynamicComId);
    }

    @Override
    public List<QuanziCommentVo> getComments(Integer dynamicId) {
        return dynamicCommentMapper.getComments(dynamicId);
    }

    @Override
    public int delCommentBydynamicComId(Integer dynamicComId) {
        Dynamic_comment dynamic_comment = dynamicCommentMapper.selectByPrimaryKey(dynamicComId);
            Dynamic dynamic = dynamicMapper.selectByPrimaryKey(dynamic_comment.getDynamicId());
            dynamic.setForwardNum(dynamic.getForwardNum()-1);
            dynamicMapper.updateByPrimaryKey(dynamic);
        return dynamicCommentMapper.delCommentBydynamicComId(dynamicComId);
    }
}
