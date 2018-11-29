package com.ef.golf.service;

import com.ef.golf.pojo.WorkType;
import com.ef.golf.util.PageBean;
import com.ef.golf.vo.PushMsgListVo;
import com.ef.golf.vo.PushWorkTypeVo;

import java.util.List;

/**
 * com.ef.golf.service
 * Administrator
 * 2018/6/6 9:18
 */
public interface WorkTypeService {
    int deleteByPrimaryKey(Long id);

    int insert(WorkType record);

    int insertSelective(WorkType record);

    WorkType selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WorkType record);

    int updateByPrimaryKey(WorkType record);
    //圈子消息
    List<PushWorkTypeVo> getPushWorkTypeMsg();
    PageBean getPushMsgList(Integer userId, Integer pageNo, Integer pageSize);
    //系统消息
    List<PushWorkTypeVo> getPushWorkCenterMsg();
    //消息数
    Integer getWorkTypeMsgCount(Integer userId);
}
