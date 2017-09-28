package cn.ustate.entity;

import java.util.Date;

/**
 * @author EricFu
 * @date 2017-7-14 下午8:16:51
 */
public class Statement {
	
	private int id;
	private String content;
	private Date createTime;
	private Profile profile;
	private int sercret;
	private int likeCount;
	private int commentCount;
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
	public Profile getProfile() {
		return profile;
	}
	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	public int getSercret() {
		return sercret;
	}
	public void setSercret(int sercret) {
		this.sercret = sercret;
	}
	public int getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
	public int getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	@Override
	public String toString() {
		return "Statement [id=" + id + ", content=" + content + ", createtime="
				+ createTime + ", profile=" + profile + ", sercret=" + sercret
				+ ", likeCount=" + likeCount + ", commentCount=" + commentCount
				+ ", liked=" + liked + "]";
	}
}
