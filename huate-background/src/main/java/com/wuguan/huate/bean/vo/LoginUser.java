/**   
* @Title: LoginUser.java 
* @Package com.wuguan.huate.bean.vo 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月1日 下午5:56:56 
* @version V1.0   
*/
package com.wuguan.huate.bean.vo;

import java.util.List;

import com.wuguan.huate.bean.entity.Resources;
import com.wuguan.huate.bean.entity.Role;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/** 
* @ClassName: LoginUser 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年4月1日 下午5:56:56 
*  
*/
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class LoginUser {
	
	private Integer id;                             //
	private String name;                            //姓名
	private String phone;                           //電話
	private String address;                         //地址
	private Integer isDel;                          //是否刪除 1 是 2 否
	private Integer isUse;                          //启用禁用 1 启用 0 禁用
	private String createTime;                      //註冊時間
	private String updateTime;                      //修改時間
	private String sex;                             //性別
	private String birthDate;                        //出生年月
	private Integer job;
	private String token;                        //出生年月
	private List<Role> roles;
	private List<Resources> resources;
	
	public LoginUser(Integer id, String name, String phone, String address, Integer isDel, Integer isUse,
			String createTime, String updateTime, String sex, String birthDate, List<Role> roles,
			List<Resources> resources) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.isDel = isDel;
		this.isUse = isUse;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.sex = sex;
		this.birthDate = birthDate;
		this.roles = roles;
		this.resources = resources;
	}
	
	

}
