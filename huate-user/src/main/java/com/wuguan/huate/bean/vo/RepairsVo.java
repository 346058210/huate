/**   
* @Title: RepairsVo.java 
* @Package com.wuguan.huate.bean.vo 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月11日 下午5:59:13 
* @version V1.0   
*/
package com.wuguan.huate.bean.vo;

import java.util.List;

import com.wuguan.huate.bean.entity.Handle;
import com.wuguan.huate.bean.entity.Repairs;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/** 
* @ClassName: RepairsVo 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年4月11日 下午5:59:13 
*  
*/
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class RepairsVo extends Repairs{

	private static final long serialVersionUID = 1L;
	private String reportor;			//上报人
	private String reportPhone;			//上报人电话
	private List<Handle> handles;		//处理流程结果

}
