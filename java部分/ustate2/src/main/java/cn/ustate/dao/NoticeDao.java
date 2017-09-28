package cn.ustate.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.ustate.entity.Notice;

/**
 * @author EricFu
 * @date 2017-7-17 下午4:46:03
 */
public interface NoticeDao {
	boolean insert(Notice notice);

	List<Notice> list(@Param("profId") int profId, @Param("fromId") int fromId);

	int getEffectiveCount(int profId);

	int getCountByProfId(int profId);

	int resetStatus(int profId);
}
