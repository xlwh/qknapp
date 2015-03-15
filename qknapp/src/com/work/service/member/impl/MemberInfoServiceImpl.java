/*
 * Title:        清清网系统 2014年8月16日
 * Description:  [描述模块的功能、作用、使用方法和注意事项]
 * Copyright:    Copyright (c) 2014
 * Company:      清清网
 * @author       Sandy Wong
 * @version      2.0  2014年8月16日
 */
package com.work.service.member.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.qing.common.exception.DaoException;
import com.qing.common.exception.DataException;
import com.qing.common.exception.ServiceException;
import com.qing.common.exception.handle.ExceptionHandle;
import com.qing.common.util.ErrorCode;
import com.qing.common.util.Page;
import com.qing.common.util.PhoneUtil;
import com.qing.common.util.SmsManager;
import com.qing.common.util.StringUtil;
import com.qing.constant.Constants;
import com.work.dao.member.IMemberInfoDao;
import com.work.domain.MemberInfo;
import com.work.domain.ScoreDetail;
import com.work.domain.ScoreSetting;
import com.work.service.member.IMemberInfoService;
import com.work.service.member.IScoreDetailService;
import com.work.service.member.IScoreSettingService;
import com.work.service.sms.ISmsConfigService;
import com.work.service.validate.IValidateCodeService;

/**
 * (一句话功能简述)
 * (功能详细描述)
 * @author       Sandy Wong
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014年8月16日
 */
@Service
@Repository
public class MemberInfoServiceImpl implements IMemberInfoService {

	private static Log log = LogFactory.getLog(MemberInfoServiceImpl.class);

	@Resource
	IMemberInfoDao dao;

	@Resource
	IValidateCodeService validateCodeService;

	@Resource
	ISmsConfigService smsConfigService;

	@Resource
	IScoreDetailService scoreDetailService;

	@Resource
	IScoreSettingService scoreSettingService;

