package com.ef.golf.service.impl;

import com.ef.golf.aspectj.Syslog;
import com.ef.golf.common.Constant;
import com.ef.golf.common.Consts;
import com.ef.golf.common.pxx.util.PingUtil;
import com.ef.golf.dao.SolrCaddieDao;
import com.ef.golf.exception.LoginException;
import com.ef.golf.mapper.*;
import com.ef.golf.pojo.*;
import com.ef.golf.service.UserService;
import com.ef.golf.util.*;
import com.ef.golf.vo.*;
import org.apache.commons.lang.StringUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.*;
import java.util.Calendar;

/**
 * for efgolf
 * Created by Bart on 2017/9/20.
 * Date: 2017/9/20 16:22
 */

@Repository
public class UserServiceImpl implements UserService {


    @Resource
    private UserMapper userMapper;
    @Resource
    private UserFeedbackMapper userFeedbackMapper;
    @Resource
    private ByBlackMapper byBlackMapper;
    @Resource
    private CollectMapper collectMapper;
    @Resource
    private AccountMapper accountMapper;
    @Resource
    private IntegralMapper integralMapper;
    @Resource
    private IntegralRecordMapper integralRecordMapper;
    @Resource
    private UserSignMapper userSignMapper;
    @Resource
    private RedisBaseDao redisBaseDao;

    private SolrClient solrClient;
    @Resource
    private SolrCaddieDao solrCaddieDao;
    @Resource
    private PingUtil pingUtil;

    @Resource
    private EfActivityMapper efActivityMapper;
    @Resource
    private UserTicketMapper userTicketMapper;
    @Resource
    private TicketsMapper ticketsMapper;

    @Override
    public Integer insertSelective(User user) {
        return userMapper.insertSelective(user);
    }
    /** 反馈信息 */
    @Override
    public int insertSelectiveFeedback(UserFeedback record) {
        return userFeedbackMapper.insertSelective(record);
    }

    @Override
    public PageBean queryUserListByType(String userType, Integer pageNo, Integer pageSize,Integer userId) {
        Map<String,Object>map = new HashMap<>();
        PageBean pageBean = new PageBean();
        map.put("userType",userType);
        Integer count = userMapper.queryUserListByTypeCount(map);
        map.put("pageNo",(pageNo-1)*pageSize);
        map.put("pageSize",pageSize);
        List<UserSearchVo>list = userMapper.queryUserListByType(map);
        /** 查询我关注的人 */
        CollectPersionsVo collectPersionsVo = new CollectPersionsVo();
        collectPersionsVo.setUserId(userId);
        collectPersionsVo.setCurrentPage(pageNo);
        collectPersionsVo.setShowCount(100);
        List<CollectPersionsVo> getCollectPersionList = collectMapper.getCollectPersionListPage(collectPersionsVo);
        //获取所有和我互相关注人的id
        /*List<Integer> userIds = collectMapper.isCollectPersion(userId);*/

        List<Integer> list1 = new ArrayList<>();
        for (int j=0;j<getCollectPersionList.size();j++){
            list1.add(getCollectPersionList.get(j).getUserId());
        }

        if(list.size()>0){
            for (int i=0;i<list.size();i++){
                boolean flag;
                flag = list1.contains(list.get(i).getUserId());
                if(flag){
                    list.get(i).setHasAttend("1");
                }else{
                    list.get(i).setHasAttend("0");
                }
            }
        }

        /*for (int i=0;i<list.size();i++){
            for (int j=0;j<getCollectPersionList.size();j++){
                boolean flag;
                flag = list.get(i).getUserId()==getCollectPersionList.get(j).getUserId();
                if(flag){
                    list.get(i).setHasAttend("1");
                }else{
                    list.get(i).setHasAttend("0");
                }
            }
        }*/

        /*for (int i=0;i<list.size();i++){
                boolean flag;
                flag = userIds.contains(list.get(i).getUserId());
                if(flag){
                    list.get(i).setHasAttend("1");
                }else{
                    list.get(i).setHasAttend("0");
                }
        }*/
        pageBean.setResultList(list);
        pageBean.setPageNo(pageNo);
        pageBean.setPageSize(pageSize);
        pageBean.setTotalCount(count);
        return pageBean;
    }

