package com.ef.golf.mapper;

import com.ef.golf.pojo.Hope;
import com.ef.golf.vo.DynamicHopeVo;
import com.ef.golf.vo.QuanziHopeDetailVo;

import java.util.List;
import java.util.Map;

public interface HopeMapper {
    int deleteByPrimaryKey(Integer hopeId);

    int insert(Hope record);

    int insertSelective(Hope record);

    Hope selectByPrimaryKey(Integer hopeId);

    int updateByPrimaryKeySelective(Hope record);

    int updateByPrimaryKey(Hope record);


    //获取愿望列表
    List<DynamicHopeVo> getHopes(Integer userId);
    //根据用户id获取愿望
    List<DynamicHopeVo> getHopeByUserId(Integer userId);
    //获取未实现的愿望
    List<Hope> getUnrealizedHopes(Integer userId);
    //根据愿望id获取愿望详情
    QuanziHopeDetailVo getHopeDetailById(Integer hopeId);
    //查询愿望
    List<Hope> getHopeList(Map map);
    Integer getHopeListCount(Map map);
    Hope getHopeDetails(Integer userId);

    //查询所有愿望
    List<Hope>selectAllHope();

}