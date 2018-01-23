package com.htkapp.modules.API.service.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import com.htkapp.core.API.APIRequestParams;
import com.htkapp.core.MethodsParamsEntity.PushMesEntity;
import com.htkapp.core.MoreMethodsUtils;
import com.htkapp.core.OtherUtils;
import com.htkapp.core.dto.APIResponseModel;
import com.htkapp.core.utils.Globals;
import com.htkapp.core.utils.Jpush;
import com.htkapp.core.utils.StringUtils;
import com.htkapp.modules.API.dto.ConfirmWithdrawalInfo;
import com.htkapp.modules.API.dto.ReturnBuffetFoodOrderData;
import com.htkapp.modules.API.dto.ScanQRCodeInfo;
import com.htkapp.modules.API.entity.Account;
import com.htkapp.modules.API.service.APICommonService;
import com.htkapp.modules.API.service.AccountFocusService;
import com.htkapp.modules.API.service.AccountServiceI;
import com.htkapp.modules.API.service.BuffetFoodService;
import com.htkapp.modules.merchant.buffetFood.dto.ReturnCommentList;
import com.htkapp.modules.merchant.buffetFood.dto.ReturnOrderDetailInfo;
import com.htkapp.modules.merchant.buffetFood.dto.ReturnOrderInfo;
import com.htkapp.modules.merchant.buffetFood.entity.*;
import com.htkapp.modules.merchant.buffetFood.service.*;
import com.htkapp.modules.merchant.shop.entity.AccountShop;
import com.htkapp.modules.merchant.shop.entity.Shop;
import com.htkapp.modules.merchant.shop.service.AccountShopServiceI;
import com.htkapp.modules.merchant.shop.service.ShopServiceI;
import com.xiaoleilu.hutool.date.DateUtil;
import com.xiaoleilu.hutool.json.JSONUtil;
import io.goeasy.GoEasy;
import jdk.nashorn.internal.objects.Global;
import net.sf.json.JsonConfig;
import net.sf.json.util.JSONUtils;
import org.json.JSONArray;
import org.springframework.stereotype.Service;
import sun.security.action.GetLongAction;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.xiaoleilu.hutool.date.DateUtil.NORM_DATETIME_PATTERN;
import static org.apache.commons.lang.time.DateFormatUtils.format;

/**
 * Created by yinqilei on 17-7-18.
 */

@Service
public class BuffetFoodServiceImpl implements BuffetFoodService {

    @Resource
    private SeatInformationService seatInformationService;
    @Resource
    private BuffetFoodCategoryService buffetFoodCategoryService;
    @Resource
    private BuffetFoodProductService buffetFoodProductService;
    @Resource
    private BuffetFoodOrderService buffetFoodOrderService;
    @Resource
    private BuffetFoodOrderProductService buffetFoodOrderProductService;
    @Resource
    private AccountFocusService accountFocusService;
    @Resource
    private AccountShopServiceI accountShopService;
    @Resource
    private BuffetFoodCommentService buffetFoodCommentService;
    @Resource
    private APICommonService apiCommonService;
    @Resource
    private AccountServiceI accountService;
    @Resource
    private BuffetFoodCollectService buffetFoodCollectService;
    @Resource
    private BuffetFoodProductPackageService productPackageService;
    @Resource
    private MoreMethodsUtils moreMethodsUtils;
    @Resource
    private ShopServiceI shopService;

    /* =============接口开始================= */
    //通过商铺id获取分类列表
    @Override
    public APIResponseModel getCategoryList(String qrKey) {
        try {
            Integer shopId = (Integer) OtherUtils.decryptString(qrKey);
            List<BuffetFoodCategory> resultList = buffetFoodCategoryService.getBuffetFoodCategoryListByShopId(shopId);
            if (resultList != null && resultList.size() > 0) {
                for (int i = 0; i < resultList.size(); i++) {
                    if (resultList.get(i).getCategoryName().equals("推荐")) {
                        BuffetFoodCategory a = resultList.get(0);
                        resultList.set(0, resultList.get(i));
                        resultList.set(i, a);
                        break;
                    }
                }
                return new APIResponseModel<List<BuffetFoodCategory>>(Globals.API_SUCCESS, "成功", resultList);
            } else {
                return new APIResponseModel<List<BuffetFoodCategory>>(Globals.API_SUCCESS, "还没有新建分类", resultList);
            }
        } catch (Exception e) {
            return new APIResponseModel(Globals.API_FAIL);
        }
    }

    @Override
    public APIResponseModel getCategoryList(int shopId) {
        try {
            List<BuffetFoodCategory> resultList = buffetFoodCategoryService.getBuffetFoodCategoryListByShopId(shopId);
            if (resultList != null && resultList.size() > 0) {
                for (int i = 0; i < resultList.size(); i++) {
                    if (resultList.get(i).getCategoryName().equals("推荐")) {
                        BuffetFoodCategory a = resultList.get(0);
                        resultList.set(0, resultList.get(i));
                        resultList.set(i, a);
                        break;
                    }
                }
                return new APIResponseModel<List<BuffetFoodCategory>>(Globals.API_SUCCESS, "成功", resultList);
            } else {
                return new APIResponseModel<List<BuffetFoodCategory>>(Globals.API_SUCCESS, "还没有新建分类", resultList);
            }
        } catch (Exception e) {
            return new APIResponseModel(Globals.API_FAIL);
        }
    }

    //根据分类获取菜品列表
    @Override
    public APIResponseModel getGoodsListByCategoryId(APIRequestParams params) {
        if (params != null && params.getCategoryId() != null) {
            int pageNumber = Globals.DEFAULT_PAGE_NO;
            int pageLimit = Globals.DEFAULT_PAGE_LIMIT;
            if (params.getPageNumber() > 1) {
                pageNumber = params.getPageNumber();
            }
            try {
                List<BuffetFoodProduct> resultList = buffetFoodProductService.getGoodsListByCategoryId(params.getCategoryId(), pageNumber, pageLimit);
                if (resultList != null && resultList.size() > 0) {
                    for (BuffetFoodProduct each : resultList) {
                        each.setImgUrl(OtherUtils.getRootDirectory() + each.getImgUrl());
                        if (each.getPgProductList() != null && each.getPgProductList().size() > 0) {
                            for (BuffetFoodProductPackage every : each.getPgProductList()) {
                                every.setcImgUrl(OtherUtils.getRootDirectory() + every.getcImgUrl());
                            }
                        } else {
                            each.setPgProductList(null);
                        }
                    }
                    if (params.getToken() != null) {
                        for (BuffetFoodProduct each : resultList) {
                            int resultInt = buffetFoodCollectService.getCollectObjById(each.getId(), params.getToken());
                            each.setCollectState(resultInt);
                        }
                    }
                    return new APIResponseModel<List<BuffetFoodProduct>>(Globals.API_SUCCESS, "成功", resultList);
                } else {
                    return new APIResponseModel<List<BuffetFoodProduct>>(Globals.API_SUCCESS, "没有产品", resultList);
                }
            } catch (Exception e) {
                return new APIResponseModel(Globals.API_FAIL, e.getMessage());
            }
        } else {
            return new APIResponseModel(Globals.API_REQUEST_BAD);
        }
    }