    /** 商家 */
    @Override
    public PageBean selectClubOrShop(String nickname, String userType,Integer pageNo,Integer pageSize,int isRecommand) {
        PageBean pageBean = new PageBean();
        Map<String,Object>map = new HashMap<>();
        Integer oldPageNo=pageNo;
        pageNo = (pageNo-1)*pageSize;
        map.put("nickname",nickname);
        map.put("userType",userType);
        map.put("isRecommand",isRecommand);
        Integer count = userMapper.selectClubOrShopCount(map);
        map.put("pageNo",pageNo);
        map.put("pageSize",pageSize);
        List<ClubOrShopVo>list = userMapper.selectClubOrShop(map);
        pageBean.setPageNo(oldPageNo);
        pageBean.setPageSize(pageSize);
        pageBean.setTotalCount(count);
        pageBean.setResultList(list);
        return pageBean;
    }

    @Override
    public List<UserSearchVo> queryAllUserListPage(UserSearchVo userSearchVo) {
        return userMapper.queryAllUserListPage(userSearchVo);
    }

    @Override
    public int insertBlacklist(Integer userId, Integer byBlackId) {
        ByBlack byBlack = new ByBlack();
        byBlack.setUserId(userId.longValue());
        byBlack.setByBlackId(byBlackId.longValue());
        byBlack.setBlackDate(new Date());
        return byBlackMapper.insertSelective(byBlack);
    }

    @Override
    public PageBean selectBlackList(Long userId,Integer pageNo,Integer pageSize) {
        PageBean pageBean = new PageBean();
        Map<String,Object>map = new HashMap<>();
        map.put("userId",userId);
        int count = byBlackMapper.selectBlackListCount(map);
        map.put("pageNo",(pageNo-1)*pageSize);
        map.put("pageSize",pageSize);
        List<ByBlackListVo> list = byBlackMapper.selectBlackList(map);
        pageBean.setTotalCount(count);
        pageBean.setPageSize(pageSize);
        pageBean.setPageNo(pageNo);
        pageBean.setResultList(list);
        return pageBean;
    }

    @Override
    public int deleteByBlack(Long userId, Long byBlackId) {
        Map<String,Object>map = new HashMap<>();
        map.put("userId",userId);
        map.put("byBlackId",byBlackId);
        return byBlackMapper.deleteByBlack(map);
    }

    @Override
    public Integer payPwdCheck(Integer userId, String passwordPay) {
        Map<String,Object>map = new HashMap<>();
        map.put("userId",userId);
        map.put("passwordPay",passwordPay);
        return userMapper.payPwdCheck(map);
    }

    @Override
    public List<Integer> getByBlackPersonByUserId(Integer userId) {
        return byBlackMapper.getByBlackPersonByUserId(userId);
    }

    @Override
    public User userLogin(User user) {
        return userMapper.userLogin(user);
    }

    @Override
    public User getUserMsgByExclusiveNo(String exclusiveNo) {
        return userMapper.getUserMsgByExclusiveNo(exclusiveNo);
    }

