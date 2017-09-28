package cn.ustate.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author EricFu
 * @date 2017-7-17 下午4:30:05
 */
@JsonInclude(Include.NON_NULL)
public class Notice {
		
	private int id;
	private int defProfId;
	private int profId;
	private int rootId;
	private int parentId;
	private String content;
	private String originContent;
	private Date createTime;
	private int status;
	private int action;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDefProfId() {
		return defProfId;
	}
	public void setDefProfId(int defProfId) {
		this.defProfId = defProfId;
	}
	public int getProfId() {
		return profId;
	}
	public void setProfId(int profId) {
		this.profId = profId;
	}
	public int getRootId() {
		return rootId;
	}
	public void setRootId(int rootId) {
		this.rootId = rootId;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getOriginContent() {
		return originContent;
	}
	public void setOriginContent(String originContent) {
		this.originContent = originContent;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getAction() {
		return action;
	}
	public void setAction(int action) {
		this.action = action;
	}
}
