/**   
* @Title: AddressBookServiceImpl.java 
* @Package com.wuguan.huate.service.impl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月19日 下午12:50:41 
* @version V1.0   
*/
package com.wuguan.huate.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wuguan.huate.bean.entity.AddressBook;
import com.wuguan.huate.bean.params.AddressBookPageParam;
import com.wuguan.huate.comm.PageInfo;
import com.wuguan.huate.db.AddressBookMapper;
import com.wuguan.huate.service.AddressBookService;

/**
 * @ClassName: AddressBookServiceImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author LiuYanHong
 * @date 2020年4月19日 下午12:50:41
 * 
 */
@Service
public class AddressBookServiceImpl implements AddressBookService {
	@Autowired
	AddressBookMapper addressBookMapper;

	@Override
	public PageInfo<AddressBook> pageData(AddressBookPageParam params) {
		PageHelper.startPage(params.getPage(), params.getRows());
		Page<AddressBook> page = addressBookMapper.pageData(params);
		return new PageInfo<AddressBook>(page.getTotal(), page);
	}

}
