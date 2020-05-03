/**   
* @Title: Advertising.java 
* @Package com.wuguan.huate.bean.entity 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月8日 下午10:47:24 
* @version V1.0   
*/
package com.wuguan.huate.bean.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/** 
* @ClassName: Advertising 
* @Description: 广告 
* @author LiuYanHong
* @date 2020年4月8日 下午10:47:24 
*  
*/
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Advertising implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String title;
	private String path;
	private String createTime;
	private Integer isDel;				//是否删除 1 是 0 否
	private Integer isPub;				//是否发布 1 是 0 否
	private Integer sequence;            //发布顺序
	private String url;            //跳转链接
	private Integer urlType;            //跳转方式 1：内部；2:H5，3:其他小程序
	private String otherAppId;            //发布顺序

}
