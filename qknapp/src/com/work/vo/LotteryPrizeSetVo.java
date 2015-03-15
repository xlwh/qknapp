/*
 * Title:        二维码系统 2014年7月8日
 * Description:  [描述模块的功能、作用、使用方法和注意事项]
 * Copyright:    Copyright (c) 2012
 * Company:      Tai woo. Co., Ltd
 * @author       huangqingjian/0050
 * @version      1.0  2014年7月8日
 */
package com.work.vo;

import java.util.Date;

/**
 * 奖项设置查询实体
 * (功能详细描述)
 * @author       huangqingjian/0050
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        二维码系统, 2014年7月8日
 */
public class LotteryPrizeSetVo {
	private String prizeId;
	private String prizeName;
	private String lotteryId;
	private String lotteryGiftId;
	private int totalNum;
	private Integer usedNum;
	private String creator;
	private Date createDate;
	private String modifier;
	private Date modifyDate;
	private Integer winAngle;

	public String getPrizeId() {
		return prizeId;
	}

	public void setPrizeId(String prizeId) {
		this.prizeId = prizeId;
	}

	public String getPrizeName() {
		return prizeName;
	}

	public void setPrizeName(String prizeName) {
		this.prizeName = prizeName;
	}

	public String getLotteryId() {
		return lotteryId;
	}

	public void setLotteryId(String lotteryId) {
		this.lotteryId = lotteryId;
	}

	public String getLotteryGiftId() {
		return lotteryGiftId;
	}

	public void setLotteryGiftId(String lotteryGiftId) {
		this.lotteryGiftId = lotteryGiftId;
	}

	public int getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}

	public Integer getUsedNum() {
		return usedNum;
	}

	public void setUsedNum(Integer usedNum) {
		this.usedNum = usedNum;
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

	public Integer getWinAngle() {
		return winAngle;
	}

	public void setWinAngle(Integer winAngle) {
		this.winAngle = winAngle;
	}

}