    public List<User> queryUserListPage(User user) {
        return userMapper.queryAll(user);
    }
    public int userRegister(User user) throws LoginException {


        User parUser = new User();
        parUser.setUserName(user.getUserName());
        if(getCount(parUser)>=1) {
            throw new LoginException(Constant.ERR_USER - 5);
        }
        if(userMapper.insertSelective(user)<=0){
            throw new LoginException(Constant.ERR_USER - 1);
        }
        /*if(userMapper.insertSelective(user)!=1){
            throw new LoginException(Constant.ERR_USER - 1);
        }*/


      com.pingplusplus.model.User user2 = pingUtil.createUser(user.getId().toString());
        //System.out.println("==============="+user2.getId());
        /** 创建用户账户 返回id */
        Account account = new Account();
        account.setCreateTime(new Date());
        account.setBalance((double) 0);
        account.setIsAlive("1");
        account.setUserId(user.getId());
        accountMapper.insertSelective(account);
        /** 创建积分 */
        Integral integral = new Integral();
        integral.setUserId(user.getId());
        integral.setScoreTotal(200);
        integral.setModifyTime(new Date());
        integral.setIsAlive("1");
        integral.setCreateTime(new Date());
        integralMapper.insertSelective(integral);

        IntegralRecord integralRecord = new IntegralRecord();
        integralRecord.setUserId(user.getId());
        integralRecord.setCreateTime(new Date());
        integralRecord.setScore("+200");
        integralRecord.setAlterationNote("注册送积分");
        integralRecordMapper.insertSelective(integralRecord);

        UserSign userSign = new UserSign();
        userSign.setUserId(user.getId().intValue());
        userSignMapper.insertSelective(userSign);

        /*User user1 = new User();
            user1.setId(user.getId());*/
            /*user1.setAccount(account.getAccountId().toString());*/
        /*userMapper.updateByPrimaryKeySelective(user1);*/

        /** state 0 开 1 关
         *  type_one 0 注册活动 1 待定
         *  type_ywo 0 送劵 1 待定
         * */
        List<ActivityMessageVo>list = efActivityMapper.getActivityMessage("0");
        if(0<list.size()&&null!=list){
            this.registerGiveCoupon(user.getId().intValue(),list);
        }
        return 0;
    }

    /** 注册发劵 */
    protected int registerGiveCoupon(int userId,List<ActivityMessageVo>list){

        //当天0点0分0秒
        //改为转天 与10月8号 对比 那个大用那个
        Calendar beginTime = Calendar.getInstance();
        //beginTime.setTime(new Date());
        beginTime.add(Calendar.DAY_OF_MONTH,1);
        beginTime.set(Calendar.HOUR_OF_DAY, 0);
        beginTime.set(Calendar.MINUTE, 0);
        beginTime.set(Calendar.SECOND, 0);
        Date beginDate = beginTime.getTime();

        //三个月后的
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(beginTime.getTime());
        calendar.add(Calendar.MONTH,3);

        /*try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
            Date beginMaxDate = simpleDateFormat.parse("2018-10-08 00:00:00");
            if(beginDate.compareTo(beginMaxDate) <=0){
                beginDate = beginMaxDate;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }*/

        List<UserTicket>ticketsList = new ArrayList<>();
        Date date = new Date();
        for (ActivityMessageVo amv: list) {//注册送劵活动
            if("0".equals(amv.getTypeTwo())){

              Tickets tickets = ticketsMapper.selectByPrimaryKey(Integer.valueOf(amv.getTemplateId()));

              if(null!=tickets){

                int couponNum = Integer.valueOf(amv.getCouponNum());//这里是各模板送劵数量

                for (int i = 0;i<couponNum;i++){//循环数量发劵

                    UserTicket userticket = new UserTicket();

                    userticket.setUserId(Long.valueOf(userId));
                    userticket.setCreateTime(date);
                    userticket.setModifyTime(date);
                    userticket.setCreateUser("0");
                    userticket.setModifyUser("0");
                    userticket.setState(3);
                    userticket.setEffectiveTime(beginDate);
                    /*userticket.setExpiryTime(tickets.getExpiryTime());*/
                    userticket.setExpiryTime(calendar.getTime());//改为开始时间后三个月
                    userticket.setTicketId(Integer.valueOf(amv.getTemplateId()));
                    userticket.setLocation(tickets.getAttribution());
                    userticket.setProductId(Long.valueOf(tickets.getProductId()));
                    ticketsList.add(userticket);
                }
            }
            }
        }
        if(ticketsList.size() >0  ){
            int i = userTicketMapper.insertUserTickets(ticketsList);
        }


        return 1;
    }


