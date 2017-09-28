package cn.ustate.service;

import cn.ustate.dto.ApiDto;
import cn.ustate.entity.Like;

/**
 * @author EricFu
 * @date 2017-7-18 下午9:18:47
 */
public interface LikeService {

	ApiDto insertLikePost(Like like, int profId);

	ApiDto DeleteLikePost(Like like, int profId);
	
	ApiDto insertLikeComment(Like like, int profId);
	
	ApiDto DeleteLikeComment(Like like, int profId);
}
