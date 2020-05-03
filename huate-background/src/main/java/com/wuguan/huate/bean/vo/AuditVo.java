/**   
* @Title: AuditVo.java 
* @Package com.wuguan.huate.bean.vo 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月9日 下午11:21:25 
* @version V1.0   
*/
package com.wuguan.huate.bean.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/** 
* @ClassName: AuditVo 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年4月9日 下午11:21:25 
*  
*/
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AuditVo implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer workerId;
	private String name;                            //姓名
	private String phone;                           //電話
	private String address;                         //地址
	private String sex;                             //性別
	private String birthDate;                            //出生日期
	private String nickname;                        //暱稱
	private String createTime;                        //暱稱
}
