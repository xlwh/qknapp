/*
 * Title:        清清网系统 2014年8月16日
 * Description:  实践圈的动态的实体对象
 * Copyright:    Copyright (c) 2014
 * Company:      清清网
 * @author       张洪斌
 * @version      2.0  2014年8月16日
 */
package com.work.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

/**
 * 
 * 封装用户发在实践圈里发的动态的消息
 * @author       张洪斌/0050
 * @see          
 * @since        清清网系统, 2014年8月16日
 */
@Entity
@Table(name = "T_CIRCLE_MESSAGE")
@Component
public class Message {
	private String id;
	private int transmitnum; //被转发分享次数
	private long longtitude;
	private long latitude;
	private Date createtime;
	private String content;
	private String uid;
	private int state;       //信息是否已经被审核(0:等待审核  1：没有通过审核  2：通过审核)
	private Set<Picture> pictures = new HashSet<Picture>();
	private Set<Discuss> discusses = new HashSet<Discuss>();

	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "MESSAGE_ID", unique = true, nullable = false, length = 32)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "MESSAGE_LONGTITUDE")
	public long getLongtitude() {
		return longtitude;
	}

	public void setLongtitude(long longtitude) {
		this.longtitude = longtitude;
	}

	@Column(name = "MESSAGE_LATITUDE")
	public long getLatitude() {
		return latitude;
	}

	public void setLatitude(long latitude) {
		this.latitude = latitude;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MESSAGE_CREATE_TIME", length = 19)
	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	@Column(name = "MESSAGE_CONTENT", length = 300)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	
	@JoinColumn(name = "MESSAGEID")

	public Set<Picture> getPictures() {
		return pictures;
	}

	public void setPictures(Set<Picture> pictures) {
		this.pictures = pictures;
	}

	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name = "MID")
	public Set<Discuss> getDiscusses() {
		return discusses;
	}

	public void setDiscusses(Set<Discuss> discusses) {
		this.discusses = discusses;
	}

	@Column(name = "MESSAGE_TRANSMITNUM")
	public int getTransmitnum() {
		return transmitnum;
	}

	public void setTransmitnum(int transmitnum) {
		this.transmitnum = transmitnum;
		 
	}

	@Column(name = "MESSAGE_UID")
	public String getUid() {
		return uid;
	}

	@Column(name = "MESSAGE_STATE")
	public void setUid(String uid) {
		this.uid = uid;
	}

	@Column(name = "MESSAGE_STATE")
	public int getState() {
		return state;
	}

	
	public void setState(int state) {
		this.state = state;
	}
	
	
	

}
