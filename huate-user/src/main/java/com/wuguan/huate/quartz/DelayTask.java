/**   
* @Title: DelayTask.java 
* @Package com.wuguan.huate.task 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月28日 上午1:00:01 
* @version V1.0   
*/
package com.wuguan.huate.quartz;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @ClassName: DelayTask
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author LiuYanHong
 * @date 2020年4月28日 上午1:00:01
 * 
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class DelayTask implements Delayed {

	private TaskBase base;
	private long expire;// 延时时间

	public DelayTask(TaskBase base, long expire) {
		super();
		this.base = base;
		this.expire = expire;
	}

	@Override
	public int compareTo(Delayed o) {
		long result = this.getDelay(TimeUnit.NANOSECONDS) - o.getDelay(TimeUnit.NANOSECONDS);
		if (result < 0) {
			return -1;
		} else if (result > 0) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public long getDelay(TimeUnit unit) {
		return unit.convert(expire - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
	}

}
