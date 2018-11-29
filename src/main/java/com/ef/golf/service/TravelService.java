package com.ef.golf.service;

import com.ef.golf.util.PageBean;
import com.ef.golf.vo.ServiceReplyVo;
import com.ef.golf.vo.TravelLineVo;

/**
 * com.ef.golf.service
 * Administrator
 * 2018/11/14 9:56
 */
public interface TravelService {

    PageBean getTravelLineByType(String type,Integer pageNo, Integer pageSize);

    TravelLineVo getTravelDetailsByLineId(Integer lineId);

    ServiceReplyVo getSearchCueMsg();

}
