package com.qing.right.service;

import java.util.List;
import java.util.Map;

import com.qing.common.exception.DaoException;
import com.qing.common.exception.ServiceException;
import com.qing.common.util.Page;
import com.qing.right.dao.domain.BaseDuties;
import com.qing.vo.DutiesVO;

public interface IBaseDutiesService {
   
    /**
     * 获取所有的职务信息
     * 
     * @return
     * @throws ServiceException
     */
    public Map<String, BaseDuties> getDutiedesc() throws ServiceException;
    
    /**
     * 保存或者修改职位
     * 
     * @param baseDuties
     * @throws ServiceException
     */
    public void saveOrUpdateBaseDuties(BaseDuties baseDuties) throws ServiceException;
    
    /**
     * 查询子组织用户
     * 
     * @param organId
     * @return
     * @throws ServiceException
     */
    @SuppressWarnings("rawtypes")
	public Page<BaseDuties> queryDutiesByOrganId(Page page, String organId) throws ServiceException;
    

    /**
     * 根据组织ID将当前组织和子组织的职位都查询出来
     * 
     * @param organId
     * @return
     * @throws DaoException
     */
    public List<DutiesVO> getDutiesList(String organId) throws ServiceException;
    
    /**
     * 根据职务ID将对应的职务信息查询出来
     * 
     * @param dutiesId
     * @return
     * @throws DaoException
     */
    public BaseDuties getBaseDuties(String dutiesId) throws ServiceException;
    
    /**
     * 根据一组ID或单个ID进行删除 
     * 
     * @author wangxiang
     * @since 深烟智能物流管理平台, 2013-1-8
     * @param ids
     * @return
     */
    public List<String> deleteBaseDuties(String[] ids) throws ServiceException;
    
  
    
}
