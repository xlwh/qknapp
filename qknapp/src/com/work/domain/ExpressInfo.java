package com.work.domain;

// Generated 2014-1-23 17:17:12 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * TExpressInfo generated by hbm2java
 */
@Entity
@Table(name = "T_EXPRESS_INFO")
public class ExpressInfo implements java.io.Serializable {

	private static final long serialVersionUID = -1286926162936615528L;
	private String expressId;
	private String expressName;
	private Integer height;
	private Integer width;
	private String picUrl;
	private Integer widthOffset;
	private Integer heightOffset;
	private Integer defaultSet;

	public ExpressInfo() {
	}

	public ExpressInfo(String expressId, String expressName) {
		this.expressId = expressId;
		this.expressName = expressName;
	}

	public ExpressInfo(String expressId, String expressName, Integer height, Integer width, String picUrl,
			Integer widthOffset, Integer heightOffset, Integer defaultSet) {
		this.expressId = expressId;
		this.expressName = expressName;
		this.height = height;
		this.width = width;
		this.picUrl = picUrl;
		this.widthOffset = widthOffset;
		this.heightOffset = heightOffset;
		this.defaultSet = defaultSet;
	}

	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "EXPRESS_ID", unique = true, nullable = false, length = 32)
	public String getExpressId() {
		return this.expressId;
	}

	public void setExpressId(String expressId) {
		this.expressId = expressId;
	}

	@Column(name = "EXPRESS_NAME", nullable = false, length = 20)
	public String getExpressName() {
		return this.expressName;
	}

	public void setExpressName(String expressName) {
		this.expressName = expressName;
	}

	@Column(name = "HEIGHT")
	public Integer getHeight() {
		return this.height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	@Column(name = "WIDTH")
	public Integer getWidth() {
		return this.width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	@Column(name = "PIC_URL", length = 500)
	public String getPicUrl() {
		return this.picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	@Column(name = "WIDTH_OFFSET")
	public Integer getWidthOffset() {
		return this.widthOffset;
	}

	public void setWidthOffset(Integer widthOffset) {
		this.widthOffset = widthOffset;
	}

	@Column(name = "HEIGHT_OFFSET")
	public Integer getHeightOffset() {
		return this.heightOffset;
	}

	public void setHeightOffset(Integer heightOffset) {
		this.heightOffset = heightOffset;
	}

	@Column(name = "DEFAULT_SET")
	public Integer getDefaultSet() {
		return defaultSet;
	}

	public void setDefaultSet(Integer defaultSet) {
		this.defaultSet = defaultSet;
	}

}