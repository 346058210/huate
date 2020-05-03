/**   
* @Title: BasicController.java 
* @Package com.wuguan.huate.web.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月8日 下午7:40:55 
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

import com.wuguan.huate.comm.Constant;
import com.wuguan.huate.comm.CustomException;
import com.wuguan.huate.service.BasicService;
import com.wuguan.huate.web.result.ApiResult;

/**
 * @ClassName: BasicController
 * @Description: 物业基础信息
 * @author LiuYanHong
 * @date 2020年4月8日 下午7:40:55
 * 
 */
@RestController
public class BasicController {
	@Autowired
	BasicService basicService;

	@RequestMapping(value = "/basic/queryBasic", method = RequestMethod.GET)
	public ApiResult queryBasic() throws CustomException {
		return ApiResult.success(basicService.queryBasic());
	}

	@RequestMapping(value = "/queryWxTml", method = RequestMethod.GET)
	public ApiResult queryWxTml() throws CustomException {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Object> order = new ArrayList<Object>();//
		order.add(Constant.PAY_TEMPLATEID);
		List<Object> notification = new ArrayList<Object>();//
		notification.add(Constant.NOTICE_TEMPLATEID);
		List<Object> waitPayFee = new ArrayList<Object>();//
		waitPayFee.add(Constant.PROPERTY_PAY_TEMPLATEID);
		List<Object> liveServe = new ArrayList<Object>();// 预约
		liveServe.add(Constant.RESERVE_TX);
		List<Object> repairs = new ArrayList<Object>();//
		repairs.add(Constant.XJ_REPAIRDS_RESULT);
		List<Object> complain = new ArrayList<Object>();//
		complain.add(Constant.COMPAIN_RESULT);
		map.put("order", order);
		map.put("notification", notification);
		map.put("waitPayFee", waitPayFee);
		map.put("liveServe", liveServe);
		map.put("repairs", repairs);
		map.put("complain", complain);
		return ApiResult.success(map);
	}

}
