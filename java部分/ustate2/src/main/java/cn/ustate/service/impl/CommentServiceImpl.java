package cn.ustate.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ustate.constraint.ApiCodeEnum;
import cn.ustate.constraint.LikeConst;
import cn.ustate.constraint.NoticeConst;
import cn.ustate.constraint.PublicConst;
import cn.ustate.dao.CommentDao;
import cn.ustate.dao.LikeDao;
import cn.ustate.dao.NoticeDao;
import cn.ustate.dao.StatementDao;
import cn.ustate.dto.ApiDto;
import cn.ustate.dto.CommentDto;
import cn.ustate.entity.Comment;
import cn.ustate.entity.Like;
import cn.ustate.entity.Notice;
import cn.ustate.entity.Profile;
import cn.ustate.expection.ServerException;
import cn.ustate.service.CommentService;

/**
 * @author EricFu
 * @date 2017-7-14 下午8:38:14
 */
@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	private CommentDao commentDao;
	
	@Autowired
	private NoticeDao noticeDao;
	
	@Autowired
	private StatementDao statementDao;
	
	@Autowired
	private LikeDao likeDao;
	
	@Override
	public int getCount() {
		return commentDao.getCount();
	}

	@Override
	public ApiDto list(int profId, int rootId) {
		
		List<CommentDto> result = new ArrayList<CommentDto>();
		try {
			List<Comment> comments = commentDao.list(profId, rootId);
			for (Comment comment : comments) {
				CommentDto cmtDto = new CommentDto();
				if (comment.getProfile().getId() == profId) {
				cmtDto.setAuthor(PublicConst.IS_AUTHOR);
				}
				BeanUtils.copyProperties(comment, cmtDto);
				result.add(cmtDto);
			}
		} catch (Exception e) {
			throw new ServerException(e);
		}
		return new ApiDto(ApiCodeEnum.SUCCESS, result);
	}

	@Override
	public ApiDto insert(int profId, Comment comment) {
		int action = -1;
		if (comment.getParentId() == 0) {
			action = NoticeConst.ACTION_CMT_STMT;
		} else {
			action = NoticeConst.ACTION_CMT_CMT;
		}
		CommentDto cmtDto = new CommentDto();
		try {
			Profile profile = new Profile();
			profile.setId(profId);
			comment.setProfile(profile);
			Date createTime = new Date();
			comment.setCreateTime(createTime);
			int defProfId = 0;
			Notice notice = new Notice();
			if (action == NoticeConst.ACTION_CMT_STMT) {
				defProfId = statementDao.getProfId(comment.getRootId());
			} else if (action == NoticeConst.ACTION_CMT_CMT) {
				defProfId = commentDao.getProfId(comment.getParentId());
			}
			
			notice.setRootId(comment.getRootId());
			notice.setCreateTime(createTime);
			notice.setProfId(profId);
			notice.setDefProfId(defProfId);
			notice.setAction(action);
			String content = comment.getContent();
			int index = action == NoticeConst.ACTION_CMT_CMT ? content.indexOf(":", 0) : -1;
			notice.setContent(content.substring(index + 1, content.length()));
			String originContent = comment.getParentId() == 0 ?statementDao.getContent(comment.getRootId()) :commentDao.getContent(comment.getParentId());
//			index = comment.getParentId() != 0 && commentDao.getParentId(comment.getParentId()) == NoticeConst.ACTION_CMT_CMT ? originContent.indexOf(":", 0) : 0;
			notice.setOriginContent(originContent);
			
			if (commentDao.insert(comment)) {
				notice.setParentId(comment.getId());
				statementDao.updateCommentCount(1,comment.getRootId());
				if (noticeDao.insert(notice)) {
					
					cmtDto.setAuthor(PublicConst.IS_AUTHOR);
					BeanUtils.copyProperties(comment, cmtDto);
				}
			}
		} catch (Exception e) {
			throw new ServerException(e);
		}
		return new ApiDto(ApiCodeEnum.SUCCESS, cmtDto);
	}

	@Override
	public ApiDto delete(int profId, Comment comment) {
		try {
			Like like = new Like();
			like.setProfId(profId);
			like.setParentId(comment.getId());
			like.setType(LikeConst.LIKE_CMT);
			likeDao.delete(like);
			commentDao.delete(profId, comment.getId());
			statementDao.updateCommentCount(-1, comment.getRootId());
		} catch (Exception e) {
			throw new ServerException(e);
		}
		return new ApiDto(ApiCodeEnum.SUCCESS, null);
	}
}
