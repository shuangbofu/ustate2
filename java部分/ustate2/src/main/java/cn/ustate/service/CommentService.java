package cn.ustate.service;

import cn.ustate.dto.ApiDto;
import cn.ustate.entity.Comment;

/**
 * @author EricFu
 * @date 2017-7-14 下午8:36:25
 */
public interface CommentService {
	
	/**
	 * 查询评论的数量
	 * @return 评论数量
	 */
	int getCount();
	ApiDto list(int profId, int rootId);
	ApiDto insert(int profId, Comment comment);
	ApiDto delete(int profId, Comment comment);
}
