/**   
* @Title: AuditEnums.java 
* @Package com.wuguan.huate.bean.enums 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月16日 下午10:31:07 
* @version V1.0   
*/
package com.wuguan.huate.bean.enums;

/**
 * @ClassName: AuditEnums
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author LiuYanHong
 * @date 2020年4月16日 下午10:31:07
 * 
 */
public class AuditEnums {

	public enum DirectType {
		COMPLAIN(1, "投诉建议"), REPAIRS(2, "报损报修"), LIVESERVE(3, "生活服务");

		private Integer value;
		private String name;

		private DirectType(Integer value, String name) {
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

		public static DirectType getByValue(Integer value) {
			for (DirectType type : values()) {
				if (value == type.getValue()) {
					return type;
				}
			}
			return null;
		}
	}
}
