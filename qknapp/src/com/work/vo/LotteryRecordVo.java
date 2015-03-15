/*
 * Title:        二维码系统 2014年7月5日
 * Description:  [描述模块的功能、作用、使用方法和注意事项]
 * Copyright:    Copyright (c) 2012
 * Company:      Tai woo. Co., Ltd
 * @author       huangqingjian/0050
 * @version      1.0  2014年7月5日
 */
package com.work.vo;

/**
 * 查询中奖记录
 * (一句话功能简述)
 * (功能详细描述)
 * @author       huangqingjian/0050
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        二维码系统, 2014年7月5日
 */
public class LotteryRecordVo {
	private String lotId;
	private String brandId;
	private String lotteryId;
	private String prizeId;
	private String prizeName;
	private String productId;
	private String cellPhone;
	private String contacts;
	private String phoneCheck;
	private Integer isWinned;
	private Integer isExpiry;
	private String expiryTime;
	private String scanIp;
	private String scanAddress;
	private String creator;
	private String createDate;
	private String endCreateDate;
	private String modifier;
	private String modifyDate;

	public String getLotId() {
		return lotId;
	}

	public void setLotId(String lotId) {
		this.lotId = lotId;
	}

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	public String getLotteryId() {
		return lotteryId;
	}

	public void setLotteryId(String lotteryId) {
		this.lotteryId = lotteryId;
	}

	public String getPrizeId() {
		return prizeId;
	}

	public void setPrizeId(String prizeId) {
		this.prizeId = prizeId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getPhoneCheck() {
		return phoneCheck;
	}

	public void setPhoneCheck(String phoneCheck) {
		this.phoneCheck = phoneCheck;
	}

	public Integer getIsWinned() {
		return isWinned;
	}

	public void setIsWinned(Integer isWinned) {
		this.isWinned = isWinned;
	}

	public Integer getIsExpiry() {
		return isExpiry;
	}

	public void setIsExpiry(Integer isExpiry) {
		this.isExpiry = isExpiry;
	}

	public String getScanIp() {
		return scanIp;
	}

	public void setScanIp(String scanIp) {
		this.scanIp = scanIp;
	}

	public String getScanAddress() {
		return scanAddress;
	}

	public void setScanAddress(String scanAddress) {
		this.scanAddress = scanAddress;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public String getExpiryTime() {
		return expiryTime;
	}

	public void setExpiryTime(String expiryTime) {
		this.expiryTime = expiryTime;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getEndCreateDate() {
		return endCreateDate;
	}

	public void setEndCreateDate(String endCreateDate) {
		this.endCreateDate = endCreateDate;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getPrizeName() {
		return prizeName;
	}

	public void setPrizeName(String prizeName) {
		this.prizeName = prizeName;
	}

}
