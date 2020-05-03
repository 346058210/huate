/**   
* @Title: Handle.java 
* @Package com.wuguan.huate.bean.entity 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月8日 下午11:01:05 
* @version V1.0   
*/
package com.wuguan.huate.bean.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/** 
* @ClassName: Handle 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年4月8日 下午11:01:05 
*  
*/
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Handle implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String handleResult;            //处理结果
	private String createTime;              //创建时间
	private Integer repairsId;              //报修ID
	private String handlePath;              //处理图片
	private Integer isDel;                  //是否删除 1 是 0 否
}
