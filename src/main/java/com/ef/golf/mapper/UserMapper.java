package com.ef.golf.mapper;

import com.ef.golf.pojo.IfunResult;
import com.ef.golf.pojo.User;
import com.ef.golf.vo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    int getCount(User user);

    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    List<User> queryAll(User user);

    User getUserMsgByExclusiveNo(String exclusiveNo);

    int updateByPrimaryKeySelective(User record);

    Long getUid(String userName);

    int updateByPrimaryKey(User record);

    User userLogin(User user);

    int updatePwd(User user);

    //获取关于我的信息
    MineVo getInfo(Integer userId);

    //更新用户个人信息
    int updateUser(User user);

    //更新用户登录位置
    int updateUserLocation(User user);

    //根据类型（2,3,4,5）获取对应的用户信息
    List<CollectPersionsVo> getUserInfoByTypeListPage( CollectPersionsVo collectPersionsVo);

    //圈子：根据用户id查询用户信息
    QuanziUserVo getUserInfoById(Integer userId);

    //球童：查询本地区推荐的球童
    List<QuanziUserVo> getQiuTongListPage(QuanziUserVo quanziUserVo);
    //球童：查询本地区推荐的球童
    List<QuanziUserVo> indexQiuTongList(QuanziUserVo quanziUserVo);
    Integer getQiuTongCount(QuanziUserVo quanziUserVo);

    //球童：快速搜索球童
    List<QuanziUserVo> qiuTongListPage(QuanziUserVo quanziUserVo);

    //球童：查询球会信息
    List<QiuhuiVo> queryQiuHuiNames(String qiuHuiName);

    //教练：快速搜索教练
    List<QuanziUserVo> coachListPage(QuanziUserVo quanziUserVo);
    //教练：快速搜索教练的数量
    Integer coachListPageCount(QuanziUserVo quanziUserVo);
    //教练：查询本地区推荐的教练
    List<QuanziUserVo> getCoachListPage(QuanziUserVo quanziUserVo);
    //教练：查询本地区推荐的教练
    List<QuanziUserVo> indexCoachList(QuanziUserVo quanziUserVo);
    //教练：查询本地区推荐的教练的数量
    Integer getCoachListPageCount(QuanziUserVo quanziUserVo);
    //solr 导入球童索引
    List<SolrCaddieVo> findCaddieDatabaseToSolrIndex();
    //查询用户
    List<UserSearchVo>fastSearchUserListPage(UserSearchVo userSearchVo);
    Integer fastSearchUserCount(UserSearchVo userSearchVo);
    //所有
    List<UserSearchVo> queryAllUserListPage(UserSearchVo userSearchVo);

    String selectIdByPhone(String phone);
    String selectRealanemById(Integer id);
    //商家模块搜索
    List<ClubOrShopVo> selectClubOrShop(Map map);
    Integer selectClubOrShopCount(Map map);
    //根据用户类型
    List<UserSearchVo> queryUserListByType(Map map);
    Integer queryUserListByTypeCount(Map map);
    //支付密码校验
    Integer payPwdCheck(Map map);

    User getUserByOpenId(String openId);
    User getUserByUserName(String userName);
}