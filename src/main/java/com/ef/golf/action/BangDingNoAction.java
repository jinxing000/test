package com.ef.golf.action;

import com.ef.golf.core.service.AbstractService;
import com.ef.golf.pojo.User;
import com.ef.golf.service.UserService;
import com.ef.golf.vo.MineVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * create by wxc
 * 2018年7月18日
 * 绑定邀请码
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class BangDingNoAction extends AbstractService {

    //@NotNull
    private Integer userId;
    //@NotNull
    private String exclusiveNo;

    @Resource
    private UserService userService;

    @Override
    public Object doService() throws Exception {
        Map<String,Object>map = new HashMap<>();

        try{
            MineVo mineVo = userService.getInfo(userId);
            if(!"1".equals(mineVo.getUserType())){
                map.put("status",3);
                map.put("message","您暂时无法绑定他人");
            }else{
            //检测专属码是否存在
            if(StringUtils.isNotEmpty(exclusiveNo)){
                User parUser = new User();
                parUser.setExclusiveNo(exclusiveNo);
                if(userService.getCount(parUser)<=0) {
                    map.put("status",2);
                    map.put("message","无效专属码");
                }else{
                    User exclusiveNoUser = userService.getUserMsgByExclusiveNo(exclusiveNo);
                    if("5".equals(exclusiveNoUser.getUserType())||"6".equals(exclusiveNoUser.getUserType())||"7".equals(exclusiveNoUser.getUserType())){
                        map.put("status",1);
                        map.put("message","专属码不可用");
                    }else{
                        User user = new User();
                        user.setId(userId.longValue());
                        user.setRecExclusiveNo(exclusiveNo);
                        int i = userService.updateByPrimaryKey(user);
                        if(i>0){
                            map.put("status",0);
                            map.put("message","绑定完成");
                        }else{
                            map.put("status",5);
                            map.put("message","绑定失败");
                        }
                    }
                }
            }
            }
        }catch (Exception e){
            map.put("status",4);
            map.put("message","绑定异常");
        }
        return map;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setExclusiveNo(String exclusiveNo) {
        this.exclusiveNo = exclusiveNo;
    }
}
