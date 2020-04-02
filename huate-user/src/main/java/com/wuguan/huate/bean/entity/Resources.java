/**   
* @Title: Resources.java 
* @Package com.wuguan.huate.bean.entity 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月15日 下午3:11:03 
* @version V1.0   
*/
package com.wuguan.huate.bean.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/** 
* @ClassName: Resources 
* @Description: 資源 
* @author LiuYanHong
* @date 2020年3月15日 下午3:11:03 
*  
*/
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Resources implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer id;                             //
	private String resName;                         //資源名稱
	private Integer resType;                        //資源類型 2功能|1菜單
	private String resPath;                         //資源路徑
	private Integer pid;                            //父ID
	private String resKey;                          //資源key
	private Integer isUse;                          //啟用禁用 1 啟用 0 禁用
	private Integer weight;                         //權重
	@Override
	public String toString() {
		return "Resources [id=" + id + ", resName=" + resName + ", resType=" + resType + ", resPath=" + resPath
				+ ", pid=" + pid + ", resKey=" + resKey + ", isUse=" + isUse + ", weight=" + weight + "]";
	}
}
