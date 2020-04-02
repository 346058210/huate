/**   
* @Title: ShopController.java 
* @Package com.wuguan.huate.web.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月31日 下午7:50:05 
* @version V1.0   
*/
package com.wuguan.huate.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
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
import com.wuguan.huate.bean.entity.Shop;
import com.wuguan.huate.bean.enums.HouseEnums;
import com.wuguan.huate.bean.params.HousePageParam;
import com.wuguan.huate.bean.params.PageParams;
import com.wuguan.huate.bean.params.ShopParam;
import com.wuguan.huate.bean.vo.ShopVo;
import com.wuguan.huate.comm.CustomException;
import com.wuguan.huate.service.FeeNormService;
import com.wuguan.huate.service.HouseService;
import com.wuguan.huate.service.ShopService;
import com.wuguan.huate.web.result.ApiResult;

/**
 * @ClassName: ShopController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author LiuYanHong
 * @date 2020年3月31日 下午7:50:05
 * 
 */
@RestController
public class ShopController {
	@Autowired
	ShopService shopService;
	@Autowired
	HouseService houseService;
	@Autowired
	FeeNormService feeNormService;
	
	@Function(key = "shopImportExcel")
	@RequestMapping(value = "/shop/importExcel", method = RequestMethod.POST)
	public ApiResult importExcel(MultipartFile file) throws CustomException, Exception {
		List<FeeNorm> norms = feeNormService.getFeeNorms();
		List<String> shopName = new ArrayList<String>();
		List<House> houses = new ArrayList<House>();
		List<Shop> shops = new ArrayList<Shop>();
		List<ErrorEntity> errorData = new ArrayList<ErrorEntity>();
		ExcelBoot.ImportBuilder(file.getInputStream(), ShopParam.class).importExcel(new ImportFunction<ShopParam>() {
			@Override
			public void onProcess(int sheetIndex, int rowIndex, ShopParam entity) {
				boolean contains = shopName.contains(entity.getShopName());
				if (contains) {
					for (House house : houses) {
						if (house.getOwner().equals(entity.getShopName())) {
							house.setArea(house.getArea() + entity.getShopArea());
							house.setHouseNo(house.getHouseNo() + "|" + entity.getShopNo());
						}
					}
				} else {
					House house = new House();
					house.setAddress(entity.getAddress());
					house.setArea(entity.getShopArea());
					house.setDueTime(entity.getDueTime());
					house.setHouseNo(entity.getShopNo());
					house.setOwner(entity.getShopName());
					house.setDueTime(entity.getDueTime().equals("")?null:entity.getDueTime());
					house.setRemarkName(entity.getRemarkName());
					house.setType(HouseEnums.TypeEnum.BUSINESS.getValue());
					house.setSale(HouseEnums.SaleEnum.getByName(entity.getSale()).getValue());
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
				}
				Shop shop = new Shop();
				shop.setShopArea(entity.getShopArea());
				shop.setShopNo(entity.getShopNo());
				shops.add(shop);
				if (houses.size() % 100 == 0) {
					houseService.addBatch(houses);
					for (House house : houses) {
						for (Shop sp : shops) {
							if (house.getHouseNo().contains(sp.getShopNo())) {
								sp.setHouseId(house.getId());
							}
						}
					}
					shopService.addBatch(shops);
					houses.clear();
					shops.clear();
					shopName.clear();
				}
			}

			@Override
			public void onError(ErrorEntity errorEntity) {
				errorData.add(errorEntity);

			}
		});
		houseService.addBatch(houses);
		for (House house : houses) {
			for (Shop sp : shops) {
				if (house.getHouseNo().contains(sp.getShopNo())) {
					sp.setHouseId(house.getId());
				}
			}
		}
		shopService.addBatch(shops);
		return ApiResult.success(errorData);
	}
	
	@Function(key = "shopExportExcel")
	@RequestMapping(value = "/shop/exportExcel", method = RequestMethod.GET)
	public void exportExcel(HttpServletResponse response, PageParams params) {
		List<FeeNorm> norms = feeNormService.getFeeNorms();
		ExcelBoot.ExportBuilder(response, "商铺信息表", ShopParam.class).exportResponse(params,
				new ExportFunction<PageParams, ShopVo>() {

					@Override
					public List<ShopVo> pageQuery(PageParams param, int pageNum, int pageSize) {
						return shopService.getListShop();
					}

					@Override
					public Object convert(ShopVo shop) {
						ShopParam param = new ShopParam();
						BeanUtils.copyProperties(shop, param);
						for (FeeNorm norm : norms) {
							if (shop.getWaterTypeId() == norm.getId()) {
								param.setWaterType(norm.getTypeName());
							} else if (shop.getElecticTypeId() == norm.getId()) {
								param.setElecticType(norm.getTypeName());
							} else if (shop.getRubbishTypeId() == norm.getId()) {
								param.setRubbishType(norm.getTypeName());
							} else if (shop.getPropertyTypeId() == norm.getId()) {
								param.setPropertyType(norm.getTypeName());
							}
						}
						return param;
					}
				});
	}
	
	/**
	 * 
	 * @Title: pageData
	 * @Description: 商铺分页查询
	 * @return
	 * @throws CustomException
	 */
	@Function(key = "shopPageData")
	@ParamsValidate(validateParams = { @Param(key = "page", type = ParamType.NUMBER),
			@Param(key = "rows", type = ParamType.NUMBER) })
	@RequestMapping(value = "/shop/pageData", method = RequestMethod.GET)
	public ApiResult pageData(HousePageParam param) throws CustomException {
		return ApiResult.success(houseService.shopPageData(param));
	}

}
