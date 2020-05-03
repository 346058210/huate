/**   
* @Title: AppointRecordPageParam.java 
* @Package com.wuguan.huate.bean.params 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月9日 下午10:25:42 
* @version V1.0   
*/
package com.wuguan.huate.bean.params;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/** 
* @ClassName: AppointRecordPageParam 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年4月9日 下午10:25:42 
*  
*/
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AppointRecordPageParam extends PageParams {
	private String state;                         //状态 1完成 0 未完成 2撤销
}
