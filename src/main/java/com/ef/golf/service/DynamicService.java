package com.ef.golf.service;

import com.ef.golf.pojo.Dynamic;
import com.ef.golf.pojo.User;
import com.ef.golf.vo.DynamicHopeVo;
import com.ef.golf.vo.DynamicVo;

import java.util.List;

public interface DynamicService {

    //查询动态列表
    List<DynamicVo> getDynamicListPage(Dynamic dynamic);
    int getDynamicListPageCount(Dynamic dynamic);

    //查询我关注的人的动态列表
    List<DynamicVo> getCollectDynamicListPage(Dynamic dynamic);
    //查询动态详情
    Dynamic selectByPrimaryKey(Integer dynamicId);
    //查询动态愿望
    List<DynamicHopeVo> getHopes(Integer userId);

    //根据用户id获取愿望
    List<DynamicHopeVo> getHopeByUserId(Integer userId);

    //更新一条动态的点赞数
    int updateDynamicNum(Integer dynamicId);

    //发布动态
    int insertSelective(Dynamic record);

    //根据用户id获取动态列表
    List<DynamicVo> getDynamicByUserIdListPage(User user);

    //根据动态id获取动态详情
    List<DynamicVo> getDyanmicByDynamicId(Dynamic dynamic);

    //更新动态状态（逻辑删除动态）
    int updateDynamicForDel(Integer dynamicId);
}
