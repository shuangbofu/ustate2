package cn.ustate.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.ustate.entity.Comment;

/**
 * @author EricFu
 * @date 2017-7-14 下午8:34:14
 */
public interface CommentDao {
		
	/**
	 * 查询评论的数量
	 * @return 评论数量
	 */
	int getCount();
	List<Comment> list(@Param("profId") int profId, @Param("rootId") int rootId);
	boolean insert(Comment comment);
	int getProfId(int id);
	String getContent(int id);
	boolean updateLikeCount(@Param("value") int value, @Param("id") int id);
	int getRootId(int id);
//	int getParentId(int id);
//	int getCountByProfId(int profId);
	int delete(@Param("profId") int profId, @Param("id")int id);
}
