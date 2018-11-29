package com.ef.golf.service.impl;

import com.ef.golf.common.Constant;
import com.ef.golf.core.structure.pageModel.Page;
import com.ef.golf.exception.SystemException;
import com.ef.golf.mapper.*;
import com.ef.golf.pojo.*;
import com.ef.golf.service.HopeService;
import com.ef.golf.util.PageBean;
import com.ef.golf.vo.QuanziHopeDetailVo;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;


/**
 * create by xzw
 * 2018年1月3日14:09:00
 */
@Repository
public class HopeServiceImpl implements HopeService {

    @Resource
    private HopeMapper hopeMapper;
    @Resource
    private GetHopeMsgMapper getHopeMsgMapper;

    @Resource
    private ProductMapper productMapper;
    @Resource
    private ImgMapper imgMapper;
    @Resource
    private SiteMapper siteMapper;
    @Resource
    private Site_priceMapper sitePriceMapper;
    @Resource
    private GoodsMapper goodsMapper;

    @Override
    public List<Hope> getUnrealizedHopes(Integer userId) {
        return hopeMapper.getUnrealizedHopes(userId);
    }

    @Override
    public Hope selectByPrimaryKey(Integer hopeId) {
        return hopeMapper.selectByPrimaryKey(hopeId);
    }

    @Override
    public Integer updateByPrimaryKeySelective(Hope hope) {

        return hopeMapper.updateByPrimaryKeySelective(hope);
    }

    @Override
    public int insertSelective(Hope record) throws SystemException {



        if("1".equals(record.getHopeType())){
            List<Img> siteImgs= imgMapper.getImgs("1",record.getProductId());
            Double price =  sitePriceMapper.getNowPrice(Integer.valueOf(record.getProductId()),record.getBeginDate());
            Site site = siteMapper.selectSiteByPrimaryKey(record.getProductId());
            record.setReserved1(siteImgs.get(0).getImgUrl());
            record.setReserved2(site.getReserve4());
            //价格可能不存在
            if(null==price){
                throw new SystemException(Constant.ERR_UNKNOW);
            }
            record.setHopeMoney(price);
        }else{

            Product product = productMapper.selectByPrimaryKey(record.getProductId());
            List<GoodsGllery> goodsGllery = goodsMapper.glleryList(product.getGoodsId());//详情中的商品图片轮播
            record.setReserved2(product.getName());
            record.setHopeMoney(product.getPrice().doubleValue());
            record.setReserved1(goodsGllery.get(0).getOriginal());
        }
        return hopeMapper.insertSelective(record);
    }

    @Override
    public Hope getHopeDetails(Integer userId) {
        return null;
    }

    @Override
    public QuanziHopeDetailVo getHopeDetailById(Integer hopeId) {
        return hopeMapper.getHopeDetailById(hopeId);
    }

    @Override
    public PageBean getHopeList(Integer userId, String hopeState, Integer pageNo, Integer pageSize) {
        PageBean pageBean = new PageBean();
        /*pageNo = ;*/
        Map<String,Object>map = new HashMap<>();
        map.put("userId",userId);
        map.put("hopeState",hopeState);
        Integer count = hopeMapper.getHopeListCount(map);
        map.put("pageNo",(pageNo-1)*pageSize);
        map.put("pageSize",pageSize);
        List<Hope>list =  hopeMapper.getHopeList(map);
        pageBean.setPageNo(pageNo);
        pageBean.setPageSize(pageSize);
        pageBean.setTotalCount(count);
        pageBean.setResultList(list);
        return pageBean;
    }

    @Override
    public Integer insertSelective(GetHopeMsg getHopeMsg) {
        return getHopeMsgMapper.insertSelective(getHopeMsg);
    }

    @Override
    public GetHopeMsg getGetHopeMsg(Integer hopeId) {
        return getHopeMsgMapper.getGetHopeMsg(hopeId);
    }

    @Override
    public List<Hope> selectAllHope() {
        return hopeMapper.selectAllHope();
    }
}
