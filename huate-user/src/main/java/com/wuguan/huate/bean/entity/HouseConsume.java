/**   
* @Title: RoomConsume.java 
* @Package com.wuguan.huate.bean.entity 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月15日 下午3:19:51 
* @version V1.0   
*/
package com.wuguan.huate.bean.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/** 
* @ClassName: RoomConsume 
* @Description: 住戶消耗
* @author LiuYanHong
* @date 2020年3月15日 下午3:19:51 
*  
*/
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class HouseConsume implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer id;                            //
	private String houseNo;                        //戶號
	private String uploadTime;                     //數據上傳時間
	private Integer isPay;                         //是否支付 1 是 0 否
	private String payTime;                        //支付時間
	private Integer type;                     //上传数据类型 3 水 4电
	private String month;                         //月份 格式：2020-01
	private Double money;                          //金額
	private Double dosage;                         //用量
	private String explain;                        //收費說明
	private String noticeTime;							//通知时间
	private Double startQuan;							//起始用量
	private Double endQuan;							//结束用量

}
