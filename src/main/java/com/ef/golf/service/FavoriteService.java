package com.ef.golf.service;

import com.ef.golf.pojo.Cllect;
import com.ef.golf.pojo.Favorite;

public interface FavoriteService {
    public  int countFavorite(Integer goods_id,Integer user_id);

    public  int insertSelective(Cllect record);
}
