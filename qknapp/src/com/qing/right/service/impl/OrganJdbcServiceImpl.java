package com.qing.right.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qing.common.exception.ServiceException;
import com.qing.constant.Constants;
import com.qing.right.dao.impl.OrganJdbcTemplate;
import com.qing.right.service.IOrganJdbcService;
import com.qing.vo.OrganVO;

/**
 * @author guojun
 * @version 创建时间：2012-11-26 上午11:44:10
 */
@Service
public class OrganJdbcServiceImpl implements IOrganJdbcService {

    @Resource
    OrganJdbcTemplate organJdbcTemplate;

    @Override
    @SuppressWarnings("rawtypes")
    public String getOragnIds(String organId) {
	List list = organJdbcTemplate.getBaseOrganList(organId);
	if (null == list || list.size() <= 0) {
	    return null;
	}
	String organIdCommas = "";
	for (Iterator itr = list.iterator(); itr.hasNext();) {
	    Map map = (Map) itr.next();
	    organIdCommas = organIdCommas + map.get("ORGAN_ID").toString() + ",";
	}
	return organIdCommas.substring(0, organIdCommas.length() - 1);
    }

    /**
     * 此方法过滤当前传入的组织，用于列表加载使用 覆盖方法
     * 
     * @see com.qing.right.service.IOrganJdbcService#getOragnList(java.lang.String)
     * @author huangqingjian/0050
     * @see 相关函数,对于重要的函数建议注释
     * @since 深烟智能物流管理平台, 2013-1-7
     * @param organId
     * @return
     * @throws ServiceException
     */
    @Override
    @SuppressWarnings("rawtypes")
    public List<OrganVO> getOragnList(String organId) throws ServiceException {
	List list = organJdbcTemplate.getBaseOrganList(organId);
	if (null == list || list.size() <= 0) {
	    return null;
	}
	List<OrganVO> baseOrganList = new ArrayList<OrganVO>();
	OrganVO organVO = null;
	for (Iterator itr = list.iterator(); itr.hasNext();) {
	    Map map = (Map) itr.next();
	    organVO = new OrganVO();
	    if (null != map.get("ORGAN_ID")) {
		// 如果加载出来的组织与本身的组织一样，不能显示在列表里
		if (organId.equals(map.get("ORGAN_ID").toString())) {
		    continue;
		}
		organVO.setOrganId(map.get("ORGAN_ID").toString());
	    }
	    if (null != map.get("ORGAN_NAME")) {
		organVO.setOrganName(map.get("ORGAN_NAME").toString());
	    }

	    if (null != map.get("ORGAN_MARK")) {
		organVO.setOrganMark(map.get("ORGAN_MARK").toString());
		if (Constants.COMPANY_FLAG.equals(organVO.getOrganMark())) {
		    organVO.setOrganMarkStr(Constants.COMPANY);
		} else {
		    organVO.setOrganMarkStr(Constants.DEPARTMENT);
		}

	    }
	    if (null != map.get("ORGAN_TYPE")) {
		organVO.setOrganType(map.get("ORGAN_TYPE").toString());
		if (Constants.TOPGROUPID.equals(organVO.getOrganType())) {
		    organVO.setOrganTypeStr(Constants.TOPGROUPNAME);
		} else if (Constants.SGROUPID.equals(organVO.getOrganType())) {
		    organVO.setOrganTypeStr(Constants.SGROUPNAME);

		} else if (Constants.TGROUPID.equals(organVO.getOrganType())) {
		    organVO.setOrganTypeStr(Constants.TGROUPNAME);

		} else if (Constants.FGROUPID.equals(organVO.getOrganType())) {
		    organVO.setOrganTypeStr(Constants.FGROUPNAME);

		} else if (Constants.FIGROUPID.equals(organVO.getOrganType())) {
		    organVO.setOrganTypeStr(Constants.FIGROUPMANE);

		} else {
		    organVO.setOrganTypeStr(Constants.COMPANYNAME);
		}
	    }

	    if (null != map.get("ORGAN_FORM")) {
		organVO.setOrganForm(map.get("ORGAN_FORM").toString());
		if (Constants.ENTITY_ORGAN_FLAG.equals(organVO.getOrganForm())) {
		    organVO.setOrganFormStr(Constants.ENTITY_ORGAN);
		} else {
		    organVO.setOrganFormStr(Constants.INVENTED_ORGAN);
		}

	    }
	    baseOrganList.add(organVO);
	}
	return baseOrganList;
    }

