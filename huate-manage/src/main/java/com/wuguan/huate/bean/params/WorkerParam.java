/**   
* @Title: WorkerParam.java 
* @Package com.wuguan.huate.bean.params 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月15日 下午6:56:08 
* @version V1.0   
*/
package com.wuguan.huate.bean.params;

import com.wuguan.huate.bean.entity.Worker;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/** 
* @ClassName: WorkerParam 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年4月15日 下午6:56:08 
*  
*/
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WorkerParam extends Worker {

	private static final long serialVersionUID = 1L;
	private String code;                        //
	private String encryptedData;               //加密数据
	private String iv;                          //加密向量
}
