package com.htkapp.modules.merchant.shop.dao;

import com.htkapp.modules.merchant.shop.entity.ShopSaverTicketRecord;

public interface ShopSaverTicketRecordMapper {

    //插入用户兑换优惠券记录
    int insertAccountExchangeRecordDAO(ShopSaverTicketRecord ticketRecord);
}
