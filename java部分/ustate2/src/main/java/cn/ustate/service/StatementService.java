package cn.ustate.service;

import cn.ustate.dto.ApiDto;
import cn.ustate.entity.Statement;

/**
 * @author EricFu
 * @date 2017-7-14 下午8:35:38
 */
public interface StatementService {
	
	/**
	 * 查询发表的数量
	 * @return 发表数量
	 */
	int getCount();
	ApiDto list(int profId, int fromId, int listType);
	ApiDto getDetail(int profId, int id);
	ApiDto insert(int profId, Statement statement);
	ApiDto delete(int profId, Statement statement);
}
