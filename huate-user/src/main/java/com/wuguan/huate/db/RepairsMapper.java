/**   
* @Title: RepairsMapper.java 
* @Package com.wuguan.huate.db 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月11日 下午5:47:57 
* @version V1.0   
*/
package com.wuguan.huate.db;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.wuguan.huate.bean.entity.Repairs;
import com.wuguan.huate.bean.params.RepairsPageParam;

/**
 * @ClassName: RepairsMapper
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author LiuYanHong
 * @date 2020年4月11日 下午5:47:57
 * 
 */
public interface RepairsMapper {

	/**
	 * @Title: updateData
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param repairs
	 */
	void updateData(Repairs repairs);

	/**
	 * @Title: detailData
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param id
	 * @return
	 */
	Repairs detailData(@Param("id") Integer id);

	/**
	 * @Title: pageData
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param params
	 * @return
	 */
	Page<Repairs> pageData(@Param("params")RepairsPageParam params,@Param("userId")Integer userId);

	/**
	* @Title: addData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param repairs
	*/
	void addData(Repairs repairs);

	/**
	* @Title: queryDelayData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @return
	*/
	List<Repairs> queryDelayData();

}
