/**   
* @Title: AppointRecord.java 
* @Package com.wuguan.huate.bean.entity 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月8日 下午10:50:41 
* @version V1.0   
*/
package com.wuguan.huate.bean.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/** 
* @ClassName: AppointRecord 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年4月8日 下午10:50:41 
*  
*/
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AppointRecord implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String title;                          //标题
	private String uploadPath;                     //上传路径
	private Integer liveId;                        //生活服务ID
	private String content;                        //内容
	private String menu;                           //表单
	private String createTime;                     //创建时间
	private Integer state;                         //状态 1完成 0 未完成
	private String appoint;                      //预约人
	private String appointPhone;                   //预约电话
	private Integer appointId;                     //预约人ID
}
