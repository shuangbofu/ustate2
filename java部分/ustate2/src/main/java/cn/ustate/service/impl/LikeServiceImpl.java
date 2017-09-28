package cn.ustate.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ustate.constraint.ApiCodeEnum;
import cn.ustate.constraint.LikeConst;
import cn.ustate.constraint.NoticeConst;
import cn.ustate.dao.CommentDao;
import cn.ustate.dao.LikeDao;
import cn.ustate.dao.NoticeDao;
import cn.ustate.dao.StatementDao;
import cn.ustate.dto.ApiDto;
import cn.ustate.entity.IdEntity;
import cn.ustate.entity.Like;
import cn.ustate.entity.Notice;
import cn.ustate.expection.NoLoginException;
import cn.ustate.expection.ServerException;
import cn.ustate.service.LikeService;

/**
 * @author EricFu
 * @date 2017-7-18 下午9:19:07
 */
@Service
public class LikeServiceImpl implements LikeService {

	@Autowired
	private LikeDao likeDao;
	
	@Autowired
	private StatementDao statementDao;
	
	@Autowired
	private CommentDao commentDao;
	
	@Autowired
	private NoticeDao noticeDao;

	@Override
	public ApiDto insertLikePost(Like like, int profId) throws RuntimeException {
		if (profId == 0) {
			throw new NoLoginException();
		}
		try {
			Date createTime = new Date();
			like.setCreateTime(createTime);
			like.setProfId(profId);
			like.setType(LikeConst.LIKE_STMT);
			likeDao.insert(like);
			Notice notice = new Notice();
			notice.setCreateTime(createTime);
			notice.setParentId(0);
			notice.setRootId(like.getParentId());
			notice.setDefProfId(statementDao.getProfId(like.getParentId()));
			notice.setProfId(profId);
			notice.setAction(NoticeConst.ACTION_LIKE_STMT);
			notice.setOriginContent(statementDao.getContent(like.getParentId()));
			noticeDao.insert(notice);
			statementDao.updateLikeCount(1, like.getParentId());
		} catch (Exception e) {
			throw new ServerException(e);
		}
		return new ApiDto(ApiCodeEnum.LIKE_SUCCESS, new IdEntity(like.getId()));
	}

	@Override
	public ApiDto DeleteLikePost(Like like, int profId) {
		if (profId == 0) {
			throw new NoLoginException();
		}
		try {
			like.setProfId(profId);
			like.setType(LikeConst.LIKE_STMT);
			likeDao.delete(like);
			statementDao.updateLikeCount(-1, like.getParentId());
		} catch (Exception e) {
			throw new ServerException(e);
		}
		return new ApiDto(ApiCodeEnum.LIKE_SUCCESS, new IdEntity(like.getId()));
	}

	@Override
	public ApiDto insertLikeComment(Like like, int profId) {
		if (profId == 0) {
			throw new NoLoginException();
		}
		try {
			Date createTime = new Date();
			like.setCreateTime(createTime);
			like.setProfId(profId);
			like.setType(LikeConst.LIKE_CMT);
			likeDao.insert(like);
			
			Notice notice = new Notice();
			notice.setCreateTime(createTime);
			notice.setParentId(like.getParentId());
			notice.setRootId(commentDao.getRootId(like.getParentId()));
			notice.setProfId(profId);
			notice.setDefProfId(commentDao.getProfId(like.getParentId()));
			notice.setAction(NoticeConst.ACTION_LIKE_CMT);
			String originContent = commentDao.getContent(like.getParentId());
			int index = originContent.indexOf(":");
			
			notice.setOriginContent(originContent.substring(index + 1, originContent.length()));
			
			noticeDao.insert(notice);
		} catch (Exception e) {
			throw new ServerException(e);
		}
		commentDao.updateLikeCount(1, like.getParentId());
		return new ApiDto(ApiCodeEnum.LIKE_SUCCESS, new IdEntity(like.getId()));
	}

	@Override
	public ApiDto DeleteLikeComment(Like like, int profId) throws RuntimeException {
		if (profId == 0) {
			throw new NoLoginException();
		}
		try {
			like.setProfId(profId);
			like.setType(LikeConst.LIKE_CMT);
			likeDao.delete(like);
		} catch (Exception e) {
			throw new ServerException(e);
		}
		commentDao.updateLikeCount(-1, like.getParentId());
		return new ApiDto(ApiCodeEnum.LIKE_SUCCESS, new IdEntity(like.getId()));
	}
}