    /**
     * 此方法不过滤自身组织 用于组织导航树和下拉选择框使用 覆盖方法
     * 
     * @see com.qing.right.service.IOrganJdbcService#getOrganTree(java.lang.String)
     * @author huangqingjian/0050
     * @see 相关函数,对于重要的函数建议注释
     * @since 深烟智能物流管理平台, 2013-1-7
     * @param organId
     * @return
     * @throws ServiceException
     */
    @SuppressWarnings("rawtypes")
    @Override
    public List<OrganVO> getOrganTree(String organId) throws ServiceException {
	List list = organJdbcTemplate.getBaseOrganList(organId);
	return buildOrganVO(list);
    }

    /**
     * 此方法不过滤自身组织 用于只显示公司类型的组织导航树和下拉选择框使用 覆盖方法
     * 
     * @see com.qing.right.service.IOrganJdbcService#getOrganTree(java.lang.String)
     * @author huangqingjian/0050
     * @see 相关函数,对于重要的函数建议注释
     * @since 深烟智能物流管理平台, 2013-1-7
     * @param organId
     * @return
     * @throws ServiceException
     */
    @SuppressWarnings("rawtypes")
    @Override
    public List<OrganVO> getCompanyTree(String organId) throws ServiceException {
	List list = organJdbcTemplate.getCompanyOrganList(organId);
	return buildOrganVO(list);
    }

    /**
     * 构建组织树对象 (一句话功能简述) (功能详细描述)
     * 
     * @author huangqingjian/0050
     * @see 相关函数,对于重要的函数建议注释
     * @since 深烟智能物流管理平台, 2013-1-8
     * @param list
     * @return
     */
    @SuppressWarnings("rawtypes")
    private List<OrganVO> buildOrganVO(List list) {
	if (null == list || list.size() <= 0) {
	    return null;
	}
	List<OrganVO> baseOrganList = new ArrayList<OrganVO>();
	OrganVO organVO = null;
	for (Iterator itr = list.iterator(); itr.hasNext();) {
	    Map map = (Map) itr.next();
	    organVO = new OrganVO();
	    if (null != map.get("ORGAN_ID")) {
		// 将超级管理员所在的系统组织过滤掉
		if (Constants.ADMIN_ORGAN.equals(map.get("ORGAN_ID").toString())) {
		    continue;
		}
		organVO.setOrganId(map.get("ORGAN_ID").toString());
	    }
	    if (null != map.get("ORGAN_NAME")) {
		organVO.setOrganName(map.get("ORGAN_NAME").toString());
	    }
	    if (null != map.get("ORGAN_FATHER")) {
		organVO.setOrganFatherId(map.get("ORGAN_FATHER").toString());
	    }
	    if (null != map.get("TREE_LEVEL")) {
		organVO.setOrganLevel(map.get("TREE_LEVEL").toString());
	    }
	    if (null != map.get("ORGAN_MARK")) {
		organVO.setOrganMark(map.get("ORGAN_MARK").toString());
	    }
	    if (null != map.get("ORGAN_TYPE")) {
		organVO.setOrganType(map.get("ORGAN_TYPE").toString());
	    }
	    if (null != map.get("SORT")) {
		String organTree = map.get("SORT").toString();

		// 将系统级组织去掉
		if (organTree.contains("ADMIN_ORGAN")) {
		    organTree = organTree.substring(organTree.indexOf("|") + 1, organTree.length());
		}
		if (organTree.indexOf("|") > 0) {
		    organVO.setOrganTree(organTree.replace("|", "-"));
		    organVO.setOrganTree(organVO.getOrganTree().replace("'", ""));
		    int pos = organVO.getOrganTree().lastIndexOf("-");
		    organVO.setOrganTreeFather(organVO.getOrganTree().substring(0, pos));
		} else {
		    organVO.setOrganTree(organVO.getOrganName());
		}
	    }
	    baseOrganList.add(organVO);
	}
	return baseOrganList;
    }

}
