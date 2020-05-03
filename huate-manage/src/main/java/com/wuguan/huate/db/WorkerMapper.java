/**   
* @Title: WorkerMapper.java 
* @Package com.wuguan.huate.db 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月15日 下午7:06:02 
* @version V1.0   
*/
package com.wuguan.huate.db;

import org.apache.ibatis.annotations.Param;

import com.wuguan.huate.bean.entity.Worker;
/**
 * @ClassName: WorkerMapper
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author LiuYanHong
 * @date 2020年4月15日 下午7:06:02
 * 
 */
public interface WorkerMapper {

	/**
	 * @Title: isExist
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param openId
	 * @return
	 */
	Integer isExist(@Param("openId") String openId);

	/**
	 * @Title: getByOpenid
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param openId
	 * @return
	 */
	Worker getByOpenid(@Param("openId") String openId);

	/**
	 * @Title: getByPhone
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param worker
	 * @return
	 */
	Worker getByPhone(@Param("phone") String phone);

	/**
	 * @Title: updateData
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param worker
	 */
	void updateData(Worker worker);

	/**
	 * @Title: detailData
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param id
	 * @return
	 */
	Worker detailData(@Param("id") Integer id);

}
