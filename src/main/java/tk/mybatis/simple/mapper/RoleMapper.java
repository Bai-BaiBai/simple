package tk.mybatis.simple.mapper;


import java.util.ArrayList;

import org.apache.ibatis.annotations.CacheNamespaceRef;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import tk.mybatis.simple.model.SysRole;

@CacheNamespaceRef(RoleMapper.class)
public interface RoleMapper {

	/**
	 * 根据id查询角色
	 * @param id
	 * @return
	 */
	@Select({"select id, role_name roleName, enabled, create_time 'createInfo.createTime', create_by 'createInfo.createBy' "
			+ "from sys_role "
			+ "where id = #{id}"})
	SysRole selectById(Long id);

	@Select({"select * from sys_role"})
	ArrayList<SysRole> selectAll();
	
	/**
	 * 增加角色
	 * @param role
	 * @return
	 */
	@Insert({"insert into sys_role(role_name, enabled, create_by, create_time) "
			+ "values(#{roleName}, #{enabled}, #{createBy}, #{createTime, jdbcType=TIMESTAMP})"})
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int insert(SysRole role);
	@Insert({"insert into sys_role(role_name, enabled, create_by, create_time) "
			+ "values(#{roleName}, #{enabled}, #{createBy}, #{createTime, jdbcType=TIMESTAMP})"})
	@SelectKey(statement = "select last_insert_id()", 
				keyColumn="id", 
				keyProperty="id", 
				before=false, 
				resultType = Long.class)
	int insert2(SysRole role);
	
	int updateById(SysRole role);
}


