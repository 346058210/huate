/**   
* @Title: HouseConsumeEnums.java 
* @Package com.wuguan.huate.bean.enums 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月18日 下午5:11:59 
* @version V1.0   
*/
package com.wuguan.huate.bean.enums;

/**
 * @ClassName: HouseConsumeEnums
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author LiuYanHong
 * @date 2020年3月18日 下午5:11:59
 * 
 */
public class HouseConsumeEnums {

	public enum FeeTypeEnum {
		WATER(3, "水"), ELECTIC(4, "电");

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
				if (value == type.getValue()) {
					return type;
				}
			}
			return null;
		}

		public static FeeTypeEnum getByName(String name) {
			for (FeeTypeEnum type : values()) {
				if (name.equals(type.getName())) {
					return type;
				}
			}
			return null;
		}

	}

	public enum PayEnum {
		YES(1, "是"), NO(2, "否");

		private Integer value;
		private String name;

		private PayEnum(Integer value, String name) {
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

		public static PayEnum getByValue(Integer value) {
			for (PayEnum pay : values()) {
				if (value == pay.getValue()) {
					return pay;
				}
			}
			return null;
		}

		public static PayEnum getByName(String name) {
			for (PayEnum pay : values()) {
				if (name.equals(pay.getName())) {
					return pay;
				}
			}
			return null;
		}
	}
}
