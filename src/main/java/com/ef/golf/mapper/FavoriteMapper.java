package com.ef.golf.mapper;

import com.ef.golf.pojo.Favorite;

public interface FavoriteMapper {
    int deleteByPrimaryKey(Integer favoriteId);

    int insert(Favorite record);

    int insertSelective(Favorite record);

    Favorite selectByPrimaryKey(Integer favoriteId);

    int updateByPrimaryKeySelective(Favorite record);

    int updateByPrimaryKey(Favorite record);

    int countFavorite(Integer goods_id,Integer user_id);
}