package cn.ustate.entity;

import java.util.Date;

/**
 * @author EricFu
 * @date 2017-7-14 下午8:20:03
 */
public class Comment {
	
	private int id;
	private String content;
	private Date createTime;
	private int parentId;
	private int rootId;
	private Profile profile;
	private int likeCount;
	private int liked;
	
	public int getLiked() {
		return liked;
	}
	public void setLiked(int liked) {
		this.liked = liked;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public int getRootId() {
		return rootId;
	}
	public void setRootId(int rootId) {
		this.rootId = rootId;
	}
	public Profile getProfile() {
		return profile;
	}
	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	public int getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
	@Override
	public String toString() {
		return "Comment [id=" + id + ", content=" + content + ", createTime="
				+ createTime + ", parentId=" + parentId + ", rootId=" + rootId
				+ ", profile=" + profile + ", likeCount=" + likeCount + ", chridenComments="
				+ "]";
	}
}
