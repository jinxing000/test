package com.ef.golf.service.impl;

import com.ef.golf.core.structure.pageModel.Page;
import com.ef.golf.mapper.CollectMapper;
import com.ef.golf.mapper.Dynamic_praiseMapper;
import com.ef.golf.mapper.WorkTypeMapper;
import com.ef.golf.pojo.Dynamic_praise;
import com.ef.golf.pojo.WorkType;
import com.ef.golf.service.DynamicPraiseService;
import com.ef.golf.util.PageBean;
import com.ef.golf.vo.CollectPersionsVo;
import com.ef.golf.vo.QuanziUserVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xzw on 2017/12/28.
 */
@Repository
public class DynamicPraiseServiceImpl implements DynamicPraiseService {

    @Resource
    private Dynamic_praiseMapper dynamicPraiseMapper;
    @Resource
    private WorkTypeMapper workTypeMapper;
    @Resource
    private CollectMapper collectMapper;

    @Override
    public int insertSelective(Dynamic_praise record) {
        WorkType workType = new WorkType(null,Long.valueOf(record.getUserId()),"1",Long.valueOf(record.getDynamicId()),"0",new Date(),"1",null);
        workTypeMapper.insertSelective(workType);
        return dynamicPraiseMapper.insertSelective(record);
    }

    @Override
    public PageBean getPraises(Integer dynamicId, Integer pageNo, Integer pageSize,Integer userId) {
        Map<String,Object>map = new HashMap<>();
        map.put("dynamicId",dynamicId);
        Integer count = dynamicPraiseMapper.getPraisesCount(dynamicId);
        map.put("pageNo",(pageNo-1)*pageSize);
        map.put("pageSize",pageSize);
        List<QuanziUserVo> list = dynamicPraiseMapper.getPraises(map);
        if(null!=userId&&userId>0) {
            /** 获取我关注的人 */
            /*CollectPersionsVo collectPersionsVo = new CollectPersionsVo();
            collectPersionsVo.setUserId(userId);
            collectPersionsVo.setCurrentPage(1);
            collectPersionsVo.setShowCount(100);
            List<CollectPersionsVo> guanzhu = collectMapper.getCollectPersionListPage(collectPersionsVo);*/

            /** 我关注的人的id */
            List<Integer>meList = collectMapper.getMeCollectPerson(userId);

            for (QuanziUserVo quv: list) {
                if(meList.contains(quv.getUserId())){
                    quv.setHasAttend("1");
                }else{
                    quv.setHasAttend("0");
                }

            }
        }

            /*for (QuanziUserVo quv : list
                    ) {
                for (CollectPersionsVo cpv : guanzhu
                        ) {
                    if (null != quv.getUserId()) {
                        if (null != cpv.getUserId()) {
                            if (quv.getUserId() == cpv.getUserId()) {
                                quv.setHasAttend("1");
                            } else {
                                quv.setHasAttend("0");
                            }
                        }
                    }
                }
            }*/

        PageBean pageBean = new PageBean();
        pageBean.setResultList(list);
        pageBean.setPageSize(pageSize);
        pageBean.setPageNo(pageNo);
        pageBean.setTotalCount(count);
        return pageBean;
    }

    @Override
    public List<Integer> selectPraisesRecord(Integer dynamicId) {
        return dynamicPraiseMapper.selectPraisesRecord(dynamicId);
    }

    @Override
    public int delPraises(Integer userId, Integer dynamicId) {
        Map<String,Object>map = new HashMap<>();
        map.put("userId",userId);
        map.put("dynamicId",dynamicId);
        return dynamicPraiseMapper.delPraises(map);
    }
}
