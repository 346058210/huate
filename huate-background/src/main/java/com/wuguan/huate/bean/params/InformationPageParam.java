/**   
* @Title: InformationPageParam.java 
* @Package com.wuguan.huate.bean.params 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月10日 下午3:26:00 
* @version V1.0   
*/
package com.wuguan.huate.bean.params;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/** 
* @ClassName: InformationPageParam 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年4月10日 下午3:26:00 
*  
*/
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class InformationPageParam extends PageParams {
	private String title;                      //标题
}
