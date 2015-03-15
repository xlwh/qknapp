package com.qing.right.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.qing.common.base.dao.HibernateDao;
import com.qing.common.exception.DaoException;
import com.qing.common.exception.handle.ExceptionHandle;
import com.qing.common.util.ErrorCode;
import com.qing.enums.OrganTypeEnum;
import com.qing.right.dao.IBaseOrganDao;
import com.qing.right.dao.domain.BaseOrgan;

@Service
@Repository
public class BaseOrganDaoImpl implements IBaseOrganDao {

	@Resource
	private HibernateDao<String, String> hibernateDaoStr;

	@Resource
	private HibernateDao<BaseOrgan, String> hibernateDaoBaseOrgan;

	@Resource
	private HibernateDao<Object[], String> hibernateDaoBelowBaseOrgan;

	@Override
	public String getOrganParentId(String organId) throws DaoException {

		List<String> list = null;
		try {
			list = hibernateDaoStr.findByValues("select o.organFather from BaseOrgan as o where o.organId=? ", null,
					false);
			if (list != null && list.size() > 0) {
				return list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException("依据组织ID,取得父组织ID失败！");
		}
		return null;
	}

	@Override
	public String getMaxOrganLev(String organFather) throws DaoException {
		List<String> list = null;
		try {
			list = hibernateDaoStr.findByValues("select max(organLev) from BaseOrgan where organFather ='"
					+ organFather + "'", null, false);
			if (list.get(0) != null) {
				return list.get(0).toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException("依据上级组织ID,取得当前组织最大分级号失败！");
		}
		return null;
	}

	@Override
	public Boolean checkOrgan(String organFather, String organName) throws DaoException {
		Boolean bool = false;
		int count = 0;
		try {
			count = hibernateDaoStr.findNumByHQL(
					"select count(o.organId) from BaseOrgan as o where o.organFather=?1 and o.organName=?2",
					new Object[] { organFather, organName });
			if (count > 0) {
				bool = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException("检查组织名是否重复失败！");
		}
		return bool;
	}

	@Override
	public BaseOrgan getBaseOrgan(String organId) throws DaoException {
		BaseOrgan bo = null;
		try {
			bo = hibernateDaoBaseOrgan.findById(BaseOrgan.class, organId);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new DaoException("根据ID获取组织失败！");
		}
		return bo;
	}

	@Override
	public BaseOrgan getBaseOrganByLev(String orgLev) throws DaoException {
		List<BaseOrgan> bo = null;
		try {
			bo = hibernateDaoBaseOrgan.findByValues("from BaseOrgan as o where o.organLev = ? ",
					new Object[] { orgLev }, false);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new DaoException("根据级别获取组织失败！");
		}
		return bo != null && bo.size() > 0 ? bo.get(0) : null;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List getBelowBaseOrganById(String organId) throws DaoException {
		StringBuffer sb = new StringBuffer();
		List<Object[]> objs = new ArrayList<Object[]>();
		sb.append("SELECT ORGAN_ID, ORGAN_NAME, ORGAN_FATHER, ORGAN_MARK, ORGAN_TYPE, ORGAN_FORM,ORGAN_LEV")
				.append(" FROM T_BASE_ORGAN ")
				.append(" WHERE CREATE_TIME>=(SELECT CREATE_TIME FROM T_BASE_ORGAN WHERE ORGAN_ID = ? ) ")
				.append(" ORDER BY CREATE_TIME ASC ");

		objs = hibernateDaoBelowBaseOrgan.findBySQL(sb.toString(), new Object[] { organId });
		return objs;
	}

	@Override
	public List<BaseOrgan> getJustSubOrganById(String organId) throws DaoException {
		StringBuffer hql = new StringBuffer();
		hql.append("from BaseOrgan as o where o.organFather=? or o.organId=? ");
		if ("0".equals(organId)) {
			hql.append(" or o.organFather in (select bo.organId from BaseOrgan bo where bo.organFather='0')");
		}
		List<BaseOrgan> bo = new ArrayList<BaseOrgan>();
		try {
			bo = hibernateDaoBaseOrgan.findByValues(hql.toString(), new Object[] { organId, organId }, false);
		} catch (DaoException e) {
			ExceptionHandle.throwDaoException(e, ErrorCode.DAO_SAVE_ERROR_001, "BaseOrganDaoImpl.getJustSubOrganById()"
					+ "根据ID获取下级组织失败！");
		}
		return bo != null && bo.size() > 0 ? bo : null;

	}

	/**
	 * Description:获取该组织的下一级子组织
	 * 
	 * @param organId
	 * @return
	 * @throws DaoException
	 * @see com.qing.right.dao.IBaseOrganDao#getSubOrganById(java.lang.String)
	 **/
	@Override
	public List<BaseOrgan> getSubOrganById(String organId) throws DaoException {
		StringBuffer hql = new StringBuffer();
		hql.append("from BaseOrgan as o where o.organFather=? order by o.organName ");
		List<BaseOrgan> organList = new ArrayList<BaseOrgan>();
		try {
			organList = hibernateDaoBaseOrgan.findByValues(hql.toString(), new Object[] { organId }, false);
		} catch (DaoException e) {
			ExceptionHandle.throwDaoException(e, ErrorCode.DAO_SAVE_ERROR_001, "BaseOrganDaoImpl.getSubOrganById()"
					+ "根据ID获取下级组织失败！");
		}
		return organList;

	}

	/**
	 * 获取所有组织
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.qing.right.dao.IBaseOrganDao#getAllOrgan()
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        瑾泉二维码系统, 2013年12月12日
	 * @return
	 * @throws DaoException
	 */
	@Override
	public List<BaseOrgan> getAllOrgan() throws DaoException {
		StringBuffer hql = new StringBuffer();
		hql.append("from BaseOrgan");
		List<BaseOrgan> organList = new ArrayList<BaseOrgan>();
		try {
			organList = hibernateDaoBaseOrgan.findByValues(hql.toString(), null, false);
		} catch (DaoException e) {
			ExceptionHandle.throwDaoException(e, ErrorCode.DAO_SAVE_ERROR_001, "BaseOrganDaoImpl.getSubOrganById()"
					+ "根据ID获取下级组织失败！");
		}
		return organList;

	}

	@Override
	public List<BaseOrgan> getOrganByType(String type) throws DaoException {
		StringBuffer hql = new StringBuffer();
		hql.append("from BaseOrgan b where b.organType = ?");
		List<BaseOrgan> organList = new ArrayList<BaseOrgan>();
		try {
			organList = hibernateDaoBaseOrgan.findByValues(hql.toString(), new Object[] { type }, false);
		} catch (DaoException e) {
			ExceptionHandle.throwDaoException(e, ErrorCode.DAO_SAVE_ERROR_001, "BaseOrganDaoImpl.getOrganByType()"
					+ "根据TYPE获取组织失败！");
		}
		return organList;

	}
	@Override
	public List<BaseOrgan> getOrganByERPCode(String erpCode) throws DaoException {
		StringBuffer hql = new StringBuffer();
		hql.append("from BaseOrgan b where b.erpOrganCode = ?");
		List<BaseOrgan> organList = new ArrayList<BaseOrgan>();
		try {
			organList = hibernateDaoBaseOrgan.findByValues(hql.toString(), new Object[] { erpCode }, false);
		} catch (DaoException e) {
			ExceptionHandle.throwDaoException(e, ErrorCode.DAO_SAVE_ERROR_001, "BaseOrganDaoImpl.getOrganByType()"
					+ "根据TYPE获取组织失败！");
		}
		return organList;

	}
	/**
	 * 根据组织类型查找最大的门店编号
	 * 
	 * @param organType
	 * @return
	 * @throws DaoException
	 */
	@Override
	public String getMaxOrganNoByOrganType(String organType) throws DaoException {
		List<String> list = new ArrayList<String>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select max(organNo) from BaseOrgan ");
		try {
			list = hibernateDaoStr.findByValues(sb.toString(), null, false);
			if (list.size() < 2) {
				return list.get(0);
			}
		} catch (DaoException e) {
			ExceptionHandle.throwDaoException(e, ErrorCode.DAO_QUERY_ERROR_004,
					"BaseOrganDaoImpl.getMaxOrganNoByOrganType()" + "根据组织类型查找最大编号！");
		}
		return null;
	}

	@Override
	public List<BaseOrgan> getAllDeptByOrgLev(String organLev, String organType) throws DaoException {
		StringBuffer hql = new StringBuffer();
		List<BaseOrgan> organList = null;
		hql.append(" FROM BaseOrgan bo WHERE SUBSTRING(bo.organLev,1,3) = SUBSTRING(?,1,3) AND bo.organType in (")
				.append(organType).append(")");
		try {
			organList = hibernateDaoBaseOrgan.findByValues(hql.toString(), new Object[] { organLev }, false);
		} catch (DaoException e) {
			ExceptionHandle.throwDaoException(e, ErrorCode.DAO_QUERY_ERROR_004, "BaseOrganDaoImpl.getAllDeptByOrgLev()"
					+ "根据当前组织级别，获取该组织所在集团的所有品牌/代理商/加盟店失败！");
		}
		return organList;
	}

	@Override
	public List<BaseOrgan> getDeptByOrgLev(String organLev, String organType) throws DaoException {
		StringBuffer hql = new StringBuffer();
		List<BaseOrgan> organList = null;
		hql.append(" FROM BaseOrgan bo WHERE bo.organLev like '").append(organLev).append("%'")
				.append(" AND bo.organType in (").append(organType).append(")");
		try {
			organList = hibernateDaoBaseOrgan.findByValues(hql.toString(), null, false);
		} catch (DaoException e) {
			ExceptionHandle.throwDaoException(e, ErrorCode.DAO_QUERY_ERROR_004, "BaseOrganDaoImpl.getAllDeptByOrgLev()"
					+ "根据当前组织级别，获取该组织所在集团的所有品牌/代理商/加盟店失败！");
		}
		return organList;
	}

	/**
	 * 根据ID获取品牌
	 * 
	 * @param organId
	 * @return
	 * @throws DaoException
	 */
	@Override
	public List<BaseOrgan> getBrandOgan(String organId) throws DaoException {
		StringBuffer hql = new StringBuffer();

		if ("0".equals(organId)) {
			hql.append("from BaseOrgan as o where o.organFather=? or o.organId=? ");
			hql.append(" or (o.organFather in (select bo.organId from BaseOrgan bo where bo.organFather='0') and o.organType='");
			hql.append(OrganTypeEnum.SGROUP.getIndex());
			hql.append("')");
		} else {
			hql.append("from BaseOrgan as o where (o.organFather=? and o.organType='");
			hql.append(OrganTypeEnum.SGROUP.getIndex());
			hql.append("') or o.organId=? ");
		}
		List<BaseOrgan> bo = new ArrayList<BaseOrgan>();
		try {
			bo = hibernateDaoBaseOrgan.findByValues(hql.toString(), new Object[] { organId, organId }, false);
		} catch (DaoException e) {
			ExceptionHandle.throwDaoException(e, ErrorCode.DAO_SAVE_ERROR_001, "BaseOrganDaoImpl.getBrandOgan()"
					+ "根据ID获取品牌失败！");
		}
		return bo != null && bo.size() > 0 ? bo : null;

	}
}