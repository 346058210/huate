/**   
* @Title: ResourcesTree.java 
* @Package com.wuguan.huate.bean.vo 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月16日 下午4:12:13 
* @version V1.0   
*/
package com.wuguan.huate.bean.vo;

import java.util.List;

import com.wuguan.huate.bean.entity.Resources;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/** 
* @ClassName: ResourcesTree 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年3月16日 下午4:12:13 
*  
*/
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ResourcesTree extends Resources {

	private static final long serialVersionUID = 1L;
	private List<ResourcesTree> childNode;
	
}
