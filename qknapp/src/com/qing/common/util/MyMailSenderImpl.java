/*
 * Title:        美芝澳二维码系统 2014年5月9日
 * Description:  [描述模块的功能、作用、使用方法和注意事项]
 * Copyright:    Copyright (c) 2012
 * Company:      Tai woo. Co., Ltd
 * @author       huangqingjian/0050
 * @version      1.0  2014年5月9日
 */
package com.qing.common.util;

import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * 扩展邮件发送实现类
 * 附加属性
 * (功能详细描述)
 * @author       huangqingjian/0050
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        美芝澳二维码系统, 2014年5月9日
 */
public class MyMailSenderImpl extends JavaMailSenderImpl {

	//每发一封邮件间隔多少毫秒
	private long interval;

	//允许连续发送失败的数量
	public int allowErrorNum;

	public long getInterval() {
		return interval;
	}

	public void setInterval(long interval) {
		this.interval = interval;
	}

	public int getAllowErrorNum() {
		return allowErrorNum;
	}

	public void setAllowErrorNum(int allowErrorNum) {
		this.allowErrorNum = allowErrorNum;
	}

}
