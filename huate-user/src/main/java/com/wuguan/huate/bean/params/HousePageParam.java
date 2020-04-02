/**   
* @Title: HousePageParam.java 
* @Package com.wuguan.huate.bean.params 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月24日 下午10:56:25 
* @version V1.0   
*/
package com.wuguan.huate.bean.params;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @ClassName: HousePageParam
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author LiuYanHong
 * @date 2020年3月24日 下午10:56:25
 * 
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class HousePageParam extends PageParams {

	private String content;
	private Integer type;
	private Integer state;
}
