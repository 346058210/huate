/**   
* @Title: AddressBookMapper.java 
* @Package com.wuguan.huate.db 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月9日 下午4:55:40 
* @version V1.0   
*/
package com.wuguan.huate.db;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.wuguan.huate.bean.entity.AddressBook;
import com.wuguan.huate.bean.params.AddressBookPageParam;

/**
 * @ClassName: AddressBookMapper
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author LiuYanHong
 * @date 2020年4月9日 下午4:55:40
 * 
 */
public interface AddressBookMapper {

	/**
	 * @Title: addData
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param book
	 */
	void addData(AddressBook book);

	/**
	 * @Title: updateData
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param book
	 */
	void updateData(AddressBook book);

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
	AddressBook detailData(@Param("id") Integer id);

	/**
	 * @Title: pageData
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param params
	 * @return
	 */
	Page<AddressBook> pageData(AddressBookPageParam params);

	/**
	 * @Title: isExist
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param name
	 * @param id
	 * @return
	 */
	Integer isExist(@Param("name") String name, @Param("id") Integer id);

}
