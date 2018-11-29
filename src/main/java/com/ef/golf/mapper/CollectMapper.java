package com.ef.golf.mapper;

import com.ef.golf.pojo.Collect;
import com.ef.golf.pojo.Integral;
import com.ef.golf.pojo.User;
import com.ef.golf.vo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CollectMapper {
    int deleteByPrimaryKey(Integer collectId);

    int insert(Collect record);

    int insertSelective(Collect record);

    Collect selectByPrimaryKey(Integer collectId);

    int updateByPrimaryKeySelective(Collect record);

    int updateByPrimaryKey(Collect record);

    int saveCollect(Collect collect);

    //获取收藏的球场信息
    List<CollectVo> getCollectSiteListPage(User user);

    //获取某个用户是否收藏过某个球场
    int getCollectSiteByUserId(Collect collect);

    //取消收藏球场信息
    int CancelCollect(@Param("userId") Integer userId,@Param("productId") int productId,@Param("productType") String productType);

    //圈子：查询我关注的人
    List<CollectPersionsVo> getCollectPersionListPage(CollectPersionsVo collectPersionsVo);

    //圈子：查询我关注的人的数量
    int getCollectPersionNum(Integer userId);

    //圈子：查询关注我的人
    List<CollectPersionsVo> getCollectMineListPage(CollectPersionsVo collectPersionsVo);

    //圈子：查询关注我的人的数量
    int getCollectMineNum(Integer userId);

    //圈子：查询我是否关注某个人
    List<Integer> isCollectPersion(int userId);

    //圈子：查询附近的人
    //List<CollectPersionsVo> getNearByPersionsListPage(@Param("lat") String lat,@Param("lng") String lng);
    List<CollectPersionsVo> getNearByPersionsListPage(CollectPersionsVo collectPersionsVo);

    //圈子：根据手机号(数组)查询注册的用户
    List<CollectPersionsVo> getAddressListUser(List phones);

    List<SiteCollectsVo> getSiteCollect(Map map);
    Integer getSiteCollectCount(Map map);

    List<CollectGoodsListVo>getCollectGoodsList(Map map);
    Integer getCollectGoodsListCount(Map map);
    //根据用户id和被关注人id或者被备注人id
    Collect getCollectByUserIdAndProductId(@Param("userId") Integer userId ,@Param("productId")Integer productId);

    //查询我关注的人id
    List<Integer>getMeCollectPerson(Integer userId);
}