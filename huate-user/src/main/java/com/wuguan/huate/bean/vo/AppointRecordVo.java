/**   
* @Title: AppointRecordVo.java 
* @Package com.wuguan.huate.bean.vo 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月13日 下午8:09:30 
* @version V1.0   
*/
package com.wuguan.huate.bean.vo;

import com.wuguan.huate.bean.entity.AppointRecord;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/** 
* @ClassName: AppointRecordVo 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年4月13日 下午8:09:30 
*  
*/
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AppointRecordVo extends AppointRecord {

	private static final long serialVersionUID = 1L;
	private String liveName;                        //生活服务名称
	private String audit;
	private Integer auditId;
	private String auditPhone;

}
