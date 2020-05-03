/**   
* @Title: ComplainVo.java 
* @Package com.wuguan.huate.bean.vo 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月10日 上午11:14:48 
* @version V1.0   
*/
package com.wuguan.huate.bean.vo;

import com.wuguan.huate.bean.entity.Complain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/** 
* @ClassName: ComplainVo 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年4月10日 上午11:14:48 
*  
*/
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ComplainVo extends Complain {

	private static final long serialVersionUID = 1L;
	private String complain;				//投诉人
	private String complainPhone;			//投诉人电话

}
