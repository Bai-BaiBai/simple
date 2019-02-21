package test;

import java.util.ArrayList;
import java.util.Date;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

import tk.mybatis.simple.mapper.PrivilegeMapper;
import tk.mybatis.simple.mapper.RoleMapper;
import tk.mybatis.simple.model.SysPrivilege;
import tk.mybatis.simple.model.SysRole;
import tk.mybatis.simple.type.Enabled;

public class RoleMapperTest extends BaseMapperTest{

	@Test
	public void testSelectById() {
		SqlSession sqlSession = getSqlSession();
		
		try {
			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
			SysRole role = roleMapper.selectById(2L);
			Assert.assertNotNull(role);
		} finally {
			// TODO: handle finally clause
			sqlSession.close();
		}
	}
	
	@Test
	public void testSelectAll() {
		SqlSession sqlSession = getSqlSession();
		
		try {
			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
			ArrayList<SysRole> roles = roleMapper.selectAll();
			Assert.assertNotNull(roles);
			Assert.assertTrue(roles.size() > 1);
			Assert.assertNotNull(roles.get(0).getRoleName());
		} finally {
			// TODO: handle finally clause
			sqlSession.close();
		}
	}
	
	@Test
	public void testInsert() {
		SqlSession sqlSession = getSqlSession();
		
		try {
			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
			SysRole role = new SysRole();
			role.setRoleName("开发人员");
			role.setEnabled(Enabled.disabled);
			
			int result = roleMapper.insert2(role);
			Assert.assertEquals(result, 1);
			Assert.assertNotNull(role.getId());
			System.out.println(role.getId());
		} finally {
			// TODO: handle finally clause
			sqlSession.rollback();
			sqlSession.close();
		}
	}
	
	@Test
	public void testUpdateById() {
		SqlSession sqlSession = getSqlSession();
		
		try {
			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
			//先查询出角色
			SysRole role = roleMapper.selectById(2L);
			//数据库中这个角色的enabled字段值为1，对应启用状态
			Assert.assertEquals(Enabled.enabled, role.getEnabled());
			//修改角色的enabled值为disabled，禁用状态
			role.setEnabled(Enabled.disabled);
			//执行更新
			roleMapper.updateById(role);
		} finally {
			// TODO: handle finally clause
			sqlSession.rollback();
			sqlSession.close();
		}
	}
	
}
