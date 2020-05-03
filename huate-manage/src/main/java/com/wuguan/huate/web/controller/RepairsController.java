/**   
* @Title: RepairsController.java 
* @Package com.wuguan.huate.web.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月17日 上午12:51:43 
* @version V1.0   
*/
package com.wuguan.huate.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wuguan.huate.bean.entity.Repairs;
import com.wuguan.huate.bean.params.RepairsPageParam;
import com.wuguan.huate.comm.CustomException;
import com.wuguan.huate.service.RepairsService;
import com.wuguan.huate.web.result.ApiResult;

/**
 * @ClassName: RepairsController
 * @Description: 报损报修||报损报修处理
 * @author LiuYanHong
 * @date 2020年4月17日 上午12:51:43
 * 
 */
@RestController
public class RepairsController {
	@Autowired
	RepairsService repairsService;

	/**
	 * 
	 * @Title: addData
	 * @Description: 新增
	 * @param repairs
	 * @return
	 * @throws CustomException
	 */
	@RequestMapping(value = "/repairs/addData", method = RequestMethod.POST)
	public ApiResult addData(Repairs repairs) throws CustomException {
		repairsService.addData(repairs);
		return ApiResult.success(repairs.getId());
	}

	/**
	 * 
	 * @Title: allotHandle
	 * @Description: 分配处理人|指派
	 * @param 处理人ID|处理人姓名|处理人电话
	 * @return
	 * @throws CustomException
	 */
	@RequestMapping(value = "/repairs/allotHandle", method = RequestMethod.POST)
	public ApiResult allotHandle(Repairs repairs) throws CustomException {
		repairsService.allotHandle(repairs);
		return ApiResult.success();
	}

	/**
	 * 
	 * @Title: handle
	 * @Description: 处理
	 * @param repairs
	 * @return
	 * @throws CustomException
	 */
	@RequestMapping(value = "/repairs/handle", method = RequestMethod.POST)
	public ApiResult handle(Repairs repairs) throws CustomException {
		repairsService.handle(repairs);
		return ApiResult.success();
	}

	/**
	 * 
	 * @Title: handleOver
	 * @Description: 处理完
	 * @param repairs
	 * @return
	 * @throws CustomException
	 */
	@RequestMapping(value = "/repairs/handleOver", method = RequestMethod.POST)
	public ApiResult handleOver(Repairs repairs) throws CustomException {
		repairsService.handleOver(repairs);
		return ApiResult.success();
	}

	/**
	 * 
	 * @Title: revocation
	 * @Description: 撤销
	 * @param repairs
	 * @return
	 * @throws CustomException
	 */
	@RequestMapping(value = "/repairs/revocation", method = RequestMethod.POST)
	public ApiResult revocation(Repairs repairs) throws CustomException {
		repairsService.revocation(repairs);
		return ApiResult.success();
	}

	@RequestMapping(value = "/repairs/detailData", method = RequestMethod.GET)
	public ApiResult detailData(Integer id) {
		return ApiResult.success(repairsService.detailData(id));
	}

	/**
	 * 
	 * @Title: pageData
	 * @Description: 我的报修分页
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/repairs/pageData", method = RequestMethod.GET)
	public ApiResult pageData(RepairsPageParam param) {
		return ApiResult.success(repairsService.pageData(param));
	}

}
