package com.ef.golf.mapper;

import com.ef.golf.pojo.Dynamic;
import com.ef.golf.pojo.User;
import com.ef.golf.vo.DynamicVo;
import com.ef.golf.vo.GiftCountAndImgVo;

import java.util.List;

public interface DynamicMapper {
    int deleteByPrimaryKey(Integer dynamicId);

    int insert(Dynamic record);

    int insertSelective(Dynamic record);

    Dynamic selectByPrimaryKey(Integer dynamicId);

    int updateByPrimaryKeySelective(Dynamic record);

    int updateByPrimaryKey(Dynamic record);

    //查询动态列表
    List<DynamicVo> getDynamicListPage(Dynamic dynamic);
    int getDynamicCount(Dynamic dynamic);
    //查询我关注的人的动态列表
    List<DynamicVo> getCollectDynamicListPage(Dynamic dynamic);
    //更新一条动态的点赞数
    int updateDynamicNum(Integer dynamicId);
    //根据用户id获取动态列表
    List<DynamicVo> getDynamicByUserIdListPage(User user);
    //根据动态id获取动态详情
    List<DynamicVo> getDyanmicByDynamicId(Dynamic dynamic);
    //更新动态状态（逻辑删除动态）
    int updateDynamicForDel(Integer dynamicId);
    //圈子受赠礼物图、数量
    List<GiftCountAndImgVo>getQuanziGiftCountAndImg(Integer dynamicId);

}