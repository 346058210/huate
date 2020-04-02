/**   
* @Title: HouseWY.java 
* @Package com.wuguan.huate.bean.vo 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月21日 下午4:29:17 
* @version V1.0   
*/
package com.wuguan.huate.bean.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/** 
* @ClassName: HouseWY 
* @Description: 物业绑定房屋信息
* @author LiuYanHong
* @date 2020年3月21日 下午4:29:17 
*  
*/
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class BindHouse implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer id;                             //
	private String houseNo;                         //戶號
	private String address;                         //地址
	private Double area;                            //面積
	private String waterName;                    //水費類型
	private Double waterPrice;                    //水費類型
	private String waterUnit;                    //水費類型
	private String electicName;                   //电费收费标准
	private Double electicPrice;                   //电费收费标准
	private String electicUnit;                   //电费收费标准
	private String rubbishName;                   //生活垃圾费标准
	private Double rubbishPrice;                   //生活垃圾费标准
	private String rubbishUnit;                   //生活垃圾费标准
	private String propertyName;                  //物管收费标准
	private Double propertyPrice;                  //物管收费标准
	private String propertyUnit;                  //物管收费标准
	private String dueTime;                         //物業費到期時間
	private String owner;							//业主
	private Integer type;                           //類型	1 住宅 2 公寓 3 商铺
	private Integer state;                          //狀態 1 自住 2 出租 3 空置

}
