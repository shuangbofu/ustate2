package cn.ustate.service;

import cn.ustate.dto.ApiDto;
import cn.ustate.entity.Feedback;

/**
 * @author EricFu
 * @date 2017-8-10 上午9:15:22
 */
public interface FeedbackService {

	ApiDto insertFeedback(Feedback feedback, int profId);

}
