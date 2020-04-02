/**   
* @Title: ConusmeOrderDetail.java 
* @Package com.wuguan.huate.bean.params 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月22日 下午8:10:19 
* @version V1.0   
*/
package com.wuguan.huate.bean.params;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/** 
* @ClassName: ConusmeOrderDetail 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年3月22日 下午8:10:19 
*  
*/
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ConsumeOrderDetail implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer consumeId;
	private String houseNo;
	private Double dosage;
	private String month;
	private Integer money;
	private Integer type;

}
