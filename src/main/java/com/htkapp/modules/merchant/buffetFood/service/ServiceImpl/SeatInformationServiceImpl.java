package com.htkapp.modules.merchant.buffetFood.service.ServiceImpl;


import com.htkapp.core.jsAjax.AjaxResponseModel;
import com.htkapp.core.utils.Globals;
import com.htkapp.modules.merchant.buffetFood.dao.SeatInformationMapper;
import com.htkapp.modules.merchant.buffetFood.entity.BuffetFoodOrder;
import com.htkapp.modules.merchant.buffetFood.entity.SeatInformation;
import com.htkapp.modules.merchant.buffetFood.service.SeatInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SeatInformationServiceImpl implements SeatInformationService{

    @Resource
    private SeatInformationMapper seatInformationDao;

    /* =======================接口开始======================== */
    //根据商户id查找商铺下的座位信息
    @Override
    public List<SeatInformation> getSeatInformationListByShopId(int shopId) throws Exception {
        try {
            List<SeatInformation> resultList = seatInformationDao.getSeatInformationListByShopIdDAO(shopId);
            if(resultList != null && resultList.size() > 0){
                return resultList;
            }else {
                return null;
            }
        }catch (Exception e){
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

	@Override
	public List<SeatInformation> getSeatInformationAllByShopId(int shopId) throws Exception {
		try {
            List<SeatInformation> resultList = seatInformationDao.getSeatInformationListByShopId(shopId);
            if(resultList != null && resultList.size() > 0){
                return resultList;
            }else {
                return null;
            }
        }catch (Exception e){
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
	}

	@Override
	public AjaxResponseModel<SeatInformation> addSeatInfoByShopId(SeatInformation seat) throws Exception {
		try {
			seatInformationDao.addSeatInfoByShopId(seat);
//			if(a<=0) {
//				return new AjaxResponseModel<>(Globals.COMMON_OPERATION_FAILED,"添加失败");
//			}
			return new AjaxResponseModel<>(Globals.COMMON_SUCCESSFUL_OPERATION,"添加成功");
		} catch (Exception e) {
			return new AjaxResponseModel<>(Globals.COMMON_OPERATION_FAILED,"添加失败");
		}
	}

	@Override
	public int updataSeatInfoByOrder(BuffetFoodOrder bfo) throws Exception {
		int a=seatInformationDao.updataSeatInfoByShopId(bfo);
		if(a<=0) {
			return a;
		}
		return a;
	}




    /* =======================接口结束========================== */
}
