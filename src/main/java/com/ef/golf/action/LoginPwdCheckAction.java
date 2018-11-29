package com.ef.golf.action;

import com.ef.golf.common.Constant;
import com.ef.golf.common.pxx.util.PingUtil;
import com.ef.golf.core.service.AbstractService;
import com.ef.golf.exception.LoginException;
import com.ef.golf.exception.SystemException;
import com.ef.golf.mapper.WithdrawalMapper;
import com.ef.golf.pojo.*;
import com.ef.golf.service.*;
import com.ef.golf.util.*;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * for efgolf
 * Created by Bart on 2017/9/21.
 * Date: 2017/9/21 19:29
 */

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class LoginPwdCheckAction extends AbstractService {


    @Resource
    private UserService userService;
    @Resource
    private AccountService accountService;
    @Resource
    private UserBankService userBankService;
    @Resource
    private WithdrawalService withdrawalService;
    @Resource
    private PoundageService poundageService;
    @Resource
    private OrderNoUtil orderNoUtil;
    @Resource
    private PingUtil pingUtil;
    @Resource
    private UserRoleService userRoleService;

    private String userName;
    private String userPwd;

    public Object doService() throws LoginException, SystemException {

        Map<String,Object>map = new HashMap<>();

        //校验登录密码
        User user = new User();
        user.setNickname(userName);
        user.setPasswordLogin(EncryptUtil.str2Md5Str(userName+userPwd));
        User user1 = userService.userLogin(user);

        if(user1==null)
            throw new LoginException(Constant.ERR_USER - 10);
        if(!"1".equals(user1.getUserState()))
            throw new LoginException(Constant.ERR_USER - 11);
        if(!"1".equals(user1.getIsAlive()))
            throw new LoginException(Constant.ERR_USER - 12);

        Account account = null;
        UserBank userBank=null;
        Date date = null;
        try{
            if(null!=user1){
                //查询用余额账户
                account = accountService.getAccount(user1.getId().intValue());
                //查询用户银行卡信息
                userBank = userBankService.getUserBankDetails(user1.getId());
                //获取用户提现手续费
                Poundage poundage = poundageService.getAllPoundage(user1.getUserType());
                //查询近七天的收入  //先查询出上次提现的时间 如果没有用当前时间
                //获取提现记录
                PageBean pageBean = withdrawalService.getWithdrawalRecordByUserId(user1.getId().intValue(),"2",1,5);
                List<Withdrawal> withdrawalList = pageBean.getResultList();
                //查询七天收入
                if(withdrawalList.size()>0){
                    if(null!=withdrawalList&&null!=withdrawalList.get(0).getCreateTime()){
                        date = withdrawalList.get(0).getCreateTime();
                    }else{
                        date = new Date();
                    }
                }else{
                    date = new Date();
                }

                Double qiShouru = accountService.getUserTxBalance(date,user1.getUserType(),user1.getId().intValue());
                if(null!=qiShouru&&0<qiShouru){
                    //可提现余额
                    Double txBalance = account.getSrBalance()-qiShouru;
                    //提现手续费
                    Integer serviceCharge = Integer.valueOf(AmountUtils.changeY2F((txBalance*(poundage.getPoint()/10000.0))+""));
                    String orderNo = orderNoUtil.serialNoGenerate("02",user1.getUserName());
                    //创建ping提现
                    com.pingplusplus.model.Withdrawal withdrawal1 = pingUtil.withdrawal(Integer.valueOf(AmountUtils.changeY2F(txBalance+"")),user1.getId()+"",orderNo,serviceCharge,
                            user1.getUserType()+":"+user1.getId()+":"+"提现","custom",userBank.getBankCard(),userBank.getIderName());
                    //本地提现记录
                    if(null!=withdrawal1){
                        if("created".equals(withdrawal1.getStatus())){

                            Withdrawal withdrawal = new Withdrawal();
                                withdrawal.setAccountId(account.getAccountId());
                                withdrawal.setCreateTime(new Date());
                                withdrawal.setModifyTime(new Date());
                                withdrawal.setState("1");
                                withdrawal.setMoney(txBalance);
                                withdrawal.setRemark(userBank.getIderName()+"提现");
                                withdrawal.setAccountId(account.getAccountId());
                                withdrawal.setBankCard(userBank.getBankCard());
                                withdrawal.setIderName(userBank.getIderName());
                                withdrawal.setOrderNo(orderNo);
                                withdrawal.setPingId(withdrawal1.getId());
                                withdrawal.setServiceCharge(Double.valueOf(AmountUtils.changeF2Y(serviceCharge+"")));
                                withdrawal.setRealMoey(txBalance-(Double.valueOf(AmountUtils.changeF2Y(serviceCharge+""))));
                                withdrawal.setBankName(userBank.getBankName());
                            withdrawalService.insertSelective(withdrawal);
                                map.put("lastTxBalance",txBalance-(Double.valueOf(AmountUtils.changeF2Y(serviceCharge+""))));
                                map.put("bankCard",userBank.getBankCard());
                                map.put("iderName",userBank.getIderName());
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                map.put("txTime",simpleDateFormat.format(new Date()));

                            //申请后扣除用户提现金额
                            accountService.updateUserSrBalance(user1.getId().intValue(),account.getSrBalance()-txBalance);

                            List<Integer> userIds = userRoleService.selectHuserIdByPermission("处理用户提现");
                            for (Integer goEasy: userIds
                                    ) {
                                GoEasyUtil.pushMessage(goEasy+"","有新的提现申请,请及时处理!");
                            }

                        }
                        map.put("message","申请异常");
                        map.put("status",1);
                    }

                }else{
                    throw new SystemException(Constant.ERR_UNKNOW);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

}
