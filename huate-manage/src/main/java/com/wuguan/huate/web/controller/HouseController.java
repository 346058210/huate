/**   
* @Title: HouseController.java 
* @Package com.wuguan.huate.web.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月17日 下午7:13:47 
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
import com.wuguan.huate.service.HouseService;
import com.wuguan.huate.web.result.ApiResult;

/**
 * @ClassName: HouseController
 * @Description: 房屋信息api
 * @author LiuYanHong
 * @date 2020年3月17日 下午7:13:47
 * 
 */
@RestController
public class HouseController {
	@Autowired
	HouseService houseService;

	/**
	 * 
	 * @Title: getBuildings
	 * @Description: 樓棟列表
	 * @return
	 * @throws CustomException
	 */
	@RequestMapping(value = "/house/queryBuildings", method = RequestMethod.GET)
	public ApiResult queryBuildings() throws CustomException {
		return ApiResult.success(houseService.getBuildings());
	}

	/**
	 * 
	 * @Title: getUnitsByBuilding
	 * @Description: 單元列表
	 * @param building
	 * @return
	 * @throws CustomException
	 */
	@ParamsValidate(validateParams = { @Param(key = "building", type = ParamType.CUSTOM) })
	@RequestMapping(value = "/house/queryUnits", method = RequestMethod.GET)
	public ApiResult queryUnitsByBuilding(Integer building) throws CustomException {
		return ApiResult.success(houseService.getUnitsByBuilding(building));
	}
	
	@RequestMapping(value = "/house/detailData", method = RequestMethod.GET)
	public ApiResult detailData(Integer id) {
		return ApiResult.success(houseService.detailData(id));
	}


	@RequestMapping(value = "/house/queryHouseByHouseNo", method = RequestMethod.GET)
	public ApiResult queryHouseByHouseNo(String houseNo) {
		return ApiResult.success(houseService.getHouseByHouseNo(houseNo));
	}

	/**
	 * 
	 * @Title: queryShop
	 * @Description: 商铺查询
	 * @param name-->owner
	 * @return
	 * @throws CustomException
	 */
	@ParamsValidate(validateParams = { @Param(key = "name",type = ParamType.CUSTOM)})
	@RequestMapping(value = "/house/queryShop", method = RequestMethod.POST)
	public ApiResult queryShop(String name) throws CustomException {
		return ApiResult.success(houseService.queryShop(name));
	}
}
