package com.htkapp.modules.merchant.shop.dao;

import com.htkapp.modules.merchant.shop.entity.AccountShopReplyComments;

public interface AccountShopReplyCommentsMapper {

    /* ======================JSP页面接口开始========================= */
    //查找商户的未回复用户评论的数量
    int getNoCommentNumberDAO(String accountShopToken);
    //查找商户未回复差评数量
    int getBadCommentNumberDAO(String accountShopToken);
    //根据评论表id查找商户是否评论，评论内容
    AccountShopReplyComments getReplyContentByIdDAO(int commentId);
    //插入商户回复用户的评论
    int insertReplyDAO(AccountShopReplyComments replyComments);

    /* ======================接口结束========================= */
}
