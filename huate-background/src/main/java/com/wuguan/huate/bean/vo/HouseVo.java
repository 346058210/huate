/**   
* @Title: HouseVo.java 
* @Package com.wuguan.huate.bean.vo 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月24日 下午11:20:43 
* @version V1.0   
*/
package com.wuguan.huate.bean.vo;

import java.util.List;

import com.wuguan.huate.bean.entity.FeeNorm;
import com.wuguan.huate.bean.entity.House;
import com.wuguan.huate.bean.entity.Resident;
import com.wuguan.huate.bean.entity.Shop;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/** 
* @ClassName: HouseVo 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年3月24日 下午11:20:43 
*  
*/
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class HouseVo extends House {

	private static final long serialVersionUID = 1L;
	private List<Resident> residents;
	private Integer isBind;   //是否绑定 1是 0 否
	private Integer isArrears;   //是否欠费 1是 0 否
	private List<FeeNorm> norms;   //是否欠费 1是 0 否
	private List<Shop> shops;

}
