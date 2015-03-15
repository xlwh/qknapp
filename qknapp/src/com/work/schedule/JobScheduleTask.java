package com.work.schedule;

import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.work.service.job.IPartTimeEngineService;

/**
 * 数据挖掘定时任务
 * (一句话功能简述)
 * (功能详细描述)
 * @author       huangqingjian/0050
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014年8月10日
 */
public class JobScheduleTask {
	/** 日志对象 */
	private static final Logger logger = LoggerFactory.getLogger(JobScheduleTask.class);

	@Resource
	IPartTimeEngineService engineService;

	/**
	 * 启动方法
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月10日
	 */
	public void run() {
		logger.info(String.valueOf(new Date(System.currentTimeMillis())) + "开始执行数据挖掘任务！");
		engineService.parsePartTime();
		logger.info(String.valueOf(new Date(System.currentTimeMillis())) + "数据挖掘解析任务结束！");
	}
}
