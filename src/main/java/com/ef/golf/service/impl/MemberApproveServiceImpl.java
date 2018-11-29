package com.ef.golf.service.impl;

import com.ef.golf.mapper.Member_approveMapper;
import com.ef.golf.pojo.Member_approve;
import com.ef.golf.service.MemberApproveService;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class MemberApproveServiceImpl implements MemberApproveService {

    @Resource
    private Member_approveMapper memberApproveMapper;

    @Override
    public String memberApproveEnd(Integer userId, String clubId) {
        return memberApproveMapper.memberApproveEnd(userId,clubId);
    }

    @Override
    public int insertMemberApprove(Member_approve member_approve) {
        return memberApproveMapper.insert(member_approve);
    }

    @Override
    public Member_approve memberApproveMsg(Integer userId, String clubId) {
        return memberApproveMapper.memberApproveMsg(userId,clubId);
    }

    @Override
    public int deleteFailMemberApprove(Integer userId, String clubId) {
        return memberApproveMapper.deleteFailMemberApprove(userId,clubId);
    }
}
