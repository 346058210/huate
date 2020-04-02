/**   
* @Title: OrderController.java 
* @Package com.wuguan.huate.web.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月22日 下午4:34:02 
* @version V1.0   
*/
package com.wuguan.huate.web.controller;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.excel.poi.ExcelBoot;
import com.excel.poi.function.ExportFunction;
import com.wuguan.huate.annotation.Function;
import com.wuguan.huate.annotation.Param;
import com.wuguan.huate.annotation.ParamType;
import com.wuguan.huate.annotation.ParamsValidate;
import com.wuguan.huate.bean.entity.Order;
import com.wuguan.huate.bean.entity.User;
import com.wuguan.huate.bean.params.OrderPageParam;
import com.wuguan.huate.bean.params.PayRecordPageParam;
import com.wuguan.huate.bean.vo.OrderExcel;
import com.wuguan.huate.bean.vo.OrderVo;
import com.wuguan.huate.comm.BaseService;
import com.wuguan.huate.comm.Constant;
import com.wuguan.huate.comm.CustomException;
import com.wuguan.huate.comm.SpringUtil;
import com.wuguan.huate.service.OrderService;
import com.wuguan.huate.service.UserService;
import com.wuguan.huate.utils.DateUtils;
import com.wuguan.huate.utils.PayUtil;
import com.wuguan.huate.utils.RedisHelper;
import com.wuguan.huate.web.result.ApiResult;

/**
 * @ClassName: OrderController
 * @Description: 订单表
 * @author LiuYanHong
 * @date 2020年3月22日 下午4:34:02
 * 
 */
@RestController
public class OrderController {
	@Autowired
	OrderService orderService;
	@Autowired
	UserService userService;
	@Autowired
	BaseService baseService;

	/**
	 * 
	 * @Title: addOrder
	 * @Description: 提交訂單
	 * @param {"data":[ {"detail":[{"houseId":101,"money":200.0,"name":"应缴物业费","timeFrame":"2020-01--2020-03"}],"type":1},
	 *                  {"detail":[{"consumeId":1,"dosage":100.0,"money":200.0,"month":"2019-01"},{"consumeId":2,"dosage":100.0,"money":200.0,"month":"2019-01"}],"type":3},
	 *                  {"detail":[{"consumeId":1,"dosage":100.0,"money":200.0,"month":"2019-01"},{"consumeId":2,"dosage":100.0,"money":200.0,"month":"2019-01"}],"type":4}
	 *                  ],"openid":1001,"amount":4530}
	 * @return
	 * @throws CustomException
	 * @throws UnsupportedEncodingException
	 */
	@ParamsValidate(validateParams = { @Param(key = "data", type = ParamType.CUSTOM) })
	@RequestMapping(value = "/order/addOrder", method = RequestMethod.POST)
	public ApiResult addOrder(HttpServletRequest request, String data)
			throws CustomException, UnsupportedEncodingException {
		return ApiResult.success(orderService.addOrder(request, data));
	}

	/**
	 * 
	 * @Title: closeOrder
	 * @Description: 关闭订单
	 * @param request
	 * @param data
	 * @return
	 * @throws CustomException
	 */
	@ParamsValidate(validateParams = { @Param(key = "orderNo", type = ParamType.NUMBER) })
	@RequestMapping(value = "/order/closeOrder", method = RequestMethod.POST)
	public ApiResult closeOrder(String orderNo) throws CustomException {
		orderService.closeOrder(orderNo, null);
		return ApiResult.success();
	}

