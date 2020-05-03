/**   
* @Title: ParkServiceImpl.java 
* @Package com.wuguan.huate.service.impl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月15日 下午10:23:16 
* @version V1.0   
*/
package com.wuguan.huate.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wuguan.huate.bean.entity.Park;
import com.wuguan.huate.db.ParkMapper;
import com.wuguan.huate.service.ParkService;

/**
 * @ClassName: ParkServiceImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author LiuYanHong
 * @date 2020年3月15日 下午10:23:16
 * 
 */
@Service
public class ParkServiceImpl implements ParkService {
	@Autowired
	ParkMapper parkMapper;

	/**
	 * 车位号查询车位信息
	 */
	@Override
	public Park getParksByParkNo(String parkNo) {
		return parkMapper.getParksByParkNo(parkNo);
	}


	/**
	 * 车牌查询车位
	 */
	@Override
	public Object queryParkByCarNo(String carNo) {
		return parkMapper.queryParkByCarNo(carNo);
	}


	@Override
	public Object detailData(Integer id) {
		return parkMapper.detailData(id);
	}


}
