/**   
* @Title: OrderItemExcel.java 
* @Package com.wuguan.huate.bean.vo 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月3日 下午10:19:47 
* @version V1.0   
*/
package com.wuguan.huate.bean.vo;

import com.excel.poi.annotation.ExportField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/** 
* @ClassName: OrderItemExcel 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年4月3日 下午10:19:47 
*  
*/
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class OrderItemExcel {
	@ExportField(columnName = "ID")
	private Integer id;
	@ExportField(columnName = "订单编号")
	private String orderNo;
	@ExportField(columnName = "缴费项")
	private String name;
	@ExportField(columnName = "缴费月数")
	private Integer num;                            //繳費月數
	@ExportField(columnName = "缴费总金额")
	private Double totalMoney;        
	@ExportField(columnName = "缴费金额")
	private Double money;                           //繳費金
	@ExportField(columnName = "到期时间")
	private String dueTime;                         //到期時間
	@ExportField(columnName = "描述")
	private String desc;                            //描述
	@ExportField(columnName = "缴费类型")
	private String feeType;                      //繳費類型
	@ExportField(columnName = "支付状态")
	private String state;						//狀態 1 成功 0 失敗
	@ExportField(columnName = "缴费时间")
	private String payTime;                         //支付時間
	@ExportField(columnName = "缴费手机号")
	private String phone;                         //繳費用戶
}
