package com.ef.golf.listener;

import com.ef.golf.common.pxx.util.PingUtil;
import com.ef.golf.pojo.Flow;
import com.ef.golf.pojo.Hope;
import com.ef.golf.pojo.RedPackage;
import com.ef.golf.service.*;
import com.ef.golf.util.AmountUtils;
import com.ef.golf.util.OrderNoUtil;
import com.ef.golf.util.RedisBaseDao;
import com.ef.golf.vo.MineVo;
import com.ef.golf.vo.RedPackageRefundVo;
import com.pingplusplus.model.Transfer;
import org.quartz.DisallowConcurrentExecution;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * com.ef.golf.listener
 * Administrator
 * 2018/5/31 16:47
 * 定时任务*/
    //每隔5秒执行一次：*/5 * * * * ?
    //每隔1分钟执行一次：0 */1 * * * ?
    //每天23点执行一次：0 0 23 * * ?
    //每天凌晨1点执行一次：0 0 1 * * ?
    //每月1号凌晨1点执行一次：0 0 1 1 * ?
    //每月最后一天23点执行一次：0 0 23 L * ?
    //每周星期天凌晨1点实行一次：0 0 1 ? * L
    //在26分、29分、33分执行一次：0 26,29,33 * * * ?
    //每天的0点、13点、18点、21点都执行一次：0 0 0,13,18,21 * * ?

@Component
@DisallowConcurrentExecution//当前的job执行后，再执行下次的job
public class QuartzJob {

    @Resource
    private HopeService hopeService;
    @Resource
    private RedPackageService redPackageService;
    @Resource
    RedisBaseDao redisBaseDao;
    @Resource
    private FlowService flowService;
    @Resource
    private PingUtil pingUtil;
    @Resource
    private OrderNoUtil orderNoUtil;
    @Resource
    private OrderService orderService;
    @Resource
    private UserService userService;

  //@Scheduled(cron = "0 */1 * * * ?")//
  public void updateCaddieOrderState() throws InterruptedException {

        int i = orderService.updateCaddieOrderState();

      System.out.println("球童成功预约订单到达时间更新状态=="+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"==更改"+i+"条");

       int j = orderService.updateSiteOrderState();

      System.out.println("订场确认成功订单到达时间更新状态=="+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"==更改"+j+"条");
    }
    /** 未支付订单 */
  // @Scheduled(cron = "0 */15 * * * ?")//
 public void updateOrderState() throws InterruptedException {
        int count = orderService.chaoshiOrder();
        System.out.println("未支付超时订单到达时间更新状态=15分钟一次="+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"==更改"+count+"条");
    }

    /** 过期红包退款 */
   //@Scheduled(cron = "0 0 0,6,9,12,15,18,21 * * ?")
    public void  redPackageRefundMoney(){
        try{
            //这里是 过时红包id 发包人 应退金额(分)
            List<RedPackageRefundVo>list = redPackageService.redPackageRefundMoney();
            //此处去key红包id 在redis校验是否存在
            for (RedPackageRefundVo rpr: list) {
                if(redisBaseDao.exist(rpr.getBigRedPackageId())){
                    continue;//存在跳过 否则企业付执行退款
                }else{
                    //调用企业付 退款
                    MineVo mineVo = userService.getInfo(Integer.valueOf(rpr.getUserId()));
                    String orderNo = orderNoUtil.serialNoGenerate("03", mineVo.getPhone());
                    Transfer transfer = pingUtil.transferAccounts(rpr.getAmount().intValue(),"balance",orderNo,rpr.getUserId(),"6");
                    //红包退款流水
                    Flow flow = new Flow();
                    flow.setUserId(Integer.valueOf(rpr.getUserId()));
                    flow.setMoney(Double.valueOf(AmountUtils.changeF2Y(rpr.getAmount())));
                    flow.setSequenceNumber(transfer.getOrderNo());
                    flow.setFlowType("8888");
                    flow.setCreateTime(new Date());
                    //余额
                    flowService.insertSelective(flow,"(balance)qyf");
                    //余额更新在webhooks
                    //最后退款完成要把红包失效避免重复退款
                    RedPackage redPackage = new RedPackage();
                    redPackage.setId(rpr.getBigRedPackageId());
                    redPackage.setState(1);
                    redPackageService.updateByPrimaryKeySelective(redPackage);
                }
            }

        }catch (Exception e){
            System.out.println("：：：：：：：：：：：：：：过期红包退款定时器异常");
            e.printStackTrace();
        }
    }

   //@Scheduled(cron = "0 0 0,6,9,12,15,18,21 * * ?")//
    //@Scheduled(fixedRate = 20000)//每个20秒执行一次
    public void hope(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("此时此刻查询了愿望"+new Date());
        List<Hope>allHope = hopeService.selectAllHope();
        try{
            for (Hope hope:allHope
                    ) {
                System.out.println("hopeType="+hope.getHopeType());
                /** 当前时间等于失效时间 */
                if("2".equals(hope.getHopeType())){//商品
                    if (hope.getReserved3() != null) {
                        if (simpleDateFormat.parse(simpleDateFormat.format(hope.getReserved3())).getTime()
                                <= simpleDateFormat.parse(simpleDateFormat.format(new Date())).getTime()) {
                            if("1".equals(hope.getHopeState())) {
                                hope.setHopeState("3");
                                hopeService.updateByPrimaryKeySelective(hope);
                            }
                        }
                    }
                }
                if("1".equals(hope.getHopeType())){
                    if (hope.getReserved3() != null) {

                        //等于打球日期且已实现更新为已领取
                        if(simpleDateFormat.parse(simpleDateFormat.format(hope.getBeginDate())).getTime()
                                <= simpleDateFormat.parse(simpleDateFormat.format(new Date())).getTime()){
                            if("2".equals(hope.getHopeState())) {
                                hope.setGetStauts("1");
                                hope.setReserved4(new Date());
                                hopeService.updateByPrimaryKeySelective(hope);
                            }
                        }
                        if(simpleDateFormat.parse(simpleDateFormat.format(hope.getReserved3())).getTime()
                                <= simpleDateFormat.parse(simpleDateFormat.format(new Date())).getTime()) {//等于失效时间失效愿望
                            if ("1".equals(hope.getHopeState())) {
                                hope.setHopeState("3");
                                hope.setGetStauts("2");
                                hopeService.updateByPrimaryKeySelective(hope);
                            }
                        }
                    }

                }
            }
        }catch (Exception e){
            System.out.println("定时器愿望处理出现异常"+e.getMessage());
            e.printStackTrace();
            //http://47.92.145.3:80/golf/pingNotice/getNotice
        }
    }
}
