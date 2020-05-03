/**   
* @Title: UserMapper.java 
* @Package com.wuguan.huate.db 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月16日 下午6:45:25 
* @version V1.0   
*/
package com.wuguan.huate.db;
import org.apache.ibatis.annotations.Param;
import com.wuguan.huate.bean.entity.User;

/** 
* @ClassName: UserMapper 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年3月16日 下午6:45:25 
*  
*/
public interface UserMapper {

	User detail(@Param("id")Integer id);
}
