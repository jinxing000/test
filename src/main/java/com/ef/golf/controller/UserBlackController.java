package com.ef.golf.controller;

import com.ef.golf.pojo.Collect;
import com.ef.golf.pojo.IfunResult;
import com.ef.golf.service.CollectService;
import com.ef.golf.service.ReportService;
import com.ef.golf.service.UserService;
import com.ef.golf.util.PageBean;
import com.ef.golf.vo.ByBlackListVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * for efgolf
 * Created by Bart on 2017/1/6.
 * Date: 2017/1/6 09:16
 */
@Controller
@RequestMapping("/by")
public class UserBlackController {
    @Resource
    private UserService userService;
    @Resource
    private CollectService collectService;
    @Resource
    private ReportService reportService;
    //    /by/black 拉黑          当前用户 userId 被拉黑人 byBlackId
    //    /by/blacklist  拉黑列表      当前用户 userId pageNo pageSize
    //    /by/cancelBlack 移除拉黑 当前用户 userId 被拉黑人 byBlackId
    @ResponseBody
    @RequestMapping(value = "/black",method = RequestMethod.POST)
    public Object black(Integer userId,Integer byBlackId){
        PageBean pageBean = userService.selectBlackList(userId.longValue(),1,5);
        List<ByBlackListVo> list= pageBean.getResultList();
        for (ByBlackListVo byblist:list) {
            if(byblist.getUserId()==byBlackId.longValue()){
                Map<String,Object>map = new HashMap<>();
                map.put("status","1");
                return IfunResult.build(0,"已拉黑",map);
            }
        }
        int i = userService.insertBlacklist(userId,byBlackId);
      return IfunResult.ok();
    }

    @ResponseBody
    @RequestMapping(value = "/blacklist",method = RequestMethod.POST)
    public Object blacklist(Integer userId, @RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo, @RequestParam(value ="pageSize",defaultValue = "5") Integer pageSize){

        PageBean pageBean = userService.selectBlackList(userId.longValue(),pageNo,pageSize);

        return IfunResult.build(200,"ok",pageBean);
    }
    @ResponseBody
    @RequestMapping(value = "/cancelBlack",method = RequestMethod.POST)
    public Object cancelBlack(Integer userId,Integer byBlackId){
        int s =userService.deleteByBlack(userId.longValue(),byBlackId.longValue());
        return IfunResult.ok(s);
    }
    @ResponseBody
    @RequestMapping(value = "/remark",method = RequestMethod.POST)
    public Object remark(Integer userId,Integer remarkId,String remark){
        Map<String,Object>map = new HashMap<>();
        Collect collect = collectService.getCollectByUserIdAndProductId(userId,remarkId);
        if(null!=collect){
            collect.setRemark(remark);
            collect.setModifyTime(new Date());
            collect.setModifyUser(userId.toString());
            int i = collectService.updateByPrimaryKeySelective(collect);
            if(i>0){
                map.put("status",0);
            }else{
                map.put("status",1);
                map.put("message","服务器开小差了");
            }
        }else{
            collect.setCreateUser(userId.toString());
            collect.setCreateTime(new Date());
            collect.setModifyUser(userId.toString());
            collect.setRemark(remark);
            collect.setUserId(userId.longValue());
            collect.setProductId(remarkId);
            int i = collectService.insertSelective(collect);
            if(i>0){
                map.put("status",0);
            }else{
                map.put("status",1);
                map.put("message","服务器开小差了");
            }
        }
        return IfunResult.build(0,"ok",map);
    }
}
