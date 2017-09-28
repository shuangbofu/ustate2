package cn.ustate.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ustate.constraint.ApiCodeEnum;
import cn.ustate.constraint.LikeConst;
import cn.ustate.constraint.ListStateConst;
import cn.ustate.constraint.PublicConst;
import cn.ustate.dao.LikeDao;
import cn.ustate.dao.StatementDao;
import cn.ustate.dto.ApiDto;
import cn.ustate.dto.StatementDto;
import cn.ustate.entity.Like;
import cn.ustate.entity.Profile;
import cn.ustate.entity.Statement;
import cn.ustate.expection.ServerException;
import cn.ustate.service.StatementService;

/**
 * @author EricFu
 * @date 2017-7-14 下午8:37:08
 */
@Service
public class StatementServiceImpl implements StatementService {

	@Autowired
	private StatementDao statementDao;
	
	@Autowired
	private LikeDao likeDao;
	
	@Override
	public int getCount() {
		return statementDao.getCount();
	}

	@Override
	public ApiDto list(int profId, int fromId, int listType) throws RuntimeException {
		List<StatementDto> result = new ArrayList<StatementDto>();
		try {
			List<Statement> statements = statementDao.list(profId, fromId, listType);
			for (Statement statement : statements) {
				StatementDto stmtDto = new StatementDto();
				if (listType == ListStateConst.LIST_FOR_POSTED) {
					stmtDto.setAuthor(PublicConst.IS_AUTHOR);
				} else {
					if (statement.getProfile().getId() == profId) {
						stmtDto.setAuthor(PublicConst.IS_AUTHOR);
					}
				}			
				if(statement.getSercret() == PublicConst.IS_SERCRET) {
					statement.setProfile(new Profile(statement.getProfile().getGender()));
				}
				String content = statement.getContent();
				int index = 0;
				int count = 0;
				while((index = content.indexOf("\n"))!=-1) {
					content = content.substring(index + 1);
					count++;
				}
				if(statement.getContent().length() + count*17 >= 200) {
					stmtDto.setMore(PublicConst.TEXT_HAS_MORE);
				}
				BeanUtils.copyProperties(statement, stmtDto);
				result.add(stmtDto);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServerException(e);
		}
		return new ApiDto(ApiCodeEnum.SUCCESS, result);
	}

	@Override
	public ApiDto getDetail(int profId, int id) throws RuntimeException {
		StatementDto stmtDto = new StatementDto();
		try {
			Statement stmt = statementDao.getDetail(profId, id);
			if (stmt != null) {
				if (stmt.getProfile().getId() == profId) {
					stmtDto.setAuthor(PublicConst.IS_AUTHOR);
				}
				if (stmt.getSercret() == PublicConst.IS_SERCRET) {
					stmt.setProfile(new Profile(stmt.getProfile().getGender()));
				}
				BeanUtils.copyProperties(stmt, stmtDto);
				return new ApiDto(ApiCodeEnum.SUCCESS, stmtDto);
			} else {
				return new ApiDto(ApiCodeEnum.FAILURE, stmtDto);
			}
		} catch (Exception e) {
			throw new ServerException(e);
		}
	}

	@Override
	public ApiDto insert(int profId, Statement statement) {
		statement.setCreateTime(new Date());
		Profile profile = new Profile();
		profile.setId(profId);
		statement.setProfile(profile);
		StatementDto stmtDto = new StatementDto();
		if (statementDao.insert(statement)) {
			stmtDto.setAuthor(PublicConst.IS_AUTHOR);
			if (statement.getContent().length() > 150) {
				stmtDto.setMore(PublicConst.TEXT_HAS_MORE);
			}
			BeanUtils.copyProperties(statement, stmtDto);
		}
		return new ApiDto(ApiCodeEnum.SUCCESS, stmtDto);
	}

	@Override
	public ApiDto delete(int profId, Statement statement) {
		try {
			Like like = new Like();
			like.setProfId(profId);
			like.setParentId(statement.getId());
			like.setType(LikeConst.LIKE_STMT);
			likeDao.delete(like);
			statementDao.delete(profId, statement.getId());
		} catch (Exception e) {
			throw new ServerException(e);
		}
		return new ApiDto(ApiCodeEnum.SUCCESS, null);
	}
}
