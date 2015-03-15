package com.qing.common.service.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.qing.common.exception.ServiceException;
import com.qing.common.exception.handle.ExceptionHandle;
import com.qing.common.util.ErrorCode;
import com.qing.right.dao.domain.BaseIpType;

/**
 * Title: JdbcTemplateServiceImpl <br>
 * Description: JdbcTemplateServiceImpl<br>
 * CreateTime: 2012-08-07 16:39 <br>
 * Copyright: Copyright (c) 2012 taiheiot.com<br>
 * 
 * @author guojun
 * @since 1.0
 */
@Service
@Repository
public class JdbcTemplateServiceImpl {

    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
	this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * jdbcTemplate调用存储过程, 并保存返回值
     * 
     * @param baseIpType
     *            BaseIpType类型
     */
    public void saveBaseIpTypeProcedure(BaseIpType baseIpType) throws ServiceException {
	try {
	    // jdbcTemplate获取连接池里面的连接
	    Connection con = jdbcTemplate.getDataSource().getConnection();
	    CallableStatement cstmt = null;
	    try {
		String procedureName = "{ call PRC_BASE_IP_TYPE_SAVE(?,?,?)}";
		cstmt = con.prepareCall(procedureName);
		cstmt.setString(1, baseIpType.getBgIp());
		cstmt.setString(2, baseIpType.getEnIp());
		cstmt.registerOutParameter(3, Types.VARCHAR);
		// cstmt.executeUpdate();
		cstmt.execute();
		baseIpType.setIpId(String.valueOf(cstmt.getString(4)));
		cstmt.close();
	    } catch (Exception e) {
		ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR, "JdbcTemplateServiceImpl.saveBaseIpTypeProcedure ");
		if (null != cstmt) {
		    cstmt.close();
		}
		if (null != con) {
		    con.close();
		}
	    } finally {
		if (null != cstmt) {
		    cstmt.close();
		}
		if (null != con) {
		    con.close();
		}
	    }

	} catch (Exception e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR, "JdbcTemplateServiceImpl.saveBaseIpTypeProcedure ");
	}
    }

    /**
     * jdbcTemplate sql查询BaseIpType
     * 
     * @param ipId
     * @param bgIp
     * @return
     */
    public BaseIpType findBaseIpType(String ipId, String bgIp) {
	String sql = "select IP_ID, BG_IP,EN_IP from T_BASE_IP_TYPE ip  " + " where ip.IP_ID = ? and ip.BG_IP = ? ";

	RowMapper<BaseIpType> mapper = new RowMapper<BaseIpType>() {
	    public BaseIpType mapRow(ResultSet rs, int rowNum) throws SQLException {
		BaseIpType baseIpType = new BaseIpType();
		baseIpType.setIpId(rs.getString("IP_ID"));
		baseIpType.setBgIp(rs.getString("BG_IP"));
		baseIpType.setEnIp(rs.getString("EN_IP"));
		return baseIpType;
	    }
	};

	return (BaseIpType) jdbcTemplate.queryForObject(sql, new Object[] { ipId, bgIp }, mapper);
    }

}
