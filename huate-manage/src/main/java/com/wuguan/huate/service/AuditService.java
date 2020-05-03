/**   
* @Title: AuditService.java 
* @Package com.wuguan.huate.service 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月16日 下午10:19:34 
* @version V1.0   
*/
package com.wuguan.huate.service;

import com.wuguan.huate.bean.entity.Audit;

/**
 * @ClassName: AuditService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author LiuYanHong
 * @date 2020年4月16日 下午10:19:34
 * 
 */
public interface AuditService {

	Audit getAudit(Integer directType);

}
