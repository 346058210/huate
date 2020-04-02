/**   
* @Title: FeeTypeNorm.java 
* @Package com.wuguan.huate.bean.vo 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月18日 下午9:08:05 
* @version V1.0   
*/
package com.wuguan.huate.bean.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/** 
* @ClassName: FeeTypeNorm 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年3月18日 下午9:08:05 
*  
*/
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class FeeTypeNorm implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer typeId;							//类型ID
	private String typeName;                        //類型名稱
	private Integer pid;                            //父ID
	private String remark;                          //備註
	private Integer type;                           //類型 1 商業 2 住宅
	private Integer min;                            //最小
	private Integer max;                            //最大
	private String unit;                            //單位
	private Double price;                           //單價
	private Integer normId;							//收费标砖ID
}
