package com.htkapp.modules.merchant.common.service.serviceImpl;

import com.htkapp.core.OtherUtils;
import com.htkapp.core.jsAjax.AjaxResponseModel;
import com.htkapp.core.utils.Globals;
import com.htkapp.modules.common.dto.IndexInfo;
import com.htkapp.modules.common.entity.LoginUser;
import com.htkapp.modules.merchant.common.service.IndexService;
import com.htkapp.modules.merchant.pay.service.OrderRecordService;
import com.htkapp.modules.merchant.pay.service.OrderRefundService;
import com.htkapp.modules.merchant.shop.entity.Shop;
import com.htkapp.modules.merchant.shop.service.AccountShopLoginLogService;
import com.htkapp.modules.merchant.shop.service.AccountShopReplyCommentsService;
import com.htkapp.modules.merchant.shop.service.AccountShopServiceI;
import com.htkapp.modules.merchant.shop.service.ShopServiceI;
import com.xiaoleilu.hutool.date.BetweenFormater;
import com.xiaoleilu.hutool.date.DateTime;
import com.xiaoleilu.hutool.date.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

import static com.xiaoleilu.hutool.date.DatePattern.NORM_DATETIME_PATTERN;
import static com.xiaoleilu.hutool.date.DateUtil.format;

/**
 * Created by yinqilei on 17-7-14.
 */

@Service
public class IndexServiceImpl implements IndexService {

    @Resource
    private OrderRecordService orderRecordService;
    @Resource
    private AccountShopLoginLogService loginLogService;
    @Resource
    private AccountShopReplyCommentsService replyCommentsService;
    @Resource
    private OrderRefundService orderRefundService;
    @Resource
    private ShopServiceI shopService;

    /* =================接口开始================== */
    //商户主页
    @Override
    public IndexInfo index(HttpServletRequest request) {
        try {
            LoginUser user = OtherUtils.getLoginUserByRequest();
            String accountShopToken = user.getToken();
            //本次登陆日志
            String loginTime = loginLogService.getCurrentLoginLogByToken(accountShopToken);
            //上次登陆日志
            String signOutTime = loginLogService.getLastLoginLogByToken(accountShopToken);
            //商户使用剩余时间
            String useRemainingTime = DateUtil.formatBetween(new Date(), DateUtil.parse(user.getUseEndTime()), BetweenFormater.Level.MINUTE);
            //未回复差评数
            int badCommentNumber = replyCommentsService.getBadCommentNumber(accountShopToken);
            //未回复评价数
            int noCommentNumber = replyCommentsService.getNoCommentNumber(accountShopToken);
            //新订单
            int newOrderNumber = orderRecordService.getNewOrderNumber(accountShopToken);
            //退单
            int currentRefundNumber = orderRefundService.getCurrentRefundOrderNumber(accountShopToken);
            DateTime date = DateUtil.yesterday();
            DateTime todayDate = DateUtil.parse(format(new Date(),NORM_DATETIME_PATTERN));
            //昨天
            DateTime yBeginDate = DateUtil.beginOfDay(date);
            DateTime yEndDate = DateUtil.endOfDay(date);
            //昨日订单数
            int yesterdayNumber = orderRecordService.getOrderNumberByDate(accountShopToken, yBeginDate.toString(), yEndDate.toString());
            //昨日营业额
            double yesterdayRevenue = orderRecordService.getRevenueByDate(accountShopToken, yBeginDate.toString(), yEndDate.toString());
            //昨天
            DateTime tBeginDate = DateUtil.beginOfDay(todayDate);
            DateTime tEndDate = DateUtil.endOfDay(todayDate);
            //今日订单数
            int todayNumber = orderRecordService.getOrderNumberByDate(accountShopToken, tBeginDate.toString(), tEndDate.toString());
            //今日营业数
            double todayRevenue = orderRecordService.getRevenueByDate(accountShopToken, tBeginDate.toString(), tEndDate.toString());
            Shop shop = shopService.getShopByAccountShopId(user.getUserId());
            IndexInfo indexInfo = new IndexInfo(
                    loginTime, signOutTime, useRemainingTime,
                    newOrderNumber, 0, currentRefundNumber,
                    badCommentNumber, noCommentNumber, yesterdayNumber,
                    String.valueOf(yesterdayRevenue), 0,todayNumber,String.valueOf(todayRevenue));
            indexInfo.setUseRemainingTime(useRemainingTime);
            indexInfo.setShopState(shop.getState());
            indexInfo.setShopName(shop.getShopName());
            return indexInfo;
        } catch (Exception e) {
            return null;
        }
    }

    //首页店铺停止营业
    @Override
    public AjaxResponseModel stopShop(HttpServletRequest request,Integer shopStateId) {
        if(shopStateId != null){
            try {
                LoginUser user = OtherUtils.getLoginUserByRequest();
                shopService.changeShopStateById(user.getUserId(),shopStateId);
                return new AjaxResponseModel<>(Globals.COMMON_SUCCESS_AND_REFRESH_CURRENT_PAGE,"操作成功","");
            }catch (Exception e){
                return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED,"操作失败，请稍后再试");
            }
        }else {
            return new AjaxResponseModel(Globals.COMMON_PARAMETER_ERROR);
        }
    }

    /* =============接口结束============== */
}
