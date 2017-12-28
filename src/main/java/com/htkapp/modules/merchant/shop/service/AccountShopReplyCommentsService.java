package com.htkapp.modules.merchant.shop.service;

import com.htkapp.modules.merchant.shop.entity.AccountShopReplyComments;

public interface AccountShopReplyCommentsService {

    /* ======================JSP页面接口开始========================= */
    //查找商户的未回复用户评论的数量
    int getNoCommentNumber(String accountShopToken) throws Exception;
    //查找商户未回复差评数量
    int getBadCommentNumber(String accountShopToken) throws Exception;
    //根据评论表id查找商户是否评论，评论内容
    AccountShopReplyComments getReplyContentById(int commentId)throws Exception;
    //插入商户回复用户的评论
    void insertReply(AccountShopReplyComments replyComments)throws Exception;
    /* ======================接口结束========================= */
}
