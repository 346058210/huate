/**   
* @Title: VisitorController.java 
* @Package com.wuguan.huate.web.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月15日 下午7:35:30 
* @version V1.0   
*/
package com.wuguan.huate.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wuguan.huate.bean.entity.Visitor;
import com.wuguan.huate.bean.params.PageParams;
import com.wuguan.huate.comm.CustomException;
import com.wuguan.huate.service.VisitorService;
import com.wuguan.huate.web.result.ApiResult;

/** 
* @ClassName: VisitorController 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年4月15日 下午7:35:30 
*  
*/
@RestController
public class VisitorController {
	@Autowired
	VisitorService visitorService;
	
	/**
	 * 
	* @Title: addData
	* @Description: 添加访客记录
	* @return
	* @throws CustomException
	 */
	@RequestMapping(value = "/visitor/addData",method = RequestMethod.POST)
	public ApiResult addData(Visitor visitor)throws CustomException{
		visitorService.addData(visitor);
		return ApiResult.success(visitor.getId());
		
	}
	
	/**
	 * 
	* @Title: updateData
	* @Description: 修改
	* @param visitor
	* @return
	* @throws CustomException
	 */
	@RequestMapping(value = "/visitor/updateData",method = RequestMethod.POST)
	public ApiResult updateData(Visitor visitor)throws CustomException{
		visitorService.updateData(visitor);
		return ApiResult.success();
		
	}
	
	/**
	 * 
	* @Title: affirmVisitor
	* @Description: 确认访客
	* @return
	* @throws CustomException
	 */
	@RequestMapping(value = "/visitor/affirmVisitor",method = RequestMethod.POST)
	public ApiResult affirmVisitor(String code)throws CustomException{
		visitorService.affirmVisitor(code);
		return ApiResult.success();
	}
	
	@RequestMapping(value = "/visitor/detailData",method = RequestMethod.GET)
	public ApiResult detailData(Integer id)throws CustomException{
		return ApiResult.success(visitorService.detailData(id));
	}
	
	@RequestMapping(value = "/visitor/queryByCode",method = RequestMethod.GET)
	public ApiResult queryByCode(String code)throws CustomException{
		return ApiResult.success(visitorService.queryByCode(code));
	}
	
	@RequestMapping(value = "/visitor/pageData",method = RequestMethod.GET)
	public ApiResult pageData(PageParams params)throws CustomException{
		return ApiResult.success(visitorService.pageData(params));
	}
}
