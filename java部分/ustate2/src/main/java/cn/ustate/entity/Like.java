package cn.ustate.entity;

import java.util.Date;

/**
 * @author EricFu
 * @date 2017-7-16 下午9:44:50
 */
public class Like {
	
	private int id;
	private int profId;
	private int parentId;
	private Date createTime;
	private int type;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProfId() {
		return profId;
	}
	public void setProfId(int profId) {
		this.profId = profId;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "Like [id=" + id + ", profId=" + profId + ", parentId=" + parentId
				+ ", createTime=" + createTime + ", type=" + type + "]";
	}
	
	
}
