package com.ef.golf.service.impl;

import com.ef.golf.mapper.ImgMapper;
import com.ef.golf.pojo.Img;
import com.ef.golf.service.ImgService;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2018/3/17.
 */
@Repository
public class ImgServiceImpl implements ImgService {
    @Resource
    private ImgMapper imgMapper;

    @Override
    public List<Img> getImgs(String imgType, Integer productId) {
        return imgMapper.getImgs(imgType,productId);
    }
}
