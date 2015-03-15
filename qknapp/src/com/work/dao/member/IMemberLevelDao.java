/*
 * Title:        清清网系统 2014-8-23
 * Description:  [描述模块的功能、作用、使用方法和注意事项]
 * Copyright:    Copyright (c) 2014
 * Company:      清清网
 * @author       luoqinglong
 * @version      2.0  2014-8-23
 */
package com.work.dao.member;

import java.util.List;

import com.qing.common.exception.DaoException;
import com.work.domain.MemberLevel;

/**
 *会员等级
 * @author       luoqinglong
 * @since        清清网系统, 2014-8-23
 */
public interface IMemberLevelDao {
	List<MemberLevel> findAllMemberLevel() throws DaoException;
}
