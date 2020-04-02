/**   
* @Title: ParkVo.java 
* @Package com.wuguan.huate.bean.vo 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月21日 下午10:42:09 
* @version V1.0   
*/
package com.wuguan.huate.bean.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/** 
* @ClassName: ParkVo 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年3月21日 下午10:42:09 
*  
*/
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class BindPark implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;                             //
	private String houseNo;                         //戶號
	private String carNo;                           //車牌號
	private String parkNo;                          //車位號
	private String owner;                           //車主1
	private Integer type;                           //類型 1 租停 2 自購
	private String dueTime;                         //到期時間
	private String normName;						//收费标准
	private Double normPrice;						//收费标准
	private String normUnit;						//收费标准
	private Double area;							//面积
	private String genre;							//车位类型
}
