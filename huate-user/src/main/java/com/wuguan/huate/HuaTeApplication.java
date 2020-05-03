/**   
* @Title: HuaTeApplication.java 
* @Package com.wuguan.huate 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月14日 下午3:47:10 
* @version V1.0   
*/
package com.wuguan.huate;

import java.util.TimeZone;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName: HuaTeApplication
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author LiuYanHong
 * @date 2020年3月14日 下午3:47:10
 * 
 */
@SpringBootApplication(scanBasePackages = { "com.wuguan.huate" })
@MapperScan(basePackages = {"com.wuguan.huate.db"})
public class HuaTeApplication {
	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
		SpringApplication.run(HuaTeApplication.class, args);
	}

}
