/**   
* @Title: HouseConsumePageParam.java 
* @Package com.wuguan.huate.bean.params 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月26日 下午2:47:20 
* @version V1.0   
*/
package com.wuguan.huate.bean.params;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/** 
* @ClassName: HouseConsumePageParam 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年3月26日 下午2:47:20 
*  
*/
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class HouseConsumePageParam extends PageParams{
	private String content;              //关键字
	private String month;                //月份
	private Integer isPay;             //是否缴费1是 0 否
	private Integer type;                //类型 3水、4电

}
