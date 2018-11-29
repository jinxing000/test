package com.ef.golf.mapper;

import com.ef.golf.pojo.EsMemberComment;
import com.ef.golf.pojo.EsMemberCommentWithBLOBs;

public interface EsMemberCommentMapper {
    int deleteByPrimaryKey(Integer commentId);

    int insert(EsMemberComment record);

    int insertSelective(EsMemberComment record);

    EsMemberComment selectByPrimaryKey(Integer commentId);

    int updateByPrimaryKeySelective(EsMemberComment record);

    int updateByPrimaryKeyWithBLOBs(EsMemberComment record);

    int updateByPrimaryKey(EsMemberComment record);
    
}