/**   
* @Title: AddressBookService.java 
* @Package com.wuguan.huate.service 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月19日 下午12:50:18 
* @version V1.0   
*/
package com.wuguan.huate.service;

import com.wuguan.huate.bean.entity.AddressBook;
import com.wuguan.huate.bean.params.AddressBookPageParam;
import com.wuguan.huate.comm.PageInfo;

/** 
* @ClassName: AddressBookService 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年4月19日 下午12:50:18 
*  
*/
public interface AddressBookService {

	/**
	* @Title: pageData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param params
	* @return
	*/
	PageInfo<AddressBook> pageData(AddressBookPageParam params);

}
