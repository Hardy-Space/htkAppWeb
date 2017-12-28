package com.htkapp.modules.merchant.integral.service.serviceImpl;

import com.htkapp.core.curdException.InsertException;
import com.htkapp.core.utils.Globals;
import com.htkapp.modules.merchant.integral.dao.AccountTicketListMapper;
import com.htkapp.modules.merchant.integral.entity.AccountTicketList;
import com.htkapp.modules.merchant.integral.service.AccountTicketListService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AccountTicketListServiceImpl implements AccountTicketListService {

    @Resource
    private AccountTicketListMapper accountTicketListDao;

    //通过用户token 和 店铺id 查找优惠券列表
    @Override
    public List<AccountTicketList> getTicketListByTokenAndShopId(String token, int shopId) {
        List<AccountTicketList> ticketList = accountTicketListDao.getTicketListByTokenAndShopIdDAO(token, shopId);
        if(ticketList != null && ticketList.size() > 0){
            return ticketList;
        }
        return null;
    }

    //通过用户token 查找优惠券列表
    @Override
    public List<AccountTicketList> getTicketListByToken(String token) {
        List<AccountTicketList> ticketList = accountTicketListDao.getTicketListByTokenDAO(token);
        if(ticketList != null && ticketList.size() > 0){
            return ticketList;
        }
        return null;
    }

    //插入用户优惠券
    @Override
    public void insertAccountTicket(AccountTicketList ticketList) {
        int row = accountTicketListDao.insertAccountTicketDAO(ticketList);
        if(row <= 0){
            throw new InsertException(Globals.DEFAULT_EXCEPTION_INSERT_FAILED);
        }
    }
}
