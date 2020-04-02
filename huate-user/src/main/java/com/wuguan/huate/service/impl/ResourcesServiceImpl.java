/**   
* @Title: ResourcesServiceImpl.java 
* @Package com.wuguan.huate.service.impl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月15日 下午10:24:13 
* @version V1.0   
*/
package com.wuguan.huate.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wuguan.huate.bean.entity.Resources;
import com.wuguan.huate.bean.vo.ResourcesTree;
import com.wuguan.huate.comm.CustomException;
import com.wuguan.huate.db.ResourcesMapper;
import com.wuguan.huate.service.ResourcesService;
import com.wuguan.huate.web.result.ResultEnums;

/** 
* @ClassName: ResourcesServiceImpl 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
 * @param <E>
* @date 2020年3月15日 下午10:24:13 
*  
*/
@Service
@Transactional
public class ResourcesServiceImpl implements ResourcesService {
	@Autowired
	ResourcesMapper resourcesMapper;
	@Override
	public void add(Resources res) {
		Boolean exist = isExist(res.getResName(), null);
		if (exist) {
			throw new CustomException(ResultEnums.BUSINESS.getCode(), "此资源名称已存在");
		}
		try {
			resourcesMapper.addData(res);
		} catch (Exception e) {
			throw new CustomException(ResultEnums.BUSINESS.getCode(), "新增失败");
		}
	}

	@Override
	public void update(Resources res) {
		Boolean exist = isExist(res.getResName(), res.getId());
		if (exist) {
			throw new CustomException(ResultEnums.BUSINESS.getCode(), "此资源名称已存在");
		}
		try {
			resourcesMapper.updateData(res);
		} catch (Exception e) {
			throw new CustomException(ResultEnums.BUSINESS.getCode(), "修改失败");
		}
		
	}

	@Override
	public void isUse(Resources res) {
		List<Resources> resources=resourcesMapper.recursionByPid(res.getId());
		resources.add(res);
		resourcesMapper.isUseBatch(resources);
	}

	@Override
	public Boolean isExist(String resName, Integer id) {
		Integer exist = resourcesMapper.isExist(resName,id);
		if (exist!=null) {
			return true;
		}
		return false;
	}
	
	/**
	 * 父查子
	 */
	@Override
	public List<Resources> getResourcesByFatherId(Integer pid) {
		return resourcesMapper.getResourcesByFatherId(pid);
	}
	
	/**
	 * 資源樹
	 */
	@Override
	public List<ResourcesTree> getResourcesTree() {
		List<ResourcesTree> array = new ArrayList<ResourcesTree>();
		List<Resources> list = getResourcesByFatherId(0);
		return recursion(array,list);
	}

	/**
	* @Title: recursion
	* @Description: 遞歸獲取所有資源
	* @param array
	* @param list
	 * @return 
	*/
	private List<ResourcesTree> recursion(List<ResourcesTree> array, List<Resources> list) {
		if (list.size()==0) {
			return array;
		}else {
			for (Resources resources : list) {
				ResourcesTree tree = new ResourcesTree();
				BeanUtils.copyProperties(resources, tree);
				List<Resources> res = getResourcesByFatherId(resources.getId());
				List<ResourcesTree> node = new ArrayList<ResourcesTree>();
				List<ResourcesTree> recursion = recursion(node, res);
				tree.setChildNode(recursion);
				array.add(tree);
			}
			return array;
		}	
	}

	@Override
	public List<Resources> getResourcesByWorkerId(Integer id) {
		return resourcesMapper.getResourcesByWorkerId(id);
	}

	@Override
	public List<Resources> resources() {
		return resourcesMapper.resources();
	}

	@Override
	public List<Resources> resourcesByRoleId(Integer roleId) {
		return resourcesMapper.resourcesByRoleId(roleId);
	}

}
