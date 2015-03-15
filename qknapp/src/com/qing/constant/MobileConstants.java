package com.qing.constant;

public interface MobileConstants {

	/**
	 * 服务器错误
	 */
	String SERVICE_ERROR = "-1";

	/**
	 * 扫码过期
	 */
	String SCAN_TIME_TIME_OUT = "-2";

	/**
	 * 输入的参数不能为空
	 */
	String INPUT_IS_NULL = "-3";

	/**
	 * 用户还未登陆
	 */
	String USER_NOT_LOGIN = "-4";

	/**
	 * 查询信息为空
	 */
	String FIND_RESULT = "0";

	/**
	 * 保存信息成功
	 */
	String SAVE_RESULT = "0";

	/* ==== 扫码获取产品信息返回状态值 ==== */
	/**
	 * 不能解密该码
	 */
	String ERROR_CODE = "1";

	/**
	 * 根据码值没有找到产品信息
	 */
	String NOT_GET_MSG_BYCODE = "0";

	/* ==== 领取积分 ==== */
	/**
	 * 积分已经被领取了
	 */
	String POINT_IS_GET = "2";

	/**
	 * 领取积分成功
	 */
	String LOTTERY_POINT_OK = "0";

	/**
	 * 查询品牌故事为空
	 */
	String FIND_BRAND_STORY_IS_NULL = "0";

	/**
	 * 活动被禁用
	 */
	String NOT_ENABLE = "0";

	/**
	 * 活动还未开始
	 */
	String NOT_BEGIN = "1";

	/**
	 * 活动已经结束
	 */
	String ALREADY_ENDED = "2";

	/**
	 * 根据产品抽奖失败，抽奖次数为0
	 */
	String LOTTERY_NUM_ERROR_PRO = "3";

	/**
	 * 在线抽奖所有抽奖机会总和不足
	 */
	String LOTTERY_NUM_ERROR = "4";

	/**
	 * 奖项设置为空
	 */
	String PRIZE_NULL = "5";

	/**
	 * 奖项用完了
	 */
	String PRIZE_ZREO = "6";

	/**
	 * 没有中奖
	 */
	String NOT_WIN = "7";

}
