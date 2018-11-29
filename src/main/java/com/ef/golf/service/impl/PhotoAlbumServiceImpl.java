package com.ef.golf.service.impl;

import com.ef.golf.mapper.Photo_albumMapper;
import com.ef.golf.pojo.Photo_album;
import com.ef.golf.service.PhotoAlbumService;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;


@Repository
public class PhotoAlbumServiceImpl implements PhotoAlbumService {

    @Resource
    private Photo_albumMapper photo_albumMapper;


    @Override
    public List<Photo_album> selectByUserId(Integer userId) {
        return photo_albumMapper.selectByUserId(userId);
    }

    @Override
    public int insertPhotoAlbum(Photo_album photo_album) {
       int i =  photo_albumMapper.insertSelective(photo_album);
        return i;
    }

    @Override
    public String getPhotoAlbumUrl(Integer photoId) {
        return photo_albumMapper.getPhotoAlbumUrl(photoId);
    }

    @Override
    public int deleteByPrimaryKey(Integer photoId) {
        return photo_albumMapper.deleteByPrimaryKey(photoId);
    }
}
