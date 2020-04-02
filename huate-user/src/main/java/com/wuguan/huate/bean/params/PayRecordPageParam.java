/**   
* @Title: PayRecordPageParam.java 
* @Package com.wuguan.huate.bean.params 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月27日 上午12:10:59 
* @version V1.0   
*/
package com.wuguan.huate.bean.params;

import lombok.NoArgsConstructor;

import lombok.Data;
import lombok.EqualsAndHashCode;

/** 
* @ClassName: PayRecordPageParam 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年3月27日 上午12:10:59 
*  
*/
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PayRecordPageParam extends PageParams {
	private Integer id;
	private Integer type;
	private String houseNo;

}
