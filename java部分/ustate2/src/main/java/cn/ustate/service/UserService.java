package cn.ustate.service;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
import cn.ustate.entity.User;

/**
 * @author EricFu
 * @date 2017-7-14 下午8:07:17
 */
public interface UserService {
	/**
	 * 查询用户数量
	 * @return 用户数量
	 */
	int getCount();

	int login(User user);

	int weiboLogin(JSONObject userInfo, HttpSession session);
}
