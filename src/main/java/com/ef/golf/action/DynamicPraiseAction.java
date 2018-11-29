package com.ef.golf.action;

import com.ef.golf.common.Constant;
import com.ef.golf.core.service.AbstractService;
import com.ef.golf.exception.SystemException;
import com.ef.golf.pojo.Dynamic_praise;
import com.ef.golf.service.DynamicPraiseService;
import com.ef.golf.service.DynamicService;
import com.ef.golf.util.RedisLoginVerifyUtil;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xzw on 2017/12/28.
 * 动态点赞表新增一条记录
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class DynamicPraiseAction extends AbstractService {
    @Resource
    private RedisLoginVerifyUtil redisLoginVerifyUtil;

    @Resource
    private DynamicPraiseService dynamicPraiseService;
    @Resource
    private DynamicService dynamicService;

    private String uid;
    private String sid;
    private String dynamicId;

    private String platform;//Android Ios


    @Override
    public Object doService() throws Exception {
        Map<String, Object> map = new HashMap<>();
        Integer userId = redisLoginVerifyUtil.longinVerifty(sid, uid);
        List<Integer> users = dynamicPraiseService.selectPraisesRecord(Integer.valueOf(dynamicId));
        if (users.contains(userId)) {
            if("Ios".equals(platform)){
                throw new SystemException(Constant.ERR_YETPraise);
            }else{
                map.put("message","已经点过赞了");
            }
        } else {
            int num = dynamicPraiseService.insertSelective(this.getDynamicPraise(userId));
            if (num > 0) {
                num += dynamicService.updateDynamicNum(Integer.parseInt(dynamicId));
            }
            map.put("message", "ok");
        }
        return map;
    }

    //获取一条点赞记录
    public Dynamic_praise getDynamicPraise(Integer userId) {
        Dynamic_praise dynamicPraise = new Dynamic_praise();
        dynamicPraise.setUserId(userId);
        dynamicPraise.setCreateTime(new Date());
        dynamicPraise.setDynamicId(Integer.parseInt(this.dynamicId));
        dynamicPraise.setIsAlive("2");
        return dynamicPraise;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public void setDynamicId(String dynamicId) {
        this.dynamicId = dynamicId;
    }
}
