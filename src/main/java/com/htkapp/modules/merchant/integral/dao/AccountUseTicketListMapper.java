package com.htkapp.modules.merchant.integral.dao;

import java.util.List;

import com.htkapp.modules.merchant.integral.entity.AccountUseTicketList;

public interface AccountUseTicketListMapper {
	//通过用户token以及店铺id查询优惠券
	List<AccountUseTicketList> getTicketListByTokenAndShopIdDAO(String token, int shopId);
}
