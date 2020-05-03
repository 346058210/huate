/**   
* @Title: OrderExcel.java 
* @Package com.wuguan.huate.bean.vo 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月24日 下午7:50:31 
* @version V1.0   
*/
package com.wuguan.huate.bean.vo;

import java.util.List;

import com.excel.poi.annotation.ExportField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/** 
* @ClassName: OrderExcel 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年3月24日 下午7:50:31 
*  
*/
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class OrderExcel {
	@ExportField(columnName = "ID")
	private Integer id;                             //
	@ExportField(columnName = "订单编号")
	private String orderNo;                         //訂單編號
	@ExportField(columnName = "金额")
	private Double money;                           //金額
	@ExportField(columnName = "支付状态")
	private String state;                          //狀態 1 成功 0 失敗
	@ExportField(columnName = "支付时间")
	private String payTime;                         //支付時間
	@ExportField(columnName = "缴费手机")
	private String phone;                         //繳費用戶
	@ExportField(columnName = "描述")
	private String desc;                            //描述
	@ExportField(columnName = "订单是否关闭")
	private String isClose;                        //是否关闭订单 1 是  0 否
	@ExportField(columnName = "订单创建时间")
	private String createTime;                        //是否关闭订单 1 是  0 否
	@ExportField(columnName = "房号")
	private String houseNo;                        //房号
	@ExportField(columnName = "车牌号")
	private String carNo;                        //车牌号
	@ExportField(columnName = "车位号")
	private String parkNo;                        //车位号
	//@ExportField(columnName = "订单明细")
	private List<OrderItemExcel> items;

}
