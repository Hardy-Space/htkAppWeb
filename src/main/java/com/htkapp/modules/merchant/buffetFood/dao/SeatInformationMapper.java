package com.htkapp.modules.merchant.buffetFood.dao;


import com.htkapp.modules.merchant.buffetFood.entity.BuffetFoodOrder;
import com.htkapp.modules.merchant.buffetFood.entity.SeatInformation;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SeatInformationMapper {


    /* =====================接口开始======================= */
    //根据商户id查找商铺下的座位信息
    List<SeatInformation> getSeatInformationListByShopIdDAO(int shopId);
//根据商户id查找商铺下所有的座位信息
    List<SeatInformation> getSeatInformationListByShopId(int shopId);
    //为商铺增加对应的座位信息
    void addSeatInfoByShopId(SeatInformation seat);
    //根据商铺id、座位id删除对应的座位信息
    int delSeatInfoByShopIdAndId(int shopId,int Id);
    //根据商铺id修改座位状态信息
    int updataSeatInfoByShopId(@Param("bfo")BuffetFoodOrder bfo,@Param("b")Integer b);


    /* =====================接口开始======================= */
}
