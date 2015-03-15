/*
 * Title:        清清网系统 2014年8月2日
 * Description:  [描述模块的功能、作用、使用方法和注意事项]
 * Copyright:    Copyright (c) 2014
 * Company:      清清网
 * @author       huangqingjian
 * @version      2.0  2014年8月2日
 */
package com.work.service.datamining;

import java.util.List;

import com.work.domain.DataMiningSet;
import com.work.domain.PartTimeEngine;

/**
 * 
 * 兼职挖掘业务处理类
 * @author       huangqingjian/0050
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014年8月2日
 */
public interface IJobInfoMiningService {

	/**
	 * 解析兼职列表
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月2日
	 * @param set
	 */
	public void extractLinks(DataMiningSet set,List<PartTimeEngine> engines);

}
