package com.ef.golf.mapper;

import com.ef.golf.pojo.Img;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ImgMapper {
    int deleteByPrimaryKey(Integer imgId);

    int insert(Img record);

    int insertSelective(Img record);

    Img selectByPrimaryKey(Integer imgId);

    int updateByPrimaryKeySelective(Img record);

    int updateByPrimaryKey(Img record);

    List<Img> getImgs(@Param("imgType")  String imgType,@Param("productId")  Integer productId);
 }