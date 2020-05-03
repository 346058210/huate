/**   
* @Title: WorkerService.java 
* @Package com.wuguan.huate.service 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月15日 下午6:48:23 
* @version V1.0   
*/
package com.wuguan.huate.service;
import com.wuguan.huate.bean.entity.Worker;
import com.wuguan.huate.bean.params.WorkerParam;

/**
 * @ClassName: WorkerService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author LiuYanHong
 * @date 2020年4月15日 下午6:48:23
 * 
 */
public interface WorkerService {

	/**
	 * @Title: getOpendId
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param worker
	 * @return
	 */
	String getOpendId(WorkerParam worker);

	/**
	 * @Title: isExist
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param openId
	 * @return
	 */
	Boolean isExist(String openId);

	/**
	 * @Title: getByOpenid
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param openId
	 * @return
	 */
	Worker getByOpenid(String openId);

	/**
	 * @Title: getByPhone
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param worker
	 * @return
	 */
	Worker getByPhone(String phone);

	/**
	 * @Title: updateData
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param worker
	 */
	void updateData(Worker worker);

	/**
	 * @Title: detailData
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param reportId
	 * @return
	 */
	Worker detailData(Integer id);

}
