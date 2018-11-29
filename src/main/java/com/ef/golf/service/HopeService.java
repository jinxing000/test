package com.ef.golf.service;


import com.ef.golf.exception.SystemException;
import com.ef.golf.pojo.GetHopeMsg;
import com.ef.golf.pojo.Hope;
import com.ef.golf.util.PageBean;
import com.ef.golf.vo.QuanziHopeDetailVo;

import java.util.List;
import java.util.Map;

/**
 * create by xzw
 * 2018年1月3日14:08:03
 */
public interface HopeService {

    //获取未实现的愿望
    List<Hope> getUnrealizedHopes(Integer userId);
    Hope selectByPrimaryKey(Integer hopeId);
    /** 愿望信息更新 */
    Integer updateByPrimaryKeySelective(Hope hope);
    //新增一条愿望
    int insertSelective(Hope record) throws SystemException;
    Hope getHopeDetails(Integer userId);

    //根据愿望id获取愿望详情
    QuanziHopeDetailVo getHopeDetailById(Integer hopeId);
    //查询愿望
    PageBean getHopeList(Integer userId, String hopeState, Integer pageNo, Integer pageSize);
    //保存愿望领取信息
    Integer insertSelective(GetHopeMsg getHopeMsg);
    /** 查询愿望领取详情 */
    GetHopeMsg getGetHopeMsg(Integer hopeId);
    List<Hope> selectAllHope();
}
