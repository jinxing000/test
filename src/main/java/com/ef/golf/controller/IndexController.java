package com.ef.golf.controller;

import com.ef.golf.common.Consts;
import com.ef.golf.common.pxx.ChargeUtil;
import com.ef.golf.common.pxx.UserUtil;
import com.ef.golf.common.pxx.model.User;
import com.ef.golf.service.UserService;
import com.ef.golf.util.RedisBaseDao;
import com.ef.golf.vo.PxxVo;
import com.google.gson.Gson;
import com.pingplusplus.Pingpp;
import com.pingplusplus.model.Charge;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

/**
 * for efgolf
 * Created by Bart on 2017/1/6.
 * Date: 2017/1/6 09:16
 */
@Controller
@RequestMapping("/Pxx")
public class IndexController {





    @Resource
    private UserService userService;

    @Resource
    private RedisBaseDao redisBaseDao;


    final static String PXX_APP_ID = Consts.PXX_APP_ID;


    @RequestMapping("/createOrder")
    @ResponseBody
    public Object createOrder(HttpServletRequest request) {


        Pingpp.apiKey = "sk_test_OC0e9OLGOebTe1SGq59GOmb1";
        StringBuilder sb = new StringBuilder();
        try(BufferedReader reader = request.getReader()) {
            char[]buff = new char[1024];
            int len;
            while((len = reader.read(buff)) != -1) {
                sb.append(buff,0, len);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(sb);


        Gson gson = new Gson();
        PxxVo pxxVo = gson.fromJson(sb.toString(), PxxVo.class);

        ChargeUtil chargeExample = new ChargeUtil(PXX_APP_ID);
        // System.out.println("------- 创建 charge -------");
        Charge charge = chargeExample.createCharge(pxxVo);
        // System.out.println(sb);
        return charge;
    }

    @RequestMapping("/createUser")
    @ResponseBody
    public Object createUser(){
        /*Pingpp.apiKey = "sk_test_OC0e9OLGOebTe1SGq59GOmb1";*/
        Pingpp.apiKey = "sk_live_BmH8Vby62d6vkrpJ8QSosJIK";
        UserUtil userUtil = new UserUtil(PXX_APP_ID);
        User pxxUser = userUtil.createUser();
        return pxxUser;
    }


    @RequestMapping("/index")
    public String Index(){
        System.out.println(redisBaseDao.exist("xzw"));


        com.ef.golf.pojo.User user = new com.ef.golf.pojo.User();
        user.setId(1L);
        List<com.ef.golf.pojo.User> list =  userService.queryUserListPage(user);
        for (com.ef.golf.pojo.User u : list) {
            System.out.println(u);
        }
        return "index";
    }

}
