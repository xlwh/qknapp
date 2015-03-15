package com.work.vo;

import java.util.List;

import com.work.domain.ExpressDetail;

public class ExpressInfoVo {

	private String expressId;
	private String expressName;
	private Integer height;
	private Integer width;
	private String picUrl;
	private Integer widthOffset;
	private Integer heightOffset;
	private Integer defaultSet;
	private List<ExpressDetail> details;

	public String getExpressId() {
		return expressId;
	}

	public void setExpressId(String expressId) {
		this.expressId = expressId;
	}

	public String getExpressName() {
		return expressName;
	}

	public void setExpressName(String expressName) {
		this.expressName = expressName;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public Integer getWidthOffset() {
		return widthOffset;
	}

	public void setWidthOffset(Integer widthOffset) {
		this.widthOffset = widthOffset;
	}

	public Integer getHeightOffset() {
		return heightOffset;
	}

	public void setHeightOffset(Integer heightOffset) {
		this.heightOffset = heightOffset;
	}

	public Integer getDefaultSet() {
		return defaultSet;
	}

	public void setDefaultSet(Integer defaultSet) {
		this.defaultSet = defaultSet;
	}

	public List<ExpressDetail> getDetails() {
		return details;
	}

	public void setDetails(List<ExpressDetail> details) {
		this.details = details;
	}

}
