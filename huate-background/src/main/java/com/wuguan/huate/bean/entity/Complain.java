/**   
* @Title: Complain.java 
* @Package com.wuguan.huate.bean.entity 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月8日 下午10:56:55 
* @version V1.0   
*/
package com.wuguan.huate.bean.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/** 
* @ClassName: Complain 
* @Description: 投诉建议
* @author LiuYanHong
* @date 2020年4月8日 下午10:56:55 
*  
*/
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Complain implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;                          //
	private String uploadPath;                  //上传路径
	private String desc;                        //描述
	private String createTime;                  //创建时间
	private Integer complainId;                  //投诉人ID
	private Integer state;                       //处理状态 1 已处理 0 未处理 2 处理中 3 撤销
	private String handlerId;
	private String handler;                     //处理人
	private String handlePhone;                 //处理人电话
	private String handleTime;                 //处理时间
	private String receipt;                     //回执
	private String handlePath;                  //处理图片
	private Integer isDel;                       //是否删除 1 是 0 否
	private String audit;               //审核人
	private String auditPhone;               //审核人电话
	private String auditId;               //审核人电话

}
