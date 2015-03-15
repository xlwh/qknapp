package com.work.service.express;

import java.util.List;

import com.qing.common.exception.DaoException;
import com.qing.common.exception.ServiceException;
import com.work.domain.ExpressDetail;
import com.work.domain.ExpressInfo;
import com.work.domain.SenderInfo;
import com.work.vo.ExpressInfoVo;

/**
 * 快递单模板相关数据处理存储接口
 * @author       huangqingjian/0050
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        瑾泉二维码系统, 2013年12月27日
 */
public interface ExpressInfoServiceAble {

	/**
	 * 获取所有的快递单模板
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        美芝澳二维码系统, 2014年1月23日
	 * @return
	 */
	public List<ExpressInfo> getALLExpressInfos() throws ServiceException;

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
	public ExpressInfoVo getExpressInfo(String id) throws ServiceException;

	/**
	 * 设置默认模板
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        美芝澳二维码系统, 2014年1月25日
	 * @param id
	 * @throws ServiceException
	 */
	public void updateExpressDefaultSet(String id) throws ServiceException;

	/**
	 * 保存快递单详细设置
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        美芝澳二维码系统, 2014年2月7日
	 * @param details
	 * @throws ServiceException
	 */
	void saveOrUpdateExpressDetails(List<ExpressDetail> details) throws ServiceException;

	/**
	 * 设置模板偏移量
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        美芝澳二维码系统, 2014年2月7日
	 * @param id
	 * @param widthOffset
	 * @param heightOffset
	 * @throws ServiceException
	 */
	void updateExpressOffSet(String id, Integer widthOffset, Integer heightOffset) throws ServiceException;

	/**
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        美芝澳二维码系统, 2014年2月21日
	 * @return
	 * @throws ServiceException
	 */
	public SenderInfo getDefaultSenderInfo() throws ServiceException;

	/**
	 * 获取默认的模板
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        美芝澳二维码系统, 2014年2月21日
	 * @return
	 * @throws ServiceException 
	 */
	public ExpressInfoVo getDefaultExpressInfo() throws ServiceException;
}
