/*
 * Title:        清清网系统 2014-8-23
 * Description:  [描述模块的功能、作用、使用方法和注意事项]
 * Copyright:    Copyright (c) 2014
 * Company:      清清网
 * @author       luoqinglong
 * @version      2.0  2014-8-23
 */
package com.work.service.member.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qing.common.exception.DaoException;
import com.qing.common.exception.ServiceException;
import com.qing.common.exception.handle.ExceptionHandle;
import com.qing.common.util.ErrorCode;
import com.work.dao.member.IMemberLevelDao;
import com.work.domain.MemberLevel;
import com.work.service.member.IMemberLevelService;

/**
 * (一句话功能简述)
 * (功能详细描述)
 * @author       luoqinglong
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014-8-23
 */
@Service
public class MemberLevelServiceImpl implements IMemberLevelService {
	@Resource
	IMemberLevelDao memberLevelDao;

	@Override
	public List<MemberLevel> findAllMemberLevel() throws ServiceException {
		List<MemberLevel> list = null;
		try {
			list = this.memberLevelDao.findAllMemberLevel();
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004,
					"MemberLevelServiceImpl.findAllMemberLevel()");
		}
		return list;
	}

}
