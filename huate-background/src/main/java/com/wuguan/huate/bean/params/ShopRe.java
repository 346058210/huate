/**   
* @Title: ShopRe.java 
* @Package com.wuguan.huate.bean.params 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月5日 下午11:30:35 
* @version V1.0   
*/
package com.wuguan.huate.bean.params;

import java.util.List;

import com.wuguan.huate.bean.entity.House;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/** 
* @ClassName: ShopRe 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年4月5日 下午11:30:35 
*  
*/
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ShopRe extends House {

	private static final long serialVersionUID = 1L;
	private List<Integer> shopIds;

}
