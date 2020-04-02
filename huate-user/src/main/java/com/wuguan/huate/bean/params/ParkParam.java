/**   
* @Title: ParkParam.java 
* @Package com.wuguan.huate.bean.params 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月20日 上午11:23:42 
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
* @ClassName: ParkParam 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年3月20日 上午11:23:42 
*  
*/
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ParkParam implements Serializable {

	private static final long serialVersionUID = 1L;
	@ExportField(columnName = "ID")
	private Integer id;
	@ExportField(columnName = "房号")
	@ImportField(required = true)
	private String houseNo;
	@ExportField(columnName = "车牌号")
	@ImportField()
	private String carNo;
	@ExportField(columnName = "车位号")
	@ImportField(required = true)
	private String parkNo;
	@ExportField(columnName = "客户1")
	@ImportField(required = true)
	private String ownerOne;
	@ExportField(columnName = "客户2")
	@ImportField()
	private String ownerTwo;
	@ExportField(columnName = "客户3")
	@ImportField()
	private String ownerThree;
	@ExportField(columnName = "电话1")
	@ImportField()
	private String phoneOne;
	@ExportField(columnName = "电话2")
	@ImportField()
	private String phoneTwo;
	@ExportField(columnName = "电话3")
	@ImportField()
	private String phoneThree;
	@ExportField(columnName = "类型")
	@ImportField(required = true)
	private String typeName;
	@ExportField(columnName = "到期时间")
	@ImportField(required = true)
	private String dueTime;
	@ExportField(columnName = "收费标准（根据收费标准表匹配）")
	@ImportField(required = true)
	private String norm;
	@ExportField(columnName = "面积")
	@ImportField(required = true)
	private Double area;
	@ExportField(columnName = "车位类型")
	@ImportField()
	private String genre;
}
