/**   
* @Title: HouseParam.java 
* @Package com.wuguan.huate.bean.params 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月17日 下午4:53:10 
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
* @ClassName: HouseParam 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年3月17日 下午4:53:10 
*  
*/
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class HouseParam implements Serializable{

	private static final long serialVersionUID = 1L;
	@ExportField(columnName = "ID")
	private Integer id;
	
	@ExportField(columnName = "房号")
	@ImportField(required = true )
	private String houseNo;                         //戶號
	
	@ExportField(columnName = "业主（可以是多个英文逗号分割）")
	@ImportField(required = true )
	private String owner;                         //戶號
	
	@ExportField(columnName = "楼栋")
	@ImportField(required = true )
	private Integer building;                       //樓棟
	
	@ExportField(columnName = "单元")
	@ImportField(required = true )
	private Integer unit;                           //單元
	
	@ExportField(columnName = "房间")
	@ImportField(required = true )
	private Integer room;                           //房號
	
	@ExportField(columnName = "地址")
	@ImportField()
	private String address;                         //地址
	
	@ExportField(columnName = "面积")
	@ImportField(required = true )
	private Double area;                            //面積
	
	@ExportField(columnName = "类型（住宅|公寓|商鋪）")
	@ImportField(required = true )
	private String typeName;                           //類型 1 商業 2住宅
	
	@ExportField(columnName = "状态（自住|出租|空置）")
	@ImportField(required = true )
	private String stateName;                          //狀態 1 自住 2 出租 3 空置
	
	@ExportField(columnName = "是否出售（是|否）")
	@ImportField(required = true)
	private String sale;                            //是否出售 1 是 0 否
	
	@ExportField(columnName = "水费类型（根据收费标准表匹配）")
	@ImportField(required = true )
	private String waterType;                    //水費類型
	
	@ExportField(columnName = "电费类型（根据收费标准表匹配）")
	@ImportField(required = true)
	private String electicType;                   //电费收费标准
	
	@ExportField(columnName = "生活垃圾费类型（根据收费标准表匹配）")
	@ImportField(required = true)
	private String rubbishType;                   //生活垃圾费标准
	
	@ExportField(columnName = "物业管理费类型（根据收费标准表匹配）")
	@ImportField(required = true)
	private String propertyType;                  //物管收费标准
	
	@ExportField(columnName = "物业费到期时间")
	@ImportField()
	private String dueTime;
	//物業費到期時間
	@ExportField(columnName = "垃圾费到期时间")
	@ImportField()
	private String rubbishDueTime;
	
	@ExportField(columnName = "备注")
	@ImportField()
	private String remarkName;                      //備註名稱（备注）
	


}
