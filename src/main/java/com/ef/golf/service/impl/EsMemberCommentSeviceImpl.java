package com.ef.golf.service.impl;

import com.ef.golf.mapper.EsMemberCommentMapper;
import com.ef.golf.pojo.EsMemberComment;
import com.ef.golf.service.EsMemberCommentSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EsMemberCommentSeviceImpl implements EsMemberCommentSevice {
@Autowired
    EsMemberCommentMapper esMemberCommentMapper;
    @Override
    public int insertSelective(EsMemberComment record) {
        return esMemberCommentMapper.insertSelective(record);
    }
}
