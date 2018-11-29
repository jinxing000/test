package com.ef.golf.service;

import com.ef.golf.pojo.Group;
import com.ef.golf.pojo.Groups;
import com.ef.golf.util.PageBean;
import com.ef.golf.vo.UserSearchVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/5/10.
 */
public interface GroupService {
    //创建群组
   void  createGroup(Group group);
    //创建群组
    int insertSelective(Group record);
    //获取群球会头像
    Groups getHeadImg(String groupId);
    //获取该群成员
    List<Integer> getGroupRen(String groupId);
    //添加本地群成员
    int insertGroupUser(Integer userId, String ronglianyungroupid);
    //删除群成员
    int delGroupRen(Integer userId, String groupId);
    //查询群列表
    PageBean getGroupMemer(String groupId,Integer pageNo,Integer pageSize);
    //校验是否已在群
    Integer jianChaShiFouZaiQun(String userId,String groupId);
    //查询群
    Group selectGroupByGroupId(String groupId);
    //修改群组属性


   //删除群组

   //按条件搜索公共群组

  //查询群组属性

  //用户申请加入群组

 //群组管理员邀请用户加入群组

 //群组管理员删除成员

 //成员主动退出群组

   //设置群组成员角色

}
