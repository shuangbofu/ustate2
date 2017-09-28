package cn.ustate.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ustate.constraint.ApiCodeEnum;
import cn.ustate.dao.LikeDao;
import cn.ustate.dao.NoticeDao;
import cn.ustate.dao.ProfileDao;
import cn.ustate.dao.StatementDao;
import cn.ustate.dto.ApiDto;
import cn.ustate.dto.ProfileDto;
import cn.ustate.entity.Profile;
import cn.ustate.expection.ServerException;
import cn.ustate.service.ProfileService;

/**
 * @author EricFu
 * @date 2017-7-15 下午12:29:18
 */
@Service
public class ProfileServiceImpl implements ProfileService {

	@Autowired
	private ProfileDao profileDao;
	
	@Autowired
	private StatementDao statementDao;
	
	@Autowired
	private LikeDao likeDao;
	
	@Autowired
	private NoticeDao noticeDao;
	
	@Override
	public ApiDto getProfile(int id) throws RuntimeException {
		if (id !=0) {
			ProfileDto profDto = new ProfileDto();
			try {
				Profile profile = profileDao.getProfile(id);
				int postsCount = statementDao.getCountByProfId(id);
				int likePostsCount = likeDao.getCountByProfId(id);
				int noticeTotalCount = noticeDao.getCountByProfId(id);
				profDto.setPostsCount(postsCount);
				profDto.setLikePostsCount(likePostsCount);
				profDto.setNoticeTotalCount(noticeTotalCount);
				BeanUtils.copyProperties(profile, profDto);
			} catch (Exception e) {
				throw new ServerException(e);
			}
			return new ApiDto(ApiCodeEnum.SUCCESS, profDto);
		} else {
			return new ApiDto(ApiCodeEnum.LOGIN_FAILURE, null);
		}
	}

}
