package com.ef.golf.action;


import com.ef.golf.common.Constant;
import com.ef.golf.core.service.AbstractService;
import com.ef.golf.exception.SystemException;
import com.ef.golf.pojo.Dynamic_comment;
import com.ef.golf.service.DynamicCommentService;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 删除圈子动态评论
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class QuanziCommentDelAction extends AbstractService {

    @Resource
    private DynamicCommentService dynamicCommentService;

    //@NotNull
    private Integer dynamicComId;
    private String userId;

    @Override
    public Object doService() throws Exception {
        Map<String, Object> map = new HashMap<>();
        int num;
        Dynamic_comment dynamic_comment = dynamicCommentService.selectByPrimaryKey(dynamicComId);
        if(userId.equals(dynamic_comment.getReplyUserId()+"")||userId.equals(dynamic_comment.getCommentsUserId()+"")){
            if (dynamic_comment.getIsDel().equals("2")) {
                num = dynamicCommentService.delCommentBydynamicComId(dynamicComId);
                if (num < 0) {
                    throw new SystemException(Constant.ERR_UPDATE);
                }
            }
        }else{
            throw new SystemException(Constant.ERR_COMMENT);
        }
        return super.doService();
    }

    public void setDynamicComId(Integer dynamicComId) {
        this.dynamicComId = dynamicComId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
