package cn.ustate.service.impl;

import java.util.Date;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ustate.dao.ProfileDao;
import cn.ustate.dao.UserDao;
import cn.ustate.entity.Profile;
import cn.ustate.entity.User;
import cn.ustate.expection.ServerException;
import cn.ustate.service.UserService;

/**
 * @author EricFu
 * @date 2017-7-14 下午8:08:33
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ProfileDao profileDao;
	
	@Override
	public int getCount() {
		return userDao.getCount();
	}

	@Override
	public int login(User user) throws RuntimeException {
		if (userDao.login(user) != null) {
			User u = new User();
			try {
				u = userDao.login(user);
			} catch (Exception e) {
				throw new ServerException(e);
			}
			return u.getProfId();
		} else {
			return 0;
		}
	}

	@Override
	public int weiboLogin(JSONObject userInfo, HttpSession session) {
		int profId = -1;
		Profile profile = new Profile();
		User user = new User();
		Date date = new Date();
		String avatar = userInfo.getString("avatar_hd");
		profile.setAvatar(avatar);
		String nickname = userInfo.getString("screen_name");
		profile.setNickname(nickname);
		String gender = userInfo.getString("gender");
		int genderInt;
		if ("m".equals(gender)) {
			genderInt = 1;
		} else if ("f".equals(gender)) {
			genderInt = 0;
		} else {
			genderInt = 2;
		}
		profile.setGender(genderInt);
		User u = userDao.getUser("wb" + userInfo.getString("idstr"));
		if (u == null) {
			if (profileDao.insert(profile)) {
				user.setProfId(profile.getId());
				user.setCreateTime(date);
				user.setUpdateTime(date);
				user.setuId("wb" + userInfo.getString("idstr"));
				userDao.insert(user);
			}
			profId = user.getProfId();
		} else {
			u.setUpdateTime(date);
			userDao.updateTime(u);
			profId = u.getProfId();
			Profile prof = profileDao.getProfile(profId);
			profile.setId(profId);
			if(!prof.getAvatar().equals(avatar) || prof.getGender() != genderInt || !prof.getNickname().equals(nickname)) {
				profileDao.update(profile);
			}
		}
		return profId;
	}
}
