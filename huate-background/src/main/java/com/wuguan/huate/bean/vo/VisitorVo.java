/**   
* @Title: VisitorVo.java 
* @Package com.wuguan.huate.bean.vo 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月15日 上午1:17:58 
* @version V1.0   
*/
package com.wuguan.huate.bean.vo;

import com.wuguan.huate.bean.entity.Visitor;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/** 
* @ClassName: VisitorVo 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年4月15日 上午1:17:58 
*  
*/
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class VisitorVo extends Visitor {

	private static final long serialVersionUID = 1L;
	private String nickname;                     //到访车辆
}
