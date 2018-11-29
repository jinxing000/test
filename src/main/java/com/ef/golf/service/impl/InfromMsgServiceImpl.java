package com.ef.golf.service.impl;

import com.ef.golf.mapper.InfromMsgMapper;
import com.ef.golf.pojo.InfromMsg;
import com.ef.golf.service.InfromMsgService;
import com.ef.golf.util.PageBean;
import com.ef.golf.vo.InfromMsgVo;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * com.ef.golf.service.impl
 * Administrator
 * 2018/6/18 14:42
 */
@Repository
public class InfromMsgServiceImpl implements InfromMsgService {

    @Resource
    private InfromMsgMapper infromMsgMapper;

    @Override
    public int deleteByPrimaryKey(Long infromId) {
        return infromMsgMapper.deleteByPrimaryKey(infromId);
    }

    @Override
    public int insert(InfromMsg record) {
        return infromMsgMapper.insert(record);
    }

    @Override
    public int insertSelective(InfromMsg record) {
        return infromMsgMapper.insertSelective(record);
    }

    @Override
    public InfromMsg selectByPrimaryKey(Long infromId) {
        return infromMsgMapper.selectByPrimaryKey(infromId);
    }

    @Override
    public int updateByPrimaryKeySelective(InfromMsg record) {
        return infromMsgMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(InfromMsg record) {
        return infromMsgMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageBean getPushAllInfromMsg(String userId,Integer pageNo,Integer pageSize) {
        PageBean pageBean = new PageBean();
        List<InfromMsgVo>list = infromMsgMapper.getPushAllInfromMsg(userId,(pageNo-1)*pageSize,pageSize);
        Integer count = infromMsgMapper.getPushAllInfromMsgCount(userId);
        for (InfromMsgVo ifm:list) {
            if("0".equals(ifm.getReadUnread())){
                InfromMsg infromMsg = new InfromMsg();
                infromMsg.setInfromId(ifm.getInfromId().longValue());
                infromMsg.setReadUnread("1");
                infromMsgMapper.updateByPrimaryKeySelective(infromMsg);
            }
        }
        pageBean.setResultList(list);
        pageBean.setPageSize(pageSize);
        pageBean.setTotalCount(count);
        pageBean.setPageNo(pageNo);
        return pageBean;
    }

    @Override
    public Integer getInfromMsgCount(String userId) {
        return infromMsgMapper.getInfromMsgCount(userId);
    }
}
