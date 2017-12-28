package com.htkapp.modules.merchant.shop.service;

import com.htkapp.modules.merchant.shop.entity.ShopSaverTicketRecord;

public interface ShopSaverTicketRecordService {

    //插入用户兑换优惠券记录
    void insertAccountExchangeRecord(ShopSaverTicketRecord ticketRecord);
}
