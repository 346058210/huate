/**   
* @Title: FeeCountController.java 
* @Package com.wuguan.huate.web.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月21日 上午12:28:37 
* @version V1.0   
*/
package com.wuguan.huate.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wuguan.huate.annotation.Param;
import com.wuguan.huate.annotation.ParamType;
import com.wuguan.huate.annotation.ParamsValidate;
import com.wuguan.huate.comm.CustomException;
import com.wuguan.huate.service.FeeCountService;
import com.wuguan.huate.web.result.ApiResult;

/** 
* @ClassName: FeeCountController 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年3月21日 上午12:28:37 
*  
*/
@RestController
public class FeeCountController {
	@Autowired
	FeeCountService feeCountService;
	
	/**
	 * 
	* @Title: propertyFeeCount
	* @Description: 物业费用查询
	* @return rule 缴费标准
	* @return payable 应缴费用
	* @throws CustomException
	 */
	@ParamsValidate(validateParams = { @Param(key = "houseNo",type = ParamType.CUSTOM) })
	@RequestMapping(value = "/feeCount/propertyFeeCount",method = RequestMethod.GET)
	public ApiResult propertyFeeCount(String houseNo)throws CustomException{
		return ApiResult.success(feeCountService.propertyFeeCount(houseNo));
	}
	
	/**
	 * 
	* @Title: waterFeeCount
	* @Description: 水费查询
	* @param houseNo
	* @return
	* @throws CustomException
	 */
	@ParamsValidate(validateParams = { @Param(key = "houseNo",type = ParamType.CUSTOM) })
	@RequestMapping(value = "/feeCount/waterFeeCount",method = RequestMethod.GET)
	public ApiResult waterFeeCount(String houseNo)throws CustomException{
		return ApiResult.success(feeCountService.waterElectricFeeCount(houseNo, 3));
	}
	
	/**
	 * 
	* @Title: electricFeeCount
	* @Description: 电费查询
	* @param houseNo
	* @return
	* @throws CustomException
	 */
	@ParamsValidate(validateParams = { @Param(key = "houseNo",type = ParamType.CUSTOM) })
	@RequestMapping(value = "/feeCount/electricFeeCount",method = RequestMethod.GET)
	public ApiResult electricFeeCount(String houseNo)throws CustomException{
		return ApiResult.success(feeCountService.waterElectricFeeCount(houseNo, 4));
	}
	
	/**
	 * 
	* @Title: parkFeeCount
	* @Description: 租赁|购买车位费查询
	* @param houseNo
	* @return
	* @throws CustomException
	 */
	@ParamsValidate(validateParams = { @Param(key = "parkNo",type = ParamType.CUSTOM) })
	@RequestMapping(value = "/feeCount/parkFeeCount",method = RequestMethod.GET)
	public ApiResult parkFeeCount(String parkNo)throws CustomException{
		return ApiResult.success(feeCountService.parkFeeCount(parkNo));
	}
	
	/**
	 * 
	* @Title: buyParkFeeCount
	* @Description: 购买车位费查询
	* @param houseNo
	* @return
	* @throws CustomException
	 */
	@ParamsValidate(validateParams = { @Param(key = "houseNo",type = ParamType.CUSTOM) })
	@RequestMapping(value = "/feeCount/buyParkFeeCount",method = RequestMethod.GET)
	public ApiResult buyParkFeeCount(String houseNo)throws CustomException{
		return ApiResult.success(feeCountService.buyParkFeeCount(houseNo));
	}
	
	/**
	 * 
	* @Title: waitPayFeeCount
	* @Description: 待缴费信息
	* @return
	* @throws CustomException
	 */
	@RequestMapping(value = "/feeCount/waitPayFeeCount",method = RequestMethod.GET)
	public ApiResult waitPayFeeCount()throws CustomException{
		return ApiResult.success(feeCountService.waitPayFeeCount());
	}
}