    public int getCount(User user) {
        return  userMapper.getCount(user);
    }

    public UserVo userLogin(String userName, String userPwd,String lat,String lng,String loginType,String openId) throws LoginException {

        User loginUser =null;

        if(null!=openId&&!"".equals(openId)&&"3".equals(loginType)){
            loginUser = userMapper.getUserByOpenId(openId);
            if(null==loginUser){
                UserVo userVo = new UserVo("-10010","-10010","-10010");
                return userVo;
            }
        }else{
            User user = new User();
            user.setNickname(userName);
            user.setPasswordLogin(EncryptUtil.str2Md5Str(userName+userPwd));

            loginUser = userMapper.userLogin(user);
        }

        if(loginUser==null)
            throw new LoginException(Constant.ERR_USER - 10);
        if(!"1".equals(loginUser.getUserState()))
            throw new LoginException(Constant.ERR_USER - 11);
        if(!"1".equals(loginUser.getIsAlive()))
            throw new LoginException(Constant.ERR_USER - 12);

        updateLastLoginTime(loginUser.getId());
        updateLastLoginLocation(lat,lng,loginUser.getId());
        String sessionID = ActiveUserManager.getInstance().createSessionID();
        if(redisBaseDao.exist(sessionID))
            sessionID =  ActiveUserManager.getInstance().createSessionID();
        if(loginType.equals("1")){
            redisBaseDao.save(sessionID,userName);
            //redisBaseDao.saveEx(sessionID,userName,Integer.valueOf(Consts.REDIS_MOBLE_OUT_TIME));//移动端设置redis永不过期
        }else {
            redisBaseDao.saveEx(sessionID,userName,Integer.valueOf(Consts.REDIS_OUT_TIME));
        }

        System.out.println("=========华丽分割线==========");
        System.out.println(loginUser.getUserName());
        /*return new  UserVo(loginUser.getUserName(),sessionID);*/
        return new  UserVo(loginUser.getId().toString(),loginUser.getUserName(),sessionID);
    }

    public int updatePWD(String userName, String newPwd) throws LoginException {
        User user = new User();
        user.setNickname(userName);
        if(getCount(user)<=0)
            throw new LoginException(Constant.ERR_USER - 7);
        String newLoginPwd = EncryptUtil.str2Md5Str(userName + newPwd);
        user.setPasswordLogin(newLoginPwd);
        if(userMapper.updatePwd(user)<0)
            throw new LoginException(Constant.ERR_USER - 2);
        return 0;
    }

    public int updateLastLoginTime(Long id) {
        User user = new User();
        user.setLastLoginTime(new Date());
        userMapper.updateByPrimaryKeySelective(user);
        return 0;
    }

