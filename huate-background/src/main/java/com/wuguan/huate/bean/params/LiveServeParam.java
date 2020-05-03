/**   
* @Title: LiveServeParam.java 
* @Package com.wuguan.huate.bean.params 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月11日 下午2:55:38 
* @version V1.0   
*/
package com.wuguan.huate.bean.params;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @ClassName: LiveServeParam
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author LiuYanHong
 * @date 2020年4月11日 下午2:55:38
 * 
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class LiveServeParam extends PageParams {
	private String content; // 名称|电话
}
