package com.ef.golf.service;

import com.ef.golf.pojo.Img;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2018/3/17.
 */
public interface ImgService {

    List<Img> getImgs(String imgType, Integer productId);
}
