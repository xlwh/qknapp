package com.qing.right.service;

import com.qing.common.exception.ServiceException;
import com.qing.common.util.Page;
import com.qing.right.dao.domain.BaseAgent;

public interface IBaseAgentService {
    /**
     * 根据查询条件取得代理设定信息
     * 
     * @param params
     *            参数
     * @return
     * @throws BusinessException
     */
    @SuppressWarnings("rawtypes")
	public Page<BaseAgent> queryBaseAgent(Page page, Object[] params) throws ServiceException;

}
