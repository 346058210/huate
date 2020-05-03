/**   
* @Title: AddressBookController.java 
* @Package com.wuguan.huate.web.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月9日 下午4:51:20 
* @version V1.0   
*/
package com.wuguan.huate.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.wuguan.huate.annotation.Param;
import com.wuguan.huate.annotation.ParamType;
import com.wuguan.huate.annotation.ParamsValidate;
import com.wuguan.huate.bean.params.AddressBookPageParam;
import com.wuguan.huate.comm.CustomException;
import com.wuguan.huate.service.AddressBookService;
import com.wuguan.huate.web.result.ApiResult;

/**
 * @ClassName: AddressBookController
 * @Description: 通讯录
 * @author LiuYanHong
 * @date 2020年4月9日 下午4:51:20
 * 
 */
@RestController
public class AddressBookController {
	@Autowired
	AddressBookService addressBookService;

	/**
	 * 
	 * @Title: pageData
	 * @Description: 分页
	 * @param params
	 * @return
	 * @throws CustomException
	 */
	@ParamsValidate(validateParams = { @Param(key = "page", limit = "0,11", type = ParamType.NUMBER),
			@Param(key = "rows", limit = "0,11", type = ParamType.NUMBER) })
	@RequestMapping(value = "/book/pageData", method = RequestMethod.GET)
	public ApiResult pageData(AddressBookPageParam params) throws CustomException {
		
		return ApiResult.success(addressBookService.pageData(params));
	}
}
