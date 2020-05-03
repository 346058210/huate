/**   
* @Title: VisitorMapper.java 
* @Package com.wuguan.huate.db 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月15日 下午7:38:33 
* @version V1.0   
*/
package com.wuguan.huate.db;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.wuguan.huate.bean.entity.Visitor;
import com.wuguan.huate.bean.params.PageParams;

/**
 * @ClassName: VisitorMapper
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author LiuYanHong
 * @date 2020年4月15日 下午7:38:33
 * 
 */
public interface VisitorMapper {

	/**
	 * @Title: affirmVisitor
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param code
	 * @return
	 */
	Integer isExist(@Param("code") String code);

	/**
	 * @Title: addData
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param visitor
	 */
	void addData(Visitor visitor);

	/**
	 * @Title: queryByCode
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param code
	 * @return
	 */
	Visitor queryByCode(@Param("code") String code);

	/**
	 * @Title: affirmVisitor
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param format
	 */
	void affirmVisitor(@Param("affirmTime") String affirmTime, @Param("id") Integer id);

	/**
	* @Title: updateData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param visitor
	*/
	void updateData(Visitor visitor);

	/**
	* @Title: detailData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param id
	* @return
	*/
	Visitor detailData(@Param("id")Integer id);

	/**
	* @Title: pageData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param params
	* @return
	*/
	Page<Visitor> pageData(PageParams params);

}
