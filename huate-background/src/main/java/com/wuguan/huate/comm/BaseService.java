/**   
* @Title: BaseService.java 
* @Package com.wuguan.huate.comm 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月17日 下午3:36:35 
* @version V1.0   
*/
package com.wuguan.huate.comm;

import java.io.UnsupportedEncodingException;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.wuguan.huate.utils.HttpClientUtil;
import com.wuguan.huate.utils.PayUtil;
import com.wuguan.huate.utils.RedisHelper;
import com.wuguan.huate.web.result.ResultEnums;

/**
 * @ClassName: BaseService
 * @Description: 公用
 * @author LiuYanHong
 * @date 2020年3月17日 下午3:36:35
 * 
 */
@Service
public class BaseService {

	private static final Logger log = LoggerFactory.getLogger("infoLogger");
	/**
	 * 
	 * @Title: getOpenid
	 * @Description: 微信授權獲取openid
	 * @param code
	 * @return
	 */
	public String getOpenid(String code, String appid, String secret) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("appid", appid);
		params.put("secret", secret);
		params.put("js_code", code);
		params.put("grant_type", Constant.GRANT_TYPE);
		String data = HttpClientUtil.doGet(Constant.CODE2SESSION, null, params);
		if (!data.contains("openid")) {
			throw new CustomException(ResultEnums.BUSINESS.getCode(), "微信后台授权失败");
		}
		return data;
	}

	/**
	 * 
	 * @Title: authGetAccessToken
	 * @Description: 获取小程序后台接口调用凭着
	 * @return
	 */
	public String authGetAccessToken(String appid, String secret) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("grant_type", Constant.AUTH_GRANT_TYPE);
		params.put("appid", appid);
		params.put("secret", secret);
		String data = HttpClientUtil.doGet(Constant.AUTH_GETACCESSTOKEN, null, params);
		if (!data.contains("access_token")) {
			throw new CustomException(ResultEnums.BUSINESS.getCode(), "微信后台获取凭证失败");
		}
		JSONObject object = JSONObject.parseObject(data);
		return object.getString("access_token");
	}

	/**
	 * 
	 * @Title: subscribeMessageGetTemplate
	 * @Description: 获取消息模板
	 * @return
	 */
	public String subscribeMessageGetTemplate() {
		return null;
	}

	/**
	 * 
	 * @Title: subscribeMessageSend
	 * @Description: 模板消息发送
	 * @return
	 */
	public void subscribeMessageSend(String accessToken, String touser, String page, String templateId, Object data,
			String appid, String secret) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("touser", touser);
		map.put("template_id", templateId);
		map.put("page", page);
		map.put("miniprogram_state", "formal");
		map.put("data", data);
		String params = JSONObject.toJSON(map).toString();
		log.info("---------------------数据推送开始---------------------");
		log.info("推送的数据: " + params);
		log.info("推送的时间: " + new Date());
		String url = Constant.SUBSCRIBEMESSAGE_SEND + "?access_token=" + accessToken;
		String result = HttpClientUtil.httpRequest(url, "POST", params);
		log.info("推送的结果: " + result);
		log.info("---------------------数据推送结束---------------------");
		JSONObject json = JSONObject.parseObject(result);
		if ("40001".equals(json.getString("errcode"))||"42001".equals(json.getString("errcode"))) {
			RedisHelper bean = SpringUtil.getBean(RedisHelper.class);
			accessToken = authGetAccessToken(appid, secret);
			bean.set("accessToken", accessToken, 60 * 60 * 2);
			url = Constant.SUBSCRIBEMESSAGE_SEND + "?access_token=" + accessToken;
			HttpClientUtil.httpRequest(url, "POST", params);
		} else if (json.getInteger("errcode") != 0) {
			throw new CustomException(ResultEnums.BUSINESS.getCode(), "消息推送失败");
		}

	}

	/**
	 * 
	 * @Title: createOrder
	 * @Description: 微信后台统一下单
	 * @return
	 * @param appid
	 * @param mch_id           商户号
	 * @param nonce_str        随机字符串
	 * @param sign             签名
	 * @param body             商品描述
	 * @param out_trade_no     商户订单号
	 * @param total_fee        标价金额 单位：分
	 * @param spbill_create_ip 终端ip
	 * @param notify_url       通知地址
	 * @param trade_type       交易类型
	 * @param openid           用户标识
	 * @throws UnsupportedEncodingException
	 */
	public Map<String, Object> createOrder(HttpServletRequest request, String body, String out_trade_no, Integer money,
			String openid) throws UnsupportedEncodingException {
		// 32位随机字符串
		String nonce_str = PayUtil.getRandomString(32);
		// 终端ip
		String spbill_create_ip = HttpClientUtil.getIpAddr(request);
		// 金额
		String fee = Integer.toString(money);

		Map<String, String> params = new HashMap<String, String>();
		params.put("appid", Constant.XJ_APPID);
		params.put("mch_id", Constant.MCH_ID);
		params.put("nonce_str", nonce_str);
		params.put("body", body);
		params.put("out_trade_no", out_trade_no);
		params.put("total_fee", fee);// 金额转成分，在转为字符串
		params.put("spbill_create_ip", spbill_create_ip);
		params.put("notify_url", Constant.NOTIFY_URL);
		params.put("trade_type", Constant.TRADE_TYPE);
		params.put("openid", openid);
		// 除去数组中的空值和签名参数 
		params = PayUtil.paraFilter(params);
		// 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
		String prestr = PayUtil.createLinkString(params);
		// prestr=prestr+"&key="+Constant.KEY;
		// System.out.println(prestr);
		// String string = new String(prestr.getBytes("utf-8"));
		// MD5获取签名
		// String sign = MD5Utils.encodePassword(string, null).toUpperCase();
		// System.out.println("sign:"+sign);
		String sign = PayUtil.getSign(prestr, Constant.KEY, "utf-8");
		// 拼接统一下单接口使用的xml数据
		String xml = "<xml>" + "<appid><![CDATA[" + Constant.XJ_APPID + "]]></appid>" + "<body><![CDATA[" + body
				+ "]]></body>" + "<mch_id><![CDATA[" + Constant.MCH_ID + "]]></mch_id>" + "<nonce_str><![CDATA["
				+ nonce_str + "]]></nonce_str>" + "<notify_url><![CDATA[" + Constant.NOTIFY_URL + "]]></notify_url>"
				+ "<openid><![CDATA[" + openid + "]]></openid>" + "<out_trade_no><![CDATA[" + out_trade_no
				+ "]]></out_trade_no>" + "<spbill_create_ip><![CDATA[" + spbill_create_ip + "]]></spbill_create_ip>"
				+ "<total_fee><![CDATA[" + fee + "]]></total_fee>" + "<trade_type><![CDATA[" + Constant.TRADE_TYPE
				+ "]]></trade_type>" + "<sign>" + sign + "</sign>" + "</xml>";
		String result = HttpClientUtil.httpRequest(Constant.ORDERS, "POST", xml);
		// 解析结果
		Map<String, Object> map = PayUtil.doXMLParse(result);
		// 获取状态码
		String return_code = (String) map.get("return_code");
		// 返回移动端需要的参数
		if (return_code.equals("SUCCESS")) {
			String resultCode = (String) map.get("result_code");
			if ("SUCCESS".equals(resultCode)) {
				Map<String, Object> response = new HashMap<String, Object>();
				// 支付订单ID
				String prepay_id = (String) map.get("prepay_id");
				long timeStamp = Instant.now().getEpochSecond();
				// 拼接小程序段的签名字符串
				String signString = "appId=" + Constant.XJ_APPID + "&nonceStr=" + nonce_str + "&package=" + "prepay_id="
						+ prepay_id + "&signType=" + Constant.SIGN_TYPE + "&timeStamp=" + timeStamp;
				// 再次生产签名
				String paySign = PayUtil.getSign(signString, Constant.KEY, "utf-8").toUpperCase();
				response.put("timeStamp", timeStamp + "");
				response.put("nonceStr", nonce_str);
				response.put("package", "prepay_id=" + prepay_id);
				response.put("paySign", paySign);
				return response;
			}
		}
		return null;
	}

	/**
	 * 
	 * @Title: closeOrder
	 * @Description: 关闭订单
	 * @param appid
	 * @param mch_id
	 * @param out_trade_no 订单号
	 * @param nonce_str
	 * @param sign
	 */
	public void closeOrders(String orderNo) {
		// 32位随机字符串
		String nonce_str = PayUtil.getRandomString(32);
		Map<String, String> params = new HashMap<String, String>();
		params.put("appid", Constant.XJ_APPID);
		params.put("mch_id", Constant.MCH_ID);
		params.put("nonce_str", nonce_str);
		params.put("out_trade_no", orderNo);
		// 除去数组中的空值和签名参数 
		params = PayUtil.paraFilter(params);
		// 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
		String prestr = PayUtil.createLinkString(params);
		// MD5获取签名
		String sign = PayUtil.getSign(prestr, Constant.KEY, "utf-8");
		// 拼接关闭订单xml
		String xml = "<xml>" + "<appid>" + Constant.XJ_APPID + "</appid>" + "<mch_id>" + Constant.MCH_ID + "</mch_id>"
				+ "<nonce_str>" + nonce_str + "</nonce_str>" + "<out_trade_no>" + orderNo + "</out_trade_no>" + "<sign>"
				+ sign + "</sign>" + "</xml>";
		String result = HttpClientUtil.httpRequest(Constant.CLOSE_ORDER, "POST", xml);
		// 解析xml
		Map<String, Object> map = PayUtil.doXMLParse(result);
		String return_code = (String) map.get("return_code");
		if (!"SUCCESS".equals(return_code)) {
			throw new CustomException(ResultEnums.BUSINESS.getCode(), "订单关闭失败");
		}
	}

	/**
	 * 
	 * @Title: queryOrder
	 * @Description: 查詢訂單狀態
	 * @param orderNo
	 * @param appid
	 * @param mch_id
	 * @param out_trade_no
	 * @param nonce_str
	 * @param sign
	 */
	public Map<String, Object> queryOrder(String orderNo) {
		// 32位随机字符串
		String nonce_str = PayUtil.getRandomString(32);
		Map<String, String> params = new HashMap<String, String>();
		params.put("appid", Constant.XJ_APPID);
		params.put("mch_id", Constant.MCH_ID);
		params.put("nonce_str", nonce_str);
		params.put("out_trade_no", orderNo);
		// 除去数组中的空值和签名参数 
		params = PayUtil.paraFilter(params);
		// 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
		String prestr = PayUtil.createLinkString(params);
		// MD5获取签名
		String sign = PayUtil.getSign(prestr, Constant.KEY, "utf-8");
		// 拼接查詢订单xml
		String xml = "<xml>" + "<appid>" + Constant.XJ_APPID + "</appid>" + "<mch_id>" + Constant.MCH_ID + "</mch_id>"
				+ "<nonce_str>" + nonce_str + "</nonce_str>" + "<out_trade_no>" + orderNo + "</out_trade_no>" + "<sign>"
				+ sign + "</sign>" + "</xml>";
		String result = HttpClientUtil.httpRequest(Constant.QUERY_ORDER, "POST", xml);
		// 解析xml
		Map<String, Object> map = PayUtil.doXMLParse(result);
		return map;
	}
}
