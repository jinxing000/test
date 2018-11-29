package com.ef.golf.action;

import com.ef.golf.common.pxx.model.AuthenticationBank;
import com.ef.golf.common.pxx.util.PingUtil;
import com.ef.golf.core.service.AbstractService;
import com.ef.golf.pojo.UserBank;
import com.ef.golf.service.UserBankService;
import com.pingplusplus.model.Identification;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 private Long userId;//用户id
 private String bankPhone;//银行卡预留手机号
 private String bankCard;//银行卡号
 private String iderName;//持卡人姓名
 private String idNumber;//证件号
 private Date createTime;//创建时间
 private Long createUser;//创建人
 * */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AuthenticationBankAction extends AbstractService {

    @Resource
    private PingUtil pingUtil;
    @Resource
    private UserBankService userBankService;

    private Long userId;
    //@NotNull(message = "银行卡不可为空!")
    private String bankCard;
    //@NotNull(message = "持卡人姓名不可为空!")
    private String iderName;//持卡人姓名
    //@NotNull(message = "证件号不可为空!")//^(13[0-9]|15[012356789]|17[0678]|18[0-9]|14[57])[0-9]{8}$
    private String idNumber;//证件号

    @Pattern(regexp = "^1(3[0-9]|4[57]|5[0-35-9]|6[06-8]|7[0-9]|8[025-9])[0-9]{8}$",message = "请输入正确手机号!")
    private String phone; //银行预留手机

    private String bankName;

    @Override
    public Object doService(){

        Map<String,Object>map = new HashMap<>();
        AuthenticationBank ab = new AuthenticationBank();
            ab.setCard_number(bankCard);
            ab.setId_name(iderName);
            ab.setId_number(idNumber);
            ab.setPhone_number(phone);
        Identification identification = pingUtil.authenticationBank(ab);
        UserBank userBankDetails = userBankService.getUserBankDetails(userId);
        if(identification.getResultCode()==0){

            if(null==userBankDetails){
                UserBank userBank = new UserBank(null,userId,phone,bankCard,iderName,idNumber,new Date(),userId,bankName);
                int i = userBankService.insertSelective(userBank);
                if(i>0){
                    map.put("status",identification.getResultCode());
                    map.put("message","认证通过");
                    map.put("identification",identification);
                }
            }else{
                userBankDetails.setBankPhone(phone);
                userBankDetails.setBankCard(bankCard);
                userBankDetails.setIderName(iderName);
                userBankDetails.setIdNumber(idNumber);
                userBankDetails.setCreateTime(new Date());
                userBankDetails.setBankName(bankName);
                userBankService.updateByPrimaryKeySelective(userBankDetails);
                map.put("status",identification.getResultCode());
                map.put("message","认证通过");
                map.put("identification",identification);
            }

        }else if(identification.getResultCode()==3441){
            map.put("status",identification.getResultCode());
            map.put("message","认证失败");
            map.put("identification",identification);
        }else{
            map.put("status",identification.getResultCode());
            map.put("message","认证异常");
            map.put("identification",identification);
        }
       /* map.put("status",0);*/
        return map;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

    public void setIderName(String iderName) {
        this.iderName = iderName;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}
