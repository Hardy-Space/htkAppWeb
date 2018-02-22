package com.htkapp.modules.merchant.buffetFood.web;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.htkapp.core.MoreMethodsUtils;
import com.htkapp.core.OtherUtils;
import com.htkapp.core.MethodsParamsEntity.PushMesEntity;
import com.htkapp.core.dto.APIResponseModel;
import com.htkapp.core.jsAjax.AjaxResponseModel;
import com.htkapp.core.params.AjaxRequestParams;
import com.htkapp.core.params.RequestParams;
import com.htkapp.core.utils.Globals;
import com.htkapp.core.utils.Jpush;
import com.htkapp.core.utils.StringUtils;
import com.htkapp.modules.common.entity.LoginUser;
import com.htkapp.modules.merchant.buffetFood.dao.BuffetFoodOrderMapper;
import com.htkapp.modules.merchant.buffetFood.entity.BuffetFoodCategory;
import com.htkapp.modules.merchant.buffetFood.entity.BuffetFoodOrder;
import com.htkapp.modules.merchant.buffetFood.entity.BuffetFoodOrderProduct;
import com.htkapp.modules.merchant.buffetFood.entity.BuffetFoodProduct;
import com.htkapp.modules.merchant.buffetFood.entity.SeatInformation;
import com.htkapp.modules.merchant.buffetFood.service.*;
import com.htkapp.modules.merchant.shop.entity.AccountShop;
import com.htkapp.modules.merchant.shop.entity.Shop;
import com.htkapp.modules.merchant.shop.service.AccountShopServiceI;
import com.htkapp.modules.merchant.shop.service.ShopServiceI;
import com.htkapp.modules.merchant.takeout.entity.TakeoutCategory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.htkapp.modules.merchant.takeout.web.TakeoutController.mTakeoutDirectory;

@Controller
@RequestMapping("/merchant/buffetFood")
public class BuffetFoodController {

	@Resource
	private BuffetFoodControllerService buffetFoodControllerService;
	@Resource
	private BuffetFoodProductService buffetFoodProductService;
	@Resource
	private BuffetFoodCategoryService buffetFoodCategoryService;
	@Resource
	private BuffetFoodOrderProductService buffetFoodOrderProductService;
	@Resource
	private BuffetFoodOrderService buffetFoodOrderService;
	@Resource
	private ShopServiceI shopService;
	@Resource
	private BuffetFoodOrderMapper buffetFoodOrderDao;
	@Resource
	private MoreMethodsUtils moreMethodsUtils;
	@Resource
	private AccountShopServiceI accountShopService;
	@Resource
	private SeatInformationService seatInfo;

	//========================================================商品
	//==== 页面
	//新增自助商品页面
	@RequestMapping(value = "/product/addProduct", method = RequestMethod.GET)
	public String addProduct(Model model) {
		//查找商铺下的所有分类，mode传值到页面
		buffetFoodControllerService.getCategoryListById(model);
		return mTakeoutDirectory + "product_buffetFood_index_add";
	}

	//新增自助商品post提交页面
	@RequestMapping(value = "/product/addProduct", method = RequestMethod.POST)
	public String addProduct(BuffetFoodProduct product, MultipartFile imgFile) {
		buffetFoodControllerService.addProduct(product, imgFile);
		return "redirect:/merchant/buffetFood/product/homePage";
	}

	//编辑商品页面
	@RequestMapping(value = "/product/editProduct", method = RequestMethod.GET)
	public String editProduct(Model model, Integer productId) {
		//根据产品id查找出商品详情
		buffetFoodControllerService.getProductDetailByPID(model, productId);
		return mTakeoutDirectory + "product_buffetFood_index_edit";
	}

	//保存自助商品修改页面
	@RequestMapping(value = "/product/saveProduct", method = RequestMethod.POST)
	public String saveProduct(BuffetFoodProduct product) {
		try {
			buffetFoodControllerService.saveProductEdit(product);
			return mTakeoutDirectory + "product_buffetFood_index";
		} catch (Exception e) {
			return "redirect:editProduct?productId=" + product.getId();
		}
	}

