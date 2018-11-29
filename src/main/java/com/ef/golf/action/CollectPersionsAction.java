package com.ef.golf.action;


import com.ef.golf.common.Constant;
import com.ef.golf.core.service.AbstractService;
import com.ef.golf.core.structure.pageModel.Page;
import com.ef.golf.exception.LoginException;
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
public class CollectPersionsAction extends AbstractService {

    @Resource
    private CollectService collectService;

    @Resource
    private RedisLoginVerifyUtil redisLoginVerifyUtil;

    //@NotNull(message = "查询类型参数不能为空")
    private Integer serachType;//1关注我的 2我关注的

    //@NotNull(message = "uid参数不能为空")
    private String uid;

    //@NotNull(message = "sid参数不能为空")
    private String sid;

    private int pageNo = 1;
    private int pageSize = 10;

    @Override
    public Object doService() throws Exception {
        Integer userId = redisLoginVerifyUtil.longinVerifty(sid, uid);
        CollectPersionsVo collectPersionsVo = new CollectPersionsVo();
        collectPersionsVo.setUserId(userId);
        collectPersionsVo.setCurrentPage(this.pageNo);
        collectPersionsVo.setShowCount(100);
        List<CollectPersionsVo> getCollectPersionList = new ArrayList<>();
        if (serachType == 1) {
            //查询所有关注我的人
            getCollectPersionList = collectService.getCollectMineListPage(collectPersionsVo);
            //查询所有和我互相关注的人的id
           List<Integer> userIdsList = collectService.isCollectPersion(userId);

           /* List<CollectPersionsVo> userIdsList = collectService.getCollectPersionListPage(collectPersionsVo);*/
            for (int i = 0; i < getCollectPersionList.size(); i++) {
                //判断关注我的人，我是否关注了ta
                boolean flag = userIdsList.contains(getCollectPersionList.get(i).getUserId());
                if (flag) {
                    getCollectPersionList.get(i).setHasAttend("1");//已关注
                } else {
                    getCollectPersionList.get(i).setHasAttend("0");//未关注
                }
            }
        } else if (serachType == 2) {
            //查询我关注的人
            collectPersionsVo.setShowCount(pageSize);
            getCollectPersionList = collectService.getCollectPersionListPage(collectPersionsVo);
        } else {
            throw new LoginException(Constant.ERR_PARAMETER);
        }
        Page page = new Page();
        page.setTotalResult(collectPersionsVo.getTotalResult());
        page.setShowCount(pageSize);
        page.setCurrentPage(pageNo);
        page.setResultList(getCollectPersionList);
        page.setTotalPage(collectPersionsVo.getTotalPage());
        return page;
    }

    public void setSerachType(Integer serachType) {
        this.serachType = serachType;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
