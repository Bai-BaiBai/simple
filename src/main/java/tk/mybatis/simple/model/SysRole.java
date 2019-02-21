package tk.mybatis.simple.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import tk.mybatis.simple.type.Enabled;

public class SysRole implements Serializable{
	
	private static final long serialVersionUID = 111111L;

	private Long id;
	private String roleName;
//	private Long enabled;
	private Enabled enabled;
//	private Long createBy;
//	private Date createTime;
	private List<SysPrivilege> privilegeList;
	private CreateInfo createInfo;
	
	
	public Enabled getEnabled() {
		return enabled;
	}
	public void setEnabled(Enabled enabled) {
		this.enabled = enabled;
	}
//	public Long getEnabled() {
//		return enabled;
//	}
//	public void setEnabled(Long enabled) {
//		this.enabled = enabled;
//	}
	public CreateInfo getCreateInfo() {
		return createInfo;
	}
	public void setCreateInfo(CreateInfo createInfo) {
		this.createInfo = createInfo;
	}
	public List<SysPrivilege> getPrivilegeList() {
		return privilegeList;
	}
	public void setPrivilegeList(List<SysPrivilege> privilegeList) {
		this.privilegeList = privilegeList;
	}
	private SysUser sysUser;
	
	
	public SysUser getSysUser() {
		return sysUser;
	}
	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

//	public Long getCreateBy() {
//		return createBy;
//	}
//	public void setCreateBy(Long createBy) {
//		this.createBy = createBy;
//	}
//	public Date getCreateTime() {
//		return createTime;
//	}
//	public void setCreateTime(Date createTime) {
//		this.createTime = createTime;
//	}
	
	
}
