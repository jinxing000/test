package com.ef.golf.action;

import com.ef.golf.core.service.AbstractService;
import com.ef.golf.core.structure.pageModel.Page;
import com.ef.golf.service.CollectService;
import com.ef.golf.util.RedisLoginVerifyUtil;
import com.ef.golf.vo.CollectPersionsVo;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class NearByPersionsAction extends AbstractService {

    @Resource
    private RedisLoginVerifyUtil redisLoginVerifyUtil;

    @Resource
    private CollectService collectService;

    private String uid;
    private String sid;

    private String lat;//纬
    private String lng;//经
    private Integer pageNum = 1;
    private Integer showCount = 7;

    @Override
    public Object doService() throws Exception {
        //验证用户是否登录
        Integer userId = redisLoginVerifyUtil.longinVerifty(sid, uid);
        List<CollectPersionsVo> userList = new ArrayList<>();

            //获取所有和我互相关注人的id
            List<Integer> userIds = collectService.isCollectPersion(userId);
            CollectPersionsVo collectPersionsVo = new CollectPersionsVo();
            collectPersionsVo.setLat(lat);
            collectPersionsVo.setLng(lng);
            collectPersionsVo.setCurrentPage(pageNum);
            collectPersionsVo.setShowCount(showCount);
            userList = collectService.getNearByPersionsListPage(collectPersionsVo);


            collectPersionsVo.setUserId(userId);
            collectPersionsVo.setShowCount(100);
            List<CollectPersionsVo> getCollectPersionList = collectService.getCollectPersionListPage(collectPersionsVo);

            List<Integer> list1 = new ArrayList<>();
            for (int j=0;j<getCollectPersionList.size();j++){
                list1.add(getCollectPersionList.get(j).getUserId());
            }

            if(userList.size()>0){
                for (int i=0;i<userList.size();i++){
                    boolean flag;
                    flag = list1.contains(userList.get(i).getUserId());
                    if(flag){
                        userList.get(i).setHasAttend("1");
                    }else{
                        userList.get(i).setHasAttend("0");
                    }
                }
            }





            /*for (int i = 0; i < userList.size(); i++) {
                boolean falg = userIds.contains(userList.get(i).getUserId());
                if (!falg) {
                    userList.get(i).setHasAttend("0");
                } else {
                    userList.get(i).setHasAttend("1");
                }
            }*/

        Page page = new Page();
        page.setTotalPage(collectPersionsVo.getTotalPage());
        page.setCurrentPage(pageNum);
        page.setShowCount(showCount);
        page.setTotalResult(collectPersionsVo.getTotalResult());
        page.setResultList(userList);
        return page;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public void setShowCount(Integer showCount) {
        this.showCount = showCount;
    }
}
