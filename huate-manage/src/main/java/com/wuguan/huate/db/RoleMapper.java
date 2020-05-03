/**   
* @Title: RoleMapper.java 
* @Package com.wuguan.huate.db 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月17日 上午12:30:36 
* @version V1.0   
*/
package com.wuguan.huate.db;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wuguan.huate.bean.entity.Role;
import com.wuguan.huate.bean.entity.Worker;

/**
 * @ClassName: RoleMapper
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author LiuYanHong
 * @date 2020年4月17日 上午12:30:36
 * 
 */
public interface RoleMapper {

	List<Role> queryRoles();

	List<Worker> queryByRoleId(@Param("roleId") Integer roleId);

	/**
	* @Title: getRoleById
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param workerId
	* @return
	*/
	Role getRoleByworkerId(@Param("workerId")Integer workerId);

}