    //获取商铺的座位列表
    @Override
    public APIResponseModel getShopSeatInfoById(Integer shopId) {
        if (shopId != null) {
            try {
                List<SeatInformation> resultList = seatInformationService.getSeatInformationListByShopId(shopId);
                if (resultList != null) {
                    return new APIResponseModel<List<SeatInformation>>(Globals.API_SUCCESS, "成功", resultList);
                } else {
                    return new APIResponseModel<String>(Globals.API_SUCCESS, "失败", null);
                }
            } catch (Exception e) {
                return new APIResponseModel(Globals.API_FAIL, e.getMessage());
            }
        } else {
            return new APIResponseModel(Globals.API_REQUEST_BAD);
        }
    }

    //通过自助点餐商品id获取商品详情
    @Override
    public APIResponseModel getProductDetailById(APIRequestParams params) {
        if (params != null && params.getProductId() != null) {
            try {
                BuffetFoodProduct result = buffetFoodProductService.getBuffetFoodProductDetailById(params.getProductId());
                if (result != null) {
                    result.setImgUrl(OtherUtils.getRootDirectory() + result.getImgUrl());
                    if (params.getToken() != null) {
                        //查询用户关注的商品集合
                        int resultInt = buffetFoodCollectService.getCollectObjById(params.getProductId(), params.getToken());
                        result.setCollectState(resultInt);
                    }
                    return new APIResponseModel<BuffetFoodProduct>(Globals.API_SUCCESS, "成功", result);
                } else {
                    return new APIResponseModel(Globals.API_FAIL, "查询失败");
                }
            } catch (Exception e) {
                return new APIResponseModel(Globals.API_FAIL);
            }
        } else {
            return new APIResponseModel(Globals.API_REQUEST_BAD);
        }
    }

    //收藏接口
    @Override
    public APIResponseModel addToWishListById(APIRequestParams params) {
        if (params != null && params.getProductId() != null && params.getToken() != null && params.getProductId() != null && params.getState() != null) {
            try {
                int resultInt = buffetFoodCollectService.getCollectObjById(params.getProductId(), params.getToken());
                if (params.getState() == 0) {
                    if (resultInt == 1) {
                        buffetFoodCollectService.deleteCollectById(params.getProductId(), params.getToken());
                        return new APIResponseModel(Globals.API_SUCCESS);
                    }
                    return new APIResponseModel(Globals.API_FAIL, "异常，当前不是收藏状态，不能取收");
                } else {
                    if (resultInt == 0) {
                        BuffetFoodCollect collect = new BuffetFoodCollect();
                        collect.setProductId(params.getProductId());
                        collect.setAccountToken(params.getToken());
                        collect.setState(params.getState());
                        buffetFoodCollectService.insertCollect(collect);
                        return new APIResponseModel(Globals.API_SUCCESS);
                    }
                    return new APIResponseModel(Globals.API_FAIL, "已收藏，不能重复收藏");
                }
            } catch (Exception e) {
                return new APIResponseModel(Globals.API_FAIL);
            }
        } else {
            return new APIResponseModel(Globals.API_REQUEST_BAD);
        }
    }

    //评论列表接口
    @Override
    public APIResponseModel getGoodsCommentListByProductId(APIRequestParams params, int pageNum) {
        if (params != null && params.getProductId() != null && params.getShopId() != null) {
            int pageNumber = Globals.DEFAULT_PAGE_NO;
            int pageLimit = Globals.DEFAULT_PAGE_LIMIT;
            try {
                List<ReturnCommentList> commentList = buffetFoodCommentService.getCommentListByProductId(params.getShopId(), params.getProductId(), pageNumber, pageLimit);
                if (commentList != null) {
                    return new APIResponseModel<>(Globals.API_SUCCESS, "成功", commentList);
                } else {
                    return new APIResponseModel<>(Globals.API_SUCCESS, "成功", null);
                }
            } catch (Exception e) {
                return new APIResponseModel(Globals.API_FAIL);
            }
        } else {
            return new APIResponseModel(Globals.API_REQUEST_BAD);
        }
    }

    //商品页面－确认按钮(生成初始订单,登陆、未登陆状态)
    @Override
    public APIResponseModel insertInitialOrder(APIRequestParams params, BuffetFoodOrder order) {
        //生成初始订单, 有用户token参数则是已登陆状态, 没有用户token 则是未登陆状态
        try {
            //已登陆 返回生成初始订单成功  未登陆  返回初始订单号
            APIResponseModel responseModel = apiCommonService.insertBuffetFoodInitOrder(order);
            if (responseModel.getCode() == 100) {
                return responseModel;
            } else {
                return responseModel;
            }
        } catch (Exception e) {
            return new APIResponseModel(Globals.API_FAIL);
        }
    }

    //订单详情页面
    @Override
    public APIResponseModel getOrderDetailsByOrderNumber(APIRequestParams params) {
        if (params != null && params.getOrderNumber() != null) {
            try {
                //根据订单号查询订单详情{ 订单号，时间，订单状态，已点商品{名字、数量、价格}, 已提交时间 }
                ReturnBuffetFoodOrderData buffetFoodOrder = buffetFoodOrderService.getOrderByOrderNumber(params.getOrderNumber());
                if (buffetFoodOrder != null) {
                    //查找订单中的商品
//                    BuffetFoodOrder buffetFoodOrder = buffetFoodOrderService.getBuffetFoodOrderByOrderNumber(params.getOrderNumber());
//                    List<BuffetFoodOrderProduct> buffetFoodOrderProductList = buffetFoodOrder.getProductLists();
                    List<BuffetFoodOrderProduct> buffetFoodOrderProductList = buffetFoodOrderProductService.getOrderProductListByOrderNumber(buffetFoodOrder.getOrderNumber());
                    buffetFoodOrder.setProductList(buffetFoodOrderProductList);
                    //格式化时间
                    buffetFoodOrder.setOrderTime(format(DateUtil.parse(buffetFoodOrder.getOrderTime()), NORM_DATETIME_PATTERN).substring(5));
                    //查找调单状态、催单状态
                    return new APIResponseModel<>(Globals.API_SUCCESS, "成功", buffetFoodOrder);
                } else {
                    return new APIResponseModel(Globals.API_FAIL, "查找不到订单");
                }
            } catch (Exception e) {
                return new APIResponseModel(Globals.API_FAIL);
            }
        } else {
            return new APIResponseModel(Globals.API_REQUEST_BAD);
        }
    }

