package com.ef.golf.action;

import com.ef.golf.common.Constant;
import com.ef.golf.core.service.AbstractService;
import com.ef.golf.exception.SystemException;
import com.ef.golf.pojo.Report;
import com.ef.golf.service.ReportService;
import com.ef.golf.service.UserRoleService;
import com.ef.golf.util.GoEasyUtil;
import com.ef.golf.util.RedisLoginVerifyUtil;
import com.pingplusplus.model.User;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * create by xzw
 * 2017年12月28日14:14:54
 * 新增举报记录
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ReportAction extends AbstractService {

    @Resource
    private RedisLoginVerifyUtil redisLoginVerifyUtil;

    @Resource
    private ReportService reportService;
    @Resource
    private UserRoleService userRoleService;

    private String sid;

    private String uid;


    private String beReportUserId;//被举报用户id

    private String content;//举报内容

    private String type;//举报类型

    @Override
    public Object doService() throws Exception {

       /*User user =  User.retrieve("46");
        System.out.println("===================="+user);*/

        Integer userId = redisLoginVerifyUtil.longinVerifty(sid, uid);

        int num = reportService.insertSelective(this.getRepot(userId));
        if (num < 0) {
            throw new SystemException(Constant.ERR_UNKNOW);
        }
        List<Integer> userIds = userRoleService.selectUserIdByRoleId("12");
        for (Integer goEasy: userIds
                ) {
            GoEasyUtil.pushMessage(goEasy+"","有新的举报信息,请及时处理!");
        }
        Map<String,Object> map = new HashMap<>();
        map.put("message","已举报");
        return map;
    }


    /**
     * 获取一条举报记录
     *
     * @return 记录
     */
    public Report getRepot(Integer userId) {
        Report report = new Report();
        report.setBereportedUserId(Integer.valueOf(this.beReportUserId));
        report.setCreateTime(new Date());
        report.setType(this.type);
        report.setUserId(userId);
        report.setCause(this.content);
        return report;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setBeReportUserId(String beReportUserId) {
        this.beReportUserId = beReportUserId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setType(String type) {
        this.type = type;
    }
}
