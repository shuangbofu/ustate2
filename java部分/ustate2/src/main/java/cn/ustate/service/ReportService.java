package cn.ustate.service;

import cn.ustate.dto.ApiDto;
import cn.ustate.entity.Report;

/**
 * @author EricFu
 * @date 2017-7-14 下午8:36:49
 */
public interface ReportService {

	ApiDto insertReport(Report report, int profId);

}
