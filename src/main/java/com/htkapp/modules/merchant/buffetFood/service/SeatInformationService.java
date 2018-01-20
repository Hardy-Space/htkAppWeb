package com.htkapp.modules.merchant.buffetFood.service;


import com.htkapp.core.jsAjax.AjaxResponseModel;
import com.htkapp.modules.merchant.buffetFood.entity.BuffetFoodOrder;
import com.htkapp.modules.merchant.buffetFood.entity.SeatInformation;

import java.util.List;

public interface SeatInformationService {


    /* =====================接口开始======================= */
   /* =====================接口开始======================= */
    //根据商户id查找商铺下空闲的的座位信息
    List<SeatInformation> getSeatInformationListByShopId(int shopId) throws Exception;
    //根据商铺id查询上铺下的所有座位信息
    List<SeatInformation> getSeatInformationAllByShopId(int shopId) throws Exception;
    //为商铺增加对应的座位信息
    AjaxResponseModel<SeatInformation> addSeatInfoByShopId(SeatInformation seat)throws Exception;
    //修改座位状态信息
    int updataSeatInfoByOrder(BuffetFoodOrder bfo, Integer b)throws Exception;


    /* =====================接口开始======================= */
}
