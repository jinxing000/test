package com.ef.golf.service;

import com.ef.golf.pojo.Member_approve;

/**
 * create by xzw
 * 2018年2月27日14:50:36
 * 会员认证记录
 */
public interface MemberApproveService {

    //查询会员认证状态
    String memberApproveEnd(Integer userId,String clubId);
    //保存会员认真信息
    int insertMemberApprove(Member_approve member_approve);
    Member_approve memberApproveMsg(Integer userId,String clubId);
    //重新提交要删除未通过的
    int deleteFailMemberApprove(Integer userId,String clubId);
}
