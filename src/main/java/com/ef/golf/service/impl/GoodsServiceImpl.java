package com.ef.golf.service.impl;

import com.ef.golf.mapper.EsOrderMapper;
import com.ef.golf.mapper.GoodsMapper;
import com.ef.golf.mapper.TicketsMapper;
import com.ef.golf.pojo.*;
import com.ef.golf.service.GoodsService;
import com.ef.golf.util.PageBean;
import com.ef.golf.util.SystemSendTicketUtils;
import com.ef.golf.vo.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GoodsServiceImpl implements GoodsService{

    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    private TicketsMapper ticketsMapper;
    @Value("${shopticketId}")
    private String shopticketId;


    @Override
    public int getGoodsHotVoCount(Map map) {
        return goodsMapper.getGoodsHotVoCount(map);
    }

    @Override
    public List<GoodsGllery> glleryList(Integer goods_id) {
        return goodsMapper.glleryList(goods_id);
    }

    @Override
    public List<GoodsMarketVo> marketList(Integer goods_id) {
        List<GoodsMarketVo> goodsMarketVos=new ArrayList<>();

        goodsMarketVos=goodsMapper.marketList(goods_id);



        //此处是该笔订单赠券信息
        List<ShopSendTicketVo> shopSendTicketVos = new ArrayList<>();

        for (GoodsMarketVo goodsMarketVo:goodsMarketVos){
            ShopSendTicketVo shopSendTicketVo=new ShopSendTicketVo();
            shopSendTicketVo.setGoodsId(goodsMarketVo.getGoods_id());//商品id
            shopSendTicketVo.setPrice(goodsMarketVo.getPrice().doubleValue());//售价
            shopSendTicketVo.setReduction(goodsMarketVo.getReduction());
            shopSendTicketVo.setCredit(goodsMarketVo.getCredit());
            shopSendTicketVo.setCount(1);
            shopSendTicketVos.add(shopSendTicketVo);
        }

        //查询ticket模板id
        List<Tickets> ticketsList = ticketsMapper.getTicketListIsShopSendTicket(Arrays.asList(shopticketId.split(",")));
        //调用赠券计算工具
        Map<String,Object> sendMap = SystemSendTicketUtils.shopSendTicket(ticketsList,shopSendTicketVos);

        List<CreditVo> creditVoList= (List<CreditVo>) sendMap.get("creditVoList");

        int i=0;//购物车里的商品有id一直  货品不一样的情况  通过项次对应

        for (GoodsMarketVo goodsMarketVo:goodsMarketVos) {
            for( CreditVo creditVo:creditVoList){
                if(goodsMarketVo.getGoods_id()==creditVo.getGoodsId()){
                    goodsMarketVo.setCredit(creditVo.getCredit());
                    break;
                }
            }
          /*  goodsMarketVo.setCredit(creditVoList.get(i).getCredit());
            i++;*/
        }
        return goodsMarketVos;
    }

    @Override
    public List<CatVo> brandFirstCat(Integer brand_id) {
        return goodsMapper.brandFirstCat(brand_id);
    }

    @Override
    public int brandGoodsCount(Map map) {
        return goodsMapper.brandGoodsCount(map);
    }

    @Override
    public GoodsHotSpecVo getGoodsHotSpecVo(Integer goods_id) {
        return goodsMapper.getGoodsHotSpecVo(goods_id);
    }

    @Override
    public GoodsHotSpecVo selectGoodsDetail(Integer goods_id) {

       /* List<GoodsHotSpecVo> goodsHotSpecVos=new ArrayList<>();

        goodsHotSpecVos=goodsMapper.getGoodsHotVoList(map);*/

        GoodsHotSpecVo goodsHotSpecVo=  goodsMapper.selectGoodsDetail(goods_id);



        //此处是该笔订单赠券信息
        List<ShopSendTicketVo> shopSendTicketVos = new ArrayList<>();


            ShopSendTicketVo shopSendTicketVo=new ShopSendTicketVo();
            shopSendTicketVo.setGoodsId(goodsHotSpecVo.getGoods_id());//商品id
            shopSendTicketVo.setPrice(goodsHotSpecVo.getPrice().doubleValue());//售价
            shopSendTicketVo.setReduction(goodsHotSpecVo.getReduction());
            shopSendTicketVo.setCredit(goodsHotSpecVo.getCredit());
            shopSendTicketVo.setCount(1);
            shopSendTicketVos.add(shopSendTicketVo);


        //查询ticket模板id
        List<Tickets> ticketsList = ticketsMapper.getTicketListIsShopSendTicket(Arrays.asList(shopticketId.split(",")));
        //调用赠券计算工具
        Map<String,Object> sendMap = SystemSendTicketUtils.shopSendTicket(ticketsList,shopSendTicketVos);

        List<CreditVo> creditVoList= (List<CreditVo>) sendMap.get("creditVoList");


        int i=0;//购物车里的商品有id一直  货品不一样的情况  通过项次对应


        goodsHotSpecVo.setCredit(creditVoList.get(i).getCredit());





        return goodsHotSpecVo;
    }
    /** 推荐商品 */
    @Override
    public PageBean getGoodsRecommendList(Integer pageNo, Integer pageSize) {
        Map<String,Object>map = new HashMap<>();
        map.put("pageNo",(pageNo-1)*pageSize);
        map.put("pageSize",pageSize);
        List<GoodsHotSpecVo>list = goodsMapper.getGoodsRecommendList(map);
        Integer count = goodsMapper.getGoodsRecommendListCount();

        //此处是该笔订单赠券信息
        List<ShopSendTicketVo> shopSendTicketVos = new ArrayList<>();

        for (GoodsHotSpecVo goodsHotSpecVo:list){
            ShopSendTicketVo shopSendTicketVo=new ShopSendTicketVo();
            shopSendTicketVo.setGoodsId(goodsHotSpecVo.getGoods_id());//商品id
            shopSendTicketVo.setPrice(goodsHotSpecVo.getPrice().doubleValue());//售价
            shopSendTicketVo.setReduction(goodsHotSpecVo.getReduction());
            shopSendTicketVo.setCredit(goodsHotSpecVo.getCredit());
            shopSendTicketVo.setCount(1);
            shopSendTicketVos.add(shopSendTicketVo);
        }

        //查询ticket模板id
        List<Tickets> ticketsList = ticketsMapper.getTicketListIsShopSendTicket(Arrays.asList(shopticketId.split(",")));
        //调用赠券计算工具
        Map<String,Object> sendMap = SystemSendTicketUtils.shopSendTicket(ticketsList,shopSendTicketVos);

        List<CreditVo> creditVoList= (List<CreditVo>) sendMap.get("creditVoList");

        int i=0;//购物车里的商品有id一直  货品不一样的情况  通过项次对应

        for (GoodsHotSpecVo goodsMarketVo:list) {
            for( CreditVo creditVo:creditVoList){
                if(goodsMarketVo.getGoods_id()==creditVo.getGoodsId()){
                    goodsMarketVo.setCredit(creditVo.getCredit());
                    break;
                }
            }
        }

        PageBean pageBean = new PageBean();
            pageBean.setPageNo(pageNo);
            pageBean.setTotalCount(count);
            pageBean.setPageSize(pageSize);
            pageBean.setResultList(list);


        return pageBean;
    }

    @Override
    public List<GoodsCatVo> brandGoodsList(Map map) {
        return goodsMapper.brandGoodsList(map);
    }

    @Override
    public List<CommentVo> commentList(Map map) {
        return goodsMapper.commentList(map);
    }

    @Override
    public int commentCount(Integer goods_id) {
        return goodsMapper.commentCount(goods_id);
    }

    @Override
    public List<CommentPrictureVo> pictureList(Integer goods_id) {
        return goodsMapper.pictureList(goods_id);
    }

    @Override
    public Goods selectByPrimaryKey(Integer goods_id) {
        return goodsMapper.getGoods(goods_id);
    }





    @Override
    public List<GoodsSpecVo> getGoodsSpecVo(Integer goods_id) {

       /* String sql ="select s.* from es_specification s inner join es_type_spec ts on s.spec_id=ts.spec_id where ts.type_id=?";
        List<Specification> specList= this.daoSupport.queryForList(sql,Specification.class, goodsTypeId);
//		sql ="select * from es_spec_values where spec_id in (select spec_id from es_type_spec where type_id=?) order by spec_value_id";
//		List valueList= this.daoSupport.queryForList(sql, new SpecValueMapper(), goodsTypeId);
        List valueList=null;
        if(goodsId == 0){
            sql ="select * from es_spec_values where inherent_or_add=0 and spec_id in (select spec_id from es_type_spec where type_id=?) order by spec_value_id";
            valueList = this.daoSupport.queryForList(sql, new SpecValueMapper(), goodsTypeId);
        }else{
            sql ="select * from es_spec_values where spec_value_id in (select spec_value_id from es_goods_spec where goods_id =?) OR inherent_or_add=0 and spec_id in (select spec_id from es_type_spec where type_id=?) order by spec_value_id";
            valueList = this.daoSupport.queryForList(sql, new SpecValueMapper(), goodsId,goodsTypeId);
        }

        for(Specification spec :specList){
            List<SpecValue> newList =  new ArrayList<SpecValue>();
            for(SpecValue value:(List<SpecValue>)valueList){
                if(value.getSpec_id().intValue() == spec.getSpec_id().intValue()){
                    newList.add(value);
                }
            }
            spec.setValueList(newList);
        }
        return specList;*/
        //根据goods_id获得type_id商品类型
      Integer type_id=goodsMapper.getTypeId(goods_id);
        //根基type_id获得规格列表
        List<Specification> specList=goodsMapper.getGoodsSpecVoByType(type_id);

        //根据goods_id和type_id获得所有的规格对应的值

        List<SpecValue> valueList=goodsMapper.getSpecValueByGoodsIdAndTypeId(goods_id,type_id);

        //根据规格列表把对应每个规格的值取得对应上
        List<GoodsSpecVo> goodsSpecVoList=new ArrayList<>();
        for(Specification spec :specList){
            List<SpecValue> newList =  new ArrayList<SpecValue>();
            List<String> stringList=new ArrayList<>();
            for(SpecValue value:(List<SpecValue>)valueList){
                if(value.getSpec_id().intValue() == spec.getSpec_id().intValue()){
                    newList.add(value);
                    stringList.add(value.getSpec_value());
                }
            }
            spec.setValueList(newList);

            GoodsSpecVo goodsSpecVo=new GoodsSpecVo();
            goodsSpecVo.setName(spec.getSpec_name());


            goodsSpecVo.setValue(StringUtils.join(stringList.toArray(), ","));
            goodsSpecVoList.add(goodsSpecVo);
        }



        return goodsSpecVoList;
    }

    @Override
    public List<GoodsHotSpecVo> getSelectGoodsListPage(GoodsHotSpecVo goods){
        return goodsMapper.getSelectGoodsListPage(goods);
    }

    @Override
    public Map getGoodsHotTime(Integer goods_id) {
        return goodsMapper.getGoodsHotTime(goods_id);
    }

    @Override
    public List<GoodsHotSpecVo> getGoodsHotVoList(Map map) {

        List<GoodsHotSpecVo> goodsHotSpecVos=new ArrayList<>();

        goodsHotSpecVos=goodsMapper.getGoodsHotVoList(map);



        //此处是该笔订单赠券信息
        List<ShopSendTicketVo> shopSendTicketVos = new ArrayList<>();

        for (GoodsHotSpecVo goodsHotSpecVo:goodsHotSpecVos){
            ShopSendTicketVo shopSendTicketVo=new ShopSendTicketVo();
            shopSendTicketVo.setGoodsId(goodsHotSpecVo.getGoods_id());//商品id
            shopSendTicketVo.setPrice(goodsHotSpecVo.getPrice().doubleValue());//售价
            shopSendTicketVo.setReduction(goodsHotSpecVo.getReduction());
            shopSendTicketVo.setCredit(goodsHotSpecVo.getCredit());
            shopSendTicketVo.setCount(1);
            shopSendTicketVos.add(shopSendTicketVo);
        }

        //查询ticket模板id
        List<Tickets> ticketsList = ticketsMapper.getTicketListIsShopSendTicket(Arrays.asList(shopticketId.split(",")));
        //调用赠券计算工具
        Map<String,Object> sendMap = SystemSendTicketUtils.shopSendTicket(ticketsList,shopSendTicketVos);

        List<CreditVo> creditVoList= (List<CreditVo>) sendMap.get("creditVoList");


        int i=0;//购物车里的商品有id一直  货品不一样的情况  通过项次对应

        for (GoodsHotSpecVo goodsMarketVo:goodsHotSpecVos) {
            for( CreditVo creditVo:creditVoList){
                if(goodsMarketVo.getGoods_id()==creditVo.getGoodsId()){
                    goodsMarketVo.setCredit(creditVo.getCredit());
                    break;
                }
            }
           /* goodsMarketVo.setCredit(creditVoList.get(i).getCredit());
            i++;*/
        }

        return goodsHotSpecVos;

    }
}
