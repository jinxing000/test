package com.ef.golf.service;

import com.ef.golf.exception.LoginException;

import com.ef.golf.pojo.IfunResult;
import com.ef.golf.pojo.User;
import com.ef.golf.pojo.UserFeedback;
import com.ef.golf.util.PageBean;
import com.ef.golf.util.SearchCaddieResult;
import com.ef.golf.vo.*;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.List;

/**
 * for efgolf
 * Created by Bart on 2017/9/20.
 * Date: 2017/9/20 16:16
 */

public interface UserService {

    User userLogin(User user);

    User getUserMsgByExclusiveNo(String exclusiveNo);

    List<User> queryUserListPage(User user);

    int userRegister(User user) throws LoginException;

    int getCount(User  user);

    UserVo userLogin(String userName, String userPwd,String lat,String lng,String loginType,String openId) throws LoginException;

    int updatePWD(String userName,String newPwd) throws LoginException;

    int updateLastLoginTime(Long id);

    User selectByPrimaryKey(Long id);

    Long getUid(String userName);
    //根据手机号获取用户
    User getUserByUserName(String userName);
    //获取关于我的信息
    MineVo getInfo(Integer userId);

    //更新用户个人信息
    int updateUser(User user);


    //根据类型（2,3,4,5）获取对应的用户信息
    List<CollectPersionsVo> getUserInfoByTypeListPage(CollectPersionsVo collectPersionsVo);

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
    List<QiuhuiVo> queryQiuHuiNames(String nickName);

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
    //球童 solr导入球童索引
    IfunResult findCaddieDatabaseToSolrIndex();
    //球童 solr查询球童列表
    SearchCaddieResult getCaddieListSolrIndex(String sortType,String serviceAge,String qiuHuiName,String realname,String exclusive_no,Integer page,Integer rows);
    //个人资料更新
    int updateByPrimaryKey(User user);

    List<UserSearchVo> fastSearchUserListPage(UserSearchVo userSearchVo,Integer userId);
    Integer fastSearchUserCount(UserSearchVo userSearchVo);
    /*List<UserSearchVo> queryAllUser(UserSearchVo userSearchVo);*/
    List<UserSearchVo> queryAllUserListPage(UserSearchVo userSearchVo);
    //获取用户id
    String getUserId(String phone);
    //获取用户真实姓名
    String getRealname(Integer id);
    Integer insertSelective(User user);
    int insertSelectiveFeedback(UserFeedback record);
    public PageBean queryUserListByType(String userType, Integer pageNo, Integer pageSize,Integer userId);
    //isRecommand  1 代表要查询推荐的  其他都是查询全部
    PageBean  selectClubOrShop(String nickname,String userType,Integer pageNo,Integer pageSize,int isRecommand);
    /** 新增拉黑用户 */
    int insertBlacklist(Integer userId, Integer byBlackId);
    PageBean selectBlackList(Long userId,Integer pageNo,Integer pageSize);
    int deleteByBlack(Long userId,Long byBlackId);
    /** 支付密码校验 */
    Integer payPwdCheck(Integer userId,String passwordPay);
    /** 获取被拉黑人userId list */
    List<Integer>getByBlackPersonByUserId(Integer userId);
}
