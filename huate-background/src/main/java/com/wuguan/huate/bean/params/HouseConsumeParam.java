/**   
* @Title: HouseConsumeParam.java 
* @Package com.wuguan.huate.bean.params 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月18日 下午5:02:45 
* @version V1.0   
*/
package com.wuguan.huate.bean.params;

import java.io.Serializable;

import com.excel.poi.annotation.ExportField;
import com.excel.poi.annotation.ImportField;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/** 
* @ClassName: HouseConsumeParam 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年3月18日 下午5:02:46 
*  
*/
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class HouseConsumeParam implements Serializable {

	private static final long serialVersionUID = 1L;
	@ExportField(columnName = "房号")
	@ImportField(required = true)
	private String houseNo;                        //戶號
	@ExportField(columnName = "数据上传时间")
	private String uploadTime;                     //數據上傳時間
	@ExportField(columnName = "是否支付（是|否）")
	private String pay;                         //是否支付 1 是 0 否
	@ExportField(columnName = "支付时间")
	private String payTime;                        //支付時間
	@ExportField(columnName = "消耗类型（水|电）")
	@ImportField(required = true)
	private String type;                     //上传数据类型 3 水 4 电
	@ExportField(columnName = "月份")
	@ImportField(required = true)
	private String month;                         //月份
	@ExportField(columnName = "金额")
	private Double money;                          //金額
	@ExportField(columnName = "用量")
	private Double dosage;                         //用量
	@ExportField(columnName = "收费说明")
	private String explain;                        //收費說明
	@ExportField(columnName = "起始量")
	@ImportField(required = true)
	private Double startQuan;                        //收費說明
	@ExportField(columnName = "结束量")
	@ImportField(required = true)
	private Double endQuan;                        //收費說明

}
