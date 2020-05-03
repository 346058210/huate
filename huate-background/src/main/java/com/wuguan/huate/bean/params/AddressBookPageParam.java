/**   
* @Title: AddressBookPageParam.java 
* @Package com.wuguan.huate.bean.params 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月9日 下午5:01:54 
* @version V1.0   
*/
package com.wuguan.huate.bean.params;

import lombok.NoArgsConstructor;

import lombok.Data;
import lombok.EqualsAndHashCode;

/** 
* @ClassName: AddressBookPageParam 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年4月9日 下午5:01:54 
*  
*/
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AddressBookPageParam extends PageParams {
	
	private String content;					//名称|电话|地址 模糊查询
	private Integer isInner;                 //是否内部 1 是 0 否

}
