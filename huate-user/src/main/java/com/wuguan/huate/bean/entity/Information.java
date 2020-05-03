/**   
* @Title: Information.java 
* @Package com.wuguan.huate.bean.entity 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月8日 下午11:05:05 
* @version V1.0   
*/
package com.wuguan.huate.bean.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/** 
* @ClassName: Information 
* @Description: 资讯
* @author LiuYanHong
* @date 2020年4月8日 下午11:05:05 
*  
*/
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Information implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;                        //
	private String title;                      //标题
	private String content;                    //内容
	private String createTime;                 //创建时间
	private String uploadPath;                 //上传路径
	private Integer isDel;                     //是否删除 1 是 0 否
}
