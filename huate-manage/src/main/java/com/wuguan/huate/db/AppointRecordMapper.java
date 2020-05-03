/**   
* @Title: AppointRecordMapper.java 
* @Package com.wuguan.huate.db 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月9日 下午10:14:45 
* @version V1.0   
*/
package com.wuguan.huate.db;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.wuguan.huate.bean.entity.AppointRecord;
import com.wuguan.huate.bean.params.AppointRecordPageParam;
import com.wuguan.huate.bean.vo.AppointRecordVo;

/**
 * @ClassName: AppointRecordMapper
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author LiuYanHong
 * @date 2020年4月9日 下午10:14:45
 * 
 */
public interface AppointRecordMapper {


	/**
	 * @Title: detailData
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param id
	 * @return
	 */
	AppointRecordVo detailData(@Param("id") Integer id);

	/**
	 * @Title: pageData
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param param
	 * @param id
	 * @return
	 */
	Page<AppointRecordVo> pageData(@Param("param")AppointRecordPageParam param, @Param("workerId") Integer workerId);

	/**
	 * @Title: updateData
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param appointRecord
	 */
	void updateData(AppointRecord appointRecord);

	/**
	* @Title: queryDelayData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @return
	*/
	List<AppointRecordVo> queryDelayData();

}
