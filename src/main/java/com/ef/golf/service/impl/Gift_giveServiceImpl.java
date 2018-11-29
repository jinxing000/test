package com.ef.golf.service.impl;

import com.ef.golf.mapper.Gift_giveMapper;
import com.ef.golf.pojo.Gift_give;
import com.ef.golf.service.Gift_giveService;
import com.ef.golf.vo.QuanziGiftVo;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class Gift_giveServiceImpl implements Gift_giveService {
    @Resource
    private Gift_giveMapper giftGiveMapper;
    @Override
    public int insertSelective(Gift_give record) {

        return giftGiveMapper.insertSelective(record);
    }

    @Override
    public List<QuanziGiftVo> getGifts(Integer dynamicId) {
        return giftGiveMapper.getGifts(dynamicId);
    }

    @Override
    public int updateByPrimaryKeySelective(Gift_give record) {
        return giftGiveMapper.updateByPrimaryKeySelective(record);
    }


}