    @Override
    public User selectByPrimaryKey(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    //更新最后一次登录的地理信息
    public int updateLastLoginLocation(String lat,String lng,Long userId){
        User user=new User();
        user.setId(userId);
        user.setLat(lat);
        user.setLng(lng);
        int num=userMapper.updateUserLocation(user);
        return num;
    }

    public Long getUid(String userName) {
        return  userMapper.getUid(userName);
    }

    @Override
    public User getUserByUserName(String userName) {
        return userMapper.getUserByUserName(userName);
    }

    public MineVo getInfo(Integer userId) {

        MineVo mineVo=userMapper.getInfo(userId);
        return mineVo;
    }

    public int updateUser(User user) {
       return userMapper.updateUser(user);
    }

    @Override
    public List<CollectPersionsVo> getUserInfoByTypeListPage( CollectPersionsVo collectPersionsVo) {
        return userMapper.getUserInfoByTypeListPage(collectPersionsVo);
    }

    @Override
    public QuanziUserVo getUserInfoById(Integer userId) {
        return userMapper.getUserInfoById(userId);
    }

    @Override
    public List<QuanziUserVo> getQiuTongListPage(QuanziUserVo quanziUserVo) {
        return userMapper.getQiuTongListPage(quanziUserVo);
    }
    @Override
    public List<QuanziUserVo> indexQiuTongList(QuanziUserVo quanziUserVo) {
        return userMapper.indexQiuTongList(quanziUserVo);
    }

    @Override
    public Integer getQiuTongCount(QuanziUserVo quanziUserVo) {
        return userMapper.getQiuTongCount(quanziUserVo);
    }

    @Override
    public List<QuanziUserVo> qiuTongListPage(QuanziUserVo quanziUserVo) {
        return userMapper.qiuTongListPage(quanziUserVo);
    }

    @Override
    public List<QiuhuiVo> queryQiuHuiNames(String nickName)
    {
        return userMapper.queryQiuHuiNames(nickName);
    }

    @Override
    public List<QuanziUserVo> coachListPage(QuanziUserVo quanziUserVo) {
        return userMapper.coachListPage(quanziUserVo);
    }

    @Override
    public Integer coachListPageCount(QuanziUserVo quanziUserVo) {
        return userMapper.coachListPageCount(quanziUserVo);
    }

    //地区推荐教练列表
    public List<QuanziUserVo>getCoachListPage(QuanziUserVo quanziUserVo){
        return userMapper.getCoachListPage(quanziUserVo);
    }
    //地区推荐教练列表
    public List<QuanziUserVo>indexCoachList(QuanziUserVo quanziUserVo){
        return userMapper.indexCoachList(quanziUserVo);
    }

    @Override
    public Integer getCoachListPageCount(QuanziUserVo quanziUserVo) {
        return userMapper.getCoachListPageCount(quanziUserVo);
    }

    @Override
    public IfunResult findCaddieDatabaseToSolrIndex() {
        solrClient = new HttpSolrClient.Builder("http://localhost:8089/solr/ef_caddie").build();
        try {
            List<SolrCaddieVo> list = userMapper.findCaddieDatabaseToSolrIndex();

            for (SolrCaddieVo solrCaddieVo : list) {
                SolrInputDocument document = new SolrInputDocument();
                    document.addField("id",solrCaddieVo.getId().toString());
                    document.addField("realname",solrCaddieVo.getRealname());
                    document.addField("subjection",solrCaddieVo.getSubjection());
                    document.addField("score",solrCaddieVo.getScore());
                    document.addField("recommend",solrCaddieVo.getRecommend());
                    document.addField("ball_age",solrCaddieVo.getBallAge());
                    document.addField("head_portrait_url",solrCaddieVo.getHeadPortraitUrl());
                    document.addField("exclusiveNo",solrCaddieVo.getExclusiveNo());
                    document.addField("nickname",solrCaddieVo.getNickName());
                    document.addField("region",solrCaddieVo.getRegion());
                    document.addField("qiuhuiId",solrCaddieVo.getQiuhuiId());
                    document.addField("qiuhuiName",solrCaddieVo.getQiuhuiName());
                solrClient.add(document);
            }
            solrClient.commit();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return IfunResult.build(1,"导入未成功!");
        }
        return IfunResult.ok();
    }

    @Override
    public SearchCaddieResult getCaddieListSolrIndex(String sortType,String serviceAge,String qiuHuiName,String realname, String exclusive_no, Integer page, Integer rows) {
        // 创建solrQuery对象,封装参数,solrQuery是solr用来封装所有参数对象

        SolrQuery solrQuery = new SolrQuery();
        //封装查询条件
        if(StringUtils.isNotBlank(realname)){
            solrQuery.setQuery(realname);
        }else if (StringUtils.isNotBlank(exclusive_no)){
            solrQuery.setQuery(exclusive_no);
        }else if (StringUtils.isNotBlank(qiuHuiName)){
            solrQuery.setQuery(qiuHuiName);
        } else{
            solrQuery.setQuery("*:*");
        }
        if(sortType.equals("2")){
            solrQuery.setSort("score", SolrQuery.ORDER.desc);
        }else{
            solrQuery.setSort("score", SolrQuery.ORDER.asc);
        }
        if(StringUtils.isNotBlank(serviceAge)){
            if (Integer.valueOf(serviceAge)>=1&&Integer.valueOf(serviceAge)<=3){
                solrQuery.set("fq", "ball_age:[" + 1 + " TO " + 3 + "]");
            }
            if(Integer.valueOf(serviceAge)>=3&&Integer.valueOf(serviceAge)<=5){
                solrQuery.set("fq","ball_age:[" + 3 + " TO " + 5 + "]");
            }
            if(Integer.valueOf(serviceAge)>=5&&Integer.valueOf(serviceAge)<=8){
                solrQuery.set("fq","ball_age:[" + 5 + " TO " + 8 + "]");
            }
            if(Integer.valueOf(serviceAge)>=8&&Integer.valueOf(serviceAge)<=10){
                solrQuery.set("fq","ball_age:[" + 8 + " TO " + 10 + "]");
            }
            if(Integer.valueOf(serviceAge)>10&&Integer.valueOf(serviceAge)<20){
                solrQuery.set("fq","ball_age:[" + 10 + " TO " + 20 + "]");
            }
        }
        //分页
        //起始页
        int startNo = (page-1)*rows;
        solrQuery.setStart(startNo);
        solrQuery.setRows(rows);

        //设置默认查询域字段  复制域
        solrQuery.set("df", "p_keywords");

        SearchCaddieResult searchCaddieResult= solrCaddieDao.getCaddieListSolrIndex(solrQuery);

        searchCaddieResult.setPage(page);

        //计算总页码数
        Integer count = searchCaddieResult.getTotalCount();

        int pages = count/rows;
        if(count%rows>0){
            pages++;
        }
        searchCaddieResult.setTotalPages(pages);
        return searchCaddieResult;
    }

    @Override
    public int updateByPrimaryKey(User user) {
        int  i = userMapper.updateByPrimaryKeySelective(user);
       return i;
    }

    @Override
    public List<UserSearchVo> fastSearchUserListPage(UserSearchVo userSearchVo,Integer userId) {
        List<UserSearchVo>list = userMapper.fastSearchUserListPage(userSearchVo);
        /** 查询我关注的人 */
        CollectPersionsVo collectPersionsVo = new CollectPersionsVo();
        collectPersionsVo.setUserId(userId);
        collectPersionsVo.setCurrentPage(0);
        collectPersionsVo.setShowCount(100);
        List<CollectPersionsVo> getCollectPersionList = collectMapper.getCollectPersionListPage(collectPersionsVo);

        List<Integer> list1 = new ArrayList<>();
        for (int j=0;j<getCollectPersionList.size();j++){
            list1.add(getCollectPersionList.get(j).getUserId());
        }

        if(list.size()>0){
            for (int i=0;i<list.size();i++){
                boolean flag;
                flag = list1.contains(list.get(i).getUserId());
                if(flag){
                    list.get(i).setHasAttend("1");
                }else{
                    list.get(i).setHasAttend("0");
                }
            }
        }


        /*for (int i=0;i<list.size();i++){
            for (int j=0;j<getCollectPersionList.size();j++){
                boolean flag;
                flag = list.get(i).getUserId()==getCollectPersionList.get(j).getUserId();
                if(flag){
                    list.get(i).setHasAttend("1");
                }else{
                    list.get(i).setHasAttend("0");
                }
            }
        }*/
        return list;
    }

    @Override
    public Integer fastSearchUserCount(UserSearchVo userSearchVo) {
        return userMapper.fastSearchUserCount(userSearchVo);
    }
    /*@Override
    public List<UserSearchVo> queryAllUserListPage(UserSearchVo userSearchVo) {
        return userMapper.queryAllUserListPage(userSearchVo);
    }*/


    @Override
    public String getUserId(String phone) {
        return userMapper.selectIdByPhone(phone);
    }

    @Override
    public String getRealname(Integer id) {
        return userMapper.selectRealanemById(id);
    }
}
