/**   
* @Title: AdvertisingController.java 
* @Package com.wuguan.huate.web.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月9日 下午5:58:43 
* @version V1.0   
*/
package com.wuguan.huate.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.wuguan.huate.annotation.Function;
import com.wuguan.huate.annotation.Param;
import com.wuguan.huate.annotation.ParamType;
import com.wuguan.huate.annotation.ParamsValidate;
import com.wuguan.huate.bean.entity.Advertising;
import com.wuguan.huate.bean.params.AdvertisingPageParam;
import com.wuguan.huate.comm.CustomException;
import com.wuguan.huate.comm.FileUpload;
import com.wuguan.huate.service.AdvertisingService;
import com.wuguan.huate.web.result.ApiResult;

/**
 * @ClassName: AdvertisingController
 * @Description: 广告管理
 * @author LiuYanHong
 * @date 2020年4月9日 下午5:58:43
 * 
 */
@RestController
public class AdvertisingController {
	@Autowired
	FileUpload fileUpload;
	@Autowired
	AdvertisingService advertisingService;

	/**
	 * 
	 * @Title: upload
	 * @Description: 图片上传
	 * @param file
	 * @return D:/logs/huate/background/timg_158643067068120.jpg
	 * @throws CustomException
	 */
	@Function(key = "upload")
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public ApiResult upload(MultipartFile file) throws CustomException {
		return ApiResult.success(fileUpload.upload(file));
	}

	/**
	 * 
	 * @Title: uploadBatch
	 * @Description: 批量上传
	 * @param file
	 * @return
	 * @throws CustomException
	 */
	@Function(key = "uploadBatch")
	@RequestMapping(value = "/uploadBatch", method = RequestMethod.POST)
	public ApiResult uploadBatch(MultipartFile[] file) throws CustomException {
		return ApiResult.success(fileUpload.uploadBatch(file));
	}

	/**
	 * 
	 * @Title: delFile
	 * @Description:删除文件
	 * @param path
	 * @return
	 * @throws CustomException
	 */
	@Function(key = "delFile")
	@ParamsValidate(validateParams = { @Param(key = "path", type = ParamType.CUSTOM) })
	@RequestMapping(value = "/delFile", method = RequestMethod.POST)
	public ApiResult delFile(String path) throws CustomException {
		fileUpload.delFile(path);
		return ApiResult.success();
	}

	/**
	 * 
	 * @Title: addData
	 * @Description: 新增广告
	 * @param advertising
	 * @return
	 * @throws CustomException
	 */
	@Function(key = "advertisingAddData")
	@ParamsValidate(validateParams = { @Param(key = "title", type = ParamType.CUSTOM),
			@Param(key = "path", type = ParamType.CUSTOM) })
	@RequestMapping(value = "/advertising/addData", method = RequestMethod.POST)
	public ApiResult addData(Advertising advertising) throws CustomException {
		advertisingService.addData(advertising);
		return ApiResult.success(advertising.getId());
	}

	/**
	 * 
	 * @Title: addData
	 * @Description: 修改
	 * @param advertising
	 * @return
	 * @throws CustomException
	 */
	@Function(key = "advertisingUpdateData")
	@ParamsValidate(validateParams = { @Param(key = "id", limit = "0,11", type = ParamType.NUMBER) })
	@RequestMapping(value = "/advertising/updateData", method = RequestMethod.POST)
	public ApiResult updateData(Advertising advertising) throws CustomException {
		advertisingService.updateData(advertising);
		return ApiResult.success();
	}

	/**
	 * 
	 * @Title: addData
	 * @Description: 删除
	 * @param advertising
	 * @return
	 * @throws CustomException
	 */
	@Function(key = "advertisingDelData")
	@ParamsValidate(validateParams = { @Param(key = "id", limit = "0,11", type = ParamType.NUMBER) })
	@RequestMapping(value = "/advertising/delData", method = RequestMethod.POST)
	public ApiResult delData(Integer id) throws CustomException {
		advertisingService.delData(id);
		return ApiResult.success();
	}

	/**
	 * 
	 * @Title: addData
	 * @Description: 详情
	 * @param advertising
	 * @return
	 * @throws CustomException
	 */
	@Function(key = "advertisingDetailData")
	@ParamsValidate(validateParams = { @Param(key = "id", limit = "0,11", type = ParamType.NUMBER) })
	@RequestMapping(value = "/advertising/detailData", method = RequestMethod.GET)
	public ApiResult detailData(Integer id) throws CustomException {
		return ApiResult.success(advertisingService.detailData(id));
	}

	/**
	 * 
	 * @Title: addData
	 * @Description: 分页
	 * @param advertising
	 * @return
	 * @throws CustomException
	 */
	@Function(key = "advertisingPageData")
	@ParamsValidate(validateParams = { @Param(key = "page", limit = "0,11", type = ParamType.NUMBER),
			@Param(key = "rows", limit = "0,11", type = ParamType.NUMBER) })
	@RequestMapping(value = "/advertising/pageData", method = RequestMethod.GET)
	public ApiResult pageData(AdvertisingPageParam param) throws CustomException {
		return ApiResult.success(advertisingService.pageData(param));
	}

}
