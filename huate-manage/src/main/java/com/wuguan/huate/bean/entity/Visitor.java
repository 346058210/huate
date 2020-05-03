/**   
* @Title: Visitor.java 
* @Package com.wuguan.huate.bean.entity 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月8日 下午11:15:15 
* @version V1.0   
*/
package com.wuguan.huate.bean.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/** 
* @ClassName: Visitor 
* @Description: 访客记录 
* @author LiuYanHong
* @date 2020年4月8日 下午11:15:15 
*  
*/
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Visitor implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;                       //姓名
	private String phone;                      //电话
	private String houseNo;                   //访问房号
	private Integer applyUserId;               //申请人ID
	private String applyUserPhone;               //申请人ID
	private String applyUserName;               //申请人ID
	private String code;                       //验证码
	private String accessTime;                //到访时间
	private String createTime;                //创建时间
	private Integer num;                       //到访人数
	private String carNo;                     //到访车辆
	private String affirmTime;                //确认时间
}
