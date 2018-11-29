package com.ef.golf.service.impl;

import com.ef.golf.mapper.CllectMapper;
import com.ef.golf.mapper.FavoriteMapper;
import com.ef.golf.pojo.Cllect;
import com.ef.golf.pojo.Favorite;
import com.ef.golf.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavoriteServiceImpl implements FavoriteService {
@Autowired
    FavoriteMapper favoriteMapper;
    @Autowired
    CllectMapper cllectMapper;
    @Override
    public int insertSelective(Cllect record) {
        return cllectMapper.insertSelective(record);
    }

    @Override
    public int countFavorite(Integer goods_id, Integer user_id) {
        return cllectMapper.countFavorite(goods_id,user_id);
    }
}
