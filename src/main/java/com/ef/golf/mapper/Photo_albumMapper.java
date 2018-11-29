package com.ef.golf.mapper;

import com.ef.golf.pojo.Photo_album;

import java.util.List;

public interface Photo_albumMapper {
    int deleteByPrimaryKey(Integer photoId);

    int insert(Photo_album record);

    int insertSelective(Photo_album record);

    Photo_album selectByPrimaryKey(Integer photoId);

    int updateByPrimaryKeySelective(Photo_album record);

    int updateByPrimaryKey(Photo_album record);


    //根据用户id查询用户的相册
    List<Photo_album> selectByUserId(Integer userId);
    //根据图片id查询图片
    String getPhotoAlbumUrl(Integer photoId);
}