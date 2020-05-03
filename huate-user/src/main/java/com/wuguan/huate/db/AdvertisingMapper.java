/**   
* @Title: AdvertisingMapper.java 
* @Package com.wuguan.huate.db 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月9日 下午6:00:46 
* @version V1.0   
*/
package com.wuguan.huate.db;

import java.util.List;

import com.wuguan.huate.bean.entity.Advertising;

/**
 * @ClassName: AdvertisingMapper
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author LiuYanHong
 * @date 2020年4月9日 下午6:00:46
 * 
 */
public interface AdvertisingMapper {

	/**
	* @Title: queryAdvertisings
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @return
	*/
	List<Advertising> queryAdvertisings();

}
