package com.ef.golf.service.impl;

import com.ef.golf.mapper.CollectMapper;
import com.ef.golf.mapper.DynamicMapper;
import com.ef.golf.mapper.ShareMapper;
import com.ef.golf.mapper.WorkTypeMapper;
import com.ef.golf.pojo.Dynamic;
import com.ef.golf.pojo.Share;
import com.ef.golf.pojo.WorkType;
import com.ef.golf.service.ShareService;
import com.ef.golf.util.PageBean;
import com.ef.golf.vo.QuanziUserVo;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * create by xzw
 * 2018年1月3日09:39:10
 */
@Repository
public class ShareServiceImpl implements ShareService {

    @Resource
    private ShareMapper shareMapper;
    @Resource
    private WorkTypeMapper workTypeMapper;
    @Resource
    private DynamicMapper dynamicMapper;
    @Resource
    private CollectMapper collectMapper;
    @Override
    public int insertSelective(Share record) {
        WorkType workType = new WorkType(null,Long.valueOf(record.getShareUserId()),
                "4",Long.valueOf(record.getShareDynamicId()),"0",new Date(),"1",null);
        workTypeMapper.insertSelective(workType);
        Dynamic dynamic = dynamicMapper.selectByPrimaryKey(record.getShareDynamicId());
        dynamic.setShareNum(dynamic.getShareNum()+1);
        dynamicMapper.updateByPrimaryKey(dynamic);
        return shareMapper.insertSelective(record);
    }

    @Override
    public PageBean getShares(Integer dynamicId,Integer pageNo,Integer pageSize,Integer userId) {
        Map<String,Object> map = new HashMap<>();
        map.put("dynamicId",dynamicId);
        Integer count = shareMapper.getSharesCount(dynamicId);
        map.put("pageNo",(pageNo-1)*pageSize);
        map.put("pageSize",pageSize);
        List<QuanziUserVo> list = shareMapper.getShares(map);
        /** 我关注的人的id */
        List<Integer>meList = collectMapper.getMeCollectPerson(userId);

        for (QuanziUserVo quv: list) {
            if(meList.contains(quv.getUserId())){
                quv.setHasAttend("1");
            }else{
                quv.setHasAttend("0");
            }

    }
        PageBean pageBean = new PageBean();
        pageBean.setResultList(list);
        pageBean.setPageSize(pageSize);
        pageBean.setPageNo(pageNo);
        pageBean.setTotalCount(count);
        return pageBean;
    }
}
