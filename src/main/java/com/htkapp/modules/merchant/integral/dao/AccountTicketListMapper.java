package com.htkapp.modules.merchant.integral.dao;

import com.htkapp.modules.merchant.integral.entity.AccountTicketList;

import java.util.List;

public interface AccountTicketListMapper {

    //通过用户token 和 店铺id 查找优惠券列表
    List<AccountTicketList> getTicketListByTokenAndShopIdDAO(String token, int shopId);
    //通过用户token 查找优惠券列表
    List<AccountTicketList> getTicketListByTokenDAO(String token);
    //插入用户优惠券
    int insertAccountTicketDAO(AccountTicketList ticketList);
}
