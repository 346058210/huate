/**   
* @Title: FeeCountServiceImpl.java 
* @Package com.wuguan.huate.service.impl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月21日 下午4:00:39 
* @version V1.0   
*/
package com.wuguan.huate.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.wuguan.huate.bean.entity.FeeNorm;
import com.wuguan.huate.bean.entity.House;
import com.wuguan.huate.bean.entity.HouseConsume;
import com.wuguan.huate.bean.entity.Park;
import com.wuguan.huate.bean.enums.FeeNormEnums;
import com.wuguan.huate.bean.enums.ParkEnums;
import com.wuguan.huate.bean.params.ConsumeOrderDetail;
import com.wuguan.huate.bean.params.ParkOrderDetail;
import com.wuguan.huate.bean.params.PropertyOrderDetail;
import com.wuguan.huate.bean.vo.UserVo;
import com.wuguan.huate.comm.SpringUtil;
import com.wuguan.huate.comm.ThreadLocalManager;
import com.wuguan.huate.service.FeeCountService;
import com.wuguan.huate.service.FeeNormService;
import com.wuguan.huate.service.HouseConsumeService;
import com.wuguan.huate.service.HouseService;
import com.wuguan.huate.service.ParkService;
import com.wuguan.huate.utils.DateUtils;

/**
 * @ClassName: FeeCountServiceImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author LiuYanHong
 * @param <V>
 * @param <E>
 * @date 2020年3月21日 下午4:00:39
 * 
 */
@Service
public class FeeCountServiceImpl implements FeeCountService {
	@Autowired
	HouseService houseService;
	@Autowired
	FeeNormService feeNormService;
	@Autowired
	HouseConsumeService houseConsumeService;
	@Autowired
	ParkService parkService;

