package com.ef.golf.service;

import com.ef.golf.pojo.Share;
import com.ef.golf.util.PageBean;
import com.ef.golf.vo.QuanziUserVo;

import java.util.List;

public interface ShareService {

    //新增一条分享记录
    int insertSelective(Share record);

    //根据动态id获取分享的记录信息
    PageBean getShares(Integer dynamicId,Integer pageNo,Integer pageSize,Integer userId);
}
