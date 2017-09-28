package cn.ustate.dto;

import cn.ustate.entity.Like;

/**
 * @author EricFu
 * @date 2017-7-18 下午9:58:42
 */
public class LikeDto extends Like{
	private boolean liked;
	
	private int defProfId;
	
	private int rootId;

	public int getDefProfId() {
		return defProfId;
	}

	public void setDefProfId(int defProfId) {
		this.defProfId = defProfId;
	}

	public int getRootId() {
		return rootId;
	}

	public void setRootId(int rootId) {
		this.rootId = rootId;
	}

	public boolean isLiked() {
		return liked;
	}

	public void setLiked(boolean liked) {
		this.liked = liked;
	}

	@Override
	public String toString() {
		return "LikeDto [liked=" + liked + ", defProfId=" + defProfId
				+ ", rootId=" + rootId + "]";
	}
}
