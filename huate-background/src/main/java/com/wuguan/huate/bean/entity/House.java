/**   
* @Title: House.java 
* @Package com.wuguan.huate.bean.entity 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月15日 下午8:30:52 
* @version V1.0   
*/
package com.wuguan.huate.bean.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/** 
* @ClassName: House 
* @Description: 房屋信息 
* @author LiuYanHong
* @date 2020年3月15日 下午8:30:52 
*  
*/
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class House implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;                             //
	private String houseNo;                         //戶號
	private Integer building;                       //樓棟
	private Integer unit;                           //單元
	private Integer room;                           //房號
	private String address;                         //地址
	private Double area;                            //面積
	private String remarkName;                      //備註名稱
	private Integer isDel;                          //是否刪除 1 是 0 否
	private Integer type;                           //類型	1 住宅 2 公寓 3 商铺
	private Integer state;                          //狀態 1 自住 2 出租 3 空置
	private Integer waterTypeId;                    //水費類型
	private String dueTime;                         //物業費到期時間
	private Integer sale;                            //是否出售 1 是 0 否
	private Integer electicTypeId;                   //电费收费标准
	private Integer rubbishTypeId;                   //生活垃圾费标准
	private Integer propertyTypeId;                  //物管收费标准
	private String parkNo;                          //绑定车牌，可以绑定多个以逗号分隔
	private String owner;							//业主
	private String noticeTime;							//通知时间
	private String rubbishDueTime;

}