	//====　接口
	//获取自助点餐商品页面接口
	@RequestMapping(value = "/product/getProductData", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResponseModel getProductById(String actionName) {
		try {
			LoginUser user = OtherUtils.getLoginUserByRequest();
			if (actionName.equals("getData")) {
				//获取全部分类和商品
				return buffetFoodControllerService.getCategoryAndProductByAccountShopId(user.getUserId());
			} else {
				return null;
			}
		} catch (Exception e) {
			return new AjaxResponseModel<>(Globals.COMMON_OPERATION_FAILED, "失败");
		}
	}

	//删除商品接口
	@RequestMapping("/product/delProductById")
	@ResponseBody
	public AjaxResponseModel delProductById(Integer productId) {
		try {
			buffetFoodProductService.delProductById(productId);
			return new AjaxResponseModel<>(Globals.COMMON_SUCCESS_AND_JUMP_URL, "删除成功", "/merchant/takeout/index");
		} catch (Exception e) {
			return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED, e.getMessage());
		}
	}

	//======================================================分类
	//==== 接口
	//删除分类接口
	@RequestMapping("/product/delCategoryById")
	@ResponseBody
	public AjaxResponseModel delCategoryById(Integer categoryId) {
		return buffetFoodControllerService.delCategoryById(categoryId);
	}

