/**   
* @Title: ComplainMapper.java 
* @Package com.wuguan.huate.db 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月10日 上午10:53:48 
* @version V1.0   
*/
package com.wuguan.huate.db;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.wuguan.huate.bean.entity.Complain;
import com.wuguan.huate.bean.params.ComplainPageParam;
import com.wuguan.huate.bean.vo.ComplainVo;

/**
 * @ClassName: ComplainMapper
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author LiuYanHong
 * @date 2020年4月10日 上午10:53:48
 * 
 */
public interface ComplainMapper {

	/**
	 * @Title: updateData
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param complain
	 */
	void updateData(Complain complain);

	/**
	 * @Title: detailData
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param id
	 * @return
	 */
	ComplainVo detailData(@Param("id") Integer id);

	/**
	 * @Title: pageData
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param param
	 * @return
	 */
	Page<ComplainVo> pageData(@Param("param")ComplainPageParam param, @Param("userId") Integer userId);

	/**
	* @Title: addData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param complain
	*/
	void addData(Complain complain);

}
