/**   
* @Title: FeeNormEnums.java 
* @Package com.wuguan.huate.bean.enums 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月21日 下午6:04:03 
* @version V1.0   
*/
package com.wuguan.huate.bean.enums;

/** 
* @ClassName: FeeNormEnums 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年3月21日 下午6:04:03 
*  
*/
public class FeeNormEnums {
	
	public enum MtcEnum{
		INHERENT(1,"固定收费"),BYAMOUNT(2,"按量收费");
		
		private Integer value;
		private String name;
		private MtcEnum(Integer value, String name) {
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
		
		public static MtcEnum getByValue(Integer value) {
			for (MtcEnum mtc : values()) {
				if (value==mtc.getValue()) {
					return mtc;
				}
			}
			return null;
		}
	}
	
	public  enum FeeTypeEnum{
		PROPERTY(1,"物业费"),PARK(2,"车位费"),WATER(3,"水费"),ELECTRIC(4,"电费"),RUBBISH(5,"生活垃圾费"),PARKMANAGE(6,"车位管理费"),PARKRENT(7,"车位租赁费");
		
		private Integer value;
		private String name;
		private FeeTypeEnum(Integer value, String name) {
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
		
		public static FeeTypeEnum getByValue(Integer value) {
			for (FeeTypeEnum type : values()) {
				if (value==type.getValue()) {
					return type;
				}
			}
			return null;
		}
	}
	
	public enum PayStateEnum{
		YES(1, "成功"), NO(0, "失败");
		
		private Integer value;
		private String name;
		private PayStateEnum(Integer value, String name) {
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
		
		public static PayStateEnum getByValue(Integer value) {
			for (PayStateEnum type : values()) {
				if (value==type.getValue()) {
					return type;
				}
			}
			return null;
		}
	}

}
