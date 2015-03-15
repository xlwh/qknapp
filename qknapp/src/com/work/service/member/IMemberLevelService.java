/*
 * Title:        清清网系统 2014-8-23
 * Description:  [描述模块的功能、作用、使用方法和注意事项]
 * Copyright:    Copyright (c) 2014
 * Company:      清清网
 * @author       huangqingjian
 * @version      2.0  2014-8-23
 */
package com.work.service.member;

import java.util.List;

import com.qing.common.exception.ServiceException;
import com.work.domain.MemberLevel;

/**
 *会员等级
 * @author       luoqinglong
 * @since        清清网系统, 2014-8-23
 */
public interface IMemberLevelService {
	List<MemberLevel> findAllMemberLevel() throws ServiceException;
}
