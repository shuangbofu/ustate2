package cn.ustate.dao;

import cn.ustate.entity.Profile;

/**
 * @author EricFu
 * @date 2017-7-16 下午7:42:43
 */
public interface ProfileDao {
	Profile getProfile(int id);

	boolean insert(Profile profile);

	void update(Profile profile);
}