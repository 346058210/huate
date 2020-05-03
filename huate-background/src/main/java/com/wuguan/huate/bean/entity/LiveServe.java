/**   
* @Title: LiveServe.java 
* @Package com.wuguan.huate.bean.entity 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月8日 下午11:08:01 
* @version V1.0   
*/
package com.wuguan.huate.bean.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/** 
* @ClassName: LiveServe 
* @Description: 生活服务 
* @author LiuYanHong
* @date 2020年4月8日 下午11:08:01 
*  
*/
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class LiveServe implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;               //名称
	private String desc;              //描述
	private String uploadPath;            //说明
	private String explain;            //说明
	private Integer isDel;              //是否删除 1 是 0 否
	private String audit;               //审核人
	private String auditPhone;               //审核人电话
	private Integer auditId;               //审核人电话
	private String serveTime;               //审核人电话
}
