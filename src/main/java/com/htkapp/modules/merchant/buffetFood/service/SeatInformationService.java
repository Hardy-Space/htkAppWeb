package com.htkapp.modules.merchant.buffetFood.service;


import com.htkapp.modules.merchant.buffetFood.entity.SeatInformation;

import java.util.List;

public interface SeatInformationService {


    /* =====================接口开始======================= */
    //根据商户id查找商铺下的座位信息
    List<SeatInformation> getSeatInformationListByShopId(int shopId) throws Exception;


    /* =====================接口开始======================= */
}
