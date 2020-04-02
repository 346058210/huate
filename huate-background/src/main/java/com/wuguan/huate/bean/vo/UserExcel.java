/**   
* @Title: UserExcel.java 
* @Package com.wuguan.huate.bean.vo 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月27日 上午1:22:42 
* @version V1.0   
*/
package com.wuguan.huate.bean.vo;

import java.io.Serializable;

import com.excel.poi.annotation.ExportField;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/** 
* @ClassName: UserExcel 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年3月27日 上午1:22:42 
*  
*/
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UserExcel implements Serializable {

	private static final long serialVersionUID = 1L;
	@ExportField(columnName = "ID")
	private Integer id;
	@ExportField(columnName = "姓名")
	private String name;                            //姓名
	@ExportField(columnName = "电话")
	private String phone;                           //電話
	@ExportField(columnName = "地址")
	private String address;                         //地址
	@ExportField(columnName = "启用|禁用")
	private String isUse;                          //启用禁用 1 启用 0 禁用
	@ExportField(columnName = "注册时间")
	private String createTime;                      //註冊時間
	@ExportField(columnName = "修改时间")
	private String updateTime;                      //修改時間
	@ExportField(columnName = "性别")
	private String sex;                             //性別
	@ExportField(columnName = "出生年月")
	private String birthDate;                        //出生年月
	@ExportField(columnName = "昵称")
	private String nickname;                        //暱稱

}
