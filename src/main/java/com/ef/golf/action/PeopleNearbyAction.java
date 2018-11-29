package com.ef.golf.action;

import com.ef.golf.common.Constant;
import com.ef.golf.core.service.AbstractService;
import com.ef.golf.exception.QueryException;
import com.ef.golf.service.CollectService;
import com.ef.golf.service.UserService;
import com.ef.golf.util.DistanceUtil;
import com.ef.golf.vo.CollectPersionsVo;
import com.ef.golf.vo.UserSearchVo;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 查询附近的人列表
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PeopleNearbyAction extends AbstractService {


    @Resource
    private UserService userService;
    @Resource
    private CollectService collectService;

    private Integer userId;

    //@NotNull(message = "当前用户经度不可为空!")
    private double lat1;
    //@NotNull(message = "当前用户纬度不可为空!")
    private double lng1;

    private Integer pageNum = 1;
    private Integer showCount = 5;
    private Double dis = 0.0;

    public Object doService() throws QueryException {
        UserSearchVo userSearchVo1 = new UserSearchVo();
        userSearchVo1.setCurrentPage(pageNum);
        userSearchVo1.setShowCount(showCount);
        List<UserSearchVo> list = userService.queryAllUserListPage(userSearchVo1);
        //获取我关注的人
        CollectPersionsVo collectPersionsVo = new CollectPersionsVo();
        collectPersionsVo.setUserId(userId);
        collectPersionsVo.setCurrentPage(this.pageNum);
        collectPersionsVo.setShowCount(100);
        List<CollectPersionsVo> myCollectionPersion = collectService.getCollectPersionListPage(collectPersionsVo);
        List<UserSearchVo> resultList = new ArrayList<>();
        try {
            /*for (UserSearchVo usv:list) {
                if(usv.getLat() !=null && usv.getLng()!="" && usv.getLng()!=null && usv.getLng()!=""){
                    Double distance = DistanceUtil.getDistance(lat1,lng1,Double.valueOf(usv.getLat()),Double.valueOf(usv.getLng()));
                    dis = dis = Math.round(distance/100d)/10d;
                    if(dis<30){
                        UserSearchVo userSearchVo = new UserSearchVo();
                        userSearchVo.setUserId(usv.getUserId());
                        userSearchVo.setNickname(usv.getNickname());
                        userSearchVo.setUserType(usv.getUserType());
                        userSearchVo.setHeadPortraitUrl(usv.getHeadPortraitUrl());
                        userSearchVo.setRegion(usv.getRegion());
                        userSearchVo.setLat(usv.getLat());
                        userSearchVo.setLng(usv.getLng());
                        peopleNearby.add(userSearchVo);
                    }
                }
            }
*/
            for (int i = 0; i < list.size(); i++) {

                    if (list.get(i).getLat() != null && list.get(i).getLng() != "" && list.get(i).getLng() != null && list.get(i).getLng() != "") {
                        Double distance = DistanceUtil.getDistance(lat1, lng1, Double.valueOf(list.get(i).getLat()), Double.valueOf(list.get(i).getLng()));
                        dis = Math.round(distance / 100d) / 10d;
                        if (dis < 30) {
                            UserSearchVo userSearchVo = new UserSearchVo();
                            userSearchVo.setUserId(list.get(i).getUserId());
                            userSearchVo.setNickname(list.get(i).getNickname());
                            userSearchVo.setUserType(list.get(i).getUserType());
                            userSearchVo.setHeadPortraitUrl(list.get(i).getHeadPortraitUrl());
                            userSearchVo.setRegion(list.get(i).getRegion());
                            userSearchVo.setLat(list.get(i).getLat());
                            userSearchVo.setLng(list.get(i).getLng());
                            resultList.add(userSearchVo);
                        }
                    }
                }
                List<Integer> list1 = new ArrayList<>();
            for (int j=0;j<myCollectionPersion.size();j++){
                list1.add(myCollectionPersion.get(j).getUserId());
            }
                /*if(resultList.size()>0){
                    for (int i=0;i<resultList.size();i++){
                        for (int j=0;j<myCollectionPersion.size();j++){
                            boolean flag;
                            flag = resultList.get(i).getUserId()==myCollectionPersion.get(j).getUserId();
                            if(flag){
                                resultList.get(i).setHasAttend("1");
                            }else{
                                resultList.get(i).setHasAttend("0");
                            }
                        }
                    }
                }*/

            if(resultList.size()>0){
                for (int i=0;i<resultList.size();i++){
                        boolean flag;
                        flag = list1.contains(resultList.get(i).getUserId());
                        if(flag){
                            resultList.get(i).setHasAttend("1");
                        }else{
                            resultList.get(i).setHasAttend("0");
                        }
                    }
            }

            Map<String, Object> map = new HashMap<>();
            map.put("pageNum", (userSearchVo1.getCurrentPage() - 1) * userSearchVo1.getShowCount());
            map.put("showCount", userSearchVo1.getShowCount());
            map.put("totalResult", userSearchVo1.getTotalResult());
            map.put("resultList", resultList);
            map.put("currentPage",userSearchVo1.getCurrentPage());
            map.put("totalPage",userSearchVo1.getTotalPage());
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            throw new QueryException(Constant.ERR_QUERY);
        }
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setLat1(double lat1) {
        this.lat1 = lat1;
    }

    public void setLng1(double lng1) {
        this.lng1 = lng1;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public void setShowCount(Integer showCount) {
        this.showCount = showCount;
    }
}
