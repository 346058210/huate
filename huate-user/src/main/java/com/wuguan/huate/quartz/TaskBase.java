/**   
* @Title: TaskBase.java 
* @Package com.wuguan.huate.task 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月28日 上午12:50:57 
* @version V1.0   
*/
package com.wuguan.huate.quartz;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/** 
* @ClassName: TaskBase 
* @Description: 延时任务基类
* @author LiuYanHong
* @date 2020年4月28日 上午12:50:57 
*  
*/
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class TaskBase {

	private Integer id;
	private Integer type;//报修|预约
	
	public TaskBase(Integer id, Integer type) {
		super();
		this.id = id;
		this.type = type;
	}
	
	
}
