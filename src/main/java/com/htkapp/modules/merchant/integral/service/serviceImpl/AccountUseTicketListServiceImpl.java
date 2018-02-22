package com.htkapp.modules.merchant.integral.service.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.htkapp.modules.merchant.integral.dao.AccountUseTicketListMapper;
import com.htkapp.modules.merchant.integral.entity.AccountUseTicketList;
import com.htkapp.modules.merchant.integral.service.AccountUseTicketListService;

@Service
public class AccountUseTicketListServiceImpl implements AccountUseTicketListService{
	
	@Resource
	private	AccountUseTicketListMapper accountUseTicketListMapper;

	@Override
	public List<AccountUseTicketList> getListByTokenAndShopId(String token, int shopId) {
		List<AccountUseTicketList> list=accountUseTicketListMapper.getTicketListByTokenAndShopIdDAO(token, shopId);
		if(list.size()<=0) {
			return null;
		}
		return list;
	}

}
