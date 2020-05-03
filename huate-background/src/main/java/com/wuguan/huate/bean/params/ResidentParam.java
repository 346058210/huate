/**   
* @Title: ResidentParam.java 
* @Package com.wuguan.huate.bean.params 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月20日 下午3:30:41 
* @version V1.0   
*/
package com.wuguan.huate.bean.params;

import java.io.Serializable;

import com.excel.poi.annotation.ExportField;
import com.excel.poi.annotation.ImportField;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/** 
* @ClassName: ResidentParam 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年3月20日 下午3:30:41 
*  
*/
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ResidentParam implements Serializable {

	private static final long serialVersionUID = 1L;
	@ExportField(columnName = "ID")
	private Integer id;                             //
	@ExportField(columnName = "姓名")
	@ImportField(required = true)
	private String name;                            //姓名
	@ExportField(columnName = "出身年月")
	@ImportField()
	private String birthDate;                            //年齡
	@ExportField(columnName = "性別")
	@ImportField()
	private String sex;                             //性別
	@ExportField(columnName = "房号")
	@ImportField(required = true)
	private String houseNo;                         //戶號
	@ExportField(columnName = "电话")
	@ImportField()
	private String phone;                           //手機
	@ExportField(columnName = "入住时间")
	@ImportField()
	private String moveinTime;                      //入住時間
	@ExportField(columnName = "关系（业主|成员|租客）")
	@ImportField()
	private String relationName;                       //關係 1 戶主 2 成員 3 租戶
	@ExportField(columnName = "身份证号")
	@ImportField()
	private String idcard;                       //身份證號碼

}
