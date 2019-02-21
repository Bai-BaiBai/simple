package tk.mybatis.simple.model;

public class SysRoleExtend extends SysUser {

	private String userName;
	private String userInfo;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(String userInfo) {
		this.userInfo = userInfo;
	}
}
