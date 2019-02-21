package tk.mybatis.simple.model;

import java.io.Serializable;
import java.util.Date;

public class CreateInfo implements Serializable{

	private static final long serialVersionUID = 222222L;
	
	private Date createTime;
	private Long createBy;
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Long getCreateBy() {
		return createBy;
	}
	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}
	
	
	
}
