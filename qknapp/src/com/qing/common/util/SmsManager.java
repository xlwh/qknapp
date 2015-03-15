package com.qing.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.qing.common.exception.ServiceException;
import com.work.domain.SmsConfig;
import com.work.service.sms.ISmsConfigService;

/**
 * 
 * Description:发送短信接口
 * 
 * @author chen,hao-hao
 */
public class SmsManager {
	private static final Logger logger = LoggerFactory.getLogger(SmsManager.class);

	private static final String SMS_URL = "SMS_URL";
	private static final String MOBILES = "MOBILES";
	private static final String MSG = "MSG";

	/**
	 * 
	 * 发送短信
	 * 
	 * @param mobiles
	 * @param content
	 * @return
	 * 
	 */
	public static String smsSender(String mobiles, String content, ISmsConfigService smsConfigService) {
		return smsSender(new String[] { mobiles }, content, smsConfigService);
	}

	/**
	 * 批量发送短信
	 * 
	 * @param mobiles
	 * @param content
	 * @param smsConfigService
	 * @return
	 */
	public static String smsSender(String[] mobiles, String content, ISmsConfigService smsConfigService) {
		StringBuilder mobile = new StringBuilder();
		// 一旦发送成功，用以下方法就可以得到服务器的回应：
		String sCurrentLine = "";
		String sTotalString = "";
		int length = mobiles.length;
		// 当数组的长度等于1的时候， 直接可以取出数组下标为0的值。
		// 同理当数组的长度等于2的时候， 也可以直接取出数组下标为0和1的值。
		// 只有当数组的长度大于或等于3的时候，考虑使用循环取值的方式。
		if (length == 1) {
			mobile.append(mobiles[0]);
		} else if (length == 2) {
			mobile.append(mobiles[0]);
			mobile.append(";");
			mobile.append(mobiles[1]);
		} else if (length >= 3) {
			for (int i = 0; i < length; i++) {
				mobile.append(mobiles[i]);
				if (i != length - 1) {
					mobile.append(";");
				}
			}
		}
		OutputStreamWriter out = null;
		InputStream l_urlStream = null;
		BufferedReader l_reader = null;
		try {

			List<SmsConfig> configs = smsConfigService.queryAllConfig();
			if (CollectionUtils.isEmpty(configs)) {
				logger.error("参数列表为空，发送短信失败！");
				return sTotalString;
			}
			//URL地址
			String sms_url = null;
			//拼接参数列表
			StringBuilder paramStr = new StringBuilder();
			String value = "";

			//循环取出参数
			for (SmsConfig smsConfig : configs) {
				if (SMS_URL.equals(smsConfig.getSmsParamTag())) {
					sms_url = smsConfig.getSmsParamValue();
					continue;
				}
				//手机号码值取传入的
				if (MOBILES.equals(smsConfig.getSmsParamTag())) {
					value = mobile.toString();
				} else if (MSG.equals(smsConfig.getSmsParamTag())) {
					//内容拼接短信签名
					value = content+smsConfig.getSmsParamValue();
				}//其他值取数据库填写的值
				else {
					value = smsConfig.getSmsParamValue();
				}
				paramStr.append("&");
				paramStr.append(smsConfig.getSmsParamName());
				paramStr.append("=");
				paramStr.append(value);
			}

			//删除第一个参数的&符号
			paramStr.delete(0, 1);

			URL url = new URL(sms_url);
			URLConnection connection = url.openConnection();
			connection.setDoOutput(true);

			/**
			 * 最后，为了得到OutputStream，简单起见，把它约束在Writer并且放入POST信息中，例如： ...
			 */
			out = new OutputStreamWriter(connection.getOutputStream(), "utf-8");
			out.write(paramStr.toString());
			// remember to clean up
			out.flush();

			l_urlStream = connection.getInputStream();

			l_reader = new BufferedReader(new InputStreamReader(l_urlStream));
			while ((sCurrentLine = l_reader.readLine()) != null) {
				sTotalString += sCurrentLine + "";
			}
			logger.info("短信发送状态信息：" + sTotalString);
		} catch (MalformedURLException exception) {
			logger.error("URL协议、格式或者路径错误！", exception);
		} catch (IOException exception) {
			logger.error("URL链接打开失败！", exception);
		} catch (ServiceException e) {
			logger.error("ServiceException:", e);
		} finally {

			try {
				if (null != out) {
					out.close();
				}
				if (null != l_urlStream) {
					l_urlStream.close();
				}
				if (null != l_reader) {
					l_reader.close();
				}
			} catch (IOException e) {
				logger.error("IOException", e);
			}
		}
		return sTotalString;
	}

}