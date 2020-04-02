/**   
* @Title: Constant.java 
* @Package com.wuguan.huate.comm 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月17日 下午2:22:57 
* @version V1.0   
*/
package com.wuguan.huate.comm;

/** 
* @ClassName: Constant 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年3月17日 下午2:22:57 
*  
*/
public class Constant {
	//华特家园小程序appid
	public static final String JY_APPID="wx5c789d1c4e649a68";
	//华特家园小程序appSecret
	public static final String JY_SECRET="cb9a8d11b76cc9a56225e2c5e0b4607d";
	//特享家小程序appid
	public static final String XJ_APPID="wxcba23a9f6bc78388";
	//特享家小程序appSecret
	public static final String XJ_SECRET="94f0286537ff5af4ab60d38c596e813d";
	//授权类型
	public static final String GRANT_TYPE="authorization_code";
	//商户号
	public static final String MCH_ID="1580523971";
	//密钥
	public static final String KEY="huate1234567890qwertyuiopasdfghj";
	//交易类型
	public static final String TRADE_TYPE="JSAPI";
	//签名类型
	public static final String SIGN_TYPE="MD5";
	//获取凭证Type
	public static final String AUTH_GRANT_TYPE="client_credential";
	//---------------------消息模板ID----------------------------------
	//缴费成功通知模板
	public static final String PAY_TEMPLATEID="wXpIF4xoCJj2yE7wKMJfB1QCaJ-fIMjDFSS6INFUZzI";
	//物业缴费通知模板
	public static final String PROPERTY_PAY_TEMPLATEID="z4hddVfE9WTVfH903gmtH-JlaSktny1ugDeCzz5mXdk";
	//物业公告通知
	public static final String NOTICE_TEMPLATEID="sxDLTMu5KyRxT6mBQyrSuT_8aYunxDzcExdhnG-OtdU";
	//---------------------消息模板ID----------------------------------
	//获取session_key、openid,微信后台验证
	public static final String CODE2SESSION = "https://api.weixin.qq.com/sns/jscode2session";
	//微信后台统一下单
	public static final String ORDERS="https://api.mch.weixin.qq.com/pay/unifiedorder";
	//异步接受微信支付结果的回调地址
	public static final String NOTIFY_URL="http://120.24.21.253:8086/order/notify";
	//关闭订单
	public static final String CLOSE_ORDER="https://api.mch.weixin.qq.com/pay/closeorder";
	//查詢訂單狀態
	public static final String QUERY_ORDER="https://api.mch.weixin.qq.com/pay/orderquery";
	//获取凭证
	public static final String AUTH_GETACCESSTOKEN="https://api.weixin.qq.com/cgi-bin/token";
	//订阅消息发送
	public static final String SUBSCRIBEMESSAGE_SEND="https://api.weixin.qq.com/cgi-bin/message/subscribe/send";
	//获取模板
	public static final String SUBSCRIBEMESSAGE_GETTEMPLATE="https://api.weixin.qq.com/wxaapi/newtmpl/gettemplate";

}
