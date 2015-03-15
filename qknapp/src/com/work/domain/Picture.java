/*
 * Title:        清清网系统 2014年8月16日
 * Description:  实践圈消息中的图片对象封装
 * Copyright:    Copyright (c) 2014
 * Company:      清清网
 * @author       张洪斌
 * @version      2.0  2014年8月16日
 */
package com.work.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.context.annotation.Lazy;

/**
 * 封装了实践圈里面的多条消息对象
 * 
 * @author       张洪斌
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014年8月16日
 */
@Entity
@Table(name = "T_CIRCLE_PICTURE")
@Lazy
public class Picture
{
	private String id;            //主键
	private String src;        //图片路径
	
	
	public Picture()
	{
		
	}
	
	public Picture(String id,String src)
	{
		this.id = id;
		this.src = src;
	}
	
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "PICTURE_ID", unique = true, nullable = false, length = 32)
	public String getId()
	{
		return id;
	}
	
	public void setId( String id )
	{
		this.id = id;
	}
	
	
	@Column(name = "PICTURE_SRC",length = 50)
	public String getSrc()
	{
		return src;
	}
	
	public void setSrc( String src )
	{
		this.src = src;
	}
	
	
}