    //订单详情－下单请求接口
    @Override
    public APIResponseModel enterOrderRequest(APIRequestParams params) {
        if (params != null && params.getOrderNumber() != null) {
            try {
                //查找数据显示在已点列表
                //数据：分类[商品名, 价格，数量]
                BuffetFoodOrder order = buffetFoodOrderService.getBuffetFoodOrderByOrderNumber(params.getOrderNumber());
                if (order != null) {
                    //验证订单状态
                    if (order.getOrderState() == 0) {
                        //执行下单操作
                        List<BuffetFoodOrderProduct> resultList = buffetFoodOrderProductService.getOrderProductListById(order.getId());
                        if (resultList != null) {
                            for (BuffetFoodOrderProduct every : resultList) {
                                every.setImgUrl(OtherUtils.getRootDirectory() + every.getImgUrl());
                            }
                            return new APIResponseModel<>(Globals.API_SUCCESS, "成功", resultList);
                        } else {
                            return new APIResponseModel(Globals.API_FAIL, "订单异常");
                        }
                    } else if (order.getOrderState() == 1) {
                        //执行下单操作
                        List<BuffetFoodOrderProduct> resultList = buffetFoodOrderProductService.getOrderProductListById(order.getId());
                        if (resultList != null) {
                            for (BuffetFoodOrderProduct every : resultList) {
                                every.setImgUrl(OtherUtils.getRootDirectory() + every.getImgUrl());
                            }
                            return new APIResponseModel<>(Globals.API_SUCCESS, "成功", resultList);
                        } else {
                            return new APIResponseModel(Globals.API_FAIL, "订单异常");
                        }
                    } else if (order.getOrderState() == 2) {
                        return new APIResponseModel(Globals.API_SUCCESS, "订单已完成");
                    }
                    return new APIResponseModel(Globals.API_FAIL);
                } else {
                    return new APIResponseModel(Globals.API_FAIL, "查找订单不存在");
                }
            } catch (Exception e) {
                return new APIResponseModel(Globals.API_FAIL);
            }
        } else {
            return new APIResponseModel(Globals.API_REQUEST_BAD);
        }
    }

    //下单按钮
@Override
    public APIResponseModel enterOrderButton(APIRequestParams params, BuffetFoodOrder order) {
        //通过订单号更新订单内商品列表
        if (params != null && params.getOrderNumber() != null && order != null) {
            try {
                //通过订单号查找订单
                BuffetFoodOrder getOrder = buffetFoodOrderService.getBuffetFoodOrderByOrderNumber(params.getOrderNumber());
                //通过订单id删除订单中的商品列表
                buffetFoodOrderProductService.deleteOrderProductByOrderId(getOrder.getId());
                //再重新插入
                for (BuffetFoodOrderProduct each : order.getProductLists()) {
                    each.setOrderId(getOrder.getId());
                    buffetFoodOrderProductService.insertProductDetailsUnderOrder(each);
                }
                return new APIResponseModel(Globals.API_SUCCESS);
            } catch (Exception e) {
                return new APIResponseModel(Globals.API_FAIL);
            }
        } else {
            return new APIResponseModel(Globals.API_REQUEST_BAD);
        }
    }

   /* //确认下单按钮
    @Override
    public APIResponseModel confirmOrderButton(APIRequestParams params, BuffetFoodOrder order) {
        //改变订单状态，订单已下单
        //备注、使用优惠券、最后总金额
        if (params != null && params.getOrderNumber() != null) {
            //插入备注、插入桌号、修改订单总金额、抵扣金额
            System.out.println(params.toString()+order.toString());
            try {
                //下单成功，推消息
                System.out.println("下单成功");
                BuffetFoodOrder buffetFoodOrder = buffetFoodOrderService.getBuffetFoodOrderByOrderNumber(params.getOrderNumber());
                //查询出订单中的商品
                List<BuffetFoodOrderProduct> resultList = buffetFoodOrderProductService.getOrderProductListById(buffetFoodOrder.getId());
                double orderAmount = 0.00;
                if(resultList != null){
                    for (BuffetFoodOrderProduct each : resultList){
                        orderAmount += each.getPrice() * each.getQuantity();
                    }
                }
                order.setOrderAmount(orderAmount);
                buffetFoodOrderService.confirmOrderButton(order);
                Shop shop = shopService.getShopDataById(buffetFoodOrder.getShopId());
                System.out.println("shop is:"+shop.toString());
                AccountShop user = accountShopService.getAccountShopDataById(shop.getAccountShopId());
                System.out.println("accountShop is:"+user.toString());
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("orderNumber", buffetFoodOrder.getOrderNumber());
                jsonObject.put("orderState", buffetFoodOrder.getOrderState());
                jsonObject.put("orderId", buffetFoodOrder.getId());
                System.out.println("token :"+buffetFoodOrder.getToken()+""+"user token:"+user.getToken()+"");
                if(buffetFoodOrder.getToken() == null){
                    Jpush.jPushMethodToMerchant(user.getToken(),"有一个自助点餐订单","ALERT", "商家版");
                    Jpush.jPushMethodToMerchant(user.getToken(),"有一个自助点餐订单","","");
                }else {
                    moreMethodsUtils.jPushToMerAndAccount(buffetFoodOrder.getToken(),"自助点餐订单下单成功", jsonObject.toJSONString(), user.getToken(),"有一个自助点餐订单", jsonObject.toJSONString(), 2);
                }
                System.out.println("buffetFoodOrderState 是:"+buffetFoodOrder.getOrderState()+""+"user id is:"+user.getId());
                moreMethodsUtils.pushMesToManagePage(new PushMesEntity("自助点餐订单消息", "b", "自助点餐订单下单成功", user.getToken(), 'b', buffetFoodOrder.getOrderState(), "您有一个的自助点餐订单消息", user.getId()));
            }catch (Exception e){
                System.out.println("确认下单异常"+e.getMessage());
                return new APIResponseModel(Globals.API_FAIL);
            }
            return new APIResponseModel(Globals.API_SUCCESS, "成功");
        } else {
            return new APIResponseModel(Globals.API_REQUEST_BAD);
        }
   }*/
   
