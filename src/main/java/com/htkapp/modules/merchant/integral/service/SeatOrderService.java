package com.htkapp.modules.merchant.integral.service;

import com.htkapp.modules.API.dto.SeatOrderDetail;
import com.htkapp.modules.merchant.integral.entity.SeatOrder;

import java.util.List;

/**
 *
 */
public interface SeatOrderService {

    /* =====================接口开始===================== */
    //根据用户token查找用户下的订座订单列表和店铺信息
    List<SeatOrder> getSeatOrderListByTokenAndShopId(String token, int shopId, int pageNumber, int pageLimit);
    //通过订单号查询订单详情
    SeatOrder getSeatOrderByOrderNumber(String orderNumber);
    //插入座位预定
    void insertSeatOrderByToken(SeatOrder order);
    /* =====================接口结束===================== */

    /* ===================JSP接口开始========================= */
    //插入座位订单
    int insert(SeatOrder seatOrder);
    //根据订座订单号查询订座详情
    SeatOrderDetail getSeatOrderDetail(String orderNumber, String token);
    /* ===================JSP接口结束========================= */
}
