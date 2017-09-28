package cn.ustate.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ustate.constraint.ApiCodeEnum;
import cn.ustate.dao.FeedbackDao;
import cn.ustate.dto.ApiDto;
import cn.ustate.entity.Feedback;
import cn.ustate.expection.ServerException;
import cn.ustate.service.FeedbackService;
import cn.ustate.entity.IdEntity;

/**
 * @author EricFu
 * @date 2017-8-10 上午9:15:42
 */
@Service
public class FeedbackServiceImpl implements FeedbackService{

	@Autowired
	private FeedbackDao feedbackDao;
	
	@Override
	public ApiDto insertFeedback(Feedback feedback, int profId) throws RuntimeException {
		try {
			feedback.setProfId(profId);
			feedback.setCreateTime(new Date());
			feedbackDao.insert(feedback);
			return new ApiDto(ApiCodeEnum.SUCCESS, new IdEntity(feedback.getId()));
		} catch (Exception e) {
			throw new ServerException(e);
		}
	}

}
