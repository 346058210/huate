/**   
* @Title: PropertyParam.java 
* @Package com.wuguan.huate.bean.params 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月29日 下午10:35:45 
* @version V1.0   
*/
package com.wuguan.huate.bean.params;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/** 
* @ClassName: PropertyParam 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年4月29日 下午10:35:45 
*  
*/
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PropertyParam {

	//private Integer type;//类型 1 住宅 2 公寓 3 商铺
	private Integer state;//状态 1 自住  2 出租 3 空置
	private String startTime;
	private String endTime;
}