	//添加分类接口
	@RequestMapping("/product/addCategory")
	@ResponseBody
	public AjaxResponseModel addCategory(String categoryName,int mark) {
		try {
			LoginUser user = OtherUtils.getLoginUserByRequest();
			//            Shop shop = shopService.getShopDataByAccountShopId(user.getUserId());
			buffetFoodCategoryService.addCategoryById(categoryName, user.getUserId(),mark);
			return new AjaxResponseModel(Globals.COMMON_SUCCESSFUL_OPERATION, "添加成功");
		} catch (Exception e) {
			return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED, "添加失败");
		}
	}


	//保存分类接口
	@RequestMapping("/product/saveCategory")
	@ResponseBody
	public AjaxResponseModel saveCategory(Integer categoryId, String categoryName) {
		try {
			BuffetFoodCategory category = new BuffetFoodCategory();
			category.setId(categoryId);
			category.setCategoryName(categoryName);
			buffetFoodCategoryService.editCategoryNameById(category);
			return new AjaxResponseModel(Globals.COMMON_SUCCESSFUL_OPERATION, "保存成功");
		} catch (Exception e) {
			return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED, "保存失败");
		}
	}

	//==========================================================订单
	//=====　接口
	//自助点餐商品确认结算按钮
	@RequestMapping(value = "/affirmSettleMethod", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResponseModel affirmSettleMethod(@RequestParam("productList") String productList,
			@RequestParam("orderNumber") String orderNumber) {
		try {
			List<BuffetFoodOrderProduct> products = new ArrayList<>();
			if (StringUtils.isNotEmpty(productList)) {
				Gson gson = new Gson();
				products = gson.fromJson(productList, new TypeToken<List<BuffetFoodOrderProduct>>() {
				}.getType());
			}
			String accountShopToken = OtherUtils.getLoginUserByRequest().getToken();
			BuffetFoodOrder order = buffetFoodOrderService.getBuffetFoodOrder(accountShopToken, orderNumber);
			System.out.println(products);
			buffetFoodOrderService.changeOrderStateByAccountShopToken(orderNumber, 2);  //更改订单支付状态
			buffetFoodOrderService.changeOrderState(orderNumber,2); //更改订单状态
			double orderAmount = order.getOrderAmount();
			//追加订单中的商品
			for (BuffetFoodOrderProduct each : products) {
				each.setState(1); //状态1追加商品
				each.setOrderId(order.getId());
				//插入订单商品记录，改变订单总价
				buffetFoodOrderProductService.insertProductDetailsUnderOrder(each);
				orderAmount += each.getPrice();
			}
			int b=0;
			int a=seatInfo.updataSeatInfoByOrder(order,b);
			if(a<=0) {
				return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED, "订单不存在");
			}
			buffetFoodOrderService.updateOrderTotalAmount(orderNumber, orderAmount);
			buffetFoodOrderService.changeOrderStateByAccountShopToken(orderNumber,2);
			Shop shop = shopService.getShopDataById(order.getShopId());
			System.out.println("shop is:"+shop.toString());
			AccountShop user = accountShopService.getAccountShopDataById(shop.getAccountShopId());
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("orderNumber", order.getOrderNumber());
			jsonObject.put("orderState", order.getOrderState());
			jsonObject.put("orderId", order.getId());
			//            推送消息
			if(order.getToken() == null){
				//            	TODO
				Jpush.jPushMethodToMerchant(accountShopToken,"有一个自助点餐结算","ALERT", "商家版");
				Jpush.jPushMethodToMerchant(accountShopToken,"有一个自助点餐结算","","");
			}else {
				moreMethodsUtils.jPushToMerAndAccount(order.getToken(),"订单已完成", jsonObject.toJSONString(),
						user.getToken(),"有一个自助点餐订单已完成", jsonObject.toJSONString(), 2);
			}
			return new AjaxResponseModel(Globals.COMMON_SUCCESSFUL_OPERATION);
		} catch (Exception e) {
			return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED);
		}
	}

	//新订单下单接口
	@RequestMapping("/dealWithNewOrder")
	@ResponseBody
	public AjaxResponseModel dealWithNewOrder(AjaxRequestParams params){
		return buffetFoodControllerService.dealWithNewOrder(params);
	}

	//回复催单接口
	@RequestMapping("/replyReminder")
	@ResponseBody
	public AjaxResponseModel replyReminder(AjaxRequestParams params){
		return buffetFoodControllerService.replyReminder(params);
	}

	//确认调单
	@RequestMapping("/enterAdjust")
	@ResponseBody
	public AjaxResponseModel enterAdjust(AjaxRequestParams params){
		return buffetFoodControllerService.enterAdjust(params);
	}

	//打印各种单据接口
	@RequestMapping("/print")
	@ResponseBody
	public AjaxResponseModel printOrder(Model model,AjaxRequestParams params, RequestParams Rparams,Integer state){
		if(params!=null&&params.getOrderNumber()!=null) {
			Map<String, Object> map = new HashMap<>();
			OtherUtils.ReturnValByModel(model, map);
			Rparams.setModel(model);
			return  buffetFoodControllerService.printOrder(params,Rparams,state);
		}
		return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED);
	}


	//核退
	@RequestMapping("/replyFalse")
	@ResponseBody
	public AjaxResponseModel replyFalse(AjaxRequestParams params){
		try {
			if(params != null){
				int row = buffetFoodOrderDao.replyFalseDAO(params.getOrderNumber(),null);
				if(row <= 0){
					return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED);
				}else {
					return new AjaxResponseModel(Globals.COMMON_SUCCESSFUL_OPERATION);
				}
			}else {
				return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED);
			}
		}catch (Exception e){
			return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED);
		}
	}
	//添加座位
	@RequestMapping("/addSeatInfo")
	@ResponseBody
	public AjaxResponseModel addaddSeatInfo(String shopId,String seatName,String numberSeat){
		if(shopId!=null||seatName!=null||numberSeat!=null) {
			SeatInformation seat=new SeatInformation();
			seat.setNumberSeat(Integer.parseInt(numberSeat));
			seat.setSeatName(seatName);
			seat.setShopId(Integer.parseInt(shopId));
			try {
				return seatInfo.addSeatInfoByShopId(seat);
			} catch (Exception e) {
				return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED,"添加失败");
			}
		}
		return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED,"添加成功");
	}
	//删除座位
	@RequestMapping("/delSeatInfo")
	@ResponseBody
	public AjaxResponseModel delSeatInfoByShopIdAndId(String shopId,String seatName,String numberSeat) {
		SeatInformation seat=new SeatInformation();
		seat.setNumberSeat(Integer.parseInt(numberSeat));
		seat.setSeatName(seatName);
		seat.setShopId(Integer.parseInt(shopId));
		return seatInfo.delSeatInfoByShopIdAndId(seat);
	}
	//修改座位信息
	@RequestMapping("/updata")
	@ResponseBody
	public AjaxResponseModel updataSeatInfoBySeatName(String shopId,String seatName,String numberSeat,String oldName) {
		SeatInformation seat=new SeatInformation();
		seat.setNumberSeat(Integer.parseInt(numberSeat));
		seat.setSeatName(seatName);
		seat.setShopId(Integer.parseInt(shopId));
		return seatInfo.updataSeatInfoBySeatName(seat, oldName);
	}
	//点击按钮修改座位状态信息
	@RequestMapping("/changeStatus")
	@ResponseBody
	public AjaxResponseModel updataSeatInfoByOrder(String shopId,String seatName,String status) {
		BuffetFoodOrder bfo=new BuffetFoodOrder();
		bfo.setShopId(Integer.parseInt(shopId));
		bfo.setSeatName(seatName);
		try {
			int a=seatInfo.updataSeatInfoByOrder(bfo, Integer.parseInt(status));
			if(a<=0) {
				return new AjaxResponseModel<>(Globals.COMMON_OPERATION_FAILED,"修改失败");
			}
		} catch (Exception e) {
			return new AjaxResponseModel<>(Globals.COMMON_OPERATION_FAILED,"修改失败");
		}
		return new AjaxResponseModel<>(Globals.COMMON_SUCCESSFUL_OPERATION,"修改成功");
	}
}
