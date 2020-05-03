/**   
* @Title: HandleController.java 
* @Package com.wuguan.huate.web.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月17日 下午3:39:07 
* @version V1.0   
*/
package com.wuguan.huate.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.wuguan.huate.annotation.Param;
import com.wuguan.huate.annotation.ParamType;
import com.wuguan.huate.annotation.ParamsValidate;
import com.wuguan.huate.bean.entity.Handle;
import com.wuguan.huate.bean.entity.Repairs;
import com.wuguan.huate.comm.CustomException;
import com.wuguan.huate.service.HandleService;
import com.wuguan.huate.service.RepairsService;
import com.wuguan.huate.web.result.ApiResult;

/** 
* @ClassName: HandleController 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年4月17日 下午3:39:07 
*  
*/
@RestController
public class HandleController {
	@Autowired
	HandleService handleService;
	@Autowired
	RepairsService repairsService;
	
	/**
	 * 
	* @Title: handleData
	* @Description: 报修处理
	* @param handle 
	* @param state 报修状态
	* @return
	 */
	@RequestMapping(value = "/handle/handleData",method = RequestMethod.POST)
	public ApiResult handleData(Handle handle,Integer state) {
		handle.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		handleService.addData(handle);
		Repairs repairs = new Repairs();
		repairs.setId(handle.getRepairsId());
		repairs.setState(state);
		repairsService.handle(repairs);
		return ApiResult.success();
	}
	
	/**
	 * 
	 * @Title: queryByRepairsId
	 * @Description: 查询某报修流程
	 * @param repairsId
	 * @return
	 * @throws CustomException
	 */
	@ParamsValidate(validateParams = { @Param(key = "repairsId", limit = "0,11", type = ParamType.NUMBER) })
	@RequestMapping(value = "/handle/queryByRepairsId", method = RequestMethod.GET)
	public ApiResult queryByRepairsId(Integer repairsId) throws CustomException {
		return ApiResult.success(handleService.queryByRepairsId(repairsId));
	}
}
