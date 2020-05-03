/**   
* @Title: HomeController.java 
* @Package com.wuguan.huate.web.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月2日 下午9:09:37 
* @version V1.0   
*/
package com.wuguan.huate.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.wuguan.huate.bean.params.ParkCountParam;
import com.wuguan.huate.bean.params.PropertyParam;
import com.wuguan.huate.comm.CustomException;
import com.wuguan.huate.service.HomeService;
import com.wuguan.huate.service.HouseConsumeService;
import com.wuguan.huate.service.OrderItemService;
import com.wuguan.huate.service.ParkService;
import com.wuguan.huate.web.result.ApiResult;

/** 
* @ClassName: HomeController 
* @Description:首页统计
* @author LiuYanHong
* @date 2020年4月2日 下午9:09:37 
*  
*/
@RestController
public class HomeController {
	@Autowired
	HomeService homeService;
	@Autowired
	HouseConsumeService houseConsumeService;
	@Autowired
	OrderItemService orderItemService;
	@Autowired
	ParkService parkService;
	
	/**
	 * 
	* @Title: numberCount
	* @Description: 数量统计
	* @return
	* @throws CustomException
	 */
	//@Function(key = "homeNumberCount")
	@RequestMapping(value = "/home/numberCount",method = RequestMethod.GET)
	public ApiResult numberCount()throws CustomException{
		return ApiResult.success(homeService.numberCount());
	}
	
	/**
	 * 
	* @Title: waterEelecticFeeCount
	* @Description: 12个月水电费统计
	* @return
	* @throws CustomException
	 */
	//@Function(key = "homeWaterEelecticFeeCount")
	@RequestMapping(value = "/home/waterEelecticFeeCount",method = RequestMethod.GET)
	public ApiResult waterEelecticFeeCount()throws CustomException{
		return ApiResult.success(houseConsumeService.waterEelecticFeeCount());
	}

	/**
	 * 
	* @Title: totadyPayFee
	* @Description: 今日缴纳的各种费用统计
	* @return
	* @throws CustomException
	 */
	//@Function(key = "homeTotadyPayFee")
	@RequestMapping(value = "/home/totadyPayFee",method = RequestMethod.GET)
	public ApiResult totadyPayFee()throws CustomException{
		return ApiResult.success(orderItemService.totadyPayFee());
	}
	
	/**
	 * 
	* @Title: parkPayFee
	* @Description: 12月车位费缴纳情况
	* @return
	* @throws CustomException
	 */
	//@Function(key = "homeParkPayFee")
	@RequestMapping(value = "/home/parkPayFee",method = RequestMethod.GET)
	public ApiResult parkPayFee()throws CustomException{
		return ApiResult.success(orderItemService.parkPayFee());
	}
	
	/**
	 * 
	* @Title: feeCountByType
	* @Description: 分类项目缴费统计  查询条件 ：开始时间-结束时间
	* @return 物业、生活垃圾、水费、电费、停车位
	* @throws CustomException
	 */
	@RequestMapping(value = "/home/feeCountByType",method = RequestMethod.GET)
	public ApiResult feeCountByType(String startTime,String endTime)throws CustomException{	
		return ApiResult.success(orderItemService.feeCountByType(startTime,endTime));
	}
	
	/**
	 * 
	* @Title: propertyFeeCount
	* @Description: 物业缴费统计 查询条件：类型、状态、开始时间-结束时间
	* @return 按房屋类型统计
	* @throws CustomException
	 */
	@RequestMapping(value = "/home/propertyFeeCount",method = RequestMethod.GET)
	public ApiResult propertyFeeCount(PropertyParam param)throws CustomException{
		return ApiResult.success(orderItemService.propertyFeeCount(param));
	}
	
	/**
	 * 
	* @Title: parkFeeCount
	* @Description: 车位缴费统计 查询条件：租售类型、车位类型、开始时间-结束时间
	* @return 按车位类型分类统计
	* @throws CustomException
	 */
	@RequestMapping(value = "/home/parkFeeCount",method = RequestMethod.GET)
	public ApiResult parkFeeCount(ParkCountParam param)throws CustomException{
		return ApiResult.success(orderItemService.parkFeeCount(param));
	}
}
