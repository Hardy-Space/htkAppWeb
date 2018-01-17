package com.htkapp.modules.merchant.integral.service;

import com.htkapp.modules.merchant.integral.entity.AccountTicketList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AccountTicketListService {

    //通过用户token 和 店铺id 查找优惠券列表
    List<AccountTicketList> getTicketListByTokenAndShopId(String token, int shopId);
    //通过用户token 查找优惠券列表
    List<AccountTicketList> getTicketListByToken(String token);
    //插入用户优惠券
    void insertAccountTicket(AccountTicketList ticketList);

    //通过用户token 和 优惠券id 查找优惠券列表（其实只能查到一条）
    List<AccountTicketList> getTicketListByTokenAndCouponId(String token, Integer ticketId);

    //更新用户优惠券数量
    void updateTicketListByTokenAndCouponIdDAO(int ticketQuantity,String token,Integer couponId);
}
