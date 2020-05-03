/**   
* @Title: AuditMapper.java 
* @Package com.wuguan.huate.db 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月9日 下午11:10:56 
* @version V1.0   
*/
package com.wuguan.huate.db;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wuguan.huate.bean.entity.Audit;
import com.wuguan.huate.bean.params.AuditPageParam;
/**
 * @ClassName: AuditMapper
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author LiuYanHong
 * @date 2020年4月9日 下午11:10:56
 * 
 */
public interface AuditMapper {

	/**
	 * @Title: addData
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param audit
	 */
	void addData(Audit audit);

	/**
	 * @Title: updateData
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param audit
	 */
	void updateData(Audit audit);

	/**
	 * @Title: delData
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param id
	 */
	void delData(@Param("id") Integer id);

	/**
	 * @Title: detailData
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param id
	 * @return
	 */
	Audit detailData(@Param("id") Integer id);

	/**
	 * @Title: queryListData
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param params
	 * @return
	 */
	List<Audit> queryListData(AuditPageParam params);

	/**
	 * @Title: isExist
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param workerId
	 * @param id
	 * @return
	 */
	Integer isExist(@Param("workerId") Integer workerId, @Param("id") Integer id);

	/**
	 * @Title: isUse
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param id
	 * @return
	 */
	Integer isUse(@Param("id") Integer id);

	/**
	* @Title: getAudit
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param directType
	* @return
	*/
	Audit getAudit(@Param("directType")Integer directType);

}
