package com.ef.golf.mapper;


import com.ef.golf.pojo.WorkType;
import com.ef.golf.vo.PushMsgListVo;
import com.ef.golf.vo.PushWorkTypeVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WorkTypeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WorkType record);

    int insertSelective(WorkType record);

    WorkType selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WorkType record);

    int updateByPrimaryKey(WorkType record);

    List<PushWorkTypeVo> getPushWorkTypeMsg();

    List<PushMsgListVo> getPushMsgList(@Param("userId") Integer userId,@Param("pageNo") Integer pageNo,@Param("pageSize") Integer pageSize);
    Integer getPushMsgListCount(Integer userId);
    List<PushWorkTypeVo> getPushWorkCenterMsg();
    /** 未读消息数 */
    Integer getWorkTypeMsgCount(Integer userId);
}