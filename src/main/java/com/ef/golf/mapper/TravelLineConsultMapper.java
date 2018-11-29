package com.ef.golf.mapper;

import com.ef.golf.pojo.TravelLineConsult;
import org.apache.ibatis.annotations.Param;

public interface TravelLineConsultMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(TravelLineConsult record);

    TravelLineConsult selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TravelLineConsult record);

    TravelLineConsult getSearchRecord(@Param("userId")String userId,@Param("lineId")String lineId);

}