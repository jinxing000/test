package com.ef.golf.service.impl;

import com.ef.golf.mapper.GroupMapper;
import com.ef.golf.pojo.Group;
import com.ef.golf.pojo.Groups;
import com.ef.golf.service.GroupService;
import com.ef.golf.util.PageBean;
import com.ef.golf.vo.UserSearchVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * com.ef.golf.service.impl
 * Administrator
 * 2018/5/28 17:03
 */
@Repository
public class GroupServiceImpl implements GroupService {
    @Resource
    private GroupMapper groupMapper;
    @Override
    public void createGroup(Group group) {

    }

    @Override
    public int insertSelective(Group record) {
               groupMapper.insertGroupUser(record.getUserId(),record.getRongLianYunGroupId(),new Date());
        return groupMapper.insertSelective(record);
    }

    @Override
    public Groups getHeadImg(String groupId) {
        return groupMapper.getHeadImg(groupId);
    }

    @Override
    public List<Integer> getGroupRen(String groupId) {
        return groupMapper.getGroupRen(groupId);
    }

    @Override
    public int insertGroupUser(Integer userId, String ronglianyungroupid) {
        return groupMapper.insertGroupUser(userId,ronglianyungroupid,new Date());
    }

    @Override
    public int delGroupRen(Integer userId, String groupId) {
        return groupMapper.delGroupRen(userId,groupId);
    }

    @Override
    public PageBean getGroupMemer(String groupId, Integer pageNo, Integer pageSize) {
        PageBean pageBean = new PageBean();
        pageNo = (pageNo-1)*pageSize;
        Map<String,Object>map = new HashMap<>();
        map.put("groupId",groupId);
        map.put("pageNo",pageNo);
        map.put("pageSize",pageSize);
        List<UserSearchVo>list = groupMapper.getGroupMemer(map);
        Integer count = groupMapper.getGroupMemerCount(groupId+"");
        pageBean.setTotalCount(count);
        pageBean.setPageNo(pageNo);
        pageBean.setPageSize(pageSize);
        pageBean.setResultList(list);
        return pageBean;
    }

    @Override
    public Integer jianChaShiFouZaiQun(String userId, String groupId) {
        return groupMapper.jianChaShiFouZaiQun(userId,groupId);
    }

    @Override
    public Group selectGroupByGroupId(String groupId) {
        return groupMapper.selectGroupByGroupId(groupId);
    }
}
