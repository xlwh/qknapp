package com.work.dao.express;

import java.util.List;

import com.qing.common.exception.DaoException;
import com.work.domain.ExpressDetail;
import com.work.domain.ExpressInfo;
import com.work.domain.SenderInfo;

/**
 * 快递单模板相关数据处理存储接口
 * @author       huangqingjian/0050
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        瑾泉二维码系统, 2013年12月27日
 */
public interface ExpressInfoDaoAble {

	/**
	 * 获取所有的快递单模板
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        美芝澳二维码系统, 2014年1月23日
	 * @return
	 */
	public List<ExpressInfo> getALLExpressInfos() throws DaoException;

	/**
	 * 根据ID获取单个快递单模板
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        美芝澳二维码系统, 2014年1月23日
	 * @param id
	 * @return
	 * @throws DaoException 
	 */
	public ExpressInfo getExpressInfo(String id) throws DaoException;

	/**
	 * 根据模板ID获取快递单上的字段信息
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        美芝澳二维码系统, 2014年1月24日
	 * @param expressId
	 * @return
	 * @throws DaoException
	 */
	List<ExpressDetail> getExpressDetail(String expressId) throws DaoException;

	/**
	 * 设置默认模板
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        美芝澳二维码系统, 2014年1月25日
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	void updateExpressDefaultSet(String id) throws DaoException;

	/**
	 * 保存快递单详细设置
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        美芝澳二维码系统, 2014年2月7日
	 * @param details
	 * @throws DaoException
	 */
	void saveOrUpdateExpressDetails(List<ExpressDetail> details) throws DaoException;

	/**
	 * 更新快递单模板偏移量
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        美芝澳二维码系统, 2014年2月7日
	 * @param id
	 * @param widthOffset
	 * @param heightOffset
	 * @throws DaoException
	 */
	void updateExpressOffSet(String id, Integer widthOffset, Integer heightOffset) throws DaoException;

	/**
	 * 获取默认的发货地址
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        美芝澳二维码系统, 2014年2月21日
	 * @return
	 * @throws DaoException
	 */
	public SenderInfo getDefaultSenderInfo() throws DaoException;

	/**
	 * 
	 * 获取默认模板
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        美芝澳二维码系统, 2014年2月21日
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public ExpressInfo getDefaultExpressInfo() throws DaoException;

}
