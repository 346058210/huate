/**   
* @Title: RepaortType.java 
* @Package com.wuguan.huate.bean.enums 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月17日 下午2:11:56 
* @version V1.0   
*/
package com.wuguan.huate.bean.enums;

/**
 * @ClassName: RepaortType
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author LiuYanHong
 * @date 2020年4月17日 下午2:11:56
 * 
 */
public class RepairsEnums {

	public enum RepaortTypeEnum {
		USER(1, "用户"), WORKER(2, "工作人员");

		private RepaortTypeEnum(Integer value, String name) {
			this.value = value;
			this.name = name;
		}

		private Integer value;
		private String name;

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

		public static RepaortTypeEnum getByValue(Integer value) {
			for (RepaortTypeEnum type : values()) {
				if (type.getValue() == value) {
					return type;
				}
			}
			return null;
		}
	}
	
	public enum State{
		UNHANDLE(0,"未处理"),HANDLE(1,"已处理"),INHANDLE(2,"处理中"),REVOCATION(3,"撤销");
		
		private Integer value;
		private String name;
	
		private State(Integer value, String name) {
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
		
		public static State getByValue(Integer value) {
			for (State type : values()) {
				if (type.getValue() == value) {
					return type;
				}
			}
			return null;
		}
	}

}
