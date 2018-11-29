package com.ef.golf.util;

import com.ef.golf.vo.CashBackVo;

import java.util.List;

/**
 * Created by Administrator on 2018/6/17.
 * 用户充值赠送工具
 * 总赠送额   zengSongSum
 * 对应区段的赠送   areaZengSong
 * 对应区段的需要用来赠送的金额 areaZengSongNeed
 * 本次充值额  presentMoney
 * 区段差      areaDistance
 * 当天已冲额  todayHadMoney
 * 本日总充值  todaySumMoney=todayHadMoney+presentMoney
 * 冲送比例基础信息 list    按最小值的升序排的列表
 *    区段的最小值  最大值    区间额  比例
 *
 *  今日已冲  区间的对应的位置   todayHadMoneyIndex
 * 今日总冲的 区间的对应的位置  todaySumMoneyIndex
 *
 */
public class ZengSongUtil {
    public static double zengSong(double presentMoney,double todayHadMoney,List<CashBackVo> list){
        double zengSongSum=0.0;
        int todayHadMoneyIndex=0;
        int todaySumMoneyIndex=0;
        double areaZengSongNeed=0.0;
        double areaZengSong=0.0;
        double todaySumMoney=todayHadMoney+presentMoney;
        //寻找今日已冲  与 今日总冲的 区间的对应的位置
         //已冲
        for(int i=0;i< list.size();i++) {
            if (todayHadMoney <= list.get(i).getMaxnum()) {
                todayHadMoneyIndex = i;
                break;
            }
        }
        //总冲
        for(int i=0;i< list.size();i++) {
            if(todaySumMoney<=list.get(i).getMaxnum()){
                todaySumMoneyIndex=i;
                break;
            }
        }

        //计算返现的钱肯定是再已冲位置与总冲位置之间来计算

                //如果处于同一个区间，那么就用 本次充值*当前区间的返现比例
        if(todaySumMoneyIndex==todayHadMoneyIndex){
            zengSongSum=list.get(todaySumMoneyIndex).getRate()*presentMoney;
        }else {//从大区间向小区间循环处理
            //这种处理方式太复杂
           /* for (int i = todaySumMoneyIndex; i >= todayHadMoneyIndex; i--) {
                //本次充值减去当前记录的低限
                areaZengSongNeed=todaySumMoney-list.get(i).getMinnum();//第二次进入时相当于间距了，是否需要+1？



                if(presentMoney<=areaZengSongNeed){//这是已经到了todayHadMoneyIndex区间
                    areaZengSongNeed= todaySumMoney-todayHadMoney;
                }
                areaZengSong=areaZengSongNeed*list.get(i).getRate();

                todaySumMoney=list.get(i-1).getMaxnum();

                zengSongSum=zengSongSum+areaZengSong;//总的需要赠送

                presentMoney=presentMoney-areaZengSongNeed;

            }*/


           //简单的思路
            for (int i = todaySumMoneyIndex; i >= todayHadMoneyIndex; i--) {
                if (i ==todaySumMoneyIndex) { //最高区   （本日总冲-本区最低限）*本区比例
                    areaZengSong=(todaySumMoney-list.get(i).getMinnum())*list.get(i).getRate();

                } else if (i ==todayHadMoneyIndex) {//最低区 本区的（最高限-本日已冲）*本区比例
                    areaZengSong=(list.get(i).getMaxnum()-todayHadMoney)*list.get(i).getRate();
                } else { //其他区  之间取间距*比例
                    areaZengSong=list.get(i).getAreaDistance()*list.get(i).getRate();
                }
                zengSongSum=zengSongSum+areaZengSong;//总的需要赠送
            }
        }
        return zengSongSum;
    }
}