	/**
	 * rule：缴费标准 payable：应缴费用
	 */
	@Override
	public Map<String, Object> propertyFeeCount(String houseNo) {
		// 获取房屋信息
		House house = houseService.getHouseByHouseNo(houseNo);
		List<FeeNorm> norms = feeNormService.getFeeNorms();
		boolean bool = (DateUtils.getDaysBetween(house.getDueTime(), new Date()) > 0) ? true : false;
		List<PropertyOrderDetail> fee = new ArrayList<PropertyOrderDetail>();
		List<FeeNorm> rule = new ArrayList<FeeNorm>();
		if (bool) {// 存在欠费
			PropertyOrderDetail propertyDetail = new PropertyOrderDetail();
			PropertyOrderDetail liveDetail = new PropertyOrderDetail();
			for (FeeNorm norm : norms) {
				if (house.getPropertyTypeId() == norm.getId()) {
					rule.add(norm);
					Double money = (norm.getMtc() == FeeNormEnums.MtcEnum.INHERENT.getValue())
							? (norm.getPrice() * DateUtils.getMonthDiff(house.getDueTime(), new Date()))
							: (norm.getPrice() * DateUtils.getMonthDiff(house.getDueTime(), new Date())
									* house.getArea());
					propertyDetail.setHouseId(house.getId());
					propertyDetail.setName("应缴物业费");
					propertyDetail.setTimeFrame(
							DateUtils.getYearMonth(house.getDueTime()) + "-" + DateUtils.getYearMonth(new Date()));
					propertyDetail.setMoney(new Double(money * 100).intValue());
					propertyDetail.setHouseNo(house.getHouseNo());
				} else if (house.getRubbishTypeId() == norm.getId()) {
					rule.add(norm);
					Double money = (norm.getMtc() == FeeNormEnums.MtcEnum.INHERENT.getValue())
							? (norm.getPrice() * DateUtils.getMonthDiff(house.getDueTime(), new Date()))
							: (norm.getPrice() * DateUtils.getMonthDiff(house.getDueTime(), new Date())
									* house.getArea());
					liveDetail.setHouseId(house.getId());
					liveDetail.setName("应缴生活垃圾费");
					liveDetail.setTimeFrame(
							DateUtils.getYearMonth(house.getDueTime()) + "-" + DateUtils.getYearMonth(new Date()));
					liveDetail.setMoney(new Double(money * 100).intValue());
					liveDetail.setHouseNo(house.getHouseNo());
				}
			}
			fee.add(propertyDetail);
			fee.add(liveDetail);
		} else {
			for (FeeNorm norm : norms) {
				if (house.getPropertyTypeId() == norm.getId()) {
					rule.add(norm);
				} else if (house.getRubbishTypeId() == norm.getId()) {
					rule.add(norm);
				}
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rule", rule);// 缴费标准
		map.put("payable", fee);// 应缴费用
		map.put("area", house.getArea());// 面积
		map.put("dueTime", house.getDueTime());// 到期时间
		map.put("houseId", house.getId());// 到期时间
		map.put("owner", house.getOwner());// 到期时间
		map.put("type", house.getType());// 到期时间
		map.put("feeType", FeeNormEnums.FeeTypeEnum.PROPERTY.getValue());
		return map;
	}

	/**
	 * 水|电费计算
	 */
	@Override
	public List<ConsumeOrderDetail> waterElectricFeeCount(String houseNo, Integer type) {
		List<HouseConsume> list = houseConsumeService.getHouseConsumeByHouseNo(houseNo, type);
		if (list.size() != 0) {
			List<ConsumeOrderDetail> payable = new ArrayList<ConsumeOrderDetail>();
			for (HouseConsume consume : list) {
				ConsumeOrderDetail detail = new ConsumeOrderDetail();
				detail.setConsumeId(consume.getId());
				detail.setDosage(consume.getDosage());
				detail.setMonth(consume.getMonth());
				detail.setMoney(new Double(consume.getMoney() * 100).intValue());
				detail.setHouseNo(consume.getHouseNo());
				payable.add(detail);
			}
			return payable;
		}
		return null;
	}

	/**
	 * 
	 * @Title: parkFeeCount
	 * @Description: 租赁|购买停车费用查询
	 * @param houseNo
	 * @return
	 */
	@Override
	public Map<String, Object> parkFeeCount(String parkNo) {
		Park park = parkService.getParksByParkNo(parkNo);
		List<FeeNorm> norms = feeNormService.getFeeNorms();
		boolean bool = (DateUtils.getDaysBetween(park.getDueTime(), new Date()) > 0) ? true : false;
		List<FeeNorm> rule = new ArrayList<FeeNorm>();
		List<ParkOrderDetail> fee = new ArrayList<ParkOrderDetail>();
		if (bool) {// 欠费
			ParkOrderDetail detail = new ParkOrderDetail();
			for (FeeNorm norm : norms) {
				if (park.getNormId() == norm.getId()) {
					rule.add(norm);
					Double money = (norm.getMtc() == FeeNormEnums.MtcEnum.INHERENT.getValue())
							? (norm.getPrice() * DateUtils.getMonthDiff(park.getDueTime(), new Date()))
							: (norm.getPrice() * DateUtils.getMonthDiff(park.getDueTime(), new Date())
									* park.getArea());
					detail.setParkId(park.getId());
					detail.setName(park.getType() == ParkEnums.TypeEnum.RENT.getValue() ? "应缴车位租赁费" : "应缴车位管理费");
					detail.setParkNo(park.getParkNo());
					detail.setTimeFrame(
							DateUtils.getYearMonth(park.getDueTime()) + "-" + DateUtils.getYearMonth(new Date()));
					detail.setMoney(new Double(money * 100).intValue());
					detail.setCarkNo(park.getCarNo());
				}

			}
			fee.add(detail);
		} else {
			for (FeeNorm norm : norms) {
				if (park.getNormId() == norm.getId()) {
					rule.add(norm);
				}
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rule", rule);// 缴费标准
		map.put("payable", fee);// 应缴费用
		map.put("area", park.getArea());// 面积
		map.put("dueTime", park.getDueTime());// 到期时间
		map.put("parkId", park.getId());// 面积
		map.put("parkNo", park.getParkNo());// 到期时间
		map.put("carNo", park.getCarNo());// 到期时间
		map.put("type", park.getType());// 到期时间
		Integer feeType = (park.getType() == ParkEnums.TypeEnum.RENT.getValue())
				? FeeNormEnums.FeeTypeEnum.PARKRENT.getValue()
				: FeeNormEnums.FeeTypeEnum.PARKMANAGE.getValue();
		map.put("feeType", feeType);
		return map;
	}

	/**
	 * 
	 * @Title: buyParkFeeCount
	 * @Description: 购买停车费用查询
	 * @param houseNo
	 * @return
	 */
	@Override
	public List<Map<String, Object>> buyParkFeeCount(String houseNo) {
		List<Park> parks = parkService.getParksByHouseNo(houseNo);
		if (parks.size() != 0) {
			List<FeeNorm> norms = feeNormService.getFeeNorms();
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			for (Park park : parks) {
				List<FeeNorm> rule = new ArrayList<FeeNorm>();
				List<ParkOrderDetail> fee = new ArrayList<ParkOrderDetail>();
				boolean bool = (DateUtils.getDaysBetween(park.getDueTime(), new Date()) > 0) ? true : false;
				if (bool) {// 欠费
					ParkOrderDetail detail = new ParkOrderDetail();
					for (FeeNorm norm : norms) {
						if (park.getNormId() == norm.getId()) {
							rule.add(norm);
							Double money = (norm.getMtc() == FeeNormEnums.MtcEnum.INHERENT.getValue())
									? (norm.getPrice() * DateUtils.getMonthDiff(park.getDueTime(), new Date()))
									: (norm.getPrice() * DateUtils.getMonthDiff(park.getDueTime(), new Date())
											* park.getArea());
							detail.setParkId(park.getId());
							detail.setName(
									park.getType() == ParkEnums.TypeEnum.RENT.getValue() ? "应缴车位租赁费" : "应缴车位管理费");
							detail.setParkNo(park.getParkNo());
							detail.setTimeFrame(DateUtils.getYearMonth(park.getDueTime()) + "-"
									+ DateUtils.getYearMonth(new Date()));
							detail.setMoney(new Double(money * 100).intValue());
							detail.setCarkNo(park.getCarNo());
						}
					}
					fee.add(detail);
				} else {
					for (FeeNorm norm : norms) {
						if (park.getNormId() == norm.getId()) {
							rule.add(norm);
						}
					}
				}
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("rule", rule);// 缴费标准
				map.put("payable", fee);// 应缴费用
				map.put("area", park.getArea());// 面积
				map.put("dueTime", park.getDueTime());// 到期时间
				map.put("parkId", park.getId());// 面积
				map.put("parkNo", park.getParkNo());// 到期时间
				map.put("carNo", park.getCarNo());// 到期时间
				map.put("type", park.getType());// 到期时间
				Integer feeType = (park.getType() == ParkEnums.TypeEnum.RENT.getValue())
						? FeeNormEnums.FeeTypeEnum.PARKRENT.getValue()
						: FeeNormEnums.FeeTypeEnum.PARKMANAGE.getValue();
				map.put("feeType", feeType);
				list.add(map);
			}
			return list;
		}
		return null;
	}

	/**
	 * 待缴费信息
	 */
	@Override
	public Object waitPayFeeCount() {
		ThreadLocalManager bean = SpringUtil.getBean(ThreadLocalManager.class);
		UserVo vo = JSONObject.parseObject(bean.getUser().toString(), UserVo.class);
		// 查询所有绑定房屋
		List<House> houses = houseService.getListHouseByUserId(vo.getId());
		List<FeeNorm> norms = feeNormService.getFeeNorms();
		List<Object> array = new ArrayList<Object>();
		if (houses.size() != 0) {
			for (House house : houses) {
				boolean bool = (DateUtils.getDaysBetween(house.getDueTime(), new Date()) > 0) ? true : false;
				// 物业|生活垃圾费
				if (bool) {// 欠物业|生活垃圾费
					Double money = 0.0;
					for (FeeNorm norm : norms) {
						if (house.getPropertyTypeId() == norm.getId()) {
							money += (norm.getMtc() == FeeNormEnums.MtcEnum.INHERENT.getValue())
									? (norm.getPrice() * DateUtils.getMonthDiff(house.getDueTime(), new Date()))
									: (norm.getPrice() * DateUtils.getMonthDiff(house.getDueTime(), new Date())
											* house.getArea());
						} else if (house.getRubbishTypeId() == norm.getId()) {
							money += (norm.getMtc() == FeeNormEnums.MtcEnum.INHERENT.getValue())
									? (norm.getPrice() * DateUtils.getMonthDiff(house.getDueTime(), new Date()))
									: (norm.getPrice() * DateUtils.getMonthDiff(house.getDueTime(), new Date())
											* house.getArea());
						}
					}
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("name", "应缴物业费");
					map.put("money", new Double(money * 100).intValue());
					map.put("dueTime", house.getDueTime());
					map.put("houseId", house.getId());
					map.put("owner", house.getOwner());
					map.put("houseNo", house.getHouseNo());
					map.put("type", house.getType());// 到期时间
					map.put("timeFrame",
							DateUtils.getYearMonth(house.getDueTime()) + "-" + DateUtils.getYearMonth(new Date()));
					map.put("feeType", FeeNormEnums.FeeTypeEnum.PROPERTY.getValue());
					array.add(map);
				}
				// 水
				List<HouseConsume> list = houseConsumeService.getHouseConsumeByHouseNo(house.getHouseNo(),
						FeeNormEnums.FeeTypeEnum.WATER.getValue());
				if (list.size() != 0) {
					Double money = 0.0;
					for (HouseConsume consume : list) {
						money += consume.getMoney();
					}
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("houseId", house.getId());
					map.put("houseNo", house.getHouseNo());
					map.put("owner", house.getOwner());
					map.put("name", "应缴水费");
					map.put("money", new Double(money * 100).intValue());
					map.put("feeType", FeeNormEnums.FeeTypeEnum.WATER.getValue());
					map.put("type", house.getType());// 到期时间
					array.add(map);
				}
				// 电费
				List<HouseConsume> electrics = houseConsumeService.getHouseConsumeByHouseNo(house.getHouseNo(),
						FeeNormEnums.FeeTypeEnum.ELECTRIC.getValue());
				if (electrics.size() != 0) {
					Double money = 0.0;
					for (HouseConsume consume : electrics) {
						money += consume.getMoney();
					}
					// String prefix=
					// house.getType()==HouseEnums.TypeEnum.BUSINESS.getValue()?house.getOwner():house.getHouseNo();
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("houseId", house.getId());
					map.put("houseNo", house.getHouseNo());
					map.put("owner", house.getOwner());
					map.put("name", "应缴电费");
					map.put("money", new Double(money * 100).intValue());
					map.put("feeType", FeeNormEnums.FeeTypeEnum.ELECTRIC.getValue());
					map.put("type", house.getType());// 到期时间
					array.add(map);
				}
			}
		}
		// 所有绑定的车
		List<Park> parks = parkService.getListParksByUserId(vo.getId());
		if (parks.size() != 0) {
			for (Park park : parks) {
				boolean bool = (DateUtils.getDaysBetween(park.getDueTime(), new Date()) > 0) ? true : false;
				if (bool) {
					Double money = 0.0;
					for (FeeNorm norm : norms) {
						if (park.getNormId() == norm.getId()) {
							money += (norm.getMtc() == FeeNormEnums.MtcEnum.INHERENT.getValue())
									? (norm.getPrice() * DateUtils.getMonthDiff(park.getDueTime(), new Date()))
									: (norm.getPrice() * DateUtils.getMonthDiff(park.getDueTime(), new Date())
											* park.getArea());
						}
					}
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("parkId", park.getId());
					map.put("parkNo", park.getParkNo());
					map.put("carNo", park.getCarNo());
					map.put("area", park.getArea());
					map.put("type", park.getType());// 到期时间
					Integer feeType = (park.getType() == ParkEnums.TypeEnum.RENT.getValue())
							? FeeNormEnums.FeeTypeEnum.PARKRENT.getValue()
							: FeeNormEnums.FeeTypeEnum.PARKMANAGE.getValue();
					map.put("feeType", feeType);
					map.put("timeFrame",
							DateUtils.getYearMonth(park.getDueTime()) + "-" + DateUtils.getYearMonth(new Date()));
					map.put("money", new Double(money * 100).intValue());
					map.put("name", (park.getType() == ParkEnums.TypeEnum.RENT.getValue() ? "应缴车位租赁费" : "应缴车位管理费"));
					array.add(map);
				}
			}
		}
		return array;
	}

	@Override
	public double todayArrearage() {
		List<House> houses = houseService.getdueTimeHouses();
		if (houses.size() != 0) {
			List<FeeNorm> norms = feeNormService.getFeeNorms();
			Double money = 0.0;
			for (House house : houses) {
				for (FeeNorm norm : norms) {
					if (house.getPropertyTypeId() == norm.getId()) {
						money += (norm.getMtc() == FeeNormEnums.MtcEnum.INHERENT.getValue())
								? (norm.getPrice() * DateUtils.getMonthDiff(house.getDueTime(), new Date()))
								: (norm.getPrice() * DateUtils.getMonthDiff(house.getDueTime(), new Date())
										* house.getArea());
					}
				}
			}
			return money;
		}
		return 0.0;
	}

}