    //确认下单按钮
    @Override
    public APIResponseModel confirmOrderButton(APIRequestParams params, BuffetFoodOrder order) {
        //改变订单状态，订单已下单
        //备注、使用优惠券、最后总金额
        if (params != null && params.getOrderNumber() != null) {
            //插入备注、插入桌号、修改订单总金额、抵扣金额
            System.out.println(params.toString()+order.toString());
            try {
                //下单成功，推消息
                System.out.println("下单成功");
                BuffetFoodOrder buffetFoodOrder = buffetFoodOrderService.getBuffetFoodOrderByOrderNumber(params.getOrderNumber());
                //查询出订单中的商品
                List<BuffetFoodOrderProduct> resultList = buffetFoodOrderProductService.getOrderProductListById(buffetFoodOrder.getId());
                double orderAmount = 0.00;
                if(resultList != null){
                    for (BuffetFoodOrderProduct each : resultList){
                        orderAmount += each.getPrice() * each.getQuantity();
                    }
                }
                //设置订单的金额
                order.setOrderAmount(orderAmount);
                //新增订单信息
                buffetFoodOrderService.confirmOrderButton(order);
                //变更座位状态信息
                //TODO
                int b=1;
                int a=seatInformationService.updataSeatInfoByOrder(order,b);
                if(a<=0) {
                	 return new APIResponseModel(Globals.API_FAIL, "座位已满");
                }
              //根据店铺id查找店铺信息
                Shop shop = shopService.getShopDataById(buffetFoodOrder.getShopId());
              //通过商户店铺id查找商户信息
                AccountShop user = accountShopService.getAccountShopDataById(shop.getAccountShopId());
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("orderNumber", buffetFoodOrder.getOrderNumber());
                jsonObject.put("orderState", buffetFoodOrder.getOrderState());
                jsonObject.put("orderId", buffetFoodOrder.getId());
                System.out.println("token :"+buffetFoodOrder.getToken()+""+"user token:"+user.getToken()+"");
                if(buffetFoodOrder.getToken() == null){
                    Jpush.jPushMethodToMerchant(user.getToken(),"有一个自助点餐订单","ALERT", "商家版");
                    Jpush.jPushMethodToMerchant(user.getToken(),"有一个自助点餐订单","","");
                }else {
                    moreMethodsUtils.jPushToMerAndAccount(buffetFoodOrder.getToken(),"自助点餐订单下单成功", jsonObject.toJSONString(), user.getToken(),"有一个自助点餐订单", jsonObject.toJSONString(), 2);
                }
                System.out.println("buffetFoodOrderState 是:"+buffetFoodOrder.getOrderState()+""+"user id is:"+user.getId());
                moreMethodsUtils.pushMesToManagePage(new PushMesEntity("自助点餐订单消息", "b", "自助点餐订单下单成功", user.getToken(), 'b', buffetFoodOrder.getOrderState(), "您有一个的自助点餐订单消息", user.getId()));
            }catch (Exception e){
                System.out.println("确认下单异常"+e.getMessage());
                return new APIResponseModel(Globals.API_FAIL);
            }
            return new APIResponseModel(Globals.API_SUCCESS, "成功");
        } else {
            return new APIResponseModel(Globals.API_REQUEST_BAD);
        }
    }

    //优惠券列表
    @Override
    public APIResponseModel getBuffetFoodCouponList(APIRequestParams params) {
        if (params != null && params.getToken() != null) {
            return new APIResponseModel<>(Globals.API_SUCCESS, "成功", null);
        } else {
            return new APIResponseModel(Globals.API_REQUEST_BAD);
        }
    }

    //已点商品列表
    @Override
    public APIResponseModel getLastModifiedProductList(APIRequestParams params) {
        if (params != null && params.getOrderNumber() != null) {
            //根据订单号查询订单详情{ 订单号，时间，订单状态，已点商品{名字、数量、价格}, 已提交时间 }
            ReturnBuffetFoodOrderData buffetFoodOrderData = buffetFoodOrderService.getOrderByOrderNumber(params.getOrderNumber());
            if (buffetFoodOrderData != null) {
                //查找订单中的商品
                BuffetFoodOrder buffetFoodOrder = buffetFoodOrderService.getBuffetFoodOrderByOrderNumber(params.getOrderNumber());
                net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(buffetFoodOrder.getAdjustOrderProductJson());
                List<BuffetFoodOrderProduct> buffetFoodOrderProductList = net.sf.json.JSONArray.toList(jsonArray,new BuffetFoodOrderProduct(),new JsonConfig());
//                List<BuffetFoodOrderProduct> buffetFoodOrderProductList = buffetFoodOrderProductService.getOrderProductListByOrderNumber(buffetFoodOrder.getOrderNumber());
                buffetFoodOrderData.setProductList(buffetFoodOrderProductList);
                System.out.println("已点商品列表");
                return new APIResponseModel<>(Globals.API_SUCCESS, "成功", buffetFoodOrderData);
            } else {
                return new APIResponseModel(Globals.API_FAIL, "查找不到订单");
            }
        } else {
            return new APIResponseModel(Globals.API_REQUEST_BAD);
        }
    }

    //自助支付、支付优惠金额
    @Override
    public APIResponseModel getBuffetDiscount(APIRequestParams params) {
        if (params != null && params.getShopId() != null) {
            return new APIResponseModel<>(Globals.API_SUCCESS, "成功", 0 + "元");
        } else {
            return new APIResponseModel(Globals.API_REQUEST_BAD);
        }
    }

