package cn.ustate.utils;

import javax.servlet.http.HttpSession;

/**
 * @author EricFu
 * @date 2017-7-16 下午8:14:28
 */
public class ObtainUserSession {
	public static Integer getProfId(HttpSession session) {
		Object object = session.getAttribute("id");
		if (object !=null) {
			return (Integer)object;
		} else{
			return 0;
		}
	}
}
