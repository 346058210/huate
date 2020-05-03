/**   
* @Title: ResidentController.java 
* @Package com.wuguan.huate.web.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月20日 下午4:30:31 
* @version V1.0   
*/
package com.wuguan.huate.web.controller;

import java.io.IOException;
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
import com.wuguan.huate.bean.entity.Resident;
import com.wuguan.huate.bean.enums.ResidentEnums;
import com.wuguan.huate.bean.params.PageParams;
import com.wuguan.huate.bean.params.ResidentParam;
import com.wuguan.huate.comm.CustomException;
import com.wuguan.huate.service.ResidentService;
import com.wuguan.huate.web.result.ApiResult;

/**
 * @ClassName: ResidentController
 * @Description: 居民信息
 * @author LiuYanHong
 * @date 2020年3月20日 下午4:30:31
 * 
 */
@RestController
public class ResidentController {
	@Autowired
	ResidentService residentService;

	@Function(key = "residentAddData")
	@ParamsValidate(validateParams = { @Param(key = "name", type = ParamType.NAME),
			@Param(key = "houseNo", type = ParamType.CUSTOM) })
	@RequestMapping(value = "/resident/addData", method = RequestMethod.POST)
	public ApiResult addData(Resident resident) throws CustomException {
		residentService.addData(resident);
		return ApiResult.success(resident.getId());
	}

	@Function(key = "residentUpdateData")
	@ParamsValidate(validateParams = { @Param(key = "id", limit = "0,11", type = ParamType.NUMBER) })
	@RequestMapping(value = "/resident/updateData", method = RequestMethod.POST)
	public ApiResult updateData(Resident resident) throws CustomException {
		residentService.updateData(resident);
		return ApiResult.success();
	}

	@Function(key = "residentDelData")
	@ParamsValidate(validateParams = { @Param(key = "id", limit = "0,11", type = ParamType.NUMBER) })
	@RequestMapping(value = "/resident/delData", method = RequestMethod.POST)
	public ApiResult delData(Integer id) throws CustomException {
		residentService.delData(id);
		return ApiResult.success();
	}

	@Function(key = "residentImportExcel")
	@RequestMapping(value = "/resident/importExcel", method = RequestMethod.POST)
	public ApiResult importExcel(MultipartFile file) throws CustomException, IOException {
		List<Resident> residents = new ArrayList<Resident>();
		List<Object> errorData = new ArrayList<Object>();
		ExcelBoot.ImportBuilder(file.getInputStream(), ResidentParam.class)
				.importExcel(new ImportFunction<ResidentParam>() {

					@Override
					public void onProcess(int sheetIndex, int rowIndex, ResidentParam entity) {
						try {
							Resident resident = new Resident();
							BeanUtils.copyProperties(entity, resident);
							resident.setRelation(
									ResidentEnums.RelationEnum.getByName(entity.getRelationName()).getValue());
							residents.add(resident);
							if (residents.size() % 100 == 0) {
								residentService.addBatch(residents);
								residents.clear();
							}
						} catch (Exception e) {
							errorData.add(entity);
							e.printStackTrace();
						}
					}

					@Override
					public void onError(ErrorEntity errorEntity) {
						errorData.add(errorEntity);
					}
				});
		if (residents.size() != 0)
			residentService.addBatch(residents);
		return ApiResult.success(errorData);
	}

	@Function(key = "residentExportExcel")
	@RequestMapping(value = "/resident/exportExcel", method = RequestMethod.GET)
	public void exportExcel(HttpServletResponse response, PageParams param) throws CustomException {
		ExcelBoot.ExportBuilder(response, "居民信息表", ResidentParam.class).exportResponse(param,
				new ExportFunction<PageParams, Resident>() {

					@Override
					public List<Resident> pageQuery(PageParams param, int pageNum, int pageSize) {
						int startRow = (param.getPage() - 1) * param.getRows() + 1;
						return residentService.getResidents(startRow, pageSize);
					}

					@Override
					public ResidentParam convert(Resident resident) {
						ResidentParam param = new ResidentParam();
						BeanUtils.copyProperties(resident, param);
						param.setRelationName(ResidentEnums.RelationEnum.getByValue(resident.getRelation()).getName());
						return param;
					}
				});
	}
}
