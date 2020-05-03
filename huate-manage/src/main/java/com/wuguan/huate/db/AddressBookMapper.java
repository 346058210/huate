/**   
* @Title: AddressBookMapper.java 
* @Package com.wuguan.huate.db 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月9日 下午4:55:40 
* @version V1.0   
*/
package com.wuguan.huate.db;
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
	 * @Title: pageData
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param params
	 * @return
	 */
	Page<AddressBook> pageData(AddressBookPageParam params);

}
