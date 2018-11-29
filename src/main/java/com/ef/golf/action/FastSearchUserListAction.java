package com.ef.golf.action;

import com.ef.golf.common.Constant;
import com.ef.golf.core.service.AbstractService;
import com.ef.golf.core.structure.pageModel.Page;
import com.ef.golf.exception.SystemException;
import com.ef.golf.pojo.IfunResult;
import com.ef.golf.service.CollectService;
import com.ef.golf.service.UserService;
import com.ef.golf.vo.CollectPersionsVo;
import com.ef.golf.vo.UserSearchVo;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * 加新关注搜索
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FastSearchUserListAction extends AbstractService {
    @Resource
    private UserService userService;
    @Resource
    private CollectService  collectService;

    //@Pattern(regexp = "^(13[0-9]|15[012356789]|17[03678]|18[0-9]|14[57])[0-9]{8}$", message = "请输入正确手机号!")
    private String phone;
    private String nickname;
    private Integer pageNo = 1;
    private Integer pageSize = 5;
    private Integer userId;

    public Object doService() throws SystemException {
        try {
            UserSearchVo userSearchVo = new UserSearchVo();
            userSearchVo.setNickname(nickname);
            userSearchVo.setPhone(phone);
            userSearchVo.setCurrentPage(pageNo);
            userSearchVo.setShowCount(pageSize);
            /** 我关注的人的id */
            List<Integer>meList = collectService.getMeCollectPerson(userId);
            List<UserSearchVo> list = userService.fastSearchUserListPage(userSearchVo,userId);

            for (UserSearchVo usv:list
                 ) {
                usv.setHasAttend("0");
                if(meList.contains(usv.getUserId())){
                    usv.setHasAttend("1");
                }
            }
            int count = userService.fastSearchUserCount(userSearchVo);
            Page page = new Page();
            page.setShowCount(pageSize);
            page.setCurrentPage(pageNo);
            page.setTotalResult(count);
            page.setResultList(list);
            return page;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException(Constant.ERR_QUERY);
        }
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
