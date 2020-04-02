/**   
* @Title: ResidentEnums.java 
* @Package com.wuguan.huate.bean.enums 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月20日 下午3:20:49 
* @version V1.0   
*/
package com.wuguan.huate.bean.enums;

/** 
* @ClassName: ResidentEnums 
* @Description: 居民信息
* @author LiuYanHong
* @date 2020年3月20日 下午3:20:49 
*  
*/
public class ResidentEnums {
	
	public enum RelationEnum{
		OWNER(1,"业主"),MEMBER(2,"成员"),RENTER(3,"租客");
		private Integer value;
		private String name;
		private RelationEnum(Integer value, String name) {
			this.value = value;
			this.name = name;
		}

		public Integer getValue() {
			return value;
		}

		public void setValue(Integer value) {
			this.value = value;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		
		public static RelationEnum getByValue(Integer value) {
			for (RelationEnum relation : values()) {
				if (value==relation.getValue()) {
					return relation;
				}
			}
			return null;
		}
		
		public static RelationEnum getByName(String name) {
			for (RelationEnum relation : values()) {
				if (name.equals(relation.getName())) {
					return relation;
				}
			}
			return null;
		}
	}

}
