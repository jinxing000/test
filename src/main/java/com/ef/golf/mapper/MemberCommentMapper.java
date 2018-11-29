package com.ef.golf.mapper;

import com.ef.golf.pojo.MemberComment;


public interface MemberCommentMapper {
    int deleteByPrimaryKey(Integer commentId);

    int insert(MemberComment record);

    int insertSelective(MemberComment record);

    MemberComment selectByPrimaryKey(Integer commentId);

    int updateByPrimaryKeySelective(MemberComment record);

    int updateByPrimaryKeyWithBLOBs(MemberComment record);

    int updateByPrimaryKey(MemberComment record);

}