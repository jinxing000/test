package com.ef.golf.action;

import com.ef.golf.core.service.AbstractService;
import com.ef.golf.service.CourseService;
import com.ef.golf.service.UserService;
import com.ef.golf.vo.CourseDetailsVo;
import com.ef.golf.vo.MineVo;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;
/**
 * ━━━━━━神兽出没━━━━━━
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　  ┃　　　　　　　┃
 * 　  ┃　　　━　　　┃
 * 　  ┃　┳┛　┗┳  ┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛Code is far away from bug with the animal protecting
 * 　　　　┃　　　┃    神兽保佑,代码无bug
 * 　　　　┃　　　┃
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * <p>
 * ━━━━━━感觉萌萌哒━━━━━━
 */

/**
 * create by xzw
 * 2018年2月23日11:41:56
 * 用户详情
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UserDetailsAction extends AbstractService {

    @Resource
    private UserService userService;

    private Integer customerId;

    @Override
    public Object doService() throws Exception {
        /*Map<String,Object> userDetails = new HashMap<>();*/

        MineVo mineVo = userService.getInfo(customerId);

        /*userDetails.put("userDetails",mineVo);*/
        return mineVo;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
}
