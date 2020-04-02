/**   
* @Title: ShopParam.java 
* @Package com.wuguan.huate.bean.params 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月31日 下午7:55:50 
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
* @ClassName: ShopParam 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年3月31日 下午7:55:50 
*  
*/
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ShopParam implements Serializable {

	private static final long serialVersionUID = 1L;
	@ExportField(columnName = "ID")
	private Integer id;
	@ExportField(columnName = "商铺名称")
	@ImportField(required = true)
	private String shopName;
	@ExportField(columnName = "房号")
	@ImportField(required = true)
	private String shopNo;
	@ExportField(columnName = "地址")
	@ImportField(required = true )
	private String address;
	@ExportField(columnName = "面积")
	@ImportField(required = true)
	private Double shopArea;       
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
	@ExportField(columnName = "垃圾、物业费到期时间")
	@ImportField()
	private String dueTime;                         //物業費到期時間
	@ExportField(columnName = "备注")
	@ImportField()
	private String remarkName;                      //備註名稱（备注）
	
	@Override
	public String toString() {
		return "ShopParam [id=" + id + ", shopName=" + shopName + ", shopNo=" + shopNo + ", address=" + address
				+ ", shopArea=" + shopArea + ", sale=" + sale + ", waterType=" + waterType + ", electicType="
				+ electicType + ", rubbishType=" + rubbishType + ", propertyType=" + propertyType + ", dueTime="
				+ dueTime + ", remarkName=" + remarkName + "]";
	}
	
	
	
}
