package cn.ustate.entity;

import java.util.Date;

/**
 * @author EricFu
 * @date 2017-7-14 下午8:27:38
 */
public class Report {
	private int id;
	private int defId;
	private int profId;
	private String content;
	private Date createTime;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDefId() {
		return defId;
	}
	public void setDefId(int defId) {
		this.defId = defId;
	}
	public int getProfId() {
		return profId;
	}
	public void setProfId(int profId) {
		this.profId = profId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
