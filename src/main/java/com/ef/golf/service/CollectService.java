package com.ef.golf.service;

import com.ef.golf.exception.SystemException;
import com.ef.golf.pojo.Collect;
import com.ef.golf.pojo.Integral;
import com.ef.golf.pojo.User;
import com.ef.golf.util.PageBean;
import com.ef.golf.vo.CollectPersionsVo;
import com.ef.golf.vo.CollectVo;
import com.ef.golf.vo.PersonalCenterVo;
import com.ef.golf.vo.SiteCollectsVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * for efgolf
 * Created by Bart on 2017/9/22.
 * Date: 2017/9/22 14:53
 */
public interface CollectService {

    int updateByPrimaryKeySelective(Collect record);
    int insertSelective(Collect record);

    int saveCollect(Long user_id,int productId,String productType,String uid) throws SystemException;

    //获取收藏的球场信息
    List<CollectVo> getCollectSite(User user);

    //获取某个用户是否收藏过某个球场
    int getCollectSiteByUserId(Collect collect);

    //取消关注
    int CancelCollect(Integer userId,int productId,String productType) throws SystemException;

    //圈子：查询我关注的人
    List<CollectPersionsVo> getCollectPersionListPage(CollectPersionsVo collectPersionsVo);

    //圈子：查询我关注的人的数量
    int getCollectPersionNum(Integer userId);

    //圈子：查询关注我的人
    List<CollectPersionsVo> getCollectMineListPage(CollectPersionsVo collectPersionsVo);

    //圈子：查询关注我的人的数量
    int getCollectMineNum(Integer userId);

    //圈子：查询和我互相关注的人的id
    List<Integer> isCollectPersion(int userId);

    Collect getCollectByUserIdAndProductId(@Param("userId") Integer userId , @Param("productId")Integer productId);

    //圈子：查询附近的人
    //List<CollectPersionsVo> getNearByPersionsListPage(String lat,String lng);
    List<CollectPersionsVo> getNearByPersionsListPage(CollectPersionsVo collectPersionsVo);

    //圈子：根据手机号(数组)查询注册的用户
    List<CollectPersionsVo> getAddressListUser(List phones);
    /** 获得收藏球场 */
    PageBean getSiteCollect(Integer userId, Integer pageNo, Integer pageSize,String lon,String lat);
    /** 获得收藏商品 */
    PageBean getCollectGoodsList(Integer userId,Integer pageNo,Integer pageSize);

    //查询我关注的人id
    List<Integer>getMeCollectPerson(Integer userId);
}
