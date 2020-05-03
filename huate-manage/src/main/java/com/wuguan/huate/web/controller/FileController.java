/**   
* @Title: FileController.java 
* @Package com.wuguan.huate.web.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月17日 下午9:23:14 
* @version V1.0   
*/
package com.wuguan.huate.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.wuguan.huate.annotation.Param;
import com.wuguan.huate.annotation.ParamType;
import com.wuguan.huate.annotation.ParamsValidate;
import com.wuguan.huate.comm.Constant;
import com.wuguan.huate.comm.CustomException;
import com.wuguan.huate.comm.FileUpload;
import com.wuguan.huate.web.result.ApiResult;

/**
 * @ClassName: FileController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author LiuYanHong
 * @param <E>
 * @date 2020年4月17日 下午9:23:14
 * 
 */
@RestController
public class FileController<E> {

	@Autowired
	FileUpload fileUpload;

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

	/**
	 * 
	 * @Title: delFile
	 * @Description: 获取模板
	 * @param path
	 * @return
	 * @throws CustomException
	 */
	@RequestMapping(value = "/queryWxTml", method = RequestMethod.GET)
	public ApiResult queryWxTml(String path) throws CustomException {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Object> repair = new ArrayList<Object>();//
		repair.add(Constant.REPAIRDS_RESULT);
		List<Object> liveServe = new ArrayList<Object>();// 预约
		liveServe.add(Constant.APPOINT_RESULT);
		List<Object> repairs = new ArrayList<Object>();//
		repairs.add(Constant.REPAIRDS);
		List<Object> complain = new ArrayList<Object>();//
		complain.add(Constant.COMPAIN);
		map.put("repair", repair);
		map.put("liveServe", liveServe);
		map.put("repairs", repairs);
		map.put("complain", complain);
		return ApiResult.success(map);
	}

}
