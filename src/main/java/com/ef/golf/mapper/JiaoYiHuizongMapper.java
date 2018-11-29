package com.ef.golf.mapper;

import com.ef.golf.pojo.JiaoYiHuizong;
import com.ef.golf.vo.JiaoYiHuizongVo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface JiaoYiHuizongMapper {
    int deleteByPrimaryKey(Long id);

    int insert(JiaoYiHuizong record);

    int insertSelective(JiaoYiHuizong record);

    JiaoYiHuizong selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(JiaoYiHuizong record);

    int updateByPrimaryKey(JiaoYiHuizong record);

    List<JiaoYiHuizongVo> selectByUserId(Map map);
    Integer selectByUserIdCount(Long userId);

    /** 可提现余额 */
    Double getUserTxBalance(@Param("createTime") Date date,@Param("userType") String userType,@Param("userId") Integer userId);
}