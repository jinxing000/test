package com.ef.golf.action;

import com.ef.golf.core.service.AbstractService;
import com.ef.golf.service.CollectService;
import com.ef.golf.util.RedisLoginVerifyUtil;
import com.ef.golf.vo.CollectPersionsVo;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CommunicationPersionsAction extends AbstractService {

    @Resource
    private RedisLoginVerifyUtil redisLoginVerifyUtil;

    @Resource
    private CollectService collectService;

    /*@NotNull(message = "Mustn't be Null!")
    private List<String[]> phones;*/

    private String phones;

    //@NotNull(message = "不能为空！")
    private String uid;

    //@NotNull(message = "不能为空！")
    private String sid;

    @Override
    public Object doService() throws Exception {
        Map<String, Object> map = new HashMap<>();
        String[] phone = phones.split(",");
        Integer userId = redisLoginVerifyUtil.longinVerifty(sid, uid);
        //获取手机通讯录里并在平台上的用户
        /*List<CollectPersionsVo> communicationPersionList=collectService.getAddressListUser(Arrays.asList(phones.get(0)));*/
        List<CollectPersionsVo> communicationPersionList = collectService.getAddressListUser(Arrays.asList(phone));
        //查询我关注的人
        CollectPersionsVo collectPersionsVo = new CollectPersionsVo();
        collectPersionsVo.setUserId(userId);
        collectPersionsVo.setCurrentPage(1);
        collectPersionsVo.setShowCount(100);
        List<CollectPersionsVo> myCollectPersionList = collectService.getCollectPersionListPage(collectPersionsVo);


        List<Integer> list1 = new ArrayList<>();
        for (int j=0;j<myCollectPersionList.size();j++){
            list1.add(myCollectPersionList.get(j).getUserId());
        }

        if(communicationPersionList.size()>0){
            for (int i=0;i<communicationPersionList.size();i++){
                boolean flag;
                flag = list1.contains(communicationPersionList.get(i).getUserId());
                if(flag){
                    communicationPersionList.get(i).setHasAttend("1");
                }else{
                    communicationPersionList.get(i).setHasAttend("0");
                }
            }
        }

        return communicationPersionList;
    }

    public void setPhones(String phones) {
        this.phones = phones;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }
}
