/*
 * Title:        清清网系统 2014年8月16日
 * Description:  评论或者点赞对象的封装
 * Copyright:    Copyright (c) 2014
 * Company:      清清网
 * @author       张洪斌
 * @version      2.0  2014年8月16日
 */
package com.work.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 评论或者点赞对象的封装
 * 
 * @author       张洪斌
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014年8月16日
 */

@Entity
@Table(name = "T_CIRCLE_DISCUSS")
public class Discuss {
	private String id; //主键
	private String uid; //评论者
	private String content; //评论内容（如果类型为点赞，则评论内容为空）
	private int type; //类型：（0：评论  1：点赞）
	private int isnew; //标记这条评论是不是已经被读过
	private Date createtime;

	public Discuss(){
		
	}
	
	public Discuss(String id,String uid,String content,int type,int isnew,Date createtime){
		this.id = id;
		this.uid = uid;
		this.content = content;
		this.type = type;
		this.isnew = isnew;
		this.createtime = createtime;
	}
	
	@Id
	@Column(name = "DISCUSS_ID", unique = true, nullable = false, length = 32)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "DISCUSS_UID")
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	@Column(name = "DISCUSS_CONTENT")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "DISCUSS_TYPE")
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Column(name = "DISCUSS_ISNEW")
	public int getIsnew() {
		return isnew;
	}

	public void setIsnew(int isnew) {
		this.isnew = isnew;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DISCUSS_CREATE_TIME", length = 19)
	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

}
