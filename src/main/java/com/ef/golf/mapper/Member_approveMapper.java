package com.ef.golf.mapper;

import com.ef.golf.pojo.Member_approve;
import org.apache.ibatis.annotations.Param;

public interface Member_approveMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Member_approve record);

    int insertSelective(Member_approve record);

    Member_approve selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Member_approve record);

    int updateByPrimaryKey(Member_approve record);

    //查询会员认证结果
    String memberApproveEnd(@Param("userId") Integer userId, @Param("clubId") String clubId);
    Member_approve memberApproveMsg(@Param("userId") Integer userId, @Param("clubId") String clubId);
    //重新提交要删除未通过的
    int deleteFailMemberApprove(@Param("userId")Integer userId,@Param("clubId")String clubId);
}