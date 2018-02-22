package com.htkapp.modules.merchant.integral.service;

import java.util.List;

import com.htkapp.modules.merchant.integral.entity.AccountUseTicketList;

public interface AccountUseTicketListService {
	//通过用户token以及店铺id查询用户名下的优惠券（可使用的优惠券）
	List<AccountUseTicketList> getListByTokenAndShopId(String token, int shopId);
}
