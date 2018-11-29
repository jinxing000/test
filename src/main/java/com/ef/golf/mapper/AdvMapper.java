package com.ef.golf.mapper;

import com.ef.golf.pojo.Adv;
import com.ef.golf.vo.AdvVo;

import java.util.List;

public interface AdvMapper {
    int deleteByPrimaryKey(Long aid);

    int insert(Adv record);

    int insertSelective(Adv record);

    Adv selectByPrimaryKey(Long aid);

    int updateByPrimaryKeySelective(Adv record);

    int updateByPrimaryKey(Adv record);
    List<AdvVo>getList();
}