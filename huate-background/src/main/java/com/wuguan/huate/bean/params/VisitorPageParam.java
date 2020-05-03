/**   
* @Title: VisitorPageParam.java 
* @Package com.wuguan.huate.bean.params 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月11日 下午7:03:21 
* @version V1.0   
*/
package com.wuguan.huate.bean.params;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/** 
* @ClassName: VisitorPageParam 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年4月11日 下午7:03:21 
*  
*/
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class VisitorPageParam extends PageParams {
	private String content;			//访客姓名|访客电话|联系人|房号|联系电话|车牌
}
