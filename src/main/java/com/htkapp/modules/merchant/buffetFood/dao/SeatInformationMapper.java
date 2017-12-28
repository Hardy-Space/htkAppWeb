package com.htkapp.modules.merchant.buffetFood.dao;


import com.htkapp.modules.merchant.buffetFood.entity.SeatInformation;

import java.util.List;

public interface SeatInformationMapper {


    /* =====================接口开始======================= */
    //根据商户id查找商铺下的座位信息
    List<SeatInformation> getSeatInformationListByShopIdDAO(int shopId);


    /* =====================接口开始======================= */
}
