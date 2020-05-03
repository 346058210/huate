/**   
* @Title: Audit.java 
* @Package com.wuguan.huate.bean.entity 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月8日 下午10:55:22 
* @version V1.0   
*/
package com.wuguan.huate.bean.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/** 
* @ClassName: Audit 
* @Description: 审核人员 
* @author LiuYanHong
* @date 2020年4月8日 下午10:55:22 
*  
*/
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Audit implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;                          //
	private Integer workerId;                    //工作人员ID
	private String createTime;                   //创建时间
	private String path;                         //头型
	private String name;                         //姓名
	private String phone;                        //电话
	private Integer directType;                  //负责类型 1 投诉|建议 2 报损|报修 3 生活服务
	private Integer basicId;                     //基础信息ID
	private String company;                      //公司单位
	private String remark;                       //备注

}
