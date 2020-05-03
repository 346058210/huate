/**   
* @Title: ParkCountParam.java 
* @Package com.wuguan.huate.bean.params 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月29日 下午11:30:45 
* @version V1.0   
*/
package com.wuguan.huate.bean.params;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/** 
* @ClassName: ParkCountParam 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年4月29日 下午11:30:45 
*  
*/
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ParkCountParam {

	private Integer type;//1 租停 2 自购 3空置
	private String startTime;
	private String endTime;
}
