package com.qing.right.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.qing.common.base.service.impl.BaseServiceImpl;
import com.qing.common.exception.DaoException;
import com.qing.common.exception.ServiceException;
import com.qing.common.exception.handle.ExceptionHandle;
import com.qing.common.util.ErrorCode;
import com.qing.common.util.StringUtil;
import com.qing.constant.Constants;
import com.qing.enums.OrganFormEnum;
import com.qing.enums.OrganTypeEnum;
import com.qing.right.dao.domain.BaseOrgan;
import com.qing.right.dao.domain.BaseOrganDetail;
import com.qing.right.dao.impl.BaseOrganDaoImpl;
import com.qing.right.service.IBaseOrganDetailService;
import com.qing.right.service.IBaseOrganService;
import com.qing.right.tree.EntitySortProvider;
import com.qing.vo.OrganVO;
import com.qing.vo.ZtreeVo;

@Service
@Transactional
public class BaseOrganServiceImpl implements IBaseOrganService {
	@Resource
	private BaseOrganDaoImpl baseOrganDaoImpl;

	@Resource
	private BaseServiceImpl<BaseOrgan, String> baseService;

	@Resource
	private BaseServiceImpl<BaseOrganDetail, String> baseOrganDetailService;

	@Resource
	private IBaseOrganDetailService baseOrganDetailServiceImpl;

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public String getMaxOrganLev(String organFather, String lev) throws ServiceException {
		String organLev = null;
		try {
			organLev = baseOrganDaoImpl.getMaxOrganLev(organFather);

			if ("0".equals(organFather) && "0".equals(organLev)) {
				organLev = "001";
			} else {
				if (organLev != null && !"".equals(organLev)) {
					organLev = String.valueOf(Long.valueOf(1 + organLev) + 1).substring(1);
				} else {

					organLev = lev + "001";
				}
			}

		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004,
					"BaseOrganServiceImpl.getMaxOrganLev()");
		}
		return organLev;
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Boolean checkOrgan(String organFather, String organName) throws ServiceException {
		Boolean bool = false;
		try {
			bool = baseOrganDaoImpl.checkOrgan(organFather, organName);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004,
					"BaseOrganServiceImpl.checkOrgan()");
		}
		return bool;
	}

	/**
	 * 保存或者修改组织
	 * 
	 * @param baseOrgan
	 * @param baseOrganDetail
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public void saveOrUpdateBaseOrgan(BaseOrgan baseOrgan, BaseOrganDetail baseOrganDetail) throws ServiceException {
		String fatherOrganTemp = "";
		try {
			// 组织类别>=6则标记为部门
			if (Integer.valueOf(baseOrgan.getOrganType()) >= 6) {
				baseOrgan.setOrganMark(Constants.DEPARTMENT_FLAG);
			} else {
				baseOrgan.setOrganMark(Constants.COMPANY_FLAG);
			}
			String fatherOrgan = baseOrgan.getOrganFather();
			// 如果是集体总公司，则他的父组织是0
			if (Integer.valueOf(baseOrgan.getOrganType()) == 1) {
				baseOrgan.setOrganFather(Constants.ADMIN_ORGAN);
			}
			// 如果父组织不为空，则截取父组织Id
			if (StringUtils.isNotEmpty(fatherOrgan)) {
				if ("0".equals(fatherOrgan)) {
					// 新建顶级组织
					baseOrgan.setOrganLev(getMaxOrganLev(fatherOrgan, ""));
				}
				int index = fatherOrgan.indexOf("|");
				if (index > -1) {
					fatherOrganTemp = fatherOrgan.substring(0, index);
					baseOrgan.setOrganFather(fatherOrganTemp);
				}
				// add by chen haohao, 利用上组织等级
				int lastIndex = fatherOrgan.lastIndexOf("|");
				if (lastIndex > -1) {

					String fatherOrganLev = fatherOrgan.substring(lastIndex + 1, fatherOrgan.length());
					baseOrgan.setOrganLev(getMaxOrganLev(fatherOrganTemp, fatherOrganLev));
				}
			}
			// 如果新建组织需要生成组织编号
			if (baseOrgan.getOrganId() == null) {
					baseOrgan.setOrganNo(getMaxOrganNoByOrganType(baseOrgan.getOrganType()));
			}
			baseService.saveOrUpdate(baseOrgan);
			// 如果新建/更新门店，保存/修改组织详细信息
			if (OrganTypeEnum.DIRECTSALESTORE.getIndex().equals(baseOrgan.getOrganType())
					|| OrganTypeEnum.FGROUP.getIndex().equals(baseOrgan.getOrganType())) {
				baseOrganDetail.setOrganId(baseOrgan.getOrganId());
				baseOrganDetail.setOrganName(baseOrgan.getOrganName());
				baseOrganDetail.setOrganFatherId(baseOrgan.getOrganFather());
				BaseOrgan baseOrganTemp = getBaseOrgan(baseOrgan.getOrganFather());
				if (null != baseOrganTemp) {
					baseOrganDetail.setOrganFatherName(baseOrganTemp.getOrganName());
				}
				baseOrganDetailService.saveOrUpdate(baseOrganDetail);
			}
		} catch (ServiceException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.DAO_SAVE_ERROR_001,
					"BaseOrganServiceImpl.saveOrUpdateBaseOrgan()");
		}
	}

	@Override
	public BaseOrgan getBaseOrgan(String organId) throws ServiceException {
		BaseOrgan bo = null;
		try {
			bo = baseOrganDaoImpl.getBaseOrgan(organId);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.DAO_QUERY_ERROR_004,
					"BaseOrganServiceImpl.getBaseOrgan()");
		}
		return bo;
	}

	@Override
	public List<String> deleteBaseOrgans(String[] ids) throws ServiceException {

		List<String> list = new ArrayList<String>();
		if (null != ids && ids.length > 0) {

			for (int i = 0; i < ids.length; i++) {
				try {
					BaseOrgan organ = baseOrganDaoImpl.getBaseOrgan(ids[i]);

					// 组织下是否有子组织
					int organFatherNum = baseService.findNumByHQL(
							"select count(t.organId) from BaseOrgan t where t.organFather=?", new Object[] { ids[i] });
					// 组织下是否有职务
					int dutiesNum = baseService.findNumByHQL(
							"select count(t.baseOrgan.organId) from BaseDuties t where t.baseOrgan.organId=?",
							new Object[] { ids[i] });

					// 组织下是否有组织管理员
					int organUsersNum = baseService.findNumByHQL(
							"select count(t.baseOrgan.organId) from BaseOrganUser t where t.baseOrgan.organId=?",
							new Object[] { ids[i] });

					// 组织下是否有组织管理员
					int roleNum = baseService.findNumByHQL(
							"select count(t.baseOrgan.organId) from BaseRole t where t.baseOrgan.organId=?",
							new Object[] { ids[i] });

					int baseUserNum = baseService.findNumByHQL(
							"select count(t.baseOrgan.organId) from BaseUser t where t.baseOrgan.organId=?",
							new Object[] { ids[i] });

					// 是否有子组织
					if (organFatherNum > 0) {
						list.add(ids[i]);
					}
					// 删除时，判断组织下是否 有引用
					else if (dutiesNum > 0) {
						list.add(ids[i]);
					} else if (organUsersNum > 0) {
						list.add(ids[i]);
					} else if (roleNum > 0) {
						list.add(ids[i]);
					} else if (baseUserNum > 0) {
						list.add(ids[i]);
					} else {
						baseService.remove(organ);
					}
					baseOrganDetailServiceImpl.deleteByOrganId(organ.getOrganId());
				} catch (Exception e) {
					ExceptionHandle.throwServiceException(e, ErrorCode.DAO_QUERY_ERROR_004,
							"BaseOrganServiceImpl.deleteBaseOrgans()");
				}
			}

		}

		return list;
	}

	/**
	 * 
	 * 
	 * Description:取得该组织ID下的所有下级组织及本组织
	 * 
	 * @param organId
	 * @return
	 * @throws ServiceException
	 * @throws
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<OrganVO> getOrganTree(String organId) throws ServiceException {
		List<OrganVO> organVOs = null;
		List<Object[]> objs = new ArrayList<Object[]>();
		Map tempMap = new LinkedHashMap<String, Object[]>();
		Set organIDSet = new LinkedHashSet();
		List organObjList = new LinkedList();
		organIDSet.clear();
		organObjList.clear();
		tempMap.clear();
		try {
			// 根据组织id取得创建时间等于或大于的该组织，既可能为同级或下级组织
			objs = baseOrganDaoImpl.getBelowBaseOrganById(organId);
			if (objs.size() > 0) {
				// 首先把本组织的ID加入集合
				organIDSet.add(objs.get(0)[0]);
				organObjList.add(objs.get(0));
				// 循环判断该组织的父组织ID是否存在organSet集合中，如果存在则加入该组织的ID到集合中
				// 同时把该组织对象加入到organObjList列表中
				for (Object[] object : objs) {
					if (organIDSet.contains(object[2])) {
						organObjList.add(object);
						organIDSet.add(object[0]);
					}
				}
				// organObjList为该组织下级所有对象列表
				// 把对象封装为：如 ADMIN_ORGAN|兰瑟集团|黑瑟
				tempMap.put(((Object[]) organObjList.get(0))[0], ((Object[]) organObjList.get(0))[1]);
				for (int i = 1; i < organObjList.size(); i++) {
					Object[] temp = (Object[]) organObjList.get(i);
					tempMap.put(temp[0], tempMap.get(temp[2]) + "|" + temp[1]);
				}
				// System.out.println(tempMap.toString());
				organVOs = buildOrganVO(organObjList, tempMap);

				EntitySortProvider.sort(organVOs, "getOrganTree");
			}

		} catch (Exception e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.DAO_QUERY_ERROR_004,
					"BaseOrganServiceImpl.getOrganTree()");
		}
		return organVOs;
	}

	/**
	 * 
	 * 
	 * Description:构建组织树对象
	 * 
	 * @param list
	 * @param tempMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private List<OrganVO> buildOrganVO(List list, Map tempMap) {
		if (null == list || list.size() <= 0) {
			return null;
		}
		List<OrganVO> baseOrganList = new ArrayList<OrganVO>();

		for (Object object : list) {
			OrganVO organVO = new OrganVO();
			Object[] temp = (Object[]) object;
			if (!"ADMIN_ORGAN".equals(temp[1].toString())) {

				organVO.setOrganId(temp[0].toString());
				organVO.setOrganName(temp[1].toString());
				organVO.setOrganFatherId(temp[2].toString());
				organVO.setOrganMark(temp[3].toString());
				organVO.setOrganType(temp[4].toString());
				organVO.setOrganForm(temp[5].toString());
				organVO.setOrganTypeStr(OrganTypeEnum.getName(temp[4].toString()));
				organVO.setOrganFormStr(OrganFormEnum.getName(temp[5].toString()));
				organVO.setOrganLevel(StringUtil.objToString(temp[6]));
				String organTree = tempMap.get(temp[0]).toString();

				// 将系统级组织去掉
				if (organTree.contains("ADMIN_ORGAN")) {
					organTree = organTree.substring(organTree.indexOf("|") + 1, organTree.length());
				}
				organVO.setOrganTree(organVO.getOrganName());
				baseOrganList.add(organVO);
			}
		}

		return baseOrganList;
	}

	@Override
	public BaseOrgan getBaseOrganByLev(String orgLev) throws ServiceException {
		BaseOrgan baseOrgan = null;
		try {
			baseOrgan = baseOrganDaoImpl.getBaseOrganByLev(orgLev);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.DAO_QUERY_ERROR_004,
					"BaseOrganServiceImpl.getBaseOrganByLev()");
		}
		return baseOrgan;
	}

	@Override
	public List<BaseOrgan> getBrandGOrBListById(String brandId) throws ServiceException {
		try {
			BaseOrgan baseOrgan = getBaseOrgan(brandId);
			if (baseOrgan != null) {
				int lev = baseOrgan.getOrganLev().length();
				// 如果是集团进行如下操作
				if (lev <= Constants.GROUP_MARK) {
					return baseOrganDaoImpl.getJustSubOrganById(brandId);
				}
			}
			// 其它都返回nul
			return null;
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.DAO_QUERY_ERROR_004,
					"BaseOrganServiceImpl.getJustSubOrganById()" + "根据ID获取下级组织失败！");
			return null;
		}
	}

	@Override
	public String getBrandGOrBIdsToStringById(String brandId) throws ServiceException {
		List<BaseOrgan> bolist = new ArrayList<BaseOrgan>();
		BaseOrgan baseOrgan = getBaseOrgan(brandId);
		String str = null;
		try {
			if (baseOrgan != null) {
				int lev = baseOrgan.getOrganLev().length();
				// 如果是品牌或品牌下的进行如下操作
				if (lev > Constants.GROUP_MARK) {
					str = "'" + brandId + "'";
				} else if (lev == Constants.GROUP_MARK) {// 如果是集团进行如下操作
					bolist = baseOrganDaoImpl.getJustSubOrganById(baseOrgan.getOrganId());
					StringBuilder sb = new StringBuilder();
					if (bolist != null && bolist.size() > 0) {
						for (BaseOrgan bo : bolist) {
							sb.append("'");
							sb.append(bo.getOrganId());
							sb.append("'");
							sb.append(",");
						}
						str = sb.substring(0, sb.lastIndexOf(","));
					}
				}
			}
			return str;
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.DAO_QUERY_ERROR_004,
					"BaseOrganServiceImpl.getJustSubOrganById()" + "根据ID获取下级组织的ID的字符串失败！");
			return null;
		}
	}

	/**
	 * Description:获取组织的儿子节点
	 * 
	 * @param organizationId
	 * @return
	 * @throws ServiceException
	 * @see com.qing.right.service.IBaseOrganService#getSonOrganList(java.lang.String)
	 **/
	@Override
	public List<BaseOrgan> getSonOrganList(String organizationId) throws ServiceException {
		List<BaseOrgan> baseOrganList = new ArrayList<BaseOrgan>();
		try {
			baseOrganList = baseOrganDaoImpl.getSubOrganById(organizationId);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.DAO_QUERY_ERROR_004,
					"BaseOrganServiceImpl.getSonOrganList():获取子节点");
		}
		return baseOrganList;

	}

	@Override
	public String getMaxOrganNoByOrganType(String organType) throws ServiceException {
		String organNo = null;
		try {
			organNo = baseOrganDaoImpl.getMaxOrganNoByOrganType(organType);
			if (organNo != null && !"".equals(organNo)) {
				organNo = String.valueOf(Long.valueOf(1 + organNo) + 1).substring(1);
			} else {
				organNo = "100001";
			}
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.DAO_QUERY_ERROR_004,
					"BaseOrganServiceImpl.getMaxOrganNoByOrganType()" + "根据组织类别获取最组织编号失败！");
		}
		return organNo;
	}

	@Override
	public List<BaseOrgan> getAllDeptByOrgLev(String organLev, String organType) throws ServiceException {
		List<BaseOrgan> baseOrganList = null;
		try {
			baseOrganList = baseOrganDaoImpl.getAllDeptByOrgLev(organLev, organType);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004,
					"BaseOrganServiceImpl.getAllDeptByOrgLev()" + "根据当前组织级别，获取该组织所在集团的所有品牌/代理商/加盟店失败！");
		}
		return baseOrganList;
	}

	@Override
	public List<BaseOrgan> getDeptByOrgLev(String organLev, String organType) throws ServiceException {
		List<BaseOrgan> baseOrganList = null;
		try {
			baseOrganList = baseOrganDaoImpl.getDeptByOrgLev(organLev, organType);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004,
					"BaseOrganServiceImpl.getAllDeptByOrgLev()" + "根据当前组织级别，获取该组织下属组织失败！");
		}
		return baseOrganList;
	}

	/**
	 * 取品牌树
	 * 
	 * @param brandId
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public List<ZtreeVo> getOrganZTreeByBrandId(BaseOrgan baseOrgan) throws ServiceException {
		List<ZtreeVo> ztreeVos = null;
		List<BaseOrgan> baseOrgans = null;
		try {
			if (baseOrgan != null) {
				int lev = baseOrgan.getOrganLev().length();
				// 如果是集团进行如下操作
				if (lev <= Constants.GROUP_MARK) {

					baseOrgans = baseOrganDaoImpl.getBrandOgan(baseOrgan.getOrganId());
				} else {
					if (baseOrgan.getOrganType().equals(OrganTypeEnum.FACTORYGROUP.getIndex())) {
						baseOrgans = baseOrganDaoImpl.getBrandOgan(baseOrgan.getOrganFather());
					}
				}
			}
			// 其它都返回nul
			if (null != baseOrgans && baseOrgans.size() > 0) {
				ztreeVos = covertOrganToZtree(baseOrgans);
			}
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004,
					"BaseOrganServiceImpl.getOrganZTreeByBrandId()" + "取品牌树失败！");
		}
		return ztreeVos;
	}

	private List<ZtreeVo> covertOrganToZtree(List<BaseOrgan> baseOrgans) {
		List<ZtreeVo> ztreeVos = new ArrayList<ZtreeVo>();
		for (BaseOrgan baseOrgan : baseOrgans) {
			ZtreeVo ztreeVo = new ZtreeVo();
			ztreeVo.setId(baseOrgan.getOrganId());
			ztreeVo.setpId(baseOrgan.getOrganFather());
			ztreeVo.setName(baseOrgan.getOrganName());
			ztreeVo.setLevels(baseOrgan.getOrganLev());
			ztreeVos.add(ztreeVo);
		}
		return ztreeVos;
	}

	/**
	 * 获取组织的儿子节点
	 * 
	 * @param organizationId
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public String getSonOrganString(String organizationId) throws ServiceException {
		List<BaseOrgan> baseOrganList = new ArrayList<BaseOrgan>();
		String str = null;
		try {
			baseOrganList = baseOrganDaoImpl.getSubOrganById(organizationId);
			StringBuilder sb = new StringBuilder();
			if (baseOrganList != null && baseOrganList.size() > 0) {
				for (BaseOrgan bo : baseOrganList) {
					sb.append("'");
					sb.append(bo.getOrganId());
					sb.append("'");
					sb.append(",");
				}
				str = sb.substring(0, sb.lastIndexOf(","));
			}
			return str;
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.DAO_QUERY_ERROR_004,
					"BaseOrganServiceImpl.getSonOrganList():获取子节点");
			return null;
		}

	}

	@Override
	public List<BaseOrgan> getAllOrgan() throws ServiceException {
		try {
			return baseOrganDaoImpl.getAllOrgan();
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.DAO_QUERY_ERROR_004,
					"BaseOrganServiceImpl.getAllOrgan():获取所有组织出错");
		}
		return null;
	}

	@Override
	public List<BaseOrgan> getOrganByType(String type) throws ServiceException {

		try {
			return baseOrganDaoImpl.getOrganByType(type);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.DAO_QUERY_ERROR_004,
					"BaseOrganServiceImpl.getOrganByType():根据TYPE获取所有组织出错");
		}
		return null;
	}

	@Override
	public List<BaseOrgan> getOrganByErpCode(String erpCode) throws ServiceException {

		try {
			return baseOrganDaoImpl.getOrganByERPCode(erpCode);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.DAO_QUERY_ERROR_004,
					"BaseOrganServiceImpl.getOrganByType():根据TYPE获取所有组织出错");
		}
		return null;
	}
}
