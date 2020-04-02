/**   
* @Title: HouseConsumeController.java 
* @Package com.wuguan.huate.web.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月18日 下午6:09:35 
* @version V1.0   
*/
package com.wuguan.huate.web.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
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
import com.wuguan.huate.bean.enums.HouseConsumeEnums;
import com.wuguan.huate.bean.params.HouseConsumePageParam;
import com.wuguan.huate.bean.params.HouseConsumeParam;
import com.wuguan.huate.comm.CustomException;
import com.wuguan.huate.service.FeeNormService;
import com.wuguan.huate.service.HouseConsumeService;
import com.wuguan.huate.service.HouseService;
import com.wuguan.huate.web.result.ApiResult;

/**
 * @ClassName: HouseConsumeController
 * @Description: 水電用來接口
 * @author LiuYanHong
 * @date 2020年3月18日 下午6:09:35
 * 
 */
@RestController
public class HouseConsumeController {
	@Autowired
	HouseConsumeService houseConsumeService;
	@Autowired
	HouseService houseService;
	@Autowired
	FeeNormService feeNormService;

	/**
	 * 
	 * @Title: importExcel
	 * @Description: 導入報表
	 * @param file
	 * @return
	 * @throws CustomException
	 * @throws IOException
	 */
	@Function(key = "houseConsumeImportExcel")
	@RequestMapping(value = "/houseConsume/importExcel", method = RequestMethod.POST)
	public ApiResult importExcel(MultipartFile file) throws CustomException, IOException {
		List<FeeNorm> norms = feeNormService.getFeeNorms();
		List<House> houses = houseService.getHouses();
		List<ErrorEntity> errorData = new ArrayList<ErrorEntity>();
		List<HouseConsume> consumes = new ArrayList<HouseConsume>();
		ExcelBoot.ImportBuilder(file.getInputStream(), HouseConsumeParam.class)
				.importExcel(new ImportFunction<HouseConsumeParam>() {

					@Override
					public void onProcess(int sheetIndex, int rowIndex, HouseConsumeParam entity) {
						HouseConsume consume = new HouseConsume();
						StringBuilder builder = new StringBuilder();
						BeanUtils.copyProperties(entity, consume);
						consume.setUploadTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
						// 消耗类型（水|电）
						consume.setType(HouseConsumeEnums.FeeTypeEnum.getByName(entity.getType()).getValue());
						if (entity.getPay() == null || "".equals(entity.getPay())) {
							consume.setIsPay(HouseConsumeEnums.PayEnum.NO.getValue());
						} else {
							consume.setIsPay(HouseConsumeEnums.FeeTypeEnum.getByName(entity.getPay()).getValue());
						}
						for (House house : houses) {
							if (entity.getHouseNo().equals(house.getHouseNo())) {
								for (FeeNorm norm : norms) {
									if (entity.getType().equals(HouseConsumeEnums.FeeTypeEnum.WATER.getName())) {// 区分：水|电
										if (house.getWaterTypeId() == norm.getId()) {// 确认相应的收费标准
											builder.append(norm.getTypeName() + ":" + norm.getPrice() + norm.getUnit());
											// 金额计算
											consume.setMoney(entity.getDosage() * norm.getPrice());
										}
									} else {
										if (house.getElecticTypeId() == norm.getId()) {// 确认相应的电费标准
											builder.append(norm.getTypeName() + ":" + norm.getPrice() + norm.getUnit());
											// 金额计算
											consume.setMoney(entity.getDosage() * norm.getPrice());
										}
									}
								}
							}
						}
						// 收费说明
						consume.setExplain(builder.toString());
						consumes.add(consume);
						if (consumes.size() % 100 == 0) {
							houseConsumeService.addBatch(consumes);
							consumes.clear();
						}
					}

					@Override
					public void onError(ErrorEntity errorEntity) {
						errorData.add(errorEntity);
					}
				});
		houseConsumeService.addBatch(consumes);
		return ApiResult.success(errorData);
	}

	@Function(key = "houseConsumeExportExcel")
	@RequestMapping(value = "/houseConsume/exportExcel", method = RequestMethod.GET)
	public void exportExcel(HttpServletResponse response, HouseConsumePageParam param) throws CustomException {
		ExcelBoot.ExportBuilder(response, "水电消耗报表", HouseConsumeParam.class).exportResponse(param,
				new ExportFunction<HouseConsumePageParam, HouseConsume>() {

					@Override
					public List<HouseConsume> pageQuery(HouseConsumePageParam param, int pageNum, int pageSize) {
						return houseConsumeService.exportExcel(param);
					}

					@Override
					public HouseConsumeParam convert(HouseConsume consume) {
						HouseConsumeParam consumeParam = new HouseConsumeParam();
						BeanUtils.copyProperties(consume, consumeParam);
						consumeParam.setPay(HouseConsumeEnums.PayEnum.getByValue(consume.getIsPay()).getName());
						consumeParam.setType(HouseConsumeEnums.FeeTypeEnum.getByValue(consume.getType()).getName());
						return consumeParam;
					}
				});
	}

	/**
	 * 
	 * @Title: detailData
	 * @Description: 查看详情
	 * @param id
	 * @return
	 * @throws CustomException
	 */
	@Function(key = "houseConsumeDetailData")
	@ParamsValidate(validateParams = { @Param(key = "id", limit = "0,11", type = ParamType.NUMBER) })
	@RequestMapping(value = "/houseConsume/detailData", method = RequestMethod.GET)
	public ApiResult detailData(Integer id) throws CustomException {
		return ApiResult.success(houseConsumeService.detailData(id));
	}

	/**
	 * 
	 * @Title: updateData
	 * @Description: 修改
	 * @param consume
	 * @return
	 * @throws CustomException
	 */
	@Function(key = "houseConsumeUpdateData")
	@ParamsValidate(validateParams = { @Param(key = "id", limit = "0,11", type = ParamType.NUMBER) })
	@RequestMapping(value = "/houseConsume/updateData", method = RequestMethod.POST)
	public ApiResult updateData(HouseConsume consume) throws CustomException {
		houseConsumeService.updateData(consume);
		return ApiResult.success();
	}
}
