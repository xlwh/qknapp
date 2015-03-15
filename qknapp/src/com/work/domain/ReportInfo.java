/*
 * Title:        清清网系统 2014-8-25
 * Description:  举报信息实体类封装
 * Copyright:    Copyright (c) 2014
 * Company:      清清网
 * @author       zhanghongbin
 * @version      2.0  2014-8-25
 */
package com.work.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 举报信息实体类封装
 * 
 * @author zhanghongbin
 * @see [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since 清清网系统, 2014-8-25
 */

@Entity
@Table(name = "T_CIRCLE_REPORTINFO")
public class ReportInfo {
	// 主键
	private String report_id;
	// 举报者id
	private String member_id;
	// 举报的动态信息
	private String message_id;
	// 举报处理状态（0：等待处理 1：已经删除 2：恢复）
	private int report_status;
	//举报原因
	private String reson;
	//举报时间
	private Date createtime;
	
	private String type;

	/**
	 * 构造函数 (一句话功能简述) (功能详细描述)
	 * 
	 * @author zhanghongbin
	 * @see 相关函数,对于重要的函数建议注释
	 * @since 清清网系统, 2014-8-25
	 */
	public ReportInfo() {
		super();

	}

	/**
	 * 构造函数 (一句话功能简述) (功能详细描述)
	 * 
	 * @author zhanghongbin
	 * @see 相关函数,对于重要的函数建议注释
	 * @since 清清网系统, 2014-8-25
	 * @param report_id
	 * @param member_id
	 * @param message_id
	 * @param report_status
	 */
	public ReportInfo(String report_id, String member_id, String message_id,
			int report_status) {
		super();
		this.report_id = report_id;
		this.member_id = member_id;
		this.message_id = message_id;
		this.report_status = report_status;
	}

	/**
	 * @return Returns the report_id.
	 */

	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "REPORT_ID", unique = true, nullable = false, length = 32)
	public String getReport_id() {
		return report_id;
	}

	/**
	 * @param report_id
	 *            The report_id to set.
	 */
	public void setReport_id(String report_id) {
		this.report_id = report_id;
	}

	/**
	 * @return Returns the member_id.
	 */
	@Column(name = "MEMBER_ID", length = 32)
	public String getMember_id() {
		return member_id;
	}

	/**
	 * @param member_id
	 *            The member_id to set.
	 */
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	/**
	 * @return Returns the message_id.
	 */

	@Column(name = "MESSAGE_ID", length = 32)
	public String getMessage_id() {
		return message_id;
	}

	/**
	 * @param message_id
	 *            The message_id to set.
	 */
	public void setMessage_id(String message_id) {
		this.message_id = message_id;
	}

	/**
	 * @return Returns the report_status.
	 */

	@Column(name = "REPORT_STATUS")
	public int getReport_status() {
		return report_status;
	}

	/**
	 * @param report_status
	 *            The report_status to set.
	 */
	public void setReport_status(int report_status) {
		this.report_status = report_status;
	}

	/**
	 * @return Returns the reson.
	 */
	
	@Column(name = "REPORT_RESON",length = 200)
	public String getReson() {
		return reson;
	}

	/**
	 * @param reson The reson to set.
	 */
	public void setReson(String reson) {
		this.reson = reson;
	}

	/**
	 * @return Returns the createtime.
	 */
	
	@Column(name = "REPORT_CREATETIME")
	public Date getCreatetime() {
		return createtime;
	}

	/**
	 * @param createtime The createtime to set.
	 */
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	/**
	 * @return Returns the type.
	 */
	
	@Column(name = "REPORT_TYPE",length = 100)
	public String getType() {
		return type;
	}

	/**
	 * @param type The type to set.
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	

}
