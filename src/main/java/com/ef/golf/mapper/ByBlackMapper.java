package com.ef.golf.mapper;

import com.ef.golf.pojo.ByBlack;
import com.ef.golf.vo.ByBlackListVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ByBlackMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ByBlack record);

    int insertSelective(ByBlack record);

    ByBlack selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ByBlack record);

    int updateByPrimaryKey(ByBlack record);
    List<ByBlackListVo>selectBlackList(Map map);
    int selectBlackListCount(Map map);
    int deleteByBlack(Map map);
    List<Integer>getByBlackPersonByUserId(Integer userId);
}