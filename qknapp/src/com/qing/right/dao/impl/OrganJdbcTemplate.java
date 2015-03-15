package com.qing.right.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * 组织JDBC TEMPLATE用于获取组织ID下面所有的组织信息
 * 
 * @author guojun
 * @version 创建时间：2012-11-26 上午11:27:36
 */
@Service
@Repository
public class OrganJdbcTemplate {

    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
	this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * 加载当前组织下的所有子组织
     * 
     * @author huangqingjian/0050
     * @see 相关函数,对于重要的函数建议注释
     * @since 深烟智能物流管理平台, 2013-1-6
     * @param organId
     * @return
     */
    @SuppressWarnings("rawtypes")
    public List getBaseOrganList(String organId) {
	StringBuilder sql = new StringBuilder();
	sql.append(" EXEC MySP_Work_Organ_Tree_Result ? ");
	List list = jdbcTemplate.queryForList(sql.toString(), organId);
	return list;
    }

    /**
     * 加载当前组织下的所有的公司类型的组织
     * 
     * @author huangqingjian/0050
     * @see 相关函数,对于重要的函数建议注释
     * @since 深烟智能物流管理平台, 2013-1-6
     * @param organId
     * @return
     */
    @SuppressWarnings("rawtypes")
    public List getCompanyOrganList(String organId) {
	StringBuilder sql = new StringBuilder();
	sql.append(" EXEC MySP_Work_Organ_Tree_Company ? ");
	List list = jdbcTemplate.queryForList(sql.toString(), organId);
	return list;
    }

    /**
     * 加载当前组织下的所有的部门类型的组织，子公司不加载出来
     * 
     * @author huangqingjian/0050
     * @see 相关函数,对于重要的函数建议注释
     * @since 深烟智能物流管理平台, 2013-1-6
     * @param organId
     * @return
     */
    @SuppressWarnings("rawtypes")
    public List getDepartmentOrganList(String organId) {
	StringBuilder sql = new StringBuilder();
	sql.append(" EXEC MySP_Work_Organ_Tree_Except_Company ? ");
	List list = jdbcTemplate.queryForList(sql.toString(), organId);
	return list;
    }
}
