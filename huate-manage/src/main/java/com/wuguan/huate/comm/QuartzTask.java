/**   
* @Title: QuartzTask.java 
* @Package com.wuguan.huate.quartz 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月24日 上午1:22:07 
* @version V1.0   
*/
package com.wuguan.huate.comm;

import java.util.concurrent.DelayQueue;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

/** 
* @ClassName: QuartzTask 
* @Description: 定时任务
* @author LiuYanHong
* @date 2020年3月24日 上午1:22:07 
*  
*/
@Component
@EnableScheduling
public class QuartzTask extends BaseService{

}
