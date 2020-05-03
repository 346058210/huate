/**   
* @Title: ParkController.java 
* @Package com.wuguan.huate.web.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月20日 上午11:07:45 
* @version V1.0   
*/
package com.wuguan.huate.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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
import com.wuguan.huate.bean.entity.Park;
import com.wuguan.huate.bean.enums.ParkEnums;
import com.wuguan.huate.bean.params.ParkPageParam;
import com.wuguan.huate.bean.params.ParkParam;
import com.wuguan.huate.bean.vo.BindPark;
import com.wuguan.huate.bean.vo.ParkVo;
import com.wuguan.huate.bean.vo.UserVo;
import com.wuguan.huate.comm.CustomException;
import com.wuguan.huate.comm.SpringUtil;
import com.wuguan.huate.comm.ThreadLocalManager;
import com.wuguan.huate.service.FeeNormService;
import com.wuguan.huate.service.ParkService;
import com.wuguan.huate.utils.DateUtils;
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
	@Autowired
	FeeNormService feeNormService;

	@Function(key = "parkImportExcel")
	@RequestMapping(value = "/park/importExcel", method = RequestMethod.POST)
	public ApiResult importExcel(MultipartFile file) throws CustomException, IOException {
		List<FeeNorm> norms = feeNormService.getFeeNorms();
		List<Park> parks = new ArrayList<Park>();
		List<ErrorEntity> errorData = new ArrayList<ErrorEntity>();
		ExcelBoot.ImportBuilder(file.getInputStream(), ParkParam.class).importExcel(new ImportFunction<ParkParam>() {

			@Override
			public void onProcess(int sheetIndex, int rowIndex, ParkParam entity) {
				Park park = new Park();
				BeanUtils.copyProperties(entity, park);
				park.setType(ParkEnums.TypeEnum.getByName(entity.getTypeName()).getValue());
				park.setGenre(ParkEnums.GenreEnum.getByName(entity.getGenre()).getValue());
				for (FeeNorm norm : norms) {
					if (entity.getNorm().equals(norm.getTypeName())) {
						park.setNormId(norm.getId());
					}
				}
				parks.add(park);
				if (parks.size() % 100 == 0) {
					parkService.addBatch(parks);
					parks.clear();
				}
			}

			@Override
			public void onError(ErrorEntity errorEntity) {
				errorData.add(errorEntity);
			}
		});
		parkService.addBatch(parks);
		return ApiResult.success(errorData);
	}

	@Function(key = "parkExportExcel")
	@RequestMapping(value = "/park/exportExcel", method = RequestMethod.GET)
	public void exportExcel(HttpServletResponse response, ParkPageParam param) throws CustomException {
		List<FeeNorm> norms = feeNormService.getFeeNorms();
		ExcelBoot.ExportBuilder(response, "车位信息表", ParkParam.class).exportResponse(param,
				new ExportFunction<ParkPageParam, Park>() {

					@Override
					public List<Park> pageQuery(ParkPageParam param, int pageNum, int pageSize) {
						return parkService.exportExcel(param);
					}

					@Override
					public ParkParam convert(Park park) {
						ParkParam param = new ParkParam();
						BeanUtils.copyProperties(park, param);
						param.setTypeName(ParkEnums.TypeEnum.getByValue(park.getType()).getName());
						param.setGenre(ParkEnums.GenreEnum.getByValue(park.getGenre()).getName());
						for (FeeNorm norm : norms) {
							if (park.getNormId() == norm.getId()) {
								param.setNorm(norm.getTypeName());
							}
						}
						return param;
					}
				});
	}

	@RequestMapping(value = "/park/queryBindPark", method = RequestMethod.GET)
	public ApiResult queryBindPark() throws CustomException {
		ThreadLocalManager bean = SpringUtil.getBean(ThreadLocalManager.class);
		UserVo vo = JSONObject.parseObject(bean.getUser().toString(), UserVo.class);
		List<Park> parks = parkService.getBindPark(vo.getOpenid());
		List<BindPark> arrayList = new ArrayList<BindPark>();
		if (parks.size() != 0) {
			List<FeeNorm> norms = feeNormService.getFeeNorms();
			for (Park park : parks) {
				BindPark bind = new BindPark();
				BeanUtils.copyProperties(park, bind);
				List<String> list = new ArrayList<String>();
				list.add(park.getOwnerOne());
				list.add(park.getOwnerTwo());
				list.removeAll(Collections.singleton(null));
				StringBuilder builder = new StringBuilder();
				for (String s : list) {
					builder.append(s);
					builder.append(",");
				}
				if (park.getGenre()!=null)
					bind.setGenre(ParkEnums.GenreEnum.getByValue(park.getGenre()).getName());
				bind.setOwner(builder.substring(0, builder.length() - 1));
				for (FeeNorm norm : norms) {
					if (norm.getId() == park.getNormId()) {
						bind.setNormName(norm.getTypeName());
						bind.setNormPrice(norm.getPrice());
						bind.setNormUnit(norm.getUnit());
					}
				}
				arrayList.add(bind);
			}
		}
		return ApiResult.success(arrayList);
	}

	/**
	 * 
	 * @Title: addData
	 * @Description: 新增
	 * @param park
	 * @return
	 * @throws CustomException
	 */
	@Function(key = "parkAddData")
	@ParamsValidate(validateParams = { @Param(key = "parkNo", type = ParamType.CUSTOM),
			@Param(key = "dueTime", type = ParamType.CUSTOM), @Param(key = "normId", type = ParamType.NUMBER) })
	@RequestMapping(value = "/park/addData", method = RequestMethod.POST)
	public ApiResult addData(Park park) throws CustomException {
		parkService.addData(park);
		return ApiResult.success();
	}

	/**
	 * 
	 * @Title: updateData
	 * @Description: 修改
	 * @param park
	 * @return
	 * @throws CustomException
	 */
	@Function(key = "parkUpdateData")
	@ParamsValidate(validateParams = { @Param(key = "id", limit = "0,11", type = ParamType.NUMBER) })
	@RequestMapping(value = "/park/updateData", method = RequestMethod.POST)
	public ApiResult updateData(Park park) throws CustomException {
		parkService.updateData(park);
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
	@Function(key = "parkDelData")
	@ParamsValidate(validateParams = { @Param(key = "id", limit = "0,11", type = ParamType.NUMBER) })
	@RequestMapping(value = "/park/delData", method = RequestMethod.POST)
	public ApiResult delData(Integer id) throws CustomException {
		parkService.delData(id);
		return ApiResult.success();
	}

	/**
	 * 
	 * @Title: detailData
	 * @Description: 详情
	 * @param id
	 * @return
	 * @throws CustomException
	 */
	@Function(key = "parkDetailData")
	@ParamsValidate(validateParams = { @Param(key = "id", limit = "0,11", type = ParamType.NUMBER) })
	@RequestMapping(value = "/park/detailData", method = RequestMethod.GET)
	public ApiResult detailData(Integer id) throws CustomException {
		return ApiResult.success(parkService.detailData(id));
	}

	/**
	 * 
	 * @Title: detailDataForMobile
	 * @Description: 详情---移动端接口
	 * @param id
	 * @return
	 * @throws CustomException
	 */
	@ParamsValidate(validateParams = { @Param(key = "id", limit = "0,11", type = ParamType.NUMBER) })
	@RequestMapping(value = "/park/detailDataForMobile", method = RequestMethod.GET)
	public ApiResult detailDataForMobile(Integer id) throws CustomException {
		Park detailData = parkService.detailData(id);
		ParkVo vo = new ParkVo();
		BeanUtils.copyProperties(detailData, vo);
		boolean boo = (DateUtils.getDaysBetween(vo.getDueTime(), new Date()) > 0) ? true : false;
		vo.setIsArrears(boo ? 1 : 0);
		ThreadLocalManager bean = SpringUtil.getBean(ThreadLocalManager.class);
		UserVo user = JSONObject.parseObject(bean.getUser().toString(), UserVo.class);
		Boolean bool = parkService.isBind(user.getId(), vo.getId());
		vo.setIsBind(bool ? 1 : 0);
		List<FeeNorm> norms = feeNormService.getFeeNorms();
		for (FeeNorm norm : norms) {
			if (norm.getId() == detailData.getNormId()) {
				vo.setNorm(norm);
			}
		}
		return ApiResult.success(vo);
	}

	/**
	 * 
	 * @Title: pageData
	 * @Description: 分页
	 * @param id
	 * @return
	 * @throws CustomException
	 */
	@Function(key = "parkPageData")
	@ParamsValidate(validateParams = { @Param(key = "page", limit = "0,11", type = ParamType.NUMBER),
			@Param(key = "rows", limit = "0,11", type = ParamType.NUMBER) })
	@RequestMapping(value = "/park/pageData", method = RequestMethod.GET)
	public ApiResult pageData(ParkPageParam param) throws CustomException {
		return ApiResult.success(parkService.pageData(param));
	}

	/**
	 * 
	 * @Title: bindPark
	 * @Description: 绑定
	 * @param parkNo
	 * @return
	 * @throws CustomException
	 */
	@ParamsValidate(validateParams = { @Param(key = "parkNo",type = ParamType.CUSTOM) })
	@RequestMapping(value = "/park/bindPark", method = RequestMethod.POST)
	public ApiResult bindPark(String parkNo) throws CustomException {
		parkService.bindPark(parkNo);
		return ApiResult.success();
	}

	/**
	 * 
	 * @Title: bindPark
	 * @Description: 解除绑定
	 * @param parkNo
	 * @return
	 * @throws CustomException
	 */
	@ParamsValidate(validateParams = { @Param(key = "parkNo",type = ParamType.CUSTOM) })
	@RequestMapping(value = "/park/unboundPark", method = RequestMethod.POST)
	public ApiResult unboundPark(String parkNo) throws CustomException {
		parkService.unboundPark(parkNo);
		return ApiResult.success();
	}

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
}