    //获取用户信息接口
    @Override
    public APIResponseModel getAccountMes(APIRequestParams params) {
        if (params != null && params.getToken() != null) {
            try {
                Account account = accountService.selectByToken(params.getToken());
                //查找优惠券和积分
                if (account != null) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("imgUrl", OtherUtils.getRootDirectory() + account.getAvatarUrl());
                    jsonObject.put("nickName", account.getNickName());
                    jsonObject.put("userPhone", account.getPhone());
                    jsonObject.put("integralVal", 0);
                    jsonObject.put("discountCouponCount", 0);
                    return new APIResponseModel<>(Globals.API_SUCCESS, "成功", jsonObject);
                } else {
                    return new APIResponseModel(Globals.API_FAIL, "用户不存在");
                }
            } catch (Exception e) {
                return new APIResponseModel(Globals.API_FAIL);
            }
        } else {
            return new APIResponseModel(Globals.API_REQUEST_BAD);
        }
    }

    //自助点餐催单接口
    @Override
    public APIResponseModel reminderInterface(APIRequestParams params) {
        if (params != null && params.getOrderNumber() != null) {
            //改变催单状态
            try {
                BuffetFoodOrder getOrder = buffetFoodOrderService.getBuffetFoodOrderByOrderNumber(params.getOrderNumber());
                if (getOrder != null) {
                    //判断订单状态
                    if (getOrder.getOrderState() == 0) {
                        return new APIResponseModel(Globals.API_FAIL, "订单还未下单");
                    } else if (getOrder.getOrderState() == 1) {
                        System.out.println("重复了");
                        buffetFoodOrderService.reminder(params.getOrderNumber(), 1);
                        Shop shop = shopService.getShopDataById(getOrder.getShopId());
                        AccountShop user = accountShopService.getAccountShopDataById(shop.getAccountShopId());
                        System.out.println("shop object is:"+shop.toString());
                        System.out.println("accountShop object is:"+user.toString());
                        JSONObject jsonObject_ = new JSONObject();
                        jsonObject_.put("orderNumber", getOrder.getOrderNumber());
                        jsonObject_.put("orderState", getOrder.getOrderState());
                        jsonObject_.put("orderId", getOrder.getId());
                        if(getOrder.getToken() != null){
                            moreMethodsUtils.jPushToMerAndAccount(getOrder.getToken(),"自助点餐订单催单请求已发送", jsonObject_.toJSONString(), user.getToken(),"有一个自助点餐订单信息", jsonObject_.toJSONString(), 2);
                        }
                        moreMethodsUtils.pushMesToManagePage(new PushMesEntity("自助点餐订单消息", "b", "自助点餐订单催单消息", user.getToken(), 'b', 3, "您有一个的自助点餐订单消息", user.getId()));
                        return new APIResponseModel(Globals.API_SUCCESS);
                    } else if (getOrder.getOrderState() == 2) {
                        return new APIResponseModel(Globals.API_FAIL, "订单已完成");
                    } else {
                        return new APIResponseModel(Globals.API_FAIL);
                    }
                } else {
                    return new APIResponseModel(Globals.API_REQUEST_BAD, "订单不存在");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return new APIResponseModel(Globals.API_FAIL);
            }
        } else {
            return new APIResponseModel(Globals.API_REQUEST_BAD);
        }
    }

    //调整订单接口
    @Override
    public APIResponseModel adjustOrder(APIRequestParams params, BuffetFoodOrder order) {
        if (params != null && order != null) {
            BuffetFoodOrder getOrder = buffetFoodOrderService.getBuffetFoodOrderByOrderNumber(params.getOrderNumber());
            if (getOrder != null) {
                //判断订单状态
                if (getOrder.getOrderState() == 0) {
                    return new APIResponseModel(Globals.API_FAIL, "订单还未下单");
                } else if (getOrder.getOrderState() == 1) {
                    //根据订单号查找未修改前订单下的数据
                    try {
                        List<BuffetFoodOrderProduct> productList = buffetFoodOrderProductService.getOrderProductListById(getOrder.getId());
                        //比对数据
                        outLoop:
                        for (BuffetFoodOrderProduct each : order.getProductLists()) {
                            innerLoop:
                            for (BuffetFoodOrderProduct every : productList) {
                                if (!each.getProductId().equals(every.getProductId())) {
                                    //状态１代表新增商品  删除后就不显示
                                    each.setState(1);
                                }
                            }
                        }
                        Gson gson = new Gson();
                        String orderProductJsonStr = gson.toJson(order.getProductLists());
                        order.setAdjustOrderProductJson(orderProductJsonStr);
                        buffetFoodOrderService.updateOrderAdjustOrderJson(order);
                        Shop shop = shopService.getShopDataById(getOrder.getShopId());
                        AccountShop user = accountShopService.getAccountShopDataById(shop.getAccountShopId());
                        JSONObject jsonObject_ = new JSONObject();
                        jsonObject_.put("orderNumber", getOrder.getOrderNumber());
                        jsonObject_.put("orderState", getOrder.getOrderState());
                        jsonObject_.put("orderId", getOrder.getId());
                        if(getOrder.getToken() != null){
                            moreMethodsUtils.jPushToMerAndAccount(getOrder.getToken(),"自助点餐订单催单请求已发送", jsonObject_.toJSONString(), user.getToken(),"有一个自助点餐订单信息", jsonObject_.toJSONString(), 2);
                        }
                        moreMethodsUtils.pushMesToManagePage(new PushMesEntity("自助点餐订单消息", "b", "自助点餐订单催单消息", user.getToken(), 'b', 4, "您有一个的自助点餐订单消息", user.getId()));
                        return new APIResponseModel(Globals.API_SUCCESS,"调单成功");
                    } catch (Exception e) {
                        return new APIResponseModel(Globals.API_FAIL,"调单失败");
                    }
                } else if (getOrder.getOrderState() == 2) {
                    return new APIResponseModel(Globals.API_FAIL, "订单已完成");
                } else {
                    return new APIResponseModel(Globals.API_FAIL,"订单状态错误");
                }
            } else {
                return new APIResponseModel(Globals.API_REQUEST_BAD, "订单不存在");
            }
        } else {
            return new APIResponseModel(Globals.API_REQUEST_BAD);
        }
    }

    //确认调单接口
    @Override
    public APIResponseModel enterAdjustOrder(APIRequestParams params, BuffetFoodOrder order) {
        if (params != null && order != null) {
            //取出参数和值，形成json字符串
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("remark", order.getRemark());
            jsonObject.put("discountAmount", order.getDiscountAmount());
            jsonObject.put("seatName", order.getSeatName());
            jsonObject.put("orderAmount", order.getOrderAmount());
            order.setAdjustOrderJson(jsonObject.toJSONString());
            buffetFoodOrderService.updateOrderAdjustOrderJson(order);
            try {
                //下单成功，推消息
                //修改
                BuffetFoodOrder buffetFoodOrder = buffetFoodOrderService.getBuffetFoodOrderByOrderNumber(order.getOrderNumber());
//                Shop shop = shopService.getShopDataById(order.getShopId());
                Shop shop = shopService.getShopDataById(buffetFoodOrder.getShopId());

                AccountShop user = accountShopService.getAccountShopDataById(shop.getAccountShopId());
                JSONObject jsonObject_ = new JSONObject();
                jsonObject_.put("orderNumber", buffetFoodOrder.getOrderNumber());
                jsonObject_.put("orderState", buffetFoodOrder.getOrderState());
                jsonObject_.put("orderId", buffetFoodOrder.getId());
//                jsonObject_.put("orderNumber", order.getOrderNumber());
//                jsonObject_.put("orderState", order.getOrderState());
//                jsonObject_.put("orderId", order.getId());
                if(order.getToken() != null){
                    moreMethodsUtils.jPushToMerAndAccount(order.getToken(),"自助点餐订单催单请求已发送", jsonObject_.toJSONString(), user.getToken(),"有一个自助点餐订单信息", jsonObject_.toJSONString(), 2);
                }
                String userToken = user.getToken();
                Integer orderState = buffetFoodOrder.getOrderState();
                //因为此处的orderState获取的是null，再加上PushMesEntity构造方法里面接收的是int基本数据类型，
                // 所以在下面的new PushMesEntity()传参数的时候，null向int基本数据类型赋值就会报空指针异常
//                Integer orderState = order.getOrderState();
                Integer userId = user.getId();
                PushMesEntity pushMesEntity = new PushMesEntity("自助点餐订单消息", "b", "自助点餐订单调单申请",userToken , 'b', orderState, "您有一个的自助点餐订单消息", userId);
                moreMethodsUtils.pushMesToManagePage(pushMesEntity);
            }catch (Exception e){
                return new APIResponseModel(Globals.API_FAIL);
            }
            return new APIResponseModel(Globals.API_SUCCESS);
        } else {
            return new APIResponseModel(Globals.API_REQUEST_BAD);
        }
    }

    //确认调单页面商品列表
    @Override
    public APIResponseModel getLastModifiedAdjustProductList(APIRequestParams params) {
        if (params != null && params.getOrderNumber() != null) {
            BuffetFoodOrder order = buffetFoodOrderService.getBuffetFoodOrderByOrderNumber(params.getOrderNumber());
            if (order != null) {
                if (order.getAdjustOrderJson() != null && order.getAdjustOrderProductJson() != null) {
                    //解析json字符串，返回app
                    try {
                        Gson gson = new Gson();
                        List<BuffetFoodOrderProduct> productLists = gson.fromJson(order.getAdjustOrderProductJson(), new TypeToken<List<BuffetFoodOrderProduct>>() {
                        }.getType());
                        if (productLists != null && productLists.size() > 0) {
                            return new APIResponseModel<>(Globals.API_SUCCESS, "成功", productLists);
                        }
                        return new APIResponseModel(Globals.API_FAIL, "异常返回");
                    } catch (Exception e) {
                        return new APIResponseModel(Globals.API_FAIL, "异常返回");
                    }
                } else {
                    return new APIResponseModel(Globals.API_FAIL, "当前订单没有执行调单操作");
                }
            } else {
                return new APIResponseModel(Globals.API_FAIL, "订单异常");
            }
        } else {
            return new APIResponseModel(Globals.API_REQUEST_BAD);
        }
    }

    //搜索商品
    @Override
    public APIResponseModel searchProductByKey(APIRequestParams params) {
        if (params != null && params.getSearchKey() != null && !params.getSearchKey().equals("")) {
            //返回匹配到的分类和分类下的商品
            try {
                //搜索分类名
                //搜索商品名
                /**
                 * 先搜分类，分类没有搜商品，商品没有则直接返回
                 * 先搜分类，分类没有搜索商品，商品有则再查找分类,并和分类商品一块返回
                 * 先搜分类，分类有则再查找分类下的商品， 并返回
                 */
                List<BuffetFoodCategory> categoryList = buffetFoodCategoryService.matchSearchingByCategoryName(params.getSearchKey());
                if (categoryList != null) {
                    //根据分类搜索商品(并加入分类集合数据中)
                    for (BuffetFoodCategory each : categoryList) {
                        List<BuffetFoodProduct> products = buffetFoodProductService.getBuffetFoodProductById(each.getId());
                        each.setProductList(products);
                    }
                    return new APIResponseModel<>(Globals.API_SUCCESS, "成功", categoryList);
                } else {
                    //搜索商品
                    List<BuffetFoodProduct> productList = buffetFoodProductService.matchSearchingByProductName(params.getSearchKey());
                    List<BuffetFoodCategory> categoryList1 = new ArrayList<>();
                    if (productList != null) {
                        for (BuffetFoodProduct each : productList) {
                            BuffetFoodCategory category = buffetFoodCategoryService.getCategoryById(each.getCategoryId());
                            categoryList1.add(category);
                        }
                        return new APIResponseModel<>(Globals.API_SUCCESS, "成功", categoryList1);
                    } else {
                        return new APIResponseModel<>(Globals.API_SUCCESS, "成功", null);
                    }
                }
            } catch (Exception e) {
                return new APIResponseModel(Globals.API_FAIL, e.getMessage());
            }
        } else {
            return new APIResponseModel(Globals.API_REQUEST_BAD);
        }
    }

    //订单状态查询接口
    @Override
    public APIResponseModel getOrderStateByOrderNumber(APIRequestParams params) {
        if (params != null && params.getOrderNumber() != null) {
            try {
                BuffetFoodOrder order = buffetFoodOrderService.getBuffetFoodOrderByOrderNumber(params.getOrderNumber());
                if (order != null) {
                    return new APIResponseModel<>(Globals.API_SUCCESS, "成功", order.getOrderState());
                } else {
                    return new APIResponseModel(Globals.API_FAIL, "查询不到订单");
                }
            } catch (Exception e) {
                return new APIResponseModel(Globals.API_FAIL);
            }
        } else {
            return new APIResponseModel(Globals.API_REQUEST_BAD);
        }
    }

    //根据父id查询套餐内详情
    @Override
    public APIResponseModel getPackageDetailById(APIRequestParams params) {
        if (params != null && params.getId() != null) {
            try {
                List<BuffetFoodProductPackage> productPackageList = productPackageService.getPackageProductListById(params.getId());
                if (productPackageList != null) {
                    for (BuffetFoodProductPackage each : productPackageList) {
                        each.setcImgUrl(OtherUtils.getRootDirectory() + each.getcImgUrl());
                    }
                }
                return new APIResponseModel<>(Globals.API_SUCCESS, "成功", productPackageList);
            } catch (Exception e) {
                return new APIResponseModel(Globals.API_FAIL);
            }
        } else {
            return new APIResponseModel(Globals.API_REQUEST_BAD);
        }
    }

    //商品点赞，数量固定加1
    @Override
    public APIResponseModel likeProduct(Integer productId) {
        if (productId != null) {
            try {
                buffetFoodProductService.likeProductAddOneById(productId);
                return new APIResponseModel(Globals.API_SUCCESS, "成功");
            } catch (Exception e) {
                return new APIResponseModel(Globals.API_FAIL, e.getMessage());
            }
        } else {
            return new APIResponseModel(Globals.API_REQUEST_BAD);
        }
    }

    //根据用户token获取自助点餐订单列表
    @Override
    public APIResponseModel getBuffetFoodOrderList(String token, Integer shopId, int pageNumber) {
        if (shopId != null) {
            //1.根据用户token查询自助订单表，分页
            int pageNo = Globals.API_HOME_PAGE_CATEGORY_NO;
            int pageLimit = Globals.API_HOME_PAGE_CATEGORY_LIMIT;
            if (pageNumber > 1) {
                pageNo = pageNumber;
            }
            try {
                List<BuffetFoodOrder> resultList = buffetFoodOrderService.getOrderListByToken(token, shopId, pageNo, pageLimit);
                if (resultList != null) {
                    return new APIResponseModel<>(Globals.API_SUCCESS, "成功", resultList);
                } else {
                    return new APIResponseModel(Globals.API_SUCCESS, "暂时没有订单数据");
                }
            } catch (Exception e) {
                return new APIResponseModel(Globals.API_FAIL, e.getMessage());
            }
        } else {
            return new APIResponseModel(Globals.API_REQUEST_BAD, "shopId为空，或key值不对");
        }
    }

    //根据订单id获取订单详情列表
    @Override
    public APIResponseModel getOrderDetailList(String token, Integer orderId) {
        //１.根据传入orderId查询订单详情(一条语句，关联shop表,关联buffet_food_order_product)
        //订单详情加订单状态
        if (orderId != null) {
            try {
                ReturnOrderDetailInfo info = buffetFoodOrderService.getOrderDetailListByTokenAndId(token, orderId);
                if (info != null) {
                    Date time = DateUtil.parse(info.getOrderTime());
                    String forMatTime = DateUtil.format(time, "MM-dd HH:mm");
                    info.setOrderTime(forMatTime);
                    info.setLogoUrl(OtherUtils.getRootDirectory() + info.getLogoUrl());
                    List<BuffetFoodOrderProduct> resultList = buffetFoodOrderProductService.getOrderProductListById(orderId);
                    if (resultList != null) {
                        info.setProductLists(resultList);
                        return new APIResponseModel<>(Globals.API_SUCCESS, "，成功", info);
                    } else {
                        return new APIResponseModel<>(Globals.API_SUCCESS, "失败", info);
                    }
                } else {
                    return new APIResponseModel<>(Globals.API_SUCCESS, "失败", null);
                }
            } catch (Exception e) {
                return new APIResponseModel(Globals.API_FAIL, e.getMessage());
            }
        } else {
            return new APIResponseModel(Globals.API_REQUEST_BAD);
        }
    }

    //支付成功调起改变订单状态
    @Override
    public APIResponseModel paymentSuccess(String token, Integer orderId, Integer paymentMethodId) {
        if (orderId == null) {
            return new APIResponseModel(Globals.API_REQUEST_BAD);
        } else {
            try {
                //存入支付方式
                buffetFoodOrderService.changeOrderPayState(orderId, 1, token, paymentMethodId);
            } catch (Exception e) {
                return new APIResponseModel(Globals.API_FAIL);
            }
            return new APIResponseModel(Globals.API_SUCCESS);
        }
    }

    //扫码确认当前店铺是否收藏
    @Override
    public APIResponseModel confirmCollection(String qrKey, String token) {
        if (StringUtils.isNotEmpty(qrKey)) {
            try {
                Integer shopId = (Integer) OtherUtils.decryptString(qrKey);
                int result = accountFocusService.checkCollectionByAccountIdAndShopId(token, shopId);
                return new APIResponseModel<>(Globals.API_SUCCESS, "成功", new ScanQRCodeInfo(shopId, result > 0));
            } catch (Exception e) {
                return new APIResponseModel(Globals.API_FAIL, e.getMessage());
            }
        } else {
            return new APIResponseModel(Globals.API_REQUEST_BAD);
        }
    }

    //根据用户token,pageNumber获取订单列表
    @Override
    public APIResponseModel getOrderListByTokenAndPageNumber(String token, int pageNumber) {
        int pageNo = Globals.API_HOME_PAGE_CATEGORY_NO;
        int pageLimit = Globals.API_HOME_PAGE_CATEGORY_LIMIT;
        if (pageNumber > 1) {
            pageNo = pageNumber;
        }
        try {
            List<ReturnOrderInfo> resultList = buffetFoodOrderService.getOrderListByToken(token, pageNo, pageLimit);
            if (resultList != null) {
                for (ReturnOrderInfo each : resultList) {
                    Date time = DateUtil.parse(each.getOrderTime());
                    String forMatTime = DateUtil.format(time, "MM-dd HH:mm");
                    each.setOrderTime(forMatTime);
                    each.setLogoUrl(OtherUtils.getRootDirectory() + each.getLogoUrl());
                }
                return new APIResponseModel<>(Globals.API_SUCCESS, "成功", resultList);
            } else {
                return new APIResponseModel<>(Globals.API_SUCCESS, "成功", null);
            }
        } catch (Exception e) {
            return new APIResponseModel(Globals.API_FAIL, "获取订单列表失败");
        }
    }

    //用户催单 发送消息给商家
    @Override
    public APIResponseModel reminderFormToMerchant(String token, String orderNumber) {
        if (StringUtils.isNotEmpty(orderNumber)) {
            try {
                BuffetFoodOrder order = buffetFoodOrderService.getOrderByOrderNumber(orderNumber, token);
                //推单给商家app  推单给后台管理页面 (并加入消息到通知中)
                GoEasy goEasy = new GoEasy(Globals.goEasyKey);
                goEasy.publish("receiveMessage", "Hello World!");
                //极光推送给商家app
                //根据订单中的店铺id查找商户信息
                AccountShop accountShop = accountShopService.getAccountShopDataById(order.getShopId());
                if (accountShop != null) {
                    Jpush.jPushMethodToMerchant(accountShop.getToken(), "您有一个催单消息", "ALERT", "商家版");
                    //下单成功，推消息
                    Shop shop = shopService.getShopDataById(order.getShopId());
                    AccountShop user = accountShopService.getAccountShopDataById(shop.getAccountShopId());
                    JSONObject jsonObject_ = new JSONObject();
                    jsonObject_.put("orderNumber", order.getOrderNumber());
                    jsonObject_.put("orderState", order.getOrderState());
                    jsonObject_.put("orderId", order.getId());
                    moreMethodsUtils.jPushToMerAndAccount(order.getToken(),"自助点餐订单催单请求已发送", jsonObject_.toJSONString(), user.getToken(),"有一个自助点餐订单信息", jsonObject_.toJSONString(), 2);
                    moreMethodsUtils.pushMesToManagePage(new PushMesEntity("自助点餐订单消息", "b", "自助点餐订单催单消息", user.getToken(), 'b', 3, "您有一个的自助点餐订单消息", user.getId()));
                    return new APIResponseModel(Globals.API_SUCCESS);
                }
                return new APIResponseModel<>(Globals.API_FAIL, "根据用户token和订单号查询订单为空", null);
            } catch (Exception e) {
                return new APIResponseModel(Globals.API_FAIL);
            }
        } else {
            return new APIResponseModel(Globals.API_REQUEST_BAD);
        }
    }

    //退菜请求商家确认接口
    @Override
    public APIResponseModel confirmWithdrawalRequestByOrderNumber(String token, String orderNumber, List<Integer> idLists) {
        //根据用户token 和 orderNumber 查找商家信息,提示商家是否允许用户退菜
        //退菜接口，用户发起退菜请求，传参订单号，自增ID号,
        //推送给商家确认
        //商家确认允许后，则退菜请求成功 推送给app退菜成功
        //商家确认拒绝后，则退菜请求失败 推送给app退菜失败原因
        if (StringUtils.isNotEmpty(orderNumber)) {
            //先根据订单号和订单产品表中查询订单下的产品信息
            //推送给商家,等待商家确认
            try {
                BuffetFoodOrder order = buffetFoodOrderService.getOrderByOrderNumber(orderNumber, token);
                //根据订单号查找该订单下有几个产品(如果当前只有点餐一个产品，取消后，则删除该订单)
                ConfirmWithdrawalInfo info = new ConfirmWithdrawalInfo();
                //再根据订单号和订单产品的自增id查询订单产品
                if (order != null) {
                    List<BuffetFoodOrderProduct> productLists = buffetFoodOrderProductService.getOrderProductListById(order.getId());
                    //订单不为空
                    info.setSeatName(order.getSeatName());
                    //根据请求的订单中产品id标识查找数据
                    List<BuffetFoodOrderProduct> productList = buffetFoodOrderProductService.getOrderProductDetailById(order.getId(), idLists);
                    if (productList != null) {
                        info.setProductList(productList);
                    }
                    AccountShop accountShop = accountShopService.getAccountShopDataById(order.getShopId());
                    if (accountShop != null) {
                        //推送给商家退菜信息
                        Jpush.jPushMethodToMerchant(accountShop.getToken(), "您有一个退单请求,请到后台管理页面处理...", "ALERT", "商家版");
                    }
                    //推送给用户提示消息
                    Jpush.jPushMethod(token, "退菜请求已发起，等待商户确认", "ALERT");
                    //休眠5秒开始
                    System.out.println(System.currentTimeMillis());
                    System.out.println("休眠5秒开始............");
                    Thread.sleep(5000);
                    System.out.println("休眠5秒结束............");
                    System.out.println(System.currentTimeMillis());
                    Jpush.jPushMethod(token, "商家已同意退菜请求", "ALERT");
                    //改变订单中产品状态
                    if (productLists.size() == idLists.size()) {
                        //删除该订单
                        buffetFoodOrderService.delOrderById(order.getId());
                    }
                    //删除订单中要退的菜品信息
                    buffetFoodOrderProductService.delOrderProductById(idLists);
                    if (accountShop != null) {
                        Jpush.jPushMethodToMerchant(accountShop.getToken(), "单号:" + orderNumber + ", 退菜请求处理成功", "ALERT", "商家版");
                    }
                    return new APIResponseModel(Globals.API_SUCCESS, "成功");
                } else {
                    return new APIResponseModel(Globals.API_FAIL, "查询订单异常");
                }
            } catch (Exception e) {
                return new APIResponseModel(Globals.API_FAIL);
            }
        } else {
            return new APIResponseModel(Globals.API_REQUEST_BAD);
        }
    }

    //商户回复用户退菜请求
    @Override
    public APIResponseModel replyToRefundRequest(String token, String accountShopToken, String result) {
        if (StringUtils.isNotEmpty(result)) {
            try {
                boolean isConfirm = Boolean.parseBoolean(result);
                if (isConfirm) {
                    //推送给用户端
                    Jpush.jPushMethod(token, "商家已同意退菜", "ALERT");
                } else {
                    Jpush.jPushMethodToMerchant(accountShopToken, "确认用户退菜请求,操作成功", "ALERT", "商家版");
                }
                return new APIResponseModel(Globals.API_SUCCESS, "成功");
            } catch (Exception e) {
                return new APIResponseModel(Globals.API_FAIL);
            }
        } else {
            return new APIResponseModel(Globals.API_REQUEST_BAD);
        }
    }

    //商户回复催单  发送消息给用户
    @Override
    public APIResponseModel reminderFormToAccount(String token, String accountShopToken, String replyContent) {
        if (StringUtils.isNotEmpty(token) && StringUtils.isNotEmpty(accountShopToken) && StringUtils.isNotEmpty(replyContent)) {
            //推送给商家
            try {
                Jpush.jPushMethod(token, replyContent, "ALERT");
                return new APIResponseModel(Globals.API_SUCCESS);
            } catch (Exception e) {
                return new APIResponseModel(Globals.API_FAIL);
            }
        } else {
            return new APIResponseModel(Globals.API_REQUEST_BAD);
        }
    }

    //快捷下单接口
    @Override
    public APIResponseModel quickOrder(BuffetFoodOrder buffetFoodOrder) {
        //生成初始订单, 有用户token参数则是已登陆状态, 没有用户token 则是未登陆状态
        try {
            //已登陆 返回生成初始订单成功  未登陆  返回初始订单号
             return apiCommonService.insertBuffetFoodInitOrder(buffetFoodOrder);
        } catch (Exception e) {
            return new APIResponseModel(Globals.API_FAIL);
        }
    }

    /* =============接口结束================= */
}
