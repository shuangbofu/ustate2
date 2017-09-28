package cn.ustate.controller.api;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.http.client.ClientProtocolException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ustate.constraint.ListStateConst;
import cn.ustate.dto.ApiDto;
import cn.ustate.entity.Comment;
import cn.ustate.entity.Feedback;
import cn.ustate.entity.Like;
import cn.ustate.entity.Report;
import cn.ustate.entity.Statement;
import cn.ustate.entity.User;
import cn.ustate.service.CommentService;
import cn.ustate.service.FeedbackService;
import cn.ustate.service.LikeService;
import cn.ustate.service.NoticeService;
import cn.ustate.service.ProfileService;
import cn.ustate.service.ReportService;
import cn.ustate.service.StatementService;
import cn.ustate.service.UserService;
import cn.ustate.utils.AuthUtil;
import cn.ustate.utils.ObtainUserSession;

/**
 * @author EricFu
 * @date 2017-7-14 下午8:46:45
 */
@Controller
@RequestMapping("/")
public class apiController {

	@Resource
	private StatementService statementService;
	
	@Resource
	private CommentService commentService;
	
	@Resource
	private NoticeService noticeService;

	@Resource
	private LikeService likeService;
	
	@Resource
	private ReportService reportService;
	
	@Resource
	private FeedbackService feedbackService;
	
	@Resource
	private UserService userService;
	
	private Integer profId;

	
	/*----------------------------------------- statement ---------------------------------------------*/
	@RequestMapping(value="posts", method=RequestMethod.GET)
	public @ResponseBody
	ApiDto posts (int fromId, HttpSession session) {
		profId = ObtainUserSession.getProfId(session);
		return statementService.list(profId, fromId, ListStateConst.LIST_FOR_INDEX);
	}
	
	@RequestMapping(value="hot", method=RequestMethod.GET)
	public @ResponseBody
	ApiDto hot (int fromId, HttpSession session) {
		profId = ObtainUserSession.getProfId(session);
		return statementService.list(profId, fromId, ListStateConst.LIST_FOR_HOT);
	}
	
	@RequestMapping(value="posts/self", method=RequestMethod.GET)
	public @ResponseBody
	ApiDto posted (int fromId, HttpSession session) {
		profId = ObtainUserSession.getProfId(session);
		return statementService.list(profId, fromId, ListStateConst.LIST_FOR_POSTED);
	}
	
	@RequestMapping(value="like/self", method=RequestMethod.GET)
	public @ResponseBody
	ApiDto liked (int fromId, HttpSession session) {
		profId = ObtainUserSession.getProfId(session);
		return statementService.list(profId, fromId, ListStateConst.LIST_FOR_LIKED);
	}
	
	/*获得详细*/
	@RequestMapping(value="post", method=RequestMethod.GET)
	public @ResponseBody
	ApiDto getDetail (int id, HttpSession session) {
		profId = ObtainUserSession.getProfId(session);
		return statementService.getDetail(profId, id);
	}
	/*发表*/
	@RequestMapping(value="post", method=RequestMethod.POST)
	public @ResponseBody
	ApiDto insert(@RequestBody Statement statement, HttpSession session) {
		profId = ObtainUserSession.getProfId(session);
		return statementService.insert(profId, statement);
	}
	/*删除*/
	@RequestMapping(value="post", method=RequestMethod.DELETE)
	public @ResponseBody
	ApiDto delete(@RequestBody Statement statement, HttpSession session) {
		profId = ObtainUserSession.getProfId(session);
		return statementService.delete(profId, statement);
	}
	
	/*----------------------------------------- comment ---------------------------------------------*/
	
	@RequestMapping(value="comments", method=RequestMethod.GET)
	public @ResponseBody
	ApiDto list(int postId, HttpSession session) {
		profId = ObtainUserSession.getProfId(session);
		return commentService.list(profId, postId);
	}
	
	@RequestMapping(value="comment", method=RequestMethod.POST)
	public @ResponseBody
	ApiDto insertComment(@RequestBody Comment comment, HttpSession session) {
		profId = ObtainUserSession.getProfId(session);
		return commentService.insert(profId, comment);
	}
	@RequestMapping(value="comment", method=RequestMethod.DELETE)
	public @ResponseBody
	ApiDto deleteComment(@RequestBody Comment comment, HttpSession session) {
		profId = ObtainUserSession.getProfId(session);
		return commentService.delete(profId, comment);
	}
	
	/*----------------------------------------- profile ---------------------------------------------*/
	
	@Resource
	private ProfileService profileService;
	
	
	@RequestMapping(value="profile", method=RequestMethod.GET)
	public @ResponseBody
	ApiDto getProfile(HttpSession session) {
		profId = ObtainUserSession.getProfId(session);
		return profileService.getProfile(profId);
	}
	
	/*----------------------------------------- notice ---------------------------------------------*/
	
	@RequestMapping(value="notices", method=RequestMethod.GET)
	public @ResponseBody
	ApiDto listNotices(int fromId, HttpSession session) {
		profId = ObtainUserSession.getProfId(session);
		return noticeService.listNotices(profId, fromId);
	}
	
