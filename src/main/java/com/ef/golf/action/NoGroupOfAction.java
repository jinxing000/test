package com.ef.golf.action;

import com.ef.golf.core.service.AbstractService;
import com.ef.golf.service.CollectService;
import com.ef.golf.service.GroupService;
import com.ef.golf.util.RedisLoginVerifyUtil;
import com.ef.golf.vo.CollectPersionsVo;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * 通讯录未加群的人
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class NoGroupOfAction extends AbstractService {

    @Resource
    private RedisLoginVerifyUtil redisLoginVerifyUtil;

    @Resource
    private CollectService collectService;
    @Resource
    private GroupService groupService;

    private String phones;

    //@NotNull(message = "不能为空！")
    private String uid;

    //@NotNull(message = "不能为空！")
    private String sid;

    private String groupId;

    @Override
    public Object doService() throws Exception {

        /*List<Integer>lists = JSONObject.parseArray(phones,Integer.class);*/
        Map<String, Object> map = new HashMap<>();
        List<CollectPersionsVo> result = new ArrayList<>();
        String[] phone = phones.replaceAll(" ","").split(",");
        Integer userId = redisLoginVerifyUtil.longinVerifty(sid, uid);
        //获取手机通讯录里并在平台上的用户

        List<CollectPersionsVo> communicationPersionList = collectService.getAddressListUser(Arrays.asList(phone));

        List<Integer> groupList = groupService.getGroupRen(groupId);
        for (CollectPersionsVo cpl : communicationPersionList) {
            if (!groupList.contains(cpl.getUserId())) {
                result.add(cpl);
            }
        }
        map.put("communicationPersionList",communicationPersionList);
        map.put("result",result);
        return map;
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

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
