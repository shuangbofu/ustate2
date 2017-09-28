package cn.ustate.dto;

import cn.ustate.entity.Profile;

/**
 * @author EricFu
 * @date 2017-8-7 下午12:42:05
 */
public class ProfileDto extends Profile{
	int postsCount;
//	int commentPostsCount;
	int likePostsCount;
	int noticeTotalCount;
	public int getPostsCount() {
		return postsCount;
	}
	public void setPostsCount(int postsCount) {
		this.postsCount = postsCount;
	}
//	public int getCommentPostsCount() {
//		return commentPostsCount;
//	}
//	public void setCommentPostsCount(int commentPostsCount) {
//		this.commentPostsCount = commentPostsCount;
//	}
	public int getLikePostsCount() {
		return likePostsCount;
	}
	public void setLikePostsCount(int likePostsCount) {
		this.likePostsCount = likePostsCount;
	}
	public int getNoticeTotalCount() {
		return noticeTotalCount;
	}
	public void setNoticeTotalCount(int noticeTotalCount) {
		this.noticeTotalCount = noticeTotalCount;
	}
	

}
