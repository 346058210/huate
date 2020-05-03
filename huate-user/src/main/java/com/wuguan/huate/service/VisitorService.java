/**   
* @Title: VisitorService.java 
* @Package com.wuguan.huate.service 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月11日 下午6:57:48 
* @version V1.0   
*/
package com.wuguan.huate.service;

import com.wuguan.huate.bean.entity.Visitor;
import com.wuguan.huate.bean.params.PageParams;
import com.wuguan.huate.bean.vo.VisitorVo;
import com.wuguan.huate.comm.PageInfo;

/**
 * @ClassName: VisitorService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author LiuYanHong
 * @date 2020年4月11日 下午6:57:48
 * 
 */
public interface VisitorService {

	/**
	 * @Title: detailData
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param id
	 * @return
	 */
	VisitorVo detailData(Integer id);

	/**
	 * @Title: pageData
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param params
	 * @return
	 */
	PageInfo<VisitorVo> pageData(PageParams params);

	/**
	* @Title: addData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param visitor
	*/
	void addData(Visitor visitor);

}
