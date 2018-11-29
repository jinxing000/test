package com.ef.golf.mapper;

import com.ef.golf.pojo.SellbackGoodsList;
import com.ef.golf.vo.BackGoodsVo;

import java.util.List;

public interface SellbackGoodsListMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SellbackGoodsList record);

    int insertSelective(SellbackGoodsList record);

    SellbackGoodsList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SellbackGoodsList record);

    int updateByPrimaryKey(SellbackGoodsList record);

    List<BackGoodsVo> BackGoodsVoList(Integer recid);

}