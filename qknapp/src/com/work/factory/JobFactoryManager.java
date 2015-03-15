/*
 * Title:        清清网系统 2014年8月2日
 * Description:  [描述模块的功能、作用、使用方法和注意事项]
 * Copyright:    Copyright (c) 2014
 * Company:      清清网
 * @author       huangqingjian
 * @version      2.0  2014年8月2日
 */
package com.work.factory;

import com.qing.enums.NetTypeEnum;
import com.work.service.datamining.IJobInfoMiningService;
import com.work.service.datamining.impl.ZeroMiningServiceImpl;

/**
 * 兼职挖掘工厂类
 * (一句话功能简述)
 * (功能详细描述)
 * @author       huangqingjian/0050
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014年8月2日
 */
public class JobFactoryManager {
	public static IJobInfoMiningService createJobMining(String netName) {
		IJobInfoMiningService infoMiningService = null;
		if (NetTypeEnum.ZEROMINING.getIndex().equals(netName)) {
			infoMiningService = new ZeroMiningServiceImpl();
		}

		return infoMiningService;
	}
}
