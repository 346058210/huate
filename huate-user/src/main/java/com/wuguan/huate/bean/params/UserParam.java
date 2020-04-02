/**   
* @Title: UserParam.java 
* @Package com.wuguan.huate.bean.params 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月17日 下午3:54:18 
* @version V1.0   
*/
package com.wuguan.huate.bean.params;

import com.wuguan.huate.bean.entity.User;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/** 
* @ClassName: UserParam 
* @Description: 用戶註冊、登錄信息
* @author LiuYanHong
* @date 2020年3月17日 下午3:54:18 
*  
*/
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UserParam extends User {

	private static final long serialVersionUID = 1L;
	private String code;
}
