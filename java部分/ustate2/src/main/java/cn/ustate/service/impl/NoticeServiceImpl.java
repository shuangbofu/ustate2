package cn.ustate.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.ustate.constraint.ApiCodeEnum;
import cn.ustate.dao.NoticeDao;
import cn.ustate.dto.ApiDto;
import cn.ustate.entity.Notice;
import cn.ustate.expection.NoLoginException;
import cn.ustate.expection.ServerException;
import cn.ustate.service.NoticeService;

/**
 * @author EricFu
 * @date 2017-7-17 下午8:09:05
 */
@Service
public class NoticeServiceImpl implements NoticeService {
	
	@Resource
	private NoticeDao noticeDao;
	
	@Override
	public ApiDto listNotices(int profId, int fromId) throws RuntimeException {
		List<Notice> notices = new ArrayList<Notice>();
		try {
			notices = noticeDao.list(profId, fromId);
			noticeDao.resetStatus(profId);
		} catch (Exception e) {
			throw new ServerException(e);
		}
		return new ApiDto(ApiCodeEnum.SUCCESS, notices);
	}

	@Override
	public ApiDto getCount(int profId) throws RuntimeException {
		if (profId == 0) {
			throw new NoLoginException();
		}
		int count = 0;
		try {
			count = noticeDao.getEffectiveCount(profId);
		} catch (Exception e) {
			throw new ServerException(e);
		}
		return new ApiDto(ApiCodeEnum.SUCCESS, count);
	}
}
