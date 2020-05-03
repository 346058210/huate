/**   
* @Title: AddressBookServiceImpl.java 
* @Package com.wuguan.huate.service.impl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月9日 下午4:55:10 
* @version V1.0   
*/
package com.wuguan.huate.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wuguan.huate.bean.entity.AddressBook;
import com.wuguan.huate.bean.params.AddressBookPageParam;
import com.wuguan.huate.comm.CustomException;
import com.wuguan.huate.comm.PageInfo;
import com.wuguan.huate.db.AddressBookMapper;
import com.wuguan.huate.service.AddressBookService;
import com.wuguan.huate.web.result.ResultEnums;

/** 
* @ClassName: AddressBookServiceImpl 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年4月9日 下午4:55:10 
*  
*/
@Service
@Transactional
public class AddressBookServiceImpl implements AddressBookService {
	@Autowired
	AddressBookMapper addressBookMapper;
	
	@Override
	public void addData(AddressBook book) {
		Boolean exist = isExist(book.getName(), null);
		if (exist) {
			throw new CustomException(ResultEnums.BUSINESS.getCode(), "此名称已存在");
		}
		addressBookMapper.addData(book);
	}

	@Override
	public void updateData(AddressBook book) {
		Boolean exist = isExist(book.getName(), book.getId());
		if (exist) {
			throw new CustomException(ResultEnums.BUSINESS.getCode(), "此名称已存在");
		}
		addressBookMapper.updateData(book);
	}

	@Override
	public void delData(Integer id) {
		addressBookMapper.delData(id);
	}

	@Override
	public AddressBook detailData(Integer id) {
		return addressBookMapper.detailData(id);
	}

	@Override
	public PageInfo<AddressBook> pageData(AddressBookPageParam params) {
		PageHelper.startPage(params.getPage(), params.getRows());
		Page<AddressBook> page=addressBookMapper.pageData(params);
		return new PageInfo<AddressBook>(page.getTotal(), page);
	}

	@Override
	public Boolean isExist(String name, Integer id) {
		Integer exist=addressBookMapper.isExist(name,id);
		if (exist!=0) {
			return true;
		}
		return false;
	}

}
