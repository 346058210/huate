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
import com.wuguan.huate.annotation.Param;
import com.wuguan.huate.annotation.ParamType;
import com.wuguan.huate.annotation.ParamsValidate;
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
	@ParamsValidate(validateParams = { @Param(key = "path", type = ParamType.CUSTOM) })
	@RequestMapping(value = "/delFile", method = RequestMethod.POST)
	public ApiResult delFile(String path) throws CustomException {
		fileUpload.delFile(path);
		return ApiResult.success();
	}

	@RequestMapping(value = "/advertising/queryAdvertisings", method = RequestMethod.GET)
	public ApiResult queryAdvertisings() throws CustomException {
		return ApiResult.success(advertisingService.queryAdvertisings());
	}
}
