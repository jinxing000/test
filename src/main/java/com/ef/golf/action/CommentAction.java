package com.ef.golf.action;

import com.ef.golf.common.Constant;
import com.ef.golf.common.Consts;
import com.ef.golf.core.service.AbstractService;
import com.ef.golf.exception.DemandException;
import com.ef.golf.exception.LoginException;
import com.ef.golf.exception.QueryException;
import com.ef.golf.exception.SystemException;
import com.ef.golf.pojo.Comments;
import com.ef.golf.pojo.Order;
import com.ef.golf.service.*;
import com.ef.golf.util.RedisBaseDao;
import com.ef.golf.vo.SiteOrderVo;
import com.ef.golf.vo.UserVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * for efgolf
 * Created by Bart on 2017/9/21.
 * Date: 2017/9/21 19:29
 */

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CommentAction extends AbstractService {

    private int orderId;

    private String content;

    private String uid;

    //@NotNull(message = "您当前尚未登录或登录失效，请登录。。。")
    private String sid;

    //create by xzw
    private Integer[] attr_id;

    private Integer[] score;

    // TODO orderId 与 order_id 区分注释?

    private Integer order_id;

    private String product_type;

    private Integer product_id;


    @Resource
    private RedisBaseDao redisBaseDao;
    @Resource
    private OrderService orderService;

    @Resource
    private UserService userService;

    @Resource
    private ScoreService scoreService;

    @Resource
    private ProductScoreService productScoreService;


    @Resource
    private CommentService commentService;

    public Object doService() throws SystemException, LoginException, QueryException, DemandException {

        // TODO 请求参数未验证
        String rUID = redisBaseDao.get(sid);
        if (StringUtils.isEmpty(rUID) || !rUID.equals(uid))
            throw new LoginException(Constant.ERR_USER);

        Long userId = userService.getUid(uid);
        Order order = new Order();
        order.setOrderId(orderId);
        order.setCreateUser(uid);
        SiteOrderVo siteOrderVo = orderService.getSiteOrder(order);
        if (siteOrderVo == null)
            //该用户下不存在此订单
            throw new QueryException(Constant.ERR_QUERY - 2);
        if ("5".equals(siteOrderVo.getOrderState()))
            throw new DemandException(Constant.ERR_DEMAND - 6);

        Comments comments = getComment();
        comments.setCommentsType(siteOrderVo.getOrderType());
        comments.setProductId(siteOrderVo.getSiteId());
        comments.setUserId(userId);
        int commentNum = commentService.getCommentCount(comments);
        if (commentNum > 0)
            throw new DemandException(Constant.ERR_DEMAND - 5);
        commentService.saveComment(comments);

        //判断该条订单是否被评分
        boolean flag = scoreService.selectHaveScore(order_id);
        //开始评分
        if (flag == false) {
            //对订单进行评分
            scoreService.doComment(order_id, attr_id, score);
            //更新或者新增产品的平均分
            productScoreService.updateScore(product_type, product_id, attr_id);
        }
        redisBaseDao.expire(sid, Integer.valueOf(Consts.REDIS_OUT_TIME));
        return new UserVo(uid, sid);
    }

    private Comments getComment() {
        Comments comments = new Comments();
        comments.setCommentsType("");
        comments.setOrderId(orderId);
        comments.setContent(content);
        comments.setPraiseNum(0);
        comments.setCreateTime(new Date());
        comments.setIsDel(false);
        return comments;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public void setAttr_id(Integer[] attr_id) {
        this.attr_id = attr_id;
    }

    public void setScore(Integer[] score) {
        this.score = score;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }


}
