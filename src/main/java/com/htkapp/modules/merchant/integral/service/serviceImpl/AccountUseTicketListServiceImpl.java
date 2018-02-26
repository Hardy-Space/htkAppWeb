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

	@Override
	public AccountUseTicketList getTicketListByTokenAndShopIdAndTime(String token, int shopId, String dataTime) {
		AccountUseTicketList autl=accountUseTicketListMapper.getTicketListByTokenAndShopIdAndTime(token, shopId, dataTime);
		if(autl!=null) {
			return autl;
		}
		return null;
	}

	@Override
	public int delTicketListByTokenAndShopIdAndTime(String token, int shopId, String dataTime) {
		int a =accountUseTicketListMapper.delTicketListByTokenAndShopIdAndTime(token, shopId, dataTime);
		if(a<=0) {
			return 0;
		}
		return a;
	}

	@Override
	public int updataTicketListByTokenAndShopIdAndTime(String token, int shopId, String dataTime, int quantity) {
		int a=accountUseTicketListMapper.updataTicketListByTokenAndShopIdAndTime(token, shopId, dataTime, quantity);
		if(a<=0) {
			return 0;
		}
		return a;
	}
}
