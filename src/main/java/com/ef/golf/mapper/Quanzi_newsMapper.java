package com.ef.golf.mapper;

import com.ef.golf.pojo.Quanzi_news;
import com.ef.golf.vo.QuanZiNewsVo;

import java.util.List;

public interface Quanzi_newsMapper {
    int deleteByPrimaryKey(Integer nsid);

    int insert(Quanzi_news record);

    int insertSelective(Quanzi_news record);

    Quanzi_news selectByPrimaryKey(Integer nsid);

    int updateByPrimaryKeySelective(Quanzi_news record);

    int updateByPrimaryKey(Quanzi_news record);


    //获取用户圈子的消息提示
    List<QuanZiNewsVo> getNewsByUserIdListPage(QuanZiNewsVo qzv);

    //清除我的全部消息通知
    int delNewsByUserId(Integer userId);

    //清除单条消息通知
    int delNewsByNewsId(Integer nsid);


}