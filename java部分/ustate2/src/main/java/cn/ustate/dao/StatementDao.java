package cn.ustate.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.ustate.entity.Statement;

/**
 * @author EricFu
 * @date 2017-7-14 下午8:33:29
 */
public interface StatementDao {
	
	/**
	 * 查询发表的数量
	 * @return 发表数量
	 */
	int getCount();
	/**
	 * 查询fromId之后15个statement的集合 
	 * @param profId 登录的用户id
	 * @param fromId 开始查询的id
	 * @return
	 */
	List<Statement> list(@Param("profId") int profId, @Param("fromId")int fromId, @Param("listType") int listType);
	Statement getDetail(@Param("profId") int profId, @Param("id")int id);
	boolean insert(Statement statement);
	int delete(@Param("profId") int profId, @Param("id")int id);
	int getProfId(int id);
	String getContent(int id);
	boolean updateCommentCount(@Param("value") int value, @Param("id") int id);
	boolean updateLikeCount(@Param("value") int value, @Param("id") int id);
	int getCountByProfId(int profId);
}
