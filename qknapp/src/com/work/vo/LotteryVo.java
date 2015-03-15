/*
 * Title:        二维码系统 2014年7月4日
 * Description:  [描述模块的功能、作用、使用方法和注意事项]
 * Copyright:    Copyright (c) 2012
 * Company:      Tai woo. Co., Ltd
 * @author       huangqingjian/0050
 * @version      1.0  2014年7月4日
 */
package com.work.vo;

import java.util.Date;

/**
 * 
 * 抽奖活动查询条件
 * (一句话功能简述)
 * (功能详细描述)
 * @author       huangqingjian/0050
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        二维码系统, 2014年7月4日
 */
public class LotteryVo {
	private String lotteryId;
	private String brandId;
	private String lotteryName;
	private String lotteryDesc;
	private String beginTime;
	private String endTime;
	private Integer lotteryNumber;
	private Integer continueLottery;
	private Double winningRate;
	private String notWinInfo;
	private String notWinAngle;
	private String lotteryPic;
	private String creator;
	private Date createDate;
	private String modifier;
	private Date modifyDate;
	private Integer status;

	public String getLotteryId() {
		return lotteryId;
	}

	public void setLotteryId(String lotteryId) {
		this.lotteryId = lotteryId;
	}

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	public String getLotteryName() {
		return lotteryName;
	}

	public void setLotteryName(String lotteryName) {
		this.lotteryName = lotteryName;
	}

	public String getLotteryDesc() {
		return lotteryDesc;
	}

	public void setLotteryDesc(String lotteryDesc) {
		this.lotteryDesc = lotteryDesc;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Integer getLotteryNumber() {
		return lotteryNumber;
	}

	public void setLotteryNumber(Integer lotteryNumber) {
		this.lotteryNumber = lotteryNumber;
	}

	public Integer getContinueLottery() {
		return continueLottery;
	}

	public void setContinueLottery(Integer continueLottery) {
		this.continueLottery = continueLottery;
	}

	public Double getWinningRate() {
		return winningRate;
	}

	public void setWinningRate(Double winningRate) {
		this.winningRate = winningRate;
	}

	public String getNotWinInfo() {
		return notWinInfo;
	}

	public void setNotWinInfo(String notWinInfo) {
		this.notWinInfo = notWinInfo;
	}

	public String getNotWinAngle() {
		return notWinAngle;
	}

	public void setNotWinAngle(String notWinAngle) {
		this.notWinAngle = notWinAngle;
	}

	public String getLotteryPic() {
		return lotteryPic;
	}

	public void setLotteryPic(String lotteryPic) {
		this.lotteryPic = lotteryPic;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
