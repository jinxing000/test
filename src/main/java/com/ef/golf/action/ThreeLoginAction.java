/*
package com.ef.golf.action;

import com.ef.golf.core.service.AbstractService;
import com.ef.golf.exception.LoginException;
import com.ef.golf.service.UserService;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

*/
/**
 * openid
 * city
 * nickname
 * headimgurl
 * sex
 * for efgolf
 * Created by Bart on 2017/9/21.
 * Date: 2017/9/21 19:29
 *//*


@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ThreeLoginAction extends AbstractService {

    @Resource
    private UserService userService;

    private Integer loginType;//登录类型
    private String openId;
    private String city;
    private String nickname;
    private String headimgurl;
    private String sex; //1男0女


    public Object doService() throws LoginException {


        Users users = null;
        if (loginType == 0) {

        }
        if (users != null) {
            if (reqWeChatLogin.getOpenId().length() > 0) {
                if (reqWeChatLogin.getLoginType() == 0) {
                    if (users.getWechatOpenId() != null && !users.getWechatOpenId().equals("")) {
                        if (users.getWechatOpenId().equals(reqWeChatLogin.getOpenId())) {
                            RespUserInfo respUserInfo = new RespUserInfo();
                            respUserInfo.setUserId(users.getId());
                            respUserInfo.setMobile(users.getMobile());
                            respUserInfo.setAreaId(users.getAreaId());
                            respUserInfo.setAreaName(users.getAreaName());
                            respUserInfo.setInvitationCode(users.getInvitationCode());
                            respUserInfo.setPortait(users.getPortait());
                            respUserInfo.setPrice(users.getPrice());
                            respUserInfo.setProfitPrice(users.getProfitPrice());
                            respUserInfo.setUserName(users.getUserName());
                            respUserInfo.setShareCode(users.getShareCode());
                            result.setCode(ResultMsg.RespCode.SUCCESS.value);
                            result.setMsg("微信登录成功");
                            result.setData(respUserInfo);
                        } else {
                            result.setCode(ResultMsg.RespCode.FAILED.value);
                            result.setMsg("微信登录失败,授权账号错误");
                        }
                    } else {
                        users.setWechatOpenId(reqWeChatLogin.getOpenId());
                        result.setCode(ResultMsg.RespCode.SUCCESS.value);
                        result.setMsg("微信登录成功");
                    }
                } else {
                    if (users.getQqOpenId() != null && !users.getQqOpenId().equals("")) {
                        if (users.getQqOpenId().equals(reqWeChatLogin.getOpenId())) {
                            RespUserInfo respUserInfo = new RespUserInfo();
                            respUserInfo.setUserId(users.getId());
                            respUserInfo.setMobile(users.getMobile());
                            respUserInfo.setAreaId(users.getAreaId());
                            respUserInfo.setAreaName(users.getAreaName());
                            respUserInfo.setInvitationCode(users.getInvitationCode());
                            respUserInfo.setPortait(users.getPortait());
                            respUserInfo.setPrice(users.getPrice());
                            respUserInfo.setProfitPrice(users.getProfitPrice());
                            respUserInfo.setUserName(users.getUserName());
                            respUserInfo.setShareCode(users.getShareCode());
                            result.setCode(ResultMsg.RespCode.SUCCESS.value);
                            result.setMsg("QQ登录成功");
                            result.setData(respUserInfo);
                        } else {
                            result.setCode(ResultMsg.RespCode.FAILED.value);
                            result.setMsg("QQ登录失败,授权账号错误");
                        }
                    } else {
                        users.setQqOpenId(reqWeChatLogin.getOpenId());
                        result.setCode(ResultMsg.RespCode.SUCCESS.value);
                        result.setMsg("QQ登录成功");
                    }
                }
            }
            users.setDeviceToken(reqWeChatLogin.getDeviceToken());
            usersRepo.save(users);
        } else {
//			Users user = new Users();
//			user.setUserName(reqWeChatLogin.getUserName());
            if (reqWeChatLogin.getOpenId().length() > 0) {
                if (reqWeChatLogin.getLoginType() == 0) {
//					user.setWechatOpenId(reqWeChatLogin.getOpenId());
                    result.setCode(ResultMsg.RespCode.FAILED.value);
                    result.setMsg("请先用手机注册");
                } else {
//					user.setQqOpenId(reqWeChatLogin.getOpenId());
                    result.setCode(ResultMsg.RespCode.FAILED.value);
                    result.setMsg("请先用手机注册");
                }
            } else {
                result.setCode(ResultMsg.RespCode.FAILED.value);
                result.setMsg("登录失败,授权账号不能为空");
            }
//			user.setDeviceToken(reqWeChatLogin.getDeviceToken());
//			usersRepo.save(user);
        }
        return result;




        return null;
    }

    public void setLoginType(Integer loginType) {
        this.loginType = loginType;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}*/
