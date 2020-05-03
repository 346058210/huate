/**   
* @Title: AuditMapper.java 
* @Package com.wuguan.huate.db 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月16日 下午10:22:08 
* @version V1.0   
*/
package com.wuguan.huate.db;

import org.apache.ibatis.annotations.Param;

import com.wuguan.huate.bean.entity.Audit;

/** 
* @ClassName: AuditMapper 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年4月16日 下午10:22:08 
*  
*/
public interface AuditMapper {

	/**
	* @Title: getAudit
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @return
	*/
	Audit getAudit(@Param("directType")Integer directType);

}
