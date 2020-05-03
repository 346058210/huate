/**   
* @Title: ShopServiceImpl.java 
* @Package com.wuguan.huate.service.impl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月31日 上午1:02:21 
* @version V1.0   
*/
package com.wuguan.huate.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wuguan.huate.bean.entity.Shop;
import com.wuguan.huate.bean.params.PageParams;
import com.wuguan.huate.bean.params.ShopRe;
import com.wuguan.huate.bean.vo.HouseVo;
import com.wuguan.huate.bean.vo.ShopVo;
import com.wuguan.huate.comm.CustomException;
import com.wuguan.huate.comm.PageInfo;
import com.wuguan.huate.db.ShopMapper;
import com.wuguan.huate.service.HouseService;
import com.wuguan.huate.service.ShopService;
import com.wuguan.huate.web.result.ResultEnums;

/**
 * @ClassName: ShopServiceImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author LiuYanHong
 * @date 2020年3月31日 上午1:02:21
 * 
 */
@Service
public class ShopServiceImpl implements ShopService {
	@Autowired
	ShopMapper shopMapper;
	@Autowired
	HouseService houseService;

	@Override
	public List<Shop> getShopsByHouseId(Integer houseId) {
		return shopMapper.getShopsByHouseId(houseId);
	}

	@Override
	public void addBatch(List<Shop> shops) {
		shopMapper.addBatch(shops);
	}

	@Override
	public List<ShopVo> getListShop() {
		return shopMapper.getListShop();
	}

	@Override
	public List<Shop> queryAllStore(String shopNo) {
		return shopMapper.queryAllStore(shopNo);
	}

	@Override
	public void addStore(Shop shop) {
		Boolean exist = isExist(shop.getShopNo(), null);
		if (exist) {
			throw new CustomException(ResultEnums.BUSINESS.getCode(), "此店面号已存在");
		}
		shopMapper.addStore(shop);
	}

	@Override
	public void updateStore(Shop shop) {
		Boolean exist = isExist(shop.getShopNo(), shop.getId());
		if (exist) {
			throw new CustomException(ResultEnums.BUSINESS.getCode(), "此店面号已存在");
		}
		shopMapper.updateStore(shop);
		Shop store = shopMapper.detailStore(shop.getId());
		if (store.getHouseId()!=null) {
			List<Shop> list = shopMapper.getShopsByHouseId(store.getHouseId());
			HouseVo param = new HouseVo();
			StringBuilder builder = new StringBuilder();
			Double area = 0.0;
			for (Shop sp : list) {
				builder.append(sp.getShopNo());
				builder.append("|");
				area+=sp.getShopArea();
			}
			param.setId(store.getHouseId());
			param.setArea(area);
			param.setHouseNo(builder.toString().endsWith("|")?builder.toString().substring(0, builder.length()-1):builder.toString());
			houseService.updateData(param);
		}
	}

	@Override
	public void delStore(Integer id) {
		Shop shop = new Shop();
		shop.setId(id);
		shop.setIsDel(1);
		shopMapper.updateStore(shop);
		Shop store = shopMapper.detailStore(id);
		if (store.getHouseId()!=null) {
			List<Shop> list = shopMapper.getShopsByHouseId(store.getHouseId());
			HouseVo param = new HouseVo();
			StringBuilder builder = new StringBuilder();
			Double area = 0.0;
			for (Shop sp : list) {
				builder.append(sp.getShopNo());
				builder.append("|");
				area+=sp.getShopArea();
			}
			param.setId(store.getHouseId());
			param.setArea(area);
			param.setHouseNo(builder.toString().endsWith("|")?builder.toString().substring(0, builder.length()-1):builder.toString());
			houseService.updateData(param);
		}
	}

	@Override
	public Shop detailStore(Integer id) {
		return shopMapper.detailStore(id);
	}

	@Override
	public PageInfo<Shop> pageStore(PageParams params) {
		PageHelper.startPage(params.getPage(), params.getRows());
		Page<Shop> page=shopMapper.pageStore();
		return new PageInfo<Shop>(page.getTotal(), page);
	}

	@Override
	public Boolean isExist(String shopNo, Integer id) {
		Integer exist=shopMapper.isExist(shopNo,id);
		if (exist!=0) {
			return true;
		}
		return false;
	}

	@Override
	public void updateData(ShopRe re) {
		HouseVo house = new HouseVo();
		BeanUtils.copyProperties(re, house);
		houseService.updateData(house);
		if (re.getShopIds()!=null&&re.getShopIds().size()!=0) {
			shopMapper.updateBatch(re.getId(),re.getShopIds());
		}else {
			shopMapper.unBind(re.getId());
		}
		
	}

	@Override
	public Object detailData(Integer id) {
		HouseVo vo = houseService.detailData(id);
		//ShopRe re = new ShopRe();
		//BeanUtils.copyProperties(vo, re);
		//List<Integer> ids=shopMapper.queryIdsByHouseId(id);
		//re.setShopIds(ids);
		vo.setShops(getShopsByHouseId(id));
		return vo;
	}

	@Override
	public void delData(Integer id) {
		houseService.delData(id);
		shopMapper.unBind(id);
	}

	@Override
	public void addData(ShopRe re) {
		HouseVo house = new HouseVo();
		BeanUtils.copyProperties(re, house);
		houseService.addData(house);
		re.setId(house.getId());
		if (re.getShopIds()!=null&&re.getShopIds().size()!=0) {
			shopMapper.updateBatch(house.getId(),re.getShopIds());
		}
	}

}
