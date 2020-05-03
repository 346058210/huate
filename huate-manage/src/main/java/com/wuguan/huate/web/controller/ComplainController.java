/**   
* @Title: ComplainController.java 
* @Package com.wuguan.huate.web.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月16日 下午9:49:25 
* @version V1.0   
*/
package com.wuguan.huate.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.wuguan.huate.bean.entity.Complain;
import com.wuguan.huate.bean.params.ComplainPageParam;
import com.wuguan.huate.comm.CustomException;
import com.wuguan.huate.service.ComplainService;
import com.wuguan.huate.web.result.ApiResult;

/**
 * @ClassName: ComplainController
 * @Description: 投诉建议
 * @author LiuYanHong
 * @date 2020年4月16日 下午9:49:25
 * 
 */
@RestController
public class ComplainController {
	@Autowired
	ComplainService complainService;

	@RequestMapping(value = "/complain/pageData", method = RequestMethod.GET)
	public ApiResult pageData(ComplainPageParam param) throws CustomException {
		return ApiResult.success(complainService.pageData(param));
	}

	@RequestMapping(value = "/complain/detailData", method = RequestMethod.GET)
	public ApiResult detailData(Integer id) throws CustomException {
		return ApiResult.success(complainService.detailData(id));
	}

	/**
	 * 
	 * @Title: allotHandle
	 * @Description: 指派
	 * @param 处理人ID|处理人姓名|处理人电话
	 * @return
	 * @throws CustomException
	 */
	@RequestMapping(value = "/complain/allotHandle", method = RequestMethod.POST)
	public ApiResult allotHandle(Complain complain) throws CustomException {
		complainService.allotHandle(complain);
		return ApiResult.success();
	}

	/**
	 * 
	 * @Title: handle
	 * @Description: 处理
	 * @param complain
	 * @return
	 * @throws CustomException
	 */
	@RequestMapping(value = "/complain/handle", method = RequestMethod.POST)
	public ApiResult handle(Complain complain) throws CustomException {
		complainService.handle(complain);
		return ApiResult.success();
	}

	/**
	 * 
	 * @Title: handleOver
	 * @Description: 完成
	 * @param complain
	 * @return
	 * @throws CustomException
	 */
	@RequestMapping(value = "/complain/handleOver", method = RequestMethod.POST)
	public ApiResult handleOver(Complain complain) throws CustomException {
		complainService.handleOver(complain);
		return ApiResult.success();
	}

	/**
	 * 
	 * @Title: revocation
	 * @Description: 撤销
	 * @param complain
	 * @return
	 * @throws CustomException
	 */
	@RequestMapping(value = "/complain/revocation", method = RequestMethod.POST)
	public ApiResult revocation(Complain complain) throws CustomException {
		complainService.revocation(complain);
		return ApiResult.success();
	}

}
