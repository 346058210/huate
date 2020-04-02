/**   
* @Title: Resident.java 
* @Package com.wuguan.huate.bean.entity 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月15日 下午9:21:20 
* @version V1.0   
*/
package com.wuguan.huate.bean.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/** 
* @ClassName: Resident 
* @Description: 住戶信息 
* @author LiuYanHong
* @date 2020年3月15日 下午9:21:20 
*  
*/
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Resident implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;                             //
	private String name;                            //姓名
	private String birthDate;                       //出身年月
	private String sex;                             //性別
	private String houseNo;                         //戶號
	private String phone;                           //手機
	private String moveinTime;                      //入住時間
	private Integer relation;                       //關係 1 戶主 2 成員 3 租戶
	private String idcard;                       //身份證號碼
	private Integer isDel;                       //是否删除 1 是 0 否
}
