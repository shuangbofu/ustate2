package cn.ustate.dao;

import cn.ustate.entity.User;

/**
 * @author EricFu
 * @date 2017-7-14 下午8:00:18
 */
public interface UserDao {
	/**
	 * 查询用户的数量
	 * @return 用户数量
	 */
	int getCount();

	User login(User user);

	boolean insert(User user);

	void updateTime(User user);

	User getUser(String string);
}