	/**
	 * 
	 * @Title: notify
	 * @Description: 支付结果通知
	 * @param request
	 * @param response
	 * @return
	 * @throws CustomException
	 * @throws IOException
	 */
	@RequestMapping(value = "/order/notify", method = RequestMethod.POST)
	public void notify(HttpServletRequest request, HttpServletResponse response) throws CustomException {
		try {
			BufferedReader br = new BufferedReader(
					new InputStreamReader((ServletInputStream) request.getInputStream()));
			String line = null;
			StringBuilder builder = new StringBuilder();
			while ((line = br.readLine()) != null) {
				builder.append(line);
			}
			br.close();
			Map<String, Object> parse = PayUtil.doXMLParse(builder.toString());
			String returnCode = (String) parse.get("return_code");
			String xml = "";
			if ("SUCCESS".equals(returnCode)) {
				String resultCode = (String) parse.get("result_code");
				String orderNo = (String) parse.get("out_trade_no");
				if ("SUCCESS".equals(resultCode)) {
					String payTime = DateUtils.convertTime((String) parse.get("time_end"));
					// 修改相关表的到期时间（物业|停车|水电消耗）
					orderService.updateOrder(orderNo, payTime);
					// 告知微信
					xml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
							+ "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
					// 推送通知
					RedisHelper bean = SpringUtil.getBean(RedisHelper.class);
					Boolean bool = bean.existsKey("accessToken");
					String accessToken = "";
					if (!bool) {
						accessToken = baseService.authGetAccessToken(Constant.XJ_APPID, Constant.XJ_SECRET);
						bean.set("accessToken", accessToken, 60 * 60 * 2);
					}
					accessToken = bean.get("accessToken");
					//
					User user = userService.getUserByOrderNo(orderNo);
					OrderVo order = (OrderVo) orderService.detailDataByOrderNo(orderNo);
					Map<String, Object> thing = new HashMap<String, Object>();
					thing.put("value", order.getDesc());
					Map<String, Object> amount = new HashMap<String, Object>();
					amount.put("value", order.getMoney() + "元");
					Map<String, Object> phrase = new HashMap<String, Object>();
					phrase.put("value", "缴费成功");
					Map<String, Object> date = new HashMap<String, Object>();
					date.put("value", payTime);
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("thing1", thing);
					params.put("amount2", amount);
					params.put("phrase4", phrase);
					params.put("date3", date);
					baseService.subscribeMessageSend(accessToken, user.getOpenid(),
							"pages/order/details/details?id=" + orderNo, Constant.PAY_TEMPLATEID, params);

				} else {
					orderService.closeOrder(orderNo, 0);
					xml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
							+ "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
				}
			} else {
				xml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
						+ "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
			}
			BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
			out.write(xml.getBytes());
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @Title: pageData
	 * @Description: 分页查询
	 * @param param
	 * @return
	 * @throws CustomException
	 */
	@Function(key = "orderPageData")
	@ParamsValidate(validateParams = { @Param(key = "page", limit = "0,11", type = ParamType.NUMBER),
			@Param(key = "rows", limit = "0,11", type = ParamType.NUMBER) })
	@RequestMapping(value = "/order/pageData", method = RequestMethod.GET)
	public ApiResult pageData(OrderPageParam param) throws CustomException {
		return ApiResult.success(orderService.pageData(param));
	}

	/**
	 * 
	 * @Title: pageDataByUser
	 * @Description: 查询个人订单
	 * @param param
	 * @return
	 * @throws CustomException
	 */
	@ParamsValidate(validateParams = { @Param(key = "page", limit = "0,11", type = ParamType.NUMBER),
			@Param(key = "rows", limit = "0,11", type = ParamType.NUMBER) })
	@RequestMapping(value = "/order/pageDataByUser", method = RequestMethod.GET)
	public ApiResult pageDataByUser(OrderPageParam param) throws CustomException {
		return ApiResult.success(orderService.pageDataByUser(param));
	}

	/**
	 * 
	 * @Title: detailData
	 * @Description: 查看订单详情
	 * @param id
	 * @return
	 * @throws CustomException
	 */
	@Function(key = "orderDetailData")
	@ParamsValidate(validateParams = { @Param(key = "id", limit = "0,11", type = ParamType.NUMBER) })
	@RequestMapping(value = "/order/detailData", method = RequestMethod.GET)
	public ApiResult detailData(Integer id) throws CustomException {
		return ApiResult.success(orderService.detailData(id));
	}

	/**
	 * 
	 * @Title: detailDataByOrderNo
	 * @Description: 订单号查看订单详情
	 * @param id
	 * @return
	 * @throws CustomException
	 */
	@ParamsValidate(validateParams = { @Param(key = "orderNo", type = ParamType.NUMBER) })
	@RequestMapping(value = "/order/detailDataByOrderNo", method = RequestMethod.GET)
	public ApiResult detailDataByOrderNo(String orderNo) throws CustomException {
		return ApiResult.success(orderService.detailDataByOrderNo(orderNo));
	}

	/**
	 * 
	 * @Title: exportExcel
	 * @Description: 导出订单
	 * @param response
	 * @param params
	 * @throws CustomException
	 */
	@Function(key = "orderExportExcel")
	@RequestMapping(value = "/order/exportExcel", method = RequestMethod.GET)
	public void exportExcel(HttpServletResponse response, OrderPageParam params) throws CustomException {
		ExcelBoot.ExportBuilder(response, "订单表信息", OrderExcel.class).exportResponse(params,
				new ExportFunction<OrderPageParam, Order>() {

					@Override
					public List<Order> pageQuery(OrderPageParam param, int pageNum, int pageSize) {
						return orderService.getOrders(param);
					}

					@Override
					public Object convert(Order order) {
						OrderExcel excel = new OrderExcel();
						BeanUtils.copyProperties(order, excel);
						excel.setIsClose(order.getIsClose() == 1 ? "是" : "否");
						excel.setState(order.getState() == 1 ? "成功" : "失败");
						excel.setPhone(userService.detail(order.getUserId()).getPhone());
						return excel;
					}
				});
	}

	/**
	 * 
	 * @Title: payRecords
	 * @Description: 物业|垃圾缴费记录查询
	 * @param id 房|车ID
	 * @param 类型 PROPERTY(1,"物业费"),PARK(2,"车位费"),WATER(3,"水费"),ELECTRIC(4,"电费"),RUBBISH(5,"生活垃圾费"),PARKMANAGE(6,"车位管理费"),PARKRENT(7,"车位租赁费")
	 * @return
	 * @throws CustomException
	 */
	@Function(key = "orderWyLjPayRecords")
	@ParamsValidate(validateParams = { @Param(key = "id", limit = "0,11", type = ParamType.NUMBER),
			@Param(key = "type", limit = "0,11", type = ParamType.NUMBER) })
	@RequestMapping(value = "/order/wyLjPayRecords", method = RequestMethod.GET)
	public ApiResult wyLjPayRecords(PayRecordPageParam param) throws CustomException {
		return ApiResult.success(orderService.wyLjPayRecords(param));
	}

	/**
	 * 
	 * @Title: payRecords
	 * @Description: 水|电缴费记录查询
	 * @param id 房|车ID
	 * @param 类型 PROPERTY(1,"物业费"),PARK(2,"车位费"),WATER(3,"水费"),ELECTRIC(4,"电费"),RUBBISH(5,"生活垃圾费"),PARKMANAGE(6,"车位管理费"),PARKRENT(7,"车位租赁费")
	 * @return
	 * @throws CustomException
	 */
	@Function(key = "orderSdPayRecords")
	@ParamsValidate(validateParams = { @Param(key = "houseNo", type = ParamType.CUSTOM),
			@Param(key = "type", limit = "0,11", type = ParamType.NUMBER) })
	@RequestMapping(value = "/order/sdPayRecords", method = RequestMethod.GET)
	public ApiResult sdPayRecords(PayRecordPageParam param) throws CustomException {
		return ApiResult.success(orderService.sdPayRecords(param));
	}

	/**
	 * 
	 * @Title: payRecords
	 * @Description: 车位（管理|租赁）缴费记录查询
	 * @param id 房|车ID
	 * @param 类型 PROPERTY(1,"物业费"),PARK(2,"车位费"),WATER(3,"水费"),ELECTRIC(4,"电费"),RUBBISH(5,"生活垃圾费"),PARKMANAGE(6,"车位管理费"),PARKRENT(7,"车位租赁费")
	 * @return
	 * @throws CustomException
	 */
	@Function(key = "orderCarPayRecords")
	@ParamsValidate(validateParams = { @Param(key = "houseNo", type = ParamType.CUSTOM),
			@Param(key = "type", limit = "0,11", type = ParamType.NUMBER) })
	@RequestMapping(value = "/order/carPayRecords", method = RequestMethod.GET)
	public ApiResult carPayRecords(PayRecordPageParam param) throws CustomException {
		return ApiResult.success(orderService.carPayRecords(param));
	}

}
