package com.ef.golf.service.impl;

import com.ef.golf.mapper.WorkTypeMapper;
import com.ef.golf.pojo.WorkType;
import com.ef.golf.service.WorkTypeService;
import com.ef.golf.util.PageBean;
import com.ef.golf.vo.PushMsgListVo;
import com.ef.golf.vo.PushWorkTypeVo;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * com.ef.golf.service.impl
 * Administrator
 * 2018/6/6 9:18
 */
@Repository
public class WorkTypeServiceImpl implements WorkTypeService{
    @Resource
    private WorkTypeMapper workTypeMapper;
    @Override
    public int deleteByPrimaryKey(Long id) {
        return workTypeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(WorkType record) {
        return workTypeMapper.insert(record);
    }

    @Override
    public int insertSelective(WorkType record) {
        return workTypeMapper.insertSelective(record);
    }

    @Override
    public WorkType selectByPrimaryKey(Long id) {
        return workTypeMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(WorkType record) {
        return workTypeMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(WorkType record) {
        return workTypeMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<PushWorkTypeVo> getPushWorkTypeMsg() {
        return workTypeMapper.getPushWorkTypeMsg();
    }

    @Override
    public PageBean getPushMsgList(Integer userId, Integer pageNo, Integer pageSize) {
        PageBean pageBean = new PageBean();
        pageNo = (pageNo-1)*pageSize;
        List<PushMsgListVo> listVos = workTypeMapper.getPushMsgList(userId,pageNo,pageSize);
        Integer count = workTypeMapper.getPushMsgListCount(userId);
        pageBean.setResultList(listVos);
        pageBean.setPageSize(pageSize);
        pageBean.setPageNo(pageNo);
         pageBean.setTotalCount(count);
        /** 查询结束更新该list  workType状态 */
        for (PushMsgListVo pmlv:listVos) {
            if ("0".equals(pmlv.getReadUnread())) {
                WorkType workType = new WorkType();
                workType.setId(Long.valueOf(pmlv.getWorkTypeId()));
                workType.setReadUnread("1");
                workTypeMapper.updateByPrimaryKeySelective(workType);
            }
        }
        return pageBean;
    }

    @Override
    public List<PushWorkTypeVo> getPushWorkCenterMsg() {
        return workTypeMapper.getPushWorkCenterMsg();
    }

    @Override
    public Integer getWorkTypeMsgCount(Integer userId) {
        return workTypeMapper.getWorkTypeMsgCount(userId);
    }
}
