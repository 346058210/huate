/**   
* @Title: UserHouseRole.java 
* @Package com.wuguan.huate.bean.entity 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月15日 下午9:39:49 
* @version V1.0   
*/
package com.wuguan.huate.bean.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/** 
* @ClassName: UserHouseRole 
* @Description: 用戶房屋角色關聯 
* @author LiuYanHong
* @date 2020年3月15日 下午9:39:49 
*  
*/
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WorkerRole implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;                            //
	private Integer workerId;                        //工作人员ID
	private Integer roleId;                        //角色ID

}
