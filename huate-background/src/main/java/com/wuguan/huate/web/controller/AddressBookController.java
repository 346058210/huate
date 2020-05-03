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

import com.wuguan.huate.annotation.Function;
import com.wuguan.huate.annotation.Param;
import com.wuguan.huate.annotation.ParamType;
import com.wuguan.huate.annotation.ParamsValidate;
import com.wuguan.huate.bean.entity.AddressBook;
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
	 * @Title: addData
	 * @Description: 新增
	 * @param book
	 * @return
	 * @throws CustomException
	 */
	
	@Function(key = "bookAddData")
	@RequestMapping(value = "/book/addData", method = RequestMethod.POST)
	public ApiResult addData(AddressBook book) throws CustomException {
		addressBookService.addData(book);
		return ApiResult.success(book.getId());
	}

	/**
	 * 
	 * @Title: updateData
	 * @Description: 修改
	 * @param book
	 * @return
	 * @throws CustomException
	 */
	@Function(key = "bookUpdateData")
	@ParamsValidate(validateParams = { @Param(key = "id", limit = "0,11", type = ParamType.NUMBER) })
	@RequestMapping(value = "/book/updateData", method = RequestMethod.POST)
	public ApiResult updateData(AddressBook book) throws CustomException {
		addressBookService.updateData(book);
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
	@Function(key = "bookDelData")
	@ParamsValidate(validateParams = { @Param(key = "id", limit = "0,11", type = ParamType.NUMBER) })
	@RequestMapping(value = "/book/delData", method = RequestMethod.POST)
	public ApiResult delData(Integer id) throws CustomException {
		addressBookService.delData(id);
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
	@Function(key = "bookDetailData")
	@ParamsValidate(validateParams = { @Param(key = "id", limit = "0,11", type = ParamType.NUMBER) })
	@RequestMapping(value = "/book/detailData", method = RequestMethod.GET)
	public ApiResult detailData(Integer id) throws CustomException {
		return ApiResult.success(addressBookService.detailData(id));
	}

	/**
	 * 
	 * @Title: pageData
	 * @Description: 分页
	 * @param params
	 * @return
	 * @throws CustomException
	 */
	@Function(key = "bookPageData")
	@ParamsValidate(validateParams = { @Param(key = "page", limit = "0,11", type = ParamType.NUMBER),
			@Param(key = "rows", limit = "0,11", type = ParamType.NUMBER) })
	@RequestMapping(value = "/book/pageData", method = RequestMethod.GET)
	public ApiResult pageData(AddressBookPageParam params) throws CustomException {
		return ApiResult.success(addressBookService.pageData(params));
	}
}
