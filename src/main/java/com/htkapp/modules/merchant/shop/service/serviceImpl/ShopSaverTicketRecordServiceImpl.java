package com.htkapp.modules.merchant.shop.service.serviceImpl;

import com.htkapp.core.curdException.InsertException;
import com.htkapp.core.utils.Globals;
import com.htkapp.modules.merchant.shop.dao.ShopSaverTicketRecordMapper;
import com.htkapp.modules.merchant.shop.entity.ShopSaverTicketRecord;
import com.htkapp.modules.merchant.shop.service.ShopSaverTicketRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ShopSaverTicketRecordServiceImpl implements ShopSaverTicketRecordService{

    @Resource
    private ShopSaverTicketRecordMapper ticketRecordDao;



    @Override
    public void insertAccountExchangeRecord(ShopSaverTicketRecord ticketRecord) {
        int row = ticketRecordDao.insertAccountExchangeRecordDAO(ticketRecord);
        if(row <= 0){
            throw new InsertException(Globals.DEFAULT_EXCEPTION_INSERT_FAILED);
        }
    }
}
