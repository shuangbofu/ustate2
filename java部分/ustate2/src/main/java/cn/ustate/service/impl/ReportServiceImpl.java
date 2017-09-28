package cn.ustate.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ustate.constraint.ApiCodeEnum;
import cn.ustate.dao.ReportDao;
import cn.ustate.dto.ApiDto;
import cn.ustate.entity.Report;
import cn.ustate.expection.ServerException;
import cn.ustate.service.ReportService;
import cn.ustate.entity.IdEntity;

/**
 * @author EricFu
 * @date 2017-7-14 下午8:39:05
 */
@Service
public class ReportServiceImpl implements ReportService{

	@Autowired
	private ReportDao reportDao;

	@Override
	public ApiDto insertReport(Report report, int profId) throws RuntimeException {
		try {
			report.setProfId(profId);
			report.setCreateTime(new Date());
			reportDao.insert(report);
			return new ApiDto(ApiCodeEnum.SUCCESS, new IdEntity(report.getId()));
		} catch (Exception e) {
			throw new ServerException(e);
		}
	}

}
