package cn.ustate.dao;

import cn.ustate.entity.Like;


/**
 * @author EricFu
 * @date 2017-7-16 下午9:50:43
 */
public interface LikeDao {

	int delete(Like like);

	boolean insert(Like like);

	int getCountByProfId(int profId);
}
