package cn.ustate.service;

import cn.ustate.dto.ApiDto;

/**
 * @author EricFu
 * @date 2017-7-17 下午8:08:43
 */
public interface NoticeService {

	ApiDto listNotices(int profId, int fromId);

	ApiDto getCount(int profId);
}
