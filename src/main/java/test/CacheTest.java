package test;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

import com.mysql.jdbc.StandardSocketFactory;

import tk.mybatis.simple.mapper.RoleMapper;
import tk.mybatis.simple.mapper.UserMapper;
import tk.mybatis.simple.model.SysRole;
import tk.mybatis.simple.model.SysUser;

public class CacheTest extends BaseMapperTest {

	@Test
	public void testL1Cache() {
		SqlSession sqlSession = getSqlSession();
		SysUser user1 = null;
		
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			//查询id=1的用户
			user1 = userMapper.selectById(1L);
			//对当前获取的对象重新赋值
			user1.setUserName("new name");
			//再次查询id相同的用户
			SysUser user2 = userMapper.selectById(1L);
			//虽然没有更新数据库，但是这个用户名和user1重新赋值的名字相同
			Assert.assertEquals("new name", user2.getUserName());
			//user2和user1完全就是同一个实例
			Assert.assertEquals(user1, user2);
		} finally {
			//关闭当前的sqlSession
			sqlSession.close();
		}
		
		System.out.println("开启新的SqlSession");
		//开始另外一个新的session
		sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			//查询id=1的用户
			SysUser user2 = userMapper.selectById(1L);
			//第二个session获取的用户名仍然是admin
			Assert.assertNotEquals("new name", user2.getUserName());
			//这里的user2和前一个session查询的结果是两个不同的实例
			Assert.assertNotEquals(user1, user2);
			//执行删除操作
			userMapper.deleteUserById(1002L);
			//获取user3
			SysUser user3 = userMapper.selectById(1L);
			//这里的user2和user3是两个不同的实例
			Assert.assertNotEquals(user2, user3);
		} finally {
			//关闭sqlSession
			sqlSession.close();
		}
	}
	
	@Test
	public void testL2Cache() {
		SqlSession sqlSession = getSqlSession();
		SysRole role1 = null;
		
		try {
			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
			//查询id=1的角色
			role1 = roleMapper.selectById(1L);
			//对当前获取的对象重新赋值
			role1.setRoleName("new name");
			//再次查询相同id的角色
			SysRole role2 = roleMapper.selectById(1L);
			//虽然没有更新数据库，但是这个角色名和role1重新赋值的名字相同
			Assert.assertEquals("new name", role2.getRoleName());
			//role1和role2是同一个实例
			Assert.assertEquals(role1, role2);
		} finally {
			// 关闭当前session
			sqlSession.close();
		}
		
		System.out.println("开启新的session");
		sqlSession = getSqlSession();
			
		try {
			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
			//查询id=1的角色
			SysRole role2 = roleMapper.selectById(1L);
			//第二个session获取的角色名仍是new name
			Assert.assertEquals("new name", role2.getRoleName());
			//这里role2和前一个session查询的结果是两个不同的实例
			Assert.assertNotEquals(role1, role2);
			//再次获取id=1的角色
			SysRole role3 = roleMapper.selectById(1L);
			//这里的role2和role3是两个不同的实例
			Assert.assertNotEquals(role2, role3);
		} finally {
			// 关闭session
			sqlSession.close();
		}
		
	}
	
	@Test
	public void testDirtyDate() {
		SqlSession sqlSession = getSqlSession();
		
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			SysUser user1 = userMapper.selectUserAndRoleById(1001L);
			//id为1001的用户角色名是普通用户
			Assert.assertEquals("普通用户", user1.getRole().getRoleName());
			System.out.println("角色名：" + user1.getRole().getRoleName());
		} finally {
			// TODO: handle finally clause
			sqlSession.close();
		}
		sqlSession = getSqlSession();
		try {
			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
			SysRole role = roleMapper.selectById(2L);
			Assert.assertEquals("普通用户", role.getRoleName());
			role.setRoleName("脏数据");
			roleMapper.updateById(role);
			//提交修改
			sqlSession.commit();
		} finally {
			// TODO: handle finally clause
			sqlSession.close();
		}
		
		System.out.println("开启新的Session");
		sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
			SysUser user = userMapper.selectUserAndRoleById(1001L);
			SysRole role = roleMapper.selectById(2L);
			System.out.println("角色名：" + user.getRole().getRoleName());
			Assert.assertEquals("脏数据", user.getRole().getRoleName());
			Assert.assertEquals("脏数据", role.getRoleName());
			//还原数据
			role.setRoleName("普通用户");
			roleMapper.updateById(role);
			sqlSession.commit();
		} finally {
			// TODO: handle finally clause
			sqlSession.close();
		}
	}
}
