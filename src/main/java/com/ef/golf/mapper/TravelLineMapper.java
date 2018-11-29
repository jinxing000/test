package com.ef.golf.mapper;

import com.ef.golf.pojo.TravelLine;
import com.ef.golf.vo.ServiceReplyVo;
import com.ef.golf.vo.TravelLineVo;

import java.util.List;
import java.util.Map;

public interface TravelLineMapper {
    int deleteByPrimaryKey(Integer lineId);

    int insert(TravelLine record);

    int insertSelective(TravelLine record);

    TravelLine selectByPrimaryKey(Integer lineId);

    int updateByPrimaryKeySelective(TravelLine record);

    int updateByPrimaryKey(TravelLine record);

    List<TravelLineVo> getTravelLineByType(Map map);

    Integer getTravelLineByTypeCount(String type);

    List<String> getPictureByTravelLineId(Integer lineId);

    TravelLineVo getTravelLineDetailsById(Integer lineId);

    //客服非工作时间数据
    ServiceReplyVo getSearchCueMsg();

}