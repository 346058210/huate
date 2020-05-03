/**   
* @Title: LiveServeMapper.java 
* @Package com.wuguan.huate.db 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月11日 下午2:46:46 
* @version V1.0   
*/
package com.wuguan.huate.db;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wuguan.huate.bean.entity.LiveServe;

/**
 * @ClassName: LiveServeMapper
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author LiuYanHong
 * @date 2020年4月11日 下午2:46:46
 * 
 */
public interface LiveServeMapper {

	/**
	* @Title: queryLiveServes
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @return
	*/
	List<LiveServe> queryLiveServes();

	/**
	* @Title: detailLiveServe
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param id
	* @return
	*/
	LiveServe detailLiveServe(@Param("id")Integer id);


}
