package com.ef.golf.controller;

import com.ef.golf.common.Constant;
import com.ef.golf.exception.LoginException;
import com.ef.golf.exception.SystemException;
import com.ef.golf.pojo.IfunResult;
import com.ef.golf.pojo.User;
import com.ef.golf.service.UserService;
import com.ef.golf.util.RedisLoginVerifyUtil;
import com.ef.golf.vo.QuanziUserVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.*;

/**
    教练下场费查询更新
 */
@Controller
@RequestMapping("/coach")
public class CoachUsePayController {

    @Resource
    private UserService userService;
    @Resource
    private RedisLoginVerifyUtil redisLoginVerifyUtil;

    @RequestMapping("/usePay")
    @ResponseBody
    public Object usePay(String uid,String sid) throws LoginException {
        Integer userId = redisLoginVerifyUtil.longinVerifty(sid,uid);
        QuanziUserVo quanziUserVo = userService.getUserInfoById(userId);
        Map<String,Object>map = new HashMap<>();
        map.put("userId",quanziUserVo.getUserId());
        map.put("usePay",quanziUserVo.getUsePay());
        return IfunResult.build(200,"ok",map);
    }
    @RequestMapping("/updateUsePay")
    @ResponseBody
    public Object updateUsePay(String uid,String sid,String usePay) throws LoginException {
        Integer userId = redisLoginVerifyUtil.longinVerifty(sid,uid);
        try{
            User user = new User();
            user.setId(Long.valueOf(userId));
            user.setUsePay(Double.valueOf(usePay));
            userService.updateByPrimaryKey(user);
        }catch (Exception e){
            IfunResult.build(1,"更新失败!");
            new SystemException(Constant.ERR_SYSTEM);
        }
        return IfunResult.ok();
    }
}
