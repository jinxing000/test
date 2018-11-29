package com.ef.golf.service;


import com.ef.golf.pojo.Photo_album;

import java.util.List;

/**
 * create by xzw
 * 2018年1月8日14:21:01
 * 保存用户相册
 */
public interface PhotoAlbumService {

    //根据用户id查询用户的相册
    List<Photo_album> selectByUserId(Integer userId);
    //相册添加图片
    int insertPhotoAlbum(Photo_album photo_album);
    //根据图片id查询图片
    String getPhotoAlbumUrl(Integer photoId);
    //根据图片id删除图片
    int deleteByPrimaryKey(Integer photoId);
}
