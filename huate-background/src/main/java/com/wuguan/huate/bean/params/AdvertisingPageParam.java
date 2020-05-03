/**   
* @Title: AdvertisingPageParam.java 
* @Package com.wuguan.huate.bean.params 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月9日 下午7:49:47 
* @version V1.0   
*/
package com.wuguan.huate.bean.params;

import lombok.NoArgsConstructor;

import lombok.Data;
import lombok.EqualsAndHashCode;

/** 
* @ClassName: AdvertisingPageParam 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年4月9日 下午7:49:47 
*  
*/
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AdvertisingPageParam extends PageParams {
	private String title;
	private Integer isPub;				//是否发布 1 是 0 否

}
