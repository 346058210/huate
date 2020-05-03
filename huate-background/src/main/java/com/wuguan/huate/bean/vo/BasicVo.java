/**   
* @Title: BasicVo.java 
* @Package com.wuguan.huate.bean.vo 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月15日 上午2:29:41 
* @version V1.0   
*/
package com.wuguan.huate.bean.vo;

import java.util.List;

import com.wuguan.huate.bean.entity.Audit;
import com.wuguan.huate.bean.entity.Basic;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/** 
* @ClassName: BasicVo 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年4月15日 上午2:29:41 
*  
*/
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class BasicVo extends Basic {

	private static final long serialVersionUID = 1L;
	private List<Audit> Audits;

}
