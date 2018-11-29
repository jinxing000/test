package com.ef.golf.action;

import com.ef.golf.core.service.AbstractService;
import com.ef.golf.pojo.Dynamic;
import com.ef.golf.pojo.Dynamic_comment;
import com.ef.golf.service.DynamicCommentService;
import com.ef.golf.service.DynamicService;
import com.ef.golf.util.RedisLoginVerifyUtil;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class DynamicCommentAction extends AbstractService {

    @Resource
    private RedisLoginVerifyUtil redisLoginVerifyUtil;

    @Resource
    private DynamicCommentService dynamicCommentService;

    /*private String uid;
    private String sid;*/

    //@NotNull(message = "can't be null")
    private String commentType;//评论类型（1.对动态评论 2.对评论者进行回复）

    private String replyUserId;//回复者id
    private Integer commentUserId;//评论者id

    private String dynamicId;

    private String content;


    @Override
    public Object doService() throws Exception {
        /*Integer userId=redisLoginVerifyUtil.longinVerifty(sid,uid);*/

        Dynamic_comment dynamicComment = this.getDynamicComment(commentUserId);

        dynamicCommentService.insertSelective(dynamicComment);
        return dynamicComment.getDynamicComId();
    }

    //获取评论记录
    public Dynamic_comment getDynamicComment(Integer userId) {
        Dynamic_comment dynamicComment = new Dynamic_comment();
        if (commentType.equals("1")) {
            dynamicComment.setCommentsUserId(userId);
            dynamicComment.setContent(this.content);
            dynamicComment.setDynamicId(Integer.valueOf(this.dynamicId));
            dynamicComment.setCreateTime(new Date());
            dynamicComment.setIsDel("2");
        } else if (commentType.equals("2")) {
            dynamicComment.setDynamicId(Integer.valueOf(this.dynamicId));
            dynamicComment.setIsDel("2");
            dynamicComment.setCreateTime(new Date());
            dynamicComment.setContent(this.content);
            dynamicComment.setReplyUserId(Integer.valueOf(replyUserId));
            dynamicComment.setCommentsUserId(userId);
        }
        return dynamicComment;
    }

    /*public void setUid(String uid) {
        this.uid = uid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }
*/
    public void setCommentUserId(Integer commentUserId) {
        this.commentUserId = commentUserId;
    }

    public void setCommentType(String commentType) {
        this.commentType = commentType;
    }

    public void setReplyUserId(String replyUserId) {
        this.replyUserId = replyUserId;
    }

    public void setDynamicId(String dynamicId) {
        this.dynamicId = dynamicId;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
