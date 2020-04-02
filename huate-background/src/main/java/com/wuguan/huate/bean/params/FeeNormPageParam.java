/**   
* @Title: FeeNormPageParam.java 
* @Package com.wuguan.huate.bean.params 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月26日 下午10:48:00 
* @version V1.0   
*/
package com.wuguan.huate.bean.params;

import lombok.NoArgsConstructor;

import lombok.Data;
import lombok.EqualsAndHashCode;

/** 
* @ClassName: FeeNormPageParam 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年3月26日 下午10:48:00 
*  
*/
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class FeeNormPageParam extends PageParams {

	private String typeName;
	private Integer pid;
	private Integer mtc;
}
