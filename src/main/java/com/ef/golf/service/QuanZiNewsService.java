package com.ef.golf.service;


import com.ef.golf.vo.QuanZiNewsVo;

import java.util.List;

public interface QuanZiNewsService {

    List<QuanZiNewsVo> getNewsByUserId(QuanZiNewsVo qzv);

    //清除我的全部消息通知
    int delNewsByUserId(Integer userId);

    //清除单条消息通知
    int delNewsByNewsId(Integer nsid);
}
