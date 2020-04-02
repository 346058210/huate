/**   
* @Title: ParkM.java 
* @Package com.wuguan.huate.bean.vo 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月31日 上午4:26:40 
* @version V1.0   
*/
package com.wuguan.huate.bean.vo;

import com.wuguan.huate.bean.entity.Park;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/** 
* @ClassName: ParkM 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年3月31日 上午4:26:40 
*  
*/
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ParkM extends Park {

	private static final long serialVersionUID = 1L;
	private String openid;
}
