package test;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import tk.mybatis.simple.mapper.UserMapper;
import tk.mybatis.simple.model.SysUser;

public class test {
	public static UserMapper userMapper;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ArrayList<SysUser> sysusers = userMapper.selectAll();
		for(SysUser user: sysusers) {
			System.out.println(user.getId()+user.getUserName()+user.getUserPassword());
		}
	}

}
