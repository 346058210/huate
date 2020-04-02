/**   
* @Title: HouseEnums.java 
* @Package com.wuguan.huate.bean.enums 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月17日 下午4:56:14 
* @version V1.0   
*/
package com.wuguan.huate.bean.enums;

/** 
* @ClassName: HouseEnums 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年3月17日 下午4:56:14 
*  
*/
public class HouseEnums {
	
	public enum TypeEnum{
		RESIDENCE(1,"住宅"),APARTMENT(2,"公寓"),BUSINESS(3,"商铺");
		private Integer value;
		private String name;
		private TypeEnum(Integer value, String name) {
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
		
		public static TypeEnum getByName(String name) {
			for (TypeEnum type : values()) {
				if (name.equals(type.getName())) {
					return type;
				}
			}
			return null;
		}
		
		public static TypeEnum getByValue(Integer value) {
			for (TypeEnum type : values()) {
				if (value==type.getValue()) {
					return type;
				}
			}
			return null;
		}
	}
	
	public enum StateEnums{
		SELFLIVE(1,"自住"),RENT(2,"出租"),VACANCY(3,"空置");
		
		private Integer value;
		private String name;
		
		private StateEnums(Integer value, String name) {
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
		
		public static StateEnums getByName(String name) {
			for (StateEnums state : values()) {
				if (name.equals(state.getName())) {
					return state;
				}
			}
			return null;
		}	
		
		public static StateEnums getByValue(Integer value) {
			for (StateEnums state : values()) {
				if (value==state.getValue()) {
					return state;
				}
			}
			return null;
		}	
	}
	
	public enum SaleEnum{
		YES(1,"是"),NO(0,"否");
		
		private Integer value;
		private String name;
		private SaleEnum(Integer value, String name) {
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
		
		public static SaleEnum getByValue(Integer value) {
			for (SaleEnum sale : values()) {
				if (value==sale.getValue()) {
					return sale;
				}
			}
			return null;
			
		}
		
		public static SaleEnum getByName(String name) {
			for (SaleEnum sale : values()) {
				if (name.equals(sale.getName())) {
					return sale;
				}
			}
			return null;
			
		}
	}
}