	/**
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.service.member.IMemberInfoService#saveOrUpdateInfo(com.work.domain.MemberInfo)
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月16日
	 * @param info
	 * @throws ServiceException
	 */
	@Override
	public void saveOrUpdateInfo(MemberInfo info) throws ServiceException {
		if (null == info) {
			return;
		}
		if (null == info.getMemberId()) {
			info.setCreateTime(new Date());
			info.setTotalScore(0);
			info.setRemainScore(0);
		}
		try {
			dao.saveOrUpdateInfo(info);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR, "MemberInfoServiceImpl.saveOrUpdateInfo");
		}
	}

	/**
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.service.member.IMemberInfoService#deleteInfo(java.util.List)
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月16日
	 * @param info
	 * @throws ServiceException
	 */
	@Override
	public void deleteInfos(String[] ids) throws ServiceException {
		List<MemberInfo> memberInfos = new ArrayList<MemberInfo>();
		MemberInfo info;
		try {
			for (int i = 0; i < ids.length; i++) {
				info = dao.getMemberInfo(ids[i]);
				memberInfos.add(info);
			}
			dao.deleteInfo(memberInfos);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR, "MemberInfoServiceImpl.deleteInfos");
		}

	}

	/**
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.service.member.IMemberInfoService#getMemberInfo(java.lang.String)
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月16日
	 * @param id
	 * @return
	 * @throws ServiceException
	 * @throws ServiceException 
	 */
	@Override
	public MemberInfo getMemberInfo(String id) throws ServiceException {
		MemberInfo info = null;
		try {
			info = dao.getMemberInfo(id);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR, "MemberInfoServiceImpl.getMemberInfo");
		}
		return info;
	}

	/**
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.service.member.IMemberInfoService#queryMemberInfo(com.qing.common.util.Page, com.work.domain.MemberInfo, java.lang.String, java.lang.String)
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月16日
	 * @param page
	 * @param info
	 * @param orderColumn
	 * @param order
	 * @throws ServiceException
	 */
	@Override
	public void queryMemberInfo(Page<MemberInfo> page, MemberInfo info, String orderColumn, String order)
			throws ServiceException {
		try {
			dao.queryMemberInfo(page, info, orderColumn, order);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR, "MemberInfoServiceImpl.queryMemberInfo");
		}
	}

	@Override
	public List<MemberInfo> queryMemberInfo(MemberInfo info) throws ServiceException {
		try {
			return dao.queryMemberInfo(info);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR, "MemberInfoServiceImpl.queryMemberInfo");
		}
		return null;
	}

	/**
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.service.member.IMemberInfoService#sendValidateCode(com.work.domain.MemberInfo, java.lang.String)
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月19日
	 * @param info
	 * @param senderType
	 * @param function
	 * @throws ServiceException
	 * @throws DataException 
	 */
	@Override
	public void sendValidateCode(MemberInfo info, String senderType, String function) throws ServiceException,
			DataException {
		if (Constants.SENDER_TYPE_EMAIL.equalsIgnoreCase(senderType)) { // 邮箱验证
			//TODO 用邮箱的方式发验证码暂时不做

		} else if (Constants.SENDER_TYPE_PHONE.equalsIgnoreCase(senderType)) { // 手机号码验证
			try {
				if (info != null && StringUtil.isValidStr(info.getCellphone())) { //手机号码不为空
					boolean canSend = validateCodeService.canGenerateValidateCode(info.getCellphone(), function);
					if (canSend) {
						// 生成验证码
						String code = validateCodeService.generateValidateCode(info.getCellphone(), function);
						log.info("code=" + code);
						// 发送到手机
						String content = "你好，在清清网注册的手机验证码是：" + code;
						String send = SmsManager.smsSender(info.getCellphone(), content, smsConfigService);
						if (StringUtil.isNullOrEmpty(send)) {
							//						ExceptionHandle.throwServiceException(new Exception("sender"), ErrorCode.SERVICE_ERROR,
							//								"MemberInfoServiceImpl.sendValidateCode");
						}
					} else {
						log.error("Has sent too much time. cellphone=" + info.getCellphone());
						ExceptionHandle.throwDataException(
								new Exception("Has sent too much time. cellphone=" + info.getCellphone()), "sendTimes",
								"MemberInfoServiceImpl.sendValidateCode");
					}

				} else { // 如果手机为空则报错
					log.error("No phone number");
					ExceptionHandle.throwDataException(new Exception("No phone number"), "cellphone",
							"MemberInfoServiceImpl.sendValidateCode");
				}
			} catch (DaoException e) {
				ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR,
						"MemberInfoServiceImpl.sendValidateCode");
			}

		} else {
			log.error("No such senderType. senderType=" + senderType);
			ExceptionHandle.throwDataException(new Exception("No such senderType. senderType=" + senderType),
					"senderType", "MemberInfoServiceImpl.sendValidateCode");
		}
	}

	/**
	 * 
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.service.member.IMemberInfoService#registerMemberInfo(com.work.domain.MemberInfo, java.lang.String, java.lang.String)
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月19日
	 * @param info
	 * @param validateCode
	 * @param senderType
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public MemberInfo registerMemberInfo(MemberInfo info, String validateCode, String senderType)
			throws ServiceException, DataException {
		if (Constants.SENDER_TYPE_EMAIL.equalsIgnoreCase(senderType)) { // 邮箱验证
			//TODO 用邮箱的方式发验证码暂时不做

		} else if (Constants.SENDER_TYPE_PHONE.equalsIgnoreCase(senderType)) { // 手机号码验证
			try {
				if (info != null && StringUtil.isValidStr(info.getCellphone())) { //手机号码不为空
					boolean isValid = validateCodeService.checkValidateCode(info.getCellphone(), validateCode,
							Constants.FUNCTION_REGISTER);
					if (isValid) {
						info.setLoginType(1);
						info.setLoginCode(info.getCellphone());
						info.setMemberName(info.getCellphone());
						info.setPasswordUpdateTime(new Date());
						info.setStatus(1);
						//根据手机号码获取地址 add by huangqingjian
						Map<String, String> map = PhoneUtil.getMobileLocation(info.getCellphone());
						if (null != map) {
							if (StringUtil.isValidStr(map.get("province"))) {
								info.setProvince(map.get("province"));
							}
							if (StringUtil.isValidStr(map.get("city"))) {
								info.setCity(map.get("city"));
							}
						}
						saveOrUpdateInfo(info);
						return info;
					} else {
						ExceptionHandle.throwDataException(new Exception("wrong validateCode"), "validateCode",
								"MemberInfoServiceImpl.registerMemberInfo");
					}

				} else { // 如果手机为空则报错
					log.error("No phone number");
					ExceptionHandle.throwDataException(new Exception("No phone number"), "cellphone",
							"MemberInfoServiceImpl.registerMemberInfo");
				}
			} catch (DaoException e) {
				ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR,
						"MemberInfoServiceImpl.registerMemberInfo");
			} catch (Exception e) {
				ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR,
						"MemberInfoServiceImpl.registerMemberInfo");
			}

		} else {
			log.error("No such senderType. senderType=" + senderType);
			ExceptionHandle.throwDataException(new Exception("No such senderType. senderType=" + senderType),
					"senderType", "MemberInfoServiceImpl.registerMemberInfo");
		}
		return null;
	}

	/**
	 * 重制密码
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月21日
	 * @param info
	 * @param validateCode
	 * @param senderType
	 * @param newPassword
	 * @return
	 * @throws ServiceException
	 * @throws DataException
	 */
	@Override
	public boolean resetPassword(MemberInfo info, String validateCode, String senderType, String newPassword)
			throws ServiceException, DataException {
		if (Constants.SENDER_TYPE_EMAIL.equalsIgnoreCase(senderType)) { // 邮箱验证
			//TODO 用邮箱的方式发验证码暂时不做

		} else if (Constants.SENDER_TYPE_PHONE.equalsIgnoreCase(senderType)) { // 手机号码验证
			try {
				if (info != null && StringUtil.isValidStr(info.getCellphone())) { //手机号码不为空
					boolean isValid = validateCodeService.checkValidateCode(info.getCellphone(), validateCode,
							Constants.FUNCTION_RESET_PASSWORD);
					if (isValid) {
						info.setPassword(newPassword);
						info.setPasswordUpdateTime(new Date());
						saveOrUpdateInfo(info);
						return true;
					} else {
						ExceptionHandle.throwDataException(new Exception("wrong validateCode"), "validateCode",
								"MemberInfoServiceImpl.resetPassword");
					}

				} else { // 如果手机为空则报错
					log.error("No phone number");
					ExceptionHandle.throwDataException(new Exception("No phone number"), "cellphone",
							"MemberInfoServiceImpl.resetPassword");
				}
			} catch (DaoException e) {
				ExceptionHandle
						.throwServiceException(e, ErrorCode.SERVICE_ERROR, "MemberInfoServiceImpl.resetPassword");
			}

		} else {
			log.error("No such senderType. senderType=" + senderType);
			ExceptionHandle.throwDataException(new Exception("No such senderType. senderType=" + senderType),
					"senderType", "MemberInfoServiceImpl.resetPassword");
		}
		return false;
	}

	/**
	 * 修改密码
	 * @see com.work.service.member.IMemberInfoService#changePassword(java.lang.String, java.lang.String, java.lang.String)
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月21日
	 * @param memberId
	 * @param oldPassword
	 * @param newPassword
	 * @return
	 * @throws ServiceException
	 * @throws DataException
	 */
	@Override
	public boolean changePassword(String memberId, String oldPassword, String newPassword) throws ServiceException,
			DataException {
		MemberInfo memberInfo = getMemberInfo(memberId);
		if (null == memberInfo) {
			log.error("No such a member. memberId=" + memberId);
			ExceptionHandle.throwDataException(new Exception("No such a member. memberId=" + memberId), "member",
					"MemberInfoServiceImpl.changePassword");
		}
		if (!memberInfo.getPassword().equals(oldPassword)) {
			log.error("Old password wrong. password=" + memberInfo.getPassword() + ", oldPassword=" + oldPassword);
			ExceptionHandle.throwDataException(new Exception("Old password wrong. password=" + memberInfo.getPassword()
					+ ", oldPassword=" + oldPassword), "password", "MemberInfoServiceImpl.changePassword");
		}
		memberInfo.setPassword(newPassword);
		memberInfo.setPasswordUpdateTime(new Date());
		saveOrUpdateInfo(memberInfo);
		return true;
	}

	/**
	 * 
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.service.member.IMemberInfoService#addScore(com.work.domain.ScoreDetail)
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月8日
	 * @param detail
	 * @throws ServiceException
	 */
	@Override
	public void addScore(ScoreDetail detail) throws ServiceException {
		try {
			if (StringUtil.isValidStr(detail.getTarget())) {
				ScoreSetting setting = scoreSettingService.getScoreSettingByTarget(detail.getTarget());
				if (setting != null) {
					detail.setScore(setting.getScore());
				}
			}
			dao.addScore(detail.getMemberId(), detail.getScore());
			scoreDetailService.saveOrUpdateDetail(detail);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR, "MemberInfoServiceImpl.addScore");
		}
	}

	/**
	 * 
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.service.member.IMemberInfoService#deleteScore(com.work.domain.ScoreDetail, boolean)
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月8日
	 * @param detail
	 * @param withTotalScore
	 * @throws ServiceException
	 */
	@Override
	public void deleteScore(ScoreDetail detail, boolean withTotalScore) throws ServiceException {
		try {
			if (StringUtil.isValidStr(detail.getTarget())) {
				ScoreSetting setting = scoreSettingService.getScoreSettingByTarget(detail.getTarget());
				if (setting != null) {
					detail.setScore(setting.getScore());
				}
			}
			if (detail.getScore() > 0) {
				detail.setScore(detail.getScore() * -1);
			}
			dao.deleteScore(detail.getMemberId(), Math.abs(detail.getScore()), withTotalScore);
			scoreDetailService.saveOrUpdateDetail(detail);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR, "MemberInfoServiceImpl.deleteScore");
		}
	}
}
