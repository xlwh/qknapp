package com.qing.constant;

public interface Constants {

	// 导出excel
	String EXPORT_EXCEL = "1";

	// 导出txt
	String EXPORT_TXT = "0";

	/**
	 * 角色 启/停用生效标识 Y/N
	 */
	String ENABLE_FLAG = "Y";

	String ENABLE_STR = "是";

	String DISABLE_FLAG = "N";

	String DISABLE_STR = "否";

	/**
	 * 是否管理 N=非管理员 Y=管理员
	 */
	String ISADMIN = "Y";

	String ISNOTADMIN = "N";

	/**
	 * 组织标记 0：部门，1为公司
	 */
	String DEPARTMENT_FLAG = "0";

	String DEPARTMENT = "部门";

	String COMPANY_FLAG = "1";

	String COMPANY = "公司";

	/**
	 * 组织形式： 1、企业实体组织 2、企业虚拟组织
	 */
	String ENTITY_ORGAN = "企业实体组织";

	String ENTITY_ORGAN_FLAG = "1";

	String INVENTED_ORGAN = "企业虚拟组织";

	String INVENTED_ORGAN_FLAG = "2";

	// 超级管理员所在的组织，是一个系统级的，便于管理
	String ADMIN_ORGAN = "0";

	String TOPGROUPID = "1";
	String TOPGROUPNAME = "集团总公司";
	String SGROUPID = "2";
	String SGROUPNAME = "下属全资公司";
	String TGROUPID = "3";
	String TGROUPNAME = "下属控股公司";
	String FGROUPID = "4";
	String FGROUPNAME = "下属参股公司";
	String FIGROUPID = "5";
	String FIGROUPMANE = "事业部";
	String COMPANYID = "6";
	String COMPANYNAME = "部门";

	// 产品图片上传相对路径
	String uploadProductImgPath = "upload/productImg/";

	// 产品属性
	String COLOR_CODE = "COLOR_ID";
	String COLOR_NAME = "颜色";
	String VOLUME_CODE = "VOLUME_ID";
	String VOLUME_NAME = "容量";
	String PURE_VOLUME_CODE = "PURE_VOLUME_ID";
	String PURE_VOLUME_NAME = "净含量";
	String GUIGE_CODE = "GUIGE_ID";
	String GUIGE_NAME = "规格";

	// 区分集团和品牌 用的是organ level
	/**
	 * 集团
	 */
	int GROUP_MARK = 3;

	/**
	 * 品牌
	 */
	int BRAND_MARK = 6;
	/**
	 * 大箱单品数
	 */
	int BIG_SINGLE_COUNT = 100;
	/**
	 * 中箱单品数
	 */
	int MIDDLE_SINGLE_COUNT = 50;

	// 包装分类
	/**
	 * 单品
	 */
	String SINGLE_PRODUCT = "0";

	/**
	 * 中箱
	 */
	String MIDDLE_BOX = "1";

	/**
	 * 大箱
	 */
	String BIG_BOX = "2";
	//

	/**
	 * 停用
	 */
	String IS_ACTIVE_STOP = "0";
	/**
	 * 启用
	 */
	String IS_ACTIVE_START = "1";

	// 入库类型
	/**
	 * 0 按出库单入库
	 */
	String OUTS_ORDER_TYPE = "0";
	String OUTS_ORDER_TYPE_TEXT = "正常入库";

	/**
	 * 1 按实际入库
	 */
	String REALITY_TYPE = "1";
	String REALITY_TYPE_TEXT = "异常入库";
	/**
	 * 实际扫码
	 */
	String IS_ACTUAL_SCAN = "1";
	/**
	 * 未实际扫码
	 */
	String NOT_ACTUAL_SCAN = "0";

	// 审批结果
	/**
	 * 不同意
	 */
	String DISAGREE = "0";

	/**
	 * 同意
	 */
	String AGREE = "1";

	// 审批状态
	/**
	 * 进行中
	 */
	String ALREADY_UNDER_WAY = "0";

	/**
	 * 已审批
	 */
	String ALREADY_APPROVAL = "1";
	/**
	 * cookie 一天的有效期
	 */
	int COOKIE_MAX_AGE_DAY = 24 * 60 * 60;
	/**
	 * 一年有效期
	 */
	int COOKIE_MAX_AGE_YEAR = 365 * 24 * 60 * 60;

	//退货明细的TYPE====start=========
	/**
	 * 正式状态
	 */
	String TYPE_OFFICIAL = "0";

	/**
	 * 临时状态
	 */
	String TYPE_TEMPORARY = "1";

	/**
	 * 转入成品库
	 */
	String TYPE_FINISH_CHANGE = "2";

	//退货明细的TYPE====end=========
	/**
	 * 自然日登录水滴
	 */
	String SYS_CONFIG_CODE_POINT_LOGIN_DAY = "point-login-day";
	/**
	 * 生日水滴倍数
	 */
	String SYS_CONFIG_CODE_POINT_BIRTHDAY = "point-birthday";
	/**
	 * 邮费水滴
	 */
	String SYS_CONFIG_CODE_POINT_EXPRESS_FEE = "point-express-fee";
	/**
	 * 注册成功水滴数
	 */
	String SYS_CONFIG_CODE_POINT_REGISTER = "point-register";
	/**
	 * 完善资料水滴数
	 */
	String SYS_CONFIG_CODE_POINT_PERFECT_INFO = "point-perfect-info";
	/**
	 * 第一次分享水滴数
	 */
	String SYS_CONFIG_CODE_POINT_FIRST_SHARE = "point-first-share";
	/**
	 * 评论后审核通过水滴数
	 */
	String SYS_CONFIG_CODE_POINT_APPROVED = "point-approved";
	/**
	 * 新品试用水滴数
	 */
	String SYS_CONFIG_CODE_POINT_PRODUCT_TRY = "point-product-try";
	String NOT_BIRTHDAY = "亲，这个月不是您的生日月，不能兑换生日礼品";
	String BELOW_LEVEL = "亲，您的会员等级不够，部分礼品不能兑换";

	/**
	 * 验证码发送方式
	 */
	String SENDER_TYPE_PHONE = "phone";
	String SENDER_TYPE_EMAIL = "email";

	/**
	 * 功能类型
	 */
	String FUNCTION_REGISTER = "register";
	String FUNCTION_RESET_PASSWORD = "resetPassword";

	/**
	 * 当天同一功能最多发送短信次数
	 */
	int MAX_SMS_SEND_TIME = 3;

	/**
	 * 手机号码正则表达式
	 */
	String REGEX_CELLPHONE = "^1[3458][0-9]\\d{8}$";

	/**
	 * 密码加密的key
	 */
	String PASSWORD_ENCRYPT_CODE = "%!#_qkn*,0";

	/**
	 * 推荐注册的target名称
	 */
	String TARGET_RECOMMEND = "recommend";

	/**
	 * 连续签到积分target名称
	 */
	String SIGN = "sign";

	/**
	 * 断续签到积分target名称
	 */
	String SIGN_BREAK = "sign_break";
}
