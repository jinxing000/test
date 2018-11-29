package com.ef.golf.service.impl;

import com.ef.golf.mapper.GoodsCatMapper;
import com.ef.golf.mapper.TicketsMapper;
import com.ef.golf.pojo.GoodsCat;
import com.ef.golf.pojo.GoodsMarketVo;
import com.ef.golf.pojo.Tickets;
import com.ef.golf.service.EsGoodsCatService;
import com.ef.golf.util.SystemSendTicketUtils;
import com.ef.golf.vo.CreditVo;
import com.ef.golf.vo.GoodsCatVo;
import com.ef.golf.vo.ShopSendTicketVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class EsGoodsCatServiceImpl implements EsGoodsCatService {

@Autowired
    GoodsCatMapper goodsCatMapper;
    @Autowired
    private TicketsMapper ticketsMapper;
    @Value("${shopticketId}")
    private String shopticketId;


  /*  @Override
    public List<GoodsCatVo> getGoodsCatVo(String cat_id) {
        return goodsCatMapper.specCatGoodsList (Integer.valueOf(cat_id));
    }*/

    @Override
    public List<GoodsCat> getCatList(Map map) {
        return goodsCatMapper.secondCatList(map);
    }

    @Override
    public List<GoodsCatVo> getSecondGoodsCatVo(Map map) {

        List<GoodsCatVo> goodsCatVos=new ArrayList<>();

        goodsCatVos=goodsCatMapper.secondspecCatGoodsList(map);



        //此处是该笔订单赠券信息
        List<ShopSendTicketVo> shopSendTicketVos = new ArrayList<>();

        for (GoodsCatVo goodsCatVo:goodsCatVos){
            ShopSendTicketVo shopSendTicketVo=new ShopSendTicketVo();
            shopSendTicketVo.setGoodsId(goodsCatVo.getGoods_id());//商品id
            shopSendTicketVo.setPrice(goodsCatVo.getPrice().doubleValue());//售价
            shopSendTicketVo.setReduction(goodsCatVo.getReduction());
            shopSendTicketVo.setCredit(goodsCatVo.getCredit());
            shopSendTicketVo.setCount(1);
            shopSendTicketVos.add(shopSendTicketVo);
        }

        //查询ticket模板id
        List<Tickets> ticketsList = ticketsMapper.getTicketListIsShopSendTicket(Arrays.asList(shopticketId.split(",")));
        //调用赠券计算工具
        Map<String,Object> sendMap = SystemSendTicketUtils.shopSendTicket(ticketsList,shopSendTicketVos);

        List<CreditVo> creditVoList= (List<CreditVo>) sendMap.get("creditVoList");

        int i=0;//购物车里的商品有id一直  货品不一样的情况  通过项次对应

        for (GoodsCatVo goodsCatVo:goodsCatVos) {
            for( CreditVo creditVo:creditVoList){
                if(goodsCatVo.getGoods_id()==creditVo.getGoodsId()){
                    goodsCatVo.setCredit(creditVo.getCredit());
                    break;
                }
            }
          /*  goodsCatVo.setCredit(creditVoList.get(i).getCredit());
            i++;*/
        }
        return goodsCatVos;

    }

    @Override
    public int sencondCount(Integer cat_id) {
        return goodsCatMapper.sencondCount(cat_id);
    }

    @Override
    public Integer getBigCatId(Integer catId) {
        return goodsCatMapper.getBigCatId(catId);
    }

    @Override
    public int firstCount(Integer cat_id) {
        return goodsCatMapper.firstCount(cat_id);
    }

    @Override
    public List<GoodsCatVo> getGoodsCatVo(Map map) {

        List<GoodsCatVo> goodsCatVos=new ArrayList<>();

        goodsCatVos=goodsCatMapper.firstspecCatGoodsList(map);



        //此处是该笔订单赠券信息
        List<ShopSendTicketVo> shopSendTicketVos = new ArrayList<>();

        for (GoodsCatVo goodsCatVo:goodsCatVos){

            ShopSendTicketVo shopSendTicketVo=new ShopSendTicketVo();
            shopSendTicketVo.setGoodsId(goodsCatVo.getGoods_id());//商品id
            shopSendTicketVo.setPrice(goodsCatVo.getPrice().doubleValue());//售价
            shopSendTicketVo.setReduction(goodsCatVo.getReduction());
            shopSendTicketVo.setCredit(goodsCatVo.getCredit());
            shopSendTicketVo.setCount(1);
            shopSendTicketVos.add(shopSendTicketVo);
        }

        //查询ticket模板id
        List<Tickets> ticketsList = ticketsMapper.getTicketListIsShopSendTicket(Arrays.asList(shopticketId.split(",")));
        //调用赠券计算工具
        Map<String,Object> sendMap = SystemSendTicketUtils.shopSendTicket(ticketsList,shopSendTicketVos);

        List<CreditVo> creditVoList= (List<CreditVo>) sendMap.get("creditVoList");

        int i=0;//购物车里的商品有id一直  货品不一样的情况  通过项次对应

        for (GoodsCatVo goodsCatVo:goodsCatVos) {

            for( CreditVo creditVo:creditVoList){
                if(goodsCatVo.getGoods_id()==creditVo.getGoodsId()){
                    goodsCatVo.setCredit(creditVo.getCredit());
                    break;
                }
            }


        }
        return goodsCatVos;

    }

    @Override
    public List<GoodsCat> list() {
        return goodsCatMapper.catList();
    }
}