	@RequestMapping(value="notice/count", method=RequestMethod.GET)
	public @ResponseBody
	ApiDto getEffectiveCount(HttpSession session) {
		profId = ObtainUserSession.getProfId(session);
		return noticeService.getCount(profId);
	}
	
	/*----------------------------------------- like ---------------------------------------------*/
	
	@RequestMapping(value="like/post", method=RequestMethod.POST)
	public @ResponseBody
	ApiDto insertLikePost(@RequestBody Like like, HttpSession session) {
		profId = ObtainUserSession.getProfId(session);
		return likeService.insertLikePost(like, profId);
	}
	
	@RequestMapping(value="like/post", method=RequestMethod.DELETE)
	public @ResponseBody
	ApiDto deleteLikePost(@RequestBody Like like, HttpSession session) {
		profId = ObtainUserSession.getProfId(session);
		return likeService.DeleteLikePost(like, profId);
	}
	
	@RequestMapping(value="like/comment", method=RequestMethod.POST)
	public @ResponseBody
	ApiDto insertLikeComment(@RequestBody Like like, HttpSession session) {
		profId = ObtainUserSession.getProfId(session);
		return likeService.insertLikeComment(like, profId);
	}
	
	@RequestMapping(value="like/comment", method=RequestMethod.DELETE)
	public @ResponseBody
	ApiDto deleteLikeComment(@RequestBody Like like, HttpSession session) {
		profId = ObtainUserSession.getProfId(session);
		return likeService.DeleteLikeComment(like, profId);
	}
	
	/*----------------------------------------- report ---------------------------------------------*/
	
	
	@RequestMapping(value="report", method=RequestMethod.POST)
	public @ResponseBody
	ApiDto insertReport(@RequestBody Report report, HttpSession session) {
		profId = ObtainUserSession.getProfId(session);
		return reportService.insertReport(report, profId);
	}
	
	/*----------------------------------------- feedback ---------------------------------------------*/
	@RequestMapping(value="feedback", method=RequestMethod.POST)
	public @ResponseBody
	ApiDto insertFeedback(@RequestBody Feedback feedback, HttpSession session) {
		profId = ObtainUserSession.getProfId(session);
		return feedbackService.insertFeedback(feedback, profId);
	}
	
	/*------------------------------------------ login ----------------------------------------------*/
	@RequestMapping(value="login", method=RequestMethod.POST)
	public @ResponseBody
	boolean login(@RequestBody User user, HttpSession session) {
		int profId = userService.login(user);
		if (profId != 0) {
			session.setAttribute("id", profId);
			return true;
		} else {
			return false;
		}
	}
	
	@RequestMapping(value="logout")
	public String logout(HttpSession session) {
		session.removeAttribute("id");
		return "redirect:http://www.ustate.cn/ustate2/index";
	}
	/*------------------------------------------ weibo_api ----------------------------------------------*/
	
	@RequestMapping(value="weiboLogin")
	public String weiboLogin() {
		String url = "https://api.weibo.com/oauth2/authorize?client_id="+AuthUtil.client_ID+"&response_type=code&redirect_uri=http://www.ustate.cn:8080/ustate2/weibo";
		return "redirect:" + url;
	}
	
	@RequestMapping(value="weibo", method=RequestMethod.GET)
	public
	String getCode(String code, HttpSession session) throws ClientProtocolException, IOException {
		String token = "";
		String uid = "";
		if (session.getAttribute("access_token") == null) {
			String url = "https://api.weibo.com/oauth2/access_token?client_id="+AuthUtil.client_ID+"&client_secret="+AuthUtil.client_SERCRET+"&grant_type=authorization_code&redirect_uri=http://www.ustate.cn:8080/ustate2/weibo&code=" + code;
			JSONObject jsonObject = AuthUtil.doGetJson(url, "post");
			token = jsonObject.getString("access_token");
			session.setAttribute("access_token", token);
			uid = jsonObject.getString("uid");
			session.setAttribute("uid", uid);
		} else {
			token = (String) session.getAttribute("access_token");
			uid = (String) session.getAttribute("uid");
		}
		String infoUrl = "https://api.weibo.com/2/users/show.json?access_token="+token +"&uid="+uid;
		JSONObject userInfo = AuthUtil.doGetJson(infoUrl, "get");
		int profId = userService.weiboLogin(userInfo, session);
		session.setAttribute("id", profId);
		return "redirect:http://www.ustate.cn/ustate2/profile";
	}
	
	
	// 取消授权
	@RequestMapping(value="removeAuth", method=RequestMethod.GET)
	public String removeAuth(HttpSession session) throws ClientProtocolException, IOException {
		String token = (String) session.getAttribute("access_token");
		String removeUrl = "https://api.weibo.com/oauth2/revokeoauth2?access_token=" + token;
		AuthUtil.doGetJson(removeUrl, "get");
		session.removeAttribute("id");
		session.removeAttribute("access_token");
		session.removeAttribute("uid");
		return "redirect:http://www.ustate.cn/ustate2/profile";
	}	
}
