/**   
* @Title: FeeNorm.java 
* @Package com.wuguan.huate.bean.entity 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月15日 下午3:00:35 
* @version V1.0   
*/
package com.wuguan.huate.bean.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/** 
* @ClassName: FeeNorm 
* @Description: 收費標準
* @author LiuYanHong
* @date 2020年3月15日 下午3:00:35 
*  
*/
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class FeeNorm implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer id;                             //
	private String typeName;                            //最小
	private Integer pid;                            //最大
	private Integer type;                            //类型 1 商业 2 住宅
	private Double price;                            //单价
	private String unit;                            //單位
	private Integer cycle;                            //生成周期
	private Integer weight;                           //权重
	private Integer mtc;                      //1固定收费；2:按量收费
	private String remark;                      //類型ID

}
