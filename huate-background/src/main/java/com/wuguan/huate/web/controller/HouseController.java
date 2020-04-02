/**   
* @Title: HouseController.java 
* @Package com.wuguan.huate.web.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月17日 下午7:13:47 
* @version V1.0   
*/
package com.wuguan.huate.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.excel.poi.ExcelBoot;
import com.excel.poi.entity.ErrorEntity;
import com.excel.poi.function.ExportFunction;
import com.excel.poi.function.ImportFunction;
import com.wuguan.huate.annotation.Function;
import com.wuguan.huate.annotation.Param;
import com.wuguan.huate.annotation.ParamType;
import com.wuguan.huate.annotation.ParamsValidate;
import com.wuguan.huate.bean.entity.FeeNorm;
import com.wuguan.huate.bean.entity.House;
import com.wuguan.huate.bean.entity.HouseConsume;
import com.wuguan.huate.bean.entity.Shop;
import com.wuguan.huate.bean.enums.HouseEnums;
import com.wuguan.huate.bean.params.HousePageParam;
import com.wuguan.huate.bean.params.HouseParam;
import com.wuguan.huate.bean.vo.BindHouse;
import com.wuguan.huate.bean.vo.HouseVo;
import com.wuguan.huate.bean.vo.UserVo;
import com.wuguan.huate.comm.CustomException;
import com.wuguan.huate.comm.SpringUtil;
import com.wuguan.huate.comm.ThreadLocalManager;
import com.wuguan.huate.service.FeeNormService;
import com.wuguan.huate.service.HouseConsumeService;
import com.wuguan.huate.service.HouseService;
import com.wuguan.huate.service.ShopService;
import com.wuguan.huate.utils.DateUtils;
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
	@Autowired
	FeeNormService feeNormService;
	@Autowired
	HouseConsumeService houseConsumeService;
	@Autowired
	ShopService shopService;

	/**
	 * 
	 * @Title: importExcel
	 * @Description: 導入房屋信息
	 * @param file
	 * @return
	 * @throws IOException
	 */
	@Function(key = "houseImportExcel")
	@RequestMapping(value = "/house/importExcel", method = RequestMethod.POST)
	public ApiResult importExcel(MultipartFile file) throws IOException {
		List<FeeNorm> norms = feeNormService.getFeeNorms();
		List<House> houses = new ArrayList<House>();
		List<ErrorEntity> errorData = new ArrayList<ErrorEntity>();
		ExcelBoot.ImportBuilder(file.getInputStream(), HouseParam.class).importExcel(new ImportFunction<HouseParam>() {
			@Override
			public void onProcess(int sheetIndex, int rowIndex, HouseParam entity) {
				House house = new House();
				BeanUtils.copyProperties(entity, house);
				house.setState(HouseEnums.StateEnums.getByName(entity.getStateName()).getValue());
				house.setType(HouseEnums.TypeEnum.getByName(entity.getTypeName()).getValue());
				house.setSale(HouseEnums.SaleEnum.getByName(entity.getSale()).getValue());
				house.setDueTime(entity.getDueTime().equals("") ? null : entity.getDueTime());
				for (FeeNorm norm : norms) {
					if (entity.getWaterType().equals(norm.getTypeName())) {// 水费收费标准
						house.setWaterTypeId(norm.getId());
					} else if (entity.getElecticType().equals(norm.getTypeName())) {// 电费收费标准
						house.setElecticTypeId(norm.getId());
					} else if (entity.getRubbishType().equals(norm.getTypeName())) {// 生活垃圾费标准
						house.setRubbishTypeId(norm.getId());
					} else if (entity.getPropertyType().equals(norm.getTypeName())) {// 物业费标准
						house.setPropertyTypeId(norm.getId());
					}
				}
				houses.add(house);
				if (houses.size() % 100 == 0) {
					houseService.addBatch(houses);
					houses.clear();
				}
			}

			@Override
			public void onError(ErrorEntity errorEntity) {
				errorData.add(errorEntity);

			}
		});
		houseService.addBatch(houses);
		return ApiResult.success(errorData);
	}

	@Function(key = "houseExportExcel")
	@RequestMapping(value = "/house/exportExcel", method = RequestMethod.GET)
	public void exportExcel(HttpServletResponse response, HousePageParam params) {
		List<FeeNorm> norms = feeNormService.getFeeNorms();
		ExcelBoot.ExportBuilder(response, "房屋信息表", HouseParam.class).exportResponse(params,
				new ExportFunction<HousePageParam, House>() {
					@Override
					public List<House> pageQuery(HousePageParam param, int pageNum, int pageSize) {
						return houseService.getListHouse(param);
					}

					@Override
					public HouseParam convert(House house) {
						HouseParam param = new HouseParam();
						BeanUtils.copyProperties(house, param);
						param.setStateName(HouseEnums.StateEnums.getByValue(house.getState()).getName());
						param.setTypeName(HouseEnums.TypeEnum.getByValue(house.getType()).getName());
						for (FeeNorm norm : norms) {
							if (house.getWaterTypeId() == norm.getId()) {
								param.setWaterType(norm.getTypeName());
							} else if (house.getElecticTypeId() == norm.getId()) {
								param.setElecticType(norm.getTypeName());
							} else if (house.getRubbishTypeId() == norm.getId()) {
								param.setRubbishType(norm.getTypeName());
							} else if (house.getPropertyTypeId() == norm.getId()) {
								param.setPropertyType(norm.getTypeName());
							}
						}
						return param;
					}
				});
	}

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

	/**
	 * 
	 * @Title: getRoomsByUnit
	 * @Description: 房號列表
	 * @param unit
	 * @return
	 * @throws CustomException
	 */
	@ParamsValidate(validateParams = { @Param(key = "unit", type = ParamType.CUSTOM) })
	@RequestMapping(value = "/house/queryRooms", method = RequestMethod.GET)
	public ApiResult queryRoomsByUnit(Integer unit) throws CustomException {
		return ApiResult.success(houseService.getRoomsByUnit(unit));
	}

	/**
	 * 
	 * @Title: getHouseInfo
	 * @Description: 查询绑定的房屋信息
	 * @return
	 * @throws CustomException
	 */
	@RequestMapping(value = "/house/queryBindHouses", method = RequestMethod.GET)
	public ApiResult queryBindHouses() throws CustomException {
		ArrayList<BindHouse> array = new ArrayList<BindHouse>();
		ThreadLocalManager bean = SpringUtil.getBean(ThreadLocalManager.class);
		UserVo vo = JSONObject.parseObject(bean.getUser().toString(), UserVo.class);
		List<House> houses = houseService.getBindHouses(vo.getOpenid());
		List<FeeNorm> norms = feeNormService.getFeeNorms();
		if (houses.size() != 0) {
			for (House house : houses) {
				BindHouse wy = new BindHouse();
				BeanUtils.copyProperties(house, wy);
				for (FeeNorm norm : norms) {
					if (house.getWaterTypeId() == norm.getId()) {
						wy.setWaterName(norm.getTypeName());
						wy.setWaterPrice(norm.getPrice());
						wy.setWaterUnit(norm.getUnit());
					} else if (house.getElecticTypeId() == norm.getId()) {
						wy.setElecticName(norm.getTypeName());
						wy.setElecticPrice(norm.getPrice());
						wy.setElecticUnit(norm.getUnit());
					} else if (house.getPropertyTypeId() == norm.getId()) {
						wy.setPropertyName(norm.getTypeName());
						wy.setPropertyPrice(norm.getPrice());
						wy.setPropertyUnit(norm.getUnit());
					} else if (house.getRubbishTypeId() == norm.getId()) {
						wy.setRubbishName(norm.getTypeName());
						wy.setRubbishPrice(norm.getPrice());
						wy.setRubbishUnit(norm.getUnit());
					}
				}
				array.add(wy);
			}
		}
		return ApiResult.success(array);
	}

	/**
	 * 
	 * @Title: pageData
	 * @Description: 房屋分页查询
	 * @return
	 * @throws CustomException
	 */
	@Function(key = "housePageData")
	@ParamsValidate(validateParams = { @Param(key = "page", type = ParamType.NUMBER),
			@Param(key = "rows", type = ParamType.NUMBER) })
	@RequestMapping(value = "/house/pageData", method = RequestMethod.GET)
	public ApiResult pageData(HousePageParam param) throws CustomException {
		return ApiResult.success(houseService.pageData(param));
	}

	/**
	 * 
	 * @Title: detailData
	 * @Description: 查看详情
	 * @param id
	 * @return
	 * @throws CustomException
	 */
	@Function(key = "houseDetailData")
	@ParamsValidate(validateParams = { @Param(key = "id", limit = "0,11", type = ParamType.NUMBER) })
	@RequestMapping(value = "/house/detailData", method = RequestMethod.GET)
	public ApiResult detailData(Integer id) throws CustomException {
		return ApiResult.success(houseService.detailData(id));
	}

	/**
	 * 
	 * @Title: detailData
	 * @Description:详情---挣对移动端
	 * @param id
	 * @return
	 * @throws CustomException
	 */
	@ParamsValidate(validateParams = { @Param(key = "id", limit = "0,11", type = ParamType.NUMBER) })
	@RequestMapping(value = "/house/detailDataForMobile", method = RequestMethod.GET)
	public ApiResult detailDataForMobile(Integer id) throws CustomException {
		HouseVo vo = houseService.detailData(id);
		ThreadLocalManager bean = SpringUtil.getBean(ThreadLocalManager.class);
		UserVo user = JSONObject.parseObject(bean.getUser().toString(), UserVo.class);
		Boolean bool = houseService.isBind(vo.getId(), user.getId());
		vo.setIsBind(bool ? 1 : 0);
		boolean boo = (DateUtils.getDaysBetween(vo.getDueTime(), new Date()) > 0) ? true : false;
		List<HouseConsume> consumes = houseConsumeService.getListDataUnPay(vo.getHouseNo());
		vo.setIsArrears((boo || consumes.size() > 0) ? 1 : 0);
		List<FeeNorm> norms = feeNormService.getFeeNorms();
		List<FeeNorm> list = new ArrayList<FeeNorm>();
		for (FeeNorm norm : norms) {
			if (norm.getId() == vo.getElecticTypeId() || norm.getId() == vo.getPropertyTypeId()
					|| norm.getId() == vo.getRubbishTypeId() || norm.getId() == vo.getWaterTypeId()) {
				list.add(norm);
			}
		}
		vo.setNorms(list);
		if (vo.getType() == HouseEnums.TypeEnum.BUSINESS.getValue()) {
			List<Shop> shops = shopService.getShopsByHouseId(vo.getId());
			vo.setShops(shops);
		}
		return ApiResult.success(vo);
	}

	/**
	 * 
	 * @Title: addData
	 * @Description: 新增
	 * @param id
	 * @return
	 * @throws CustomException
	 */
	@Function(key = "houseAddData")
	@ParamsValidate(validateParams = { @Param(key = "houseNo", type = ParamType.CUSTOM),
			@Param(key = "area", type = ParamType.CUSTOM), @Param(key = "type", type = ParamType.CUSTOM),
			@Param(key = "waterTypeId", type = ParamType.CUSTOM), @Param(key = "dueTime", type = ParamType.CUSTOM),
			@Param(key = "electicTypeId", type = ParamType.CUSTOM),
			@Param(key = "rubbishTypeId", type = ParamType.CUSTOM),
			@Param(key = "propertyTypeId", type = ParamType.CUSTOM) })
	@RequestMapping(value = "/house/addData", method = RequestMethod.POST)
	public ApiResult addData(HouseVo param) throws CustomException {
		houseService.addData(param);
		return ApiResult.success();
	}

	/**
	 * 
	 * @Title: updateData
	 * @Description: 修改
	 * @param param
	 * @return
	 * @throws CustomException
	 */
	@Function(key = "houseUpdateData")
	@ParamsValidate(validateParams = { @Param(key = "id",limit = "0,11", type = ParamType.NUMBER)})
	@RequestMapping(value = "/house/updateData", method = RequestMethod.POST)
	public ApiResult updateData(HouseVo param) throws CustomException {
		houseService.updateData(param);
		return ApiResult.success();
	}

	/**
	 * 
	 * @Title: delData
	 * @Description: 删除
	 * @param id
	 * @return
	 * @throws CustomException
	 */
	@Function(key = "houseDelData")
	@ParamsValidate(validateParams = { @Param(key = "id",limit = "0,11", type = ParamType.NUMBER)})
	@RequestMapping(value = "/house/delData", method = RequestMethod.POST)
	public ApiResult delData(Integer id) throws CustomException {
		houseService.delData(id);
		return ApiResult.success();
	}

	/**
	 * 
	 * @Title: bindHouse
	 * @Description: 绑定房屋
	 * @param houseNo
	 * @return
	 * @throws CustomException
	 */
	@ParamsValidate(validateParams = { @Param(key = "houseNo",type = ParamType.CUSTOM)})
	@RequestMapping(value = "/house/bindHouse", method = RequestMethod.POST)
	public ApiResult bindHouse(String houseNo) throws CustomException {
		houseService.bindHouse(houseNo);
		return ApiResult.success();
	}

	/**
	 * 
	 * @Title: unboundHouse
	 * @Description: 解除绑定
	 * @param houseNo
	 * @return
	 * @throws CustomException
	 */
	@ParamsValidate(validateParams = { @Param(key = "houseNo",type = ParamType.CUSTOM)})
	@RequestMapping(value = "/house/unboundHouse", method = RequestMethod.POST)
	public ApiResult unboundHouse(String houseNo) throws CustomException {
		houseService.unboundHouse(houseNo);
		return ApiResult.success();
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
