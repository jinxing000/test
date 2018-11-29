package com.ef.golf.action;

import com.ef.golf.common.Constant;
import com.ef.golf.core.service.AbstractService;
import com.ef.golf.exception.LoginException;
import com.ef.golf.pojo.User;
import com.ef.golf.service.UserService;
import com.ef.golf.util.EncryptUtil;
import com.ef.golf.util.RedisBaseDao;
import com.ef.golf.util.SmsUtil;
import com.ef.golf.vo.UserVo;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * for efgolf
 * Created by Bart on 2017/9/21.
 * Date: 2017/9/21 11:31
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class RegisterAction extends AbstractService {


    private final Log log = LogFactory.getLog(RegisterAction.class);


   @Pattern(regexp = "^(13[0-9]|15[012356789]|16[0678]|17[03678]|19[0678]|18[0-9]|14[57])[0-9]{8}$",message = "请输入正确手机号!")
    private String phone;

    //@NotNull
    private String userPwd;

    //@NotNull(message = "请选择正确城市 !")
    private String city;

    @Pattern(regexp = "^[1-4]$",message = "身份错误!!")
    private String user_type = "1";

    //@NotNull(message = "请输入正确验证码 !")
    private String validateCode;

    private String exclusive_no;

    private String lat;

    private String lng;

    private String loginType="1";

    private String openId;



    @Resource
    private UserService userService;

    @Resource
    private RedisBaseDao redisBaseDao;


    public Object doService() throws LoginException {
        UserVo userVo = null;
        if("3".equals(loginType)){
            User user = userService.getUserByUserName(phone);
            if(null!=user){
                user.setOpenId(openId);
                userService.updateByPrimaryKey(user);
                userVo=userService.userLogin(null,null,lat,lng,loginType,openId);
                return userVo;
            }
        }else{
            // 验证码暂时跳过
            String realValidateCode = redisBaseDao.get("code" + phone);
            System.out.println("realValidateCode"+realValidateCode);
            if(StringUtils.isEmpty(realValidateCode)||StringUtils.isEmpty(validateCode)||!validateCode.equals(realValidateCode))
                throw new LoginException(Constant.ERR_USER - 4);

            //检测专属码是否存在
            if(StringUtils.isNotEmpty(exclusive_no)){
                User parUser = new User();
                parUser.setExclusiveNo(exclusive_no);
                //if(userService.getCount(parUser)<=0)
                    if(userService.getCount(parUser)>1)
                    throw new LoginException(Constant.ERR_USER - 6);
            }

            //为用户创建账户
            User user = getUser();
            userService.userRegister(user);


            //缓存手机号
            //redisBaseDao.save(user.getPhone(),phone);


            //注册成功后登陆
            userVo=userService.userLogin(phone,userPwd,lat,lng,loginType,openId);
            Long userId= userService.getUid(userVo.getUid());
            //创建容联云子账户
            try{
             SmsUtil.createUser(userId.toString());
            }catch (Exception e){
                e.printStackTrace();
            }

            userVo.setUserId(userId.toString());
            //新增用户积分记录
            //if(userVo.getUid()!=null&&userVo.getUid()!=""){
            if(StringUtils.isNotEmpty(userVo.getUid())){
                if(userId==null){
                    throw new LoginException(Constant.ERR_USER);
                }
            }else{
                throw new LoginException(Constant.ERR_USER);
            }
        }
        return userVo;
    }

    private String getExclusiveNo(String phone){

        // （时间戳/1000）取后5位+3位随机数 共8位
        /*long dateTime = new Date().getTime();
        String timeStr = String.valueOf(dateTime / 1000);
        return timeStr.substring(timeStr.length()-4,timeStr.length())+RandomUtil.getRandom(2);*/
        return phone.substring(1,3)+phone.substring(phone.length()-4,phone.length());
    }

    private User getUser(){
        User user = new User();
        user.setNickname(phone.substring(phone.length()-7,phone.length()));
        user.setPhone(phone);
        user.setUserName(phone);
        user.setPasswordLogin(EncryptUtil.str2Md5Str(phone+userPwd));
        user.setRegion(city);
        user.setBirthday(new Date());
        user.setUserType(user_type);
        //非打球人注册 生成专属码
        if(!"1".equals(user_type)&&!"5".equals(user_type)){
            user.setExclusiveNo(getExclusiveNo(phone));
        }
        if("3".equals(loginType)){
            user.setOpenId(openId);
        }
        user.setUserState("1");
        user.setIsAlive("1");
        user.setRecExclusiveNo(exclusive_no);
        user.setLastLoginTime(new Date());
        user.setCreateTime(new Date());
        user.setModifyTime(new Date());
        return user;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }

    public void setExclusive_no(String exclusive_no) {
        this.exclusive_no = exclusive_no;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}
