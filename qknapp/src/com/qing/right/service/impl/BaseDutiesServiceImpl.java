package com.qing.right.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.qing.common.base.service.impl.BaseServiceImpl;
import com.qing.common.exception.DaoException;
import com.qing.common.exception.ServiceException;
import com.qing.common.exception.handle.ExceptionHandle;
import com.qing.common.util.ErrorCode;
import com.qing.common.util.Page;
import com.qing.right.dao.domain.BaseDuties;
import com.qing.right.dao.impl.BaseDutiesDaoImpl;
import com.qing.right.service.IBaseDutiesService;
import com.qing.vo.DutiesVO;
import com.qing.vo.OrganVO;

@Service
@Transactional
public class BaseDutiesServiceImpl implements IBaseDutiesService {
    @Resource
    private BaseDutiesDaoImpl baseDutiesDaoImpl;

    @Resource
    private BaseServiceImpl<BaseDuties, String> baseService;
    @Resource
    public BaseOrganServiceImpl baseOrganServiceImpl;

    @Override
    public Map<String, BaseDuties> getDutiedesc() throws ServiceException {
	Map<String, BaseDuties> map = null;
	try {
	    map = baseDutiesDaoImpl.getDutiedesc();
	} catch (DaoException e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004, "BaseModuleServiceImpl.Dutiedesc()");
	}
	return map;
    }

    /**
     * 保存或者修改职位
     * 
     * @param baseLog
     * @throws ServiceException
     */
    @Override
    public void saveOrUpdateBaseDuties(BaseDuties baseDuties) throws ServiceException {
	try {
	    // 将空字符设置为null，否则""也会当成ID进行更新
	    if ("".equals(baseDuties.getDutieid())) {
		baseDuties.setDutieid(null);
	    }
	    baseService.saveOrUpdate(baseDuties);
	} catch (ServiceException e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.DAO_SAVE_ERROR_001, "BaseOrganServiceImpl.saveOrUpdateBaseOrgan()");
	}
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public Page<BaseDuties> queryDutiesByOrganId(Page page, String organId) throws ServiceException {
	try {
	    baseDutiesDaoImpl.queryDutiesByOrganId(page, organId);
	} catch (DaoException e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004, "BaseUserServiceImpl.queryDutiesByOrganId()");
	}
	return page;
    }

    /**
     * 根据组织ID将当前组织和子组织的职位都查询出来
     * 
     * @param organId
     * @return
     * @throws DaoException
     */
    @Override
    public List<DutiesVO> getDutiesList(String organId) throws ServiceException {

	List<BaseDuties> list = null;
	List<DutiesVO> dutieslist = new ArrayList<DutiesVO>();
	try {
	    // 取得该组织下所有子组织
	    List<OrganVO> organVOs = baseOrganServiceImpl.getOrganTree(organId);
	    list = baseDutiesDaoImpl.getDutiesList(organId, organVOs);

	    for (BaseDuties baseDuties : list) {

		DutiesVO dutiesVO = new DutiesVO();
		dutiesVO.setDutieid(baseDuties.getDutieid());
		dutiesVO.setDutiedesc(baseDuties.getDutiedesc());
		dutiesVO.setDutiename(baseDuties.getDutiename());
		dutiesVO.setOrganName(baseDuties.getBaseOrgan().getOrganName());
		dutiesVO.setOrganId(baseDuties.getBaseOrgan().getOrganId());

		dutieslist.add(dutiesVO);
	    }

	} catch (Exception e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004, "BaseDutiesServiceImpl.getDutiesList()");
	}
	return dutieslist;
    }

    /**
     * 根据职务ID将对应的职务信息查询出来
     * 
     * @param dutiesId
     * @return
     * @throws DaoException
     */
    @Override
    public BaseDuties getBaseDuties(String dutiesId) throws ServiceException {
	BaseDuties bd = null;
	try {
	    bd = baseDutiesDaoImpl.getBaseDuties(dutiesId);
	} catch (DaoException e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.DAO_QUERY_ERROR_004, "BaseDutiesServiceImpl.getBaseDuties()");
	}
	return bd;
    }

    @Override
    public List<String> deleteBaseDuties(String[] ids) throws ServiceException {
	List<String> list = new ArrayList<String>();
	if (null != ids && ids.length > 0) {

	    for (int i = 0; i < ids.length; i++) {
		try {
		    BaseDuties duties = baseDutiesDaoImpl.getBaseDuties(ids[i]);
		    List<String> userIds = baseDutiesDaoImpl.getUserIdList(ids[i]);
		    // 删除时，判断职位下是否 有用户
		    if (null != userIds && userIds.size() > 0) {
			list.add(ids[i]);
		    } else {
			baseService.remove(duties);
		    }

		} catch (DaoException e) {
		    ExceptionHandle.throwServiceException(e, ErrorCode.DAO_QUERY_ERROR_004, "BaseDutiesServiceImpl.deleteBaseDuties()");
		}
	    }

	}

	return list;
    }

}
