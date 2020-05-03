/**   
* @Title: ParkController.java 
* @Package com.wuguan.huate.web.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月20日 上午11:07:45 
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
import com.wuguan.huate.service.ParkService;
import com.wuguan.huate.web.result.ApiResult;

/**
 * @ClassName: ParkController
 * @Description: 停车位接口
 * @author LiuYanHong
 * @date 2020年3月20日 上午11:07:45
 * 
 */
@RestController
public class ParkController {
	@Autowired
	ParkService parkService;

	/**
	 * 
	 * @Title: queryParkByCarNo
	 * @Description: 车牌查询车位
	 * @param carNo
	 * @return
	 * @throws CustomException
	 */
	@ParamsValidate(validateParams = { @Param(key = "carNo",type = ParamType.CUSTOM) })
	@RequestMapping(value = "/park/queryParkByCarNo", method = RequestMethod.GET)
	public ApiResult queryParkByCarNo(String carNo) throws CustomException {
		return ApiResult.success(parkService.queryParkByCarNo(carNo));
	}

	/**
	 * 
	 * @Title: queryParkByCarNo
	 * @Description: 车牌查询车位
	 * @param carNo
	 * @return
	 * @throws CustomException
	 */
	@ParamsValidate(validateParams = { @Param(key = "parkNo",type = ParamType.CUSTOM) })
	@RequestMapping(value = "/park/queryParkByParkNo", method = RequestMethod.GET)
	public ApiResult queryParkByParkNo(String parkNo) throws CustomException {
		return ApiResult.success(parkService.getParksByParkNo(parkNo));
	}
	
	@ParamsValidate(validateParams = { @Param(key = "id",type = ParamType.CUSTOM) })
	@RequestMapping(value = "/park/detailData", method = RequestMethod.GET)
	public ApiResult detailData(Integer id) throws CustomException {
		return ApiResult.success(parkService.detailData(id));
	}
}
