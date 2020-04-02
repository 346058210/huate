/**   
* @Title: FeeNormMapper.java 
* @Package com.wuguan.huate.db 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月19日 下午9:20:38 
* @version V1.0   
*/
package com.wuguan.huate.db;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.wuguan.huate.bean.entity.FeeNorm;
import com.wuguan.huate.bean.params.FeeNormPageParam;

/** 
* @ClassName: FeeNormMapper 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年3月19日 下午9:20:38 
*  
*/
public interface FeeNormMapper {
	void addData(FeeNorm norm);
	void updateData(FeeNorm norm);
	void del(@Param("id")Integer id);
	List<FeeNorm> getFeeNorms();
	Integer isExist(@Param("typeName")String typeName,@Param("id")Integer id);
	Integer isUse(@Param("id")Integer id);
	/**
	* @Title: detailData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param id
	* @return
	*/
	FeeNorm detailData(@Param("id")Integer id);
	/**
	* @Title: pageData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param param
	* @return
	*/
	Page<FeeNorm> pageData(FeeNormPageParam param);
}
