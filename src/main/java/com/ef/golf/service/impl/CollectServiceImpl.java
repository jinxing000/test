package com.ef.golf.service.impl;

import com.ef.golf.common.Constant;
import com.ef.golf.exception.SystemException;
import com.ef.golf.mapper.CollectMapper;
import com.ef.golf.mapper.Product_scoreMapper;
import com.ef.golf.pojo.Collect;
import com.ef.golf.pojo.User;
import com.ef.golf.service.CollectService;
import com.ef.golf.util.PageBean;
import com.ef.golf.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * for efgolf
 * Created by Bart on 2017/9/22.
 * Date: 2017/9/22 14:55
 */

@Repository
public class CollectServiceImpl implements CollectService {

    @Resource
    private CollectMapper collectMapper;
    @Autowired
    private Product_scoreMapper product_scoreMapper;


    @Override
    public int updateByPrimaryKeySelective(Collect record) {
        return collectMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int insertSelective(Collect record) {
        return collectMapper.insertSelective(record);
    }

    public int saveCollect(Long user_id, int productId, String productType, String uid) throws SystemException {
        Collect collect = new Collect();
        collect.setUserId(user_id);
        collect.setProductId(productId);
        collect.setCollectType(productType);
        collect.setCreateTime(new Date());
        collect.setModifyTime(new Date());
        collect.setCreateUser(user_id.toString());
        collect.setModifyUser(user_id.toString());
        int  i = collectMapper.saveCollect(collect);
        if(i<1)
            throw new SystemException(Constant.ERR_SYSTEM);
        return i;
    }

    public List<CollectVo> getCollectSite(User user) {
        return collectMapper.getCollectSiteListPage(user);
    }

    public int getCollectSiteByUserId(Collect collect){
        return collectMapper.getCollectSiteByUserId(collect);
    }

    public int CancelCollect(Integer userId, int productId, String productType) throws SystemException {
        int i = collectMapper.CancelCollect(userId,productId,productType);
        if(i<=0)
            throw new SystemException(Constant.ERR_SYSTEM);
        return i;
    }

    @Override
    public List<CollectPersionsVo> getCollectPersionListPage(CollectPersionsVo collectPersionsVo) {
        return collectMapper.getCollectPersionListPage(collectPersionsVo);
    }

    @Override
    public int getCollectPersionNum(Integer userId) {
        return collectMapper.getCollectPersionNum(userId);
    }

    @Override
    public List<CollectPersionsVo> getCollectMineListPage(CollectPersionsVo collectPersionsVo) {
        return collectMapper.getCollectMineListPage(collectPersionsVo);
    }

    @Override
    public int getCollectMineNum(Integer userId) {
        return collectMapper.getCollectMineNum(userId);
    }

    @Override
    public List<Integer> isCollectPersion(int userId) {
        return collectMapper.isCollectPersion(userId);
    }

    @Override
    public Collect getCollectByUserIdAndProductId(Integer userId, Integer productId) {
        return collectMapper.getCollectByUserIdAndProductId(userId,productId);
    }

    @Override
    public List<CollectPersionsVo> getNearByPersionsListPage(CollectPersionsVo collectPersionsVo) {
        return collectMapper.getNearByPersionsListPage(collectPersionsVo);
    }

    @Override
    public List<CollectPersionsVo> getAddressListUser(List phones) {
        return collectMapper.getAddressListUser(phones);
    }

    @Override
    public PageBean getSiteCollect(Integer userId,Integer pageNo,Integer pageSize,String lon,String lat) {
        PageBean pageBean = new PageBean();

        Map<String,Object>map = new HashMap<>();
        map.put("userId",userId);
        Integer count = collectMapper.getSiteCollectCount(map);
        map.put("lon",lon);
        map.put("lat",lat);
        map.put("pageNo",(pageNo-1)*pageSize);
        map.put("pageSize",pageSize);
        List<SiteCollectsVo>list = collectMapper.getSiteCollect(map);

        for (SiteCollectsVo score: list) {
            Double siteScore = product_scoreMapper.getScoreAvg(Integer.valueOf(score.getSiteId()));
            if(null==siteScore||siteScore<0.0){
                siteScore = 0.0;
            }
            score.setScore(siteScore);
        }

        if(count!=null){
            pageBean.setTotalCount(count);
        }
        pageBean.setPageSize(pageSize);
        pageBean.setPageNo(pageNo);
        pageBean.setResultList(list);


        return pageBean;
    }

    @Override
    public PageBean getCollectGoodsList(Integer userId, Integer pageNo, Integer pageSize) {
        PageBean pageBean = new PageBean();
        Map<String,Object>map = new HashMap<>();
        map.put("userId",userId);
        Integer count = collectMapper.getCollectGoodsListCount(map);
        map.put("pageNo", (pageNo-1)*pageSize);
        map.put("pageSize",pageSize);
        List<CollectGoodsListVo>listVos = collectMapper.getCollectGoodsList(map);
        pageBean.setTotalCount(count);
        pageBean.setPageNo(pageNo);
        pageBean.setPageSize(pageSize);
        pageBean.setResultList(listVos);
        return pageBean;
    }

    @Override
    public List<Integer> getMeCollectPerson(Integer userId) {
        return collectMapper.getMeCollectPerson(userId);
    }
}
