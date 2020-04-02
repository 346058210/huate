/**   
* @Title: UserVo.java 
* @Package com.wuguan.huate.bean.vo 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月27日 下午10:07:15 
* @version V1.0   
*/
package com.wuguan.huate.bean.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/** 
* @ClassName: UserVo 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年3月27日 下午10:07:15 
*  
*/
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UserVo implements Serializable{

	private static final long serialVersionUID = 1L;
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
	private String nickname;                        //暱稱
	private String openid;                         //微信upenId
	
}
