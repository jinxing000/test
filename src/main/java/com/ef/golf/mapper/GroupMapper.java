package com.ef.golf.mapper;

import com.ef.golf.pojo.Group;
import com.ef.golf.pojo.Groups;
import com.ef.golf.vo.UserSearchVo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface GroupMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Group record);

    int insertSelective(Group record);

    int insertGroupUser(@Param("userId") Integer userId, @Param("rongLianYunGroupId") String ronglianyungroupid, @Param("createTime") Date createTime);

    Group selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Group record);

    int updateByPrimaryKey(Group record);
    //获取群球会头像
    Groups getHeadImg(String groupId);
    //查询群成员id
    List<Integer> getGroupRen(String groupId);
    //删除群成员
    int delGroupRen(@Param("userId") Integer userId, @Param("groupId") String ronglianyungroupid);
    //查询群列表
    List<UserSearchVo>getGroupMemer(Map map);
    Integer getGroupMemerCount(String groupId);
    Integer jianChaShiFouZaiQun(@Param("userId") String userId,@Param("groupId") String groupId);

    Group selectGroupByGroupId(String groupId);
}