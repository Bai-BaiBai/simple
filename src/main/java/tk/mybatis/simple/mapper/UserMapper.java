package tk.mybatis.simple.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import tk.mybatis.simple.model.SysRole;
import tk.mybatis.simple.model.SysUser;


public interface UserMapper {

	/***
	 * 通过id查询用户
	 * @param id
	 * @return
	 */
	SysUser selectById(Long id);
	
	/**
	 * 查询全部用户
	 * @return
	 */
	ArrayList<SysUser> selectAll();
	
	/**
	 * 根据用户id获取用户拥有的所有角色
	 * @param userId
	 * @return
	 */
	ArrayList<SysRole> selectRolesByUserId(Long userId);
	
	/**
	 * 增加用户
	 * @param sysUser
	 * @return
	 */
	int insert(SysUser sysUser);
	/**
	 * 使用jdbc方式设置useGeneratedKeys=true获取自增主键值
	 * 只适用于支持主键自增的数据库
	 * @param sysUser
	 * @return
	 */
	int insert2(SysUser sysUser);
	/**
	 * 使用selectKey返回主键的值
	 * 
	 * @param sysUser
	 * @return
	 */
	int insert3(SysUser sysUser);
	
	/**
	 * 根据主键更新
	 * @param sysUser
	 * @return
	 */
	int updateById(SysUser sysUser);
	
	/**
	 * 根据用户id和角色的enabled状态获取用户的角色
	 * @param userId
	 * @param enabled
	 * @return
	 */
	ArrayList<SysRole> selectRolesByUserIdAndRoleEnabled(@Param("userId")Long userId, @Param("enabled")Integer enabled);
	
	/**
	 * 根据userName和userInfo查询用户
	 * @param sysUser
	 * @return
	 */
	ArrayList<SysUser> selectByUser(SysUser sysUser);
	
	/**
	 * 根据主键更新，只更新改变的值
	 * @param sysUser
	 * @return
	 */
	int updateByIdSelective(SysUser sysUser);
	
	/**
	 * 根据用户名或id查询用户
	 * @param sysUser
	 * @return
	 */
	SysUser selectByIdOrUserName(SysUser sysUser);
	
	/**
	 * 根据用户id集合查询
	 * @param idList
	 * @return
	 */
	List<SysUser> selectByIdList(@Param("array")List<Long> idList); 
	
	/**
	 * 批量插入用户信息
	 * @param list
	 * @return
	 */
	int insertList(@Param("users")List<SysUser> list);
	
	/**
	 * 通过map更新列
	 * @param map
	 * @return
	 */
	int updateByMap(@Param("user")Map<String, Object> map);
	
	/**
	 * 根据用户id获取用户信息和用户的角色信息（一对一映射）
	 * @param id
	 * @return
	 */
	SysUser selectUserAndRoleById(@Param("id")Long id);
	SysUser selectUserAndRoleById2(@Param("id")Long id);
	SysUser selectUserAndRoleByIdSelect(@Param("id")Long id);
	
	/**
	 * 获取所有的用户对应角色信息
	 * @return
	 */
	List<SysUser> selectAllUserAndRoles();
	
	/**
	 * 通过嵌套查询获取指定用户的信息以及用户的角色和权限信息
	 * @param id
	 * @return
	 */
	SysUser selectAllUserAndRolesSelect(@Param("id")Long id);
	
	/**
	 * 使用存储过程查询用户信息
	 * @param user
	 */
	void selectUserById(SysUser user);
	
	/**
	 * 使用存储过程分页查询
	 * @param userName
	 * @param pageNum
	 * @param pageSize
	 * @param total
	 * @return
	 */
	List<SysUser> selectUserPage(Map<String, Object> params);
	
	/**
	 * 保存用户信息和角色关联信息
	 * @param user
	 * @param roleIds
	 * @return
	 */
	int insertUserAndRoles(@Param("user")SysUser user, @Param("roleIds")String roleIds);
	
	/**
	 * 根据用户id删除用户和用户的角色信息
	 * @param id
	 * @return
	 */
	int deleteUserById(Long id);
}
