/**   
* @Title: Repairs.java 
* @Package com.wuguan.huate.bean.entity 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月8日 下午11:10:05 
* @version V1.0   
*/
package com.wuguan.huate.bean.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/** 
* @ClassName: Repairs 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年4月8日 下午11:10:05 
*  
*/
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Repairs implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer reportId;               //上报人ID
	private Integer reportType;             //上报类型 1 用户 2 工作人员
	private String uploadPath;              //上报图片路径
	private String content;              //报修内容
	private String appointmentTime;         //预约时间
	private Integer state;                  //处理状态 1 已处理 0 未处理 2 处理中 3 撤销
	private Integer handlerId;
	private String handler;                 //处理人
	private String handlePhone;             //处理人电话
	private String createTime;              //创建时间
	private String serveAddr;               //服务地址
	private String audit;               //审核人
	private String auditPhone;               //审核人电话
	private Integer auditId;               //审核人电话
}
