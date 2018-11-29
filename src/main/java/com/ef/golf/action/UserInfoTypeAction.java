package com.ef.golf.action;


import com.ef.golf.core.service.AbstractService;
import com.ef.golf.core.structure.pageModel.Page;
import com.ef.golf.service.CollectService;
import com.ef.golf.service.UserService;
import com.ef.golf.util.RedisLoginVerifyUtil;
import com.ef.golf.vo.CollectPersionsVo;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * create by xzw
 * 2017年12月28日10:16:443
 * 根据类型获取用户信息
 * 安卓调用
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UserInfoTypeAction extends AbstractService {

    @Resource
    private RedisLoginVerifyUtil redisLoginVerifyUtil;

    @Resource
    private UserService userService;

    @Resource
    private CollectService collectService;


    //@NotNull(message = "Can't be Null!")
    private String userType;

    private int pageNum = 1;
    private int showCount = 10;

    private String uid;
    private String sid;


    @Override
    public Object doService() throws Exception {
        Integer userId = redisLoginVerifyUtil.longinVerifty(sid, uid);
        CollectPersionsVo collectPersionsVo = new CollectPersionsVo();
        collectPersionsVo.setUserType(userType);
        collectPersionsVo.setUserId(userId);

        collectPersionsVo.setCurrentPage(1);
        collectPersionsVo.setShowCount(100);
        List<CollectPersionsVo> myCollectionPersion = collectService.getCollectPersionListPage(collectPersionsVo);

        collectPersionsVo.setCurrentPage(pageNum);
        collectPersionsVo.setShowCount(showCount);
        List<CollectPersionsVo> userList = userService.getUserInfoByTypeListPage(collectPersionsVo);



        List<Integer> list1 = new ArrayList<>();
        for (int j=0;j<myCollectionPersion.size();j++){
            list1.add(myCollectionPersion.get(j).getUserId());
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
        /*boolean flag = false;
        for (int i = 0; i < userList.size(); i++) {
            for (int j = 0; j < myCollectionPersion.size(); j++) {
                if(null!=myCollectionPersion){
                    flag = myCollectionPersion.get(j).getUserId().equals(userList.get(i).getUserId());
                    if (flag) {
                        break;
                    }
                }
            }
            if (!flag) {
                userList.get(i).setHasAttend("0");//未关注
            } else {
                userList.get(i).setHasAttend("1");//已关注
            }

        }*/
        Page page = new Page();
        page.setResultList(userList);
        page.setCurrentPage(pageNum);
        page.setShowCount(showCount);
        page.setTotalResult(collectPersionsVo.getTotalResult());
        page.setTotalPage(collectPersionsVo.getTotalPage());
        return page;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public void setShowCount(int showCount) {
        this.showCount = showCount;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }
}
