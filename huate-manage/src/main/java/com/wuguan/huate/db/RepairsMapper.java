/**   
* @Title: RepairsMapper.java 
* @Package com.wuguan.huate.db 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月17日 上午12:57:42 
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
 * @date 2020年4月17日 上午12:57:42
 * 
 */
public interface RepairsMapper {

	/**
	 * @Title: addData
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param repairs
	 */
	void addData(Repairs repairs);

	/**
	 * @Title: allotHandle
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param repairs
	 */
	void allotHandle(Repairs repairs);

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
	 * @param param
	 * @return
	 */
	Page<Repairs> pageData(@Param("param")RepairsPageParam param, @Param("workerId") Integer workerId);

	/**
	 * @Title: pageHandleData
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param param
	 * @return
	 */
	Page<Repairs> pageHandleData(RepairsPageParam param, @Param("workerId") Integer workerId);

	/**
	* @Title: queryDelayData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @return
	*/
	List<Repairs> queryDelayData();

}
