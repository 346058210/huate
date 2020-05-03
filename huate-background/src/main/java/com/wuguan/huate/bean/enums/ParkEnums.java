/**   
* @Title: ParkEnums.java 
* @Package com.wuguan.huate.bean.enums 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月20日 上午11:16:21 
* @version V1.0   
*/
package com.wuguan.huate.bean.enums;

/**
 * @ClassName: ParkEnums
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author LiuYanHong
 * @date 2020年3月20日 上午11:16:21
 * 
 */
public class ParkEnums {
	public enum TypeEnum {
		RENT(1, "租停"), BUY(2, "购买"),VACANCY(3, "空置");
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
				if (value == type.getValue()) {
					return type;
				}
			}
			return null;
		}
	}

	public enum GenreEnum {
		MINI(1, "微型"), STANDARD(2, "标准"), LUXURY(3, "豪华"), ZMMM(4, "子母（微型+微型）"), ZMMS(5, "子母（微型+标准）"),
		ZMSS(6, "子母（标准+标准）");
		private Integer value;
		private String name;

		private GenreEnum(Integer value, String name) {
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

		public static GenreEnum getByValue(Integer value) {
			for (GenreEnum genre : values()) {
				if (genre.getValue() == value) {
					return genre;
				}
			}
			return null;
		}

		public static GenreEnum getByName(String name) {
			for (GenreEnum genre : values()) {
				if (name.equals(genre.getName())) {
					return genre;
				}
			}
			return null;
		}

	}
}
