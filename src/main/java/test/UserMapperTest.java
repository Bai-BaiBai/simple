package test;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.session.SqlSession;
import org.junit.*;

import tk.mybatis.simple.mapper.UserMapper;
import tk.mybatis.simple.model.SysPrivilege;
import tk.mybatis.simple.model.SysRole;
import tk.mybatis.simple.model.SysUser;


public class UserMapperTest extends BaseMapperTest{
	
	@Test
	public void testSelectAll() {
		SqlSession sqlSession = getSqlSession();
		
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			ArrayList<SysUser> users = userMapper.selectAll();
			printUserList(users);
		} finally {
			// TODO: handle finally clause
			sqlSession.close();
		}
	}
	
	@Test
	public void testSelectById() {
		SqlSession sqlSession = getSqlSession();
		
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			SysUser user = userMapper.selectById(new Long(1001));
			System.out.println(user.getUserName()+user.getUserInfo());
		} finally {
			// TODO: handle finally clause
			sqlSession.close();
		}
	}
	
	@Test
	public void testSelectRolesById() {
		SqlSession sqlSession = getSqlSession();
		
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		ArrayList<SysRole> roles = userMapper.selectRolesByUserId(new Long(1));
		for(SysRole r : roles) {
			System.out.println(r.getId() 
							+"----"+ r.getRoleName() 
							+"----"+ r.getSysUser().getUserName() 
							+"----"+ r.getSysUser().getUserInfo());
		}
	}
	
	@Test
	public void testInsert() {
		SqlSession sqlSession = getSqlSession();
		
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			SysUser user = new SysUser();
			user.setUserName("测试");
			user.setUserPassword("test");
			user.setUserInfo("测试1");
			user.setCreateTime(new Date());
			int result = userMapper.insert(user);
			
			Assert.assertEquals(result, 1);
			Assert.assertNull(user.getId());
		} finally {
			// TODO: handle finally clause
			sqlSession.rollback();
			sqlSession.close();
		}
	}
	
	@Test
	public void testInsert3() {
		SqlSession sqlSession = getSqlSession();
		
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			SysUser user = new SysUser();
			user.setUserName("测试");
			user.setUserPassword("");
			user.setUserInfo("test2");
			user.setCreateTime(new Date());
			int result = userMapper.insert3(user);
			
			Assert.assertEquals(result, 1);
			Assert.assertNotNull(user.getId());
			System.out.println(user.getId());
			
			SysUser u = userMapper.selectById(new Long(user.getId()));
			System.out.println(u.getUserName() + "---" + u.getUserPassword() + "---" + u.getUserInfo());
		} finally {
			// TODO: handle finally clause
			sqlSession.rollback();
			sqlSession.close();
		}
	}
	
	@Test
	public void testUpdate() {
		SqlSession sqlSession = getSqlSession();
		
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			SysUser user = userMapper.selectById(1L);
			Assert.assertEquals("admin", user.getUserName());
			user.setUserName("admin_test");
			int result = userMapper.updateById(user);
			Assert.assertEquals(1, result);
			user = userMapper.selectById(1L);
			Assert.assertEquals(user.getUserName(), "admin_test");
			System.out.println(UserMapper.class.getCanonicalName());
		} finally {
			// TODO: handle finally clause
			sqlSession.rollback();
			sqlSession.close();
		}
	}
	
	@Test
	public void testSelectRolesByUserIdAndRoleEnabled() {
		SqlSession sqlSession = getSqlSession();
		
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			ArrayList<SysRole> roles = userMapper.selectRolesByUserIdAndRoleEnabled(1L, 1);
			Assert.assertNotNull(roles);
			Assert.assertTrue(roles.size() > 0);
		} finally {
			// TODO: handle finally clause
			sqlSession.close();
		}
	}
	
	@Test
	public void testSelectByUser() {
		SqlSession sqlSession = getSqlSession();
		
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			SysUser query = new SysUser();
			//query.setUserName("admin");
			query.setUserInfo("1");
			ArrayList<SysUser> users = userMapper.selectByUser(query);
			Assert.assertNotNull(users);
			Assert.assertTrue(users.size() > 0);
			printUserList(users);
		} finally {
			// TODO: handle finally clause
			sqlSession.close();
		}
	}
	
	@Test
	public void testUpdateByIdSelective() {
		SqlSession sqlSession = getSqlSession();
		
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			SysUser query = new SysUser();
			query.setId(1001L);
			query.setUserInfo("测试用户1");
			query.setUserPassword("");
			int result = userMapper.updateByIdSelective(query);
			Assert.assertEquals(result, 1);
			SysUser user = userMapper.selectById(1001L);
			System.out.println(user.getUserName() + user.getUserInfo());
		} finally {
			// TODO: handle finally clause
			sqlSession.rollback();
			sqlSession.close();
		}
	}
	
	@Test
	public void testSelectByIdOrUserName() {
		SqlSession sqlSession = getSqlSession();
		
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		SysUser user = new SysUser();
		user.setId(null);
		user.setUserName(null);
		SysUser u = userMapper.selectByIdOrUserName(user);
		Assert.assertNull(u);
		//System.out.println(u.getUserPassword());
	}
	
	@Test
	public void testSelectByIdList() {
		SqlSession sqlSession = getSqlSession();
		
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			ArrayList<Long> ids = new ArrayList<Long>();
			ids.add(1L);
			ids.add(1001L);
			ArrayList<SysUser> users = (ArrayList<SysUser>) userMapper.selectByIdList(ids);
			printUserList(users);
		} finally {
			// TODO: handle finally clause
			sqlSession.close();
		}
	}
	
	@Test
	public void testInsertList() {
		SqlSession sqlSession = getSqlSession();
		
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			List<SysUser> userList = new ArrayList<SysUser>();
			for (int i = 0; i < 3; i++) {
				SysUser user = new SysUser();
				user.setUserName("insert" + i);
				user.setUserPassword("123");
				user.setUserInfo("testinsert");
				user.setCreateTime(new Date());
				userList.add(user);
			}
			int result = userMapper.insertList(userList);
			Assert.assertEquals(result, 3);
			
		} finally {
			// TODO: handle finally clause
			sqlSession.rollback();
			sqlSession.close();
		}
	}
	
	@Test
	public void testUpdateByMap() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", 1001L);
			map.put("user_name", "a");
			map.put("user_password", "12345");
			userMapper.updateByMap(map);
			SysUser user = userMapper.selectById(1001L);
			Assert.assertEquals(user.getUserName(), "a");
			Assert.assertEquals(user.getUserPassword(), "12345");
		} finally {
			// TODO: handle finally clause
			sqlSession.rollback();
			sqlSession.close();
		}
	}
	
	@Test
	public void testSelectUserAndRoleById() {
		SqlSession sqlSession = getSqlSession();
		
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			SysUser user = userMapper.selectUserAndRoleById2(1001L);
			Assert.assertNotNull(user);
			Assert.assertNotNull(user.getRole());
		} finally {
			// TODO: handle finally clause
			sqlSession.close();
		}
	}
	
	@Test
	public void testSelectUserAndRoleByIdSelect() {
		SqlSession sqlSession = getSqlSession();
		
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			SysUser user = userMapper.selectUserAndRoleByIdSelect(1001L);
			Assert.assertNotNull(user);
			user.equals(null);
			//Assert.assertNotNull(user.getRole());
		} finally {
			// TODO: handle finally clause
			sqlSession.close();
		}
	}
	
	@Test
	public void testSelectAllUserAndRoles() {
		SqlSession sqlSession = getSqlSession();
		
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			List<SysUser> list = userMapper.selectAllUserAndRoles();
			System.out.println("用户数： " + list.size());
			for (SysUser user : list) {
				System.out.println("用户名：" + user.getUserName());
				for (SysRole role : user.getRoleList()) {
					System.out.println("角色：" + role.getRoleName());
					for(SysPrivilege p : role.getPrivilegeList()) {
						System.out.println("权限：" + p.getPrivilegeName());
					}
				}
			} 
		} finally {
			// TODO: handle finally clause
			sqlSession.close();
		}
	}
	
	@Test
	public void testSelectAllUserAndRolesSelect() {
		SqlSession sqlSession = getSqlSession();
		
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			SysUser user = userMapper.selectAllUserAndRolesSelect(1L);
			System.out.println("用户名：" + user.getUserName());
			for (SysRole role : user.getRoleList()) {
				System.out.println("角色：" + role.getRoleName());
				if(!(role.getPrivilegeList() == null)) {
					for (SysPrivilege p : role.getPrivilegeList()) {
						System.out.println("权限：" + p.getPrivilegeName());
					}
				}
			} 
		} finally {
			// TODO: handle finally clause
			sqlSession.close();
		}
	}
	
	@Test
	public void testSelectUserById() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			SysUser user = new SysUser();
			user.setId(1L);
			userMapper.selectUserById(user);
			Assert.assertNotNull(user.getUserName());
		} finally {
			// TODO: handle finally clause
			sqlSession.close();
		}
	}
	
	@Test
	public void testSelectUserPage() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("userName", "ad");
			params.put("offset", 0);
			params.put("limit", 5);
			List<SysUser> userList = userMapper.selectUserPage(params);
			Long total = (Long)params.get("total");
			System.out.println("总数：" + total);
			for(SysUser user : userList) {
				System.out.println("用户名：" + user.getUserName());
				System.out.println("密码：" + user.getUserPassword());
			}
		} finally {
			// TODO: handle finally clause
			sqlSession.close();
		}
	}
	
	@Test
	public void testInsertAndDelete() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			SysUser user = new SysUser();
			user.setUserName("test1");
			user.setUserPassword("test1");
			user.setUserInfo("test");
			userMapper.insertUserAndRoles(user, "1,2");
			Assert.assertNotNull(user.getId());
			Assert.assertNotNull(user.getCreateTime());
			userMapper.deleteUserById(user.getId());
		} finally {
			// TODO: handle finally clause
			sqlSession.close();
		}
	}
	
	private void printUserList(ArrayList<SysUser> users) {
		// TODO Auto-generated method stub
		for(SysUser s : users) {
			System.out.println(s.getId()+s.getUserName()+s.getUserInfo());
		}
	}
}

