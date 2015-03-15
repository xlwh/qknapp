package com.qing.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 通过第三方获取ip 对应的城市接口
 * 
 * @author luoqinglong
 * @date 2014-1-2 上午10:14:13
 * @since 1.0
 **/
public class IPService {
	private static final Logger logger = LoggerFactory.getLogger(IPService.class);
	public static final String NOT_KNOW_AREA = "未知地区";
	private final String SINA_URL = "http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=js&ip=";
	private final String BAIDU_URL = "http://api.map.baidu.com/location/ip?ak=FFc5906072fb081d8a11bd20df0c0fc6&ip=";

	/**
	 * 获取国家，省份，地址
	 * 
	 * @param ip
	 * @return
	 * 
	 **/
	public Address getAddress(String ip) {
		Address address = null;
		//sina
		String sinaline = httpRequestProxy(SINA_URL + ip);
		if (sinaline.length() > 100) {
			address = new Address();
			sinaline = sinaline.substring(sinaline.indexOf("{"), sinaline.indexOf("}") + 1);
			JSONObject wsJsonObject = JSONObject.fromObject(sinaline);

			address.setCountry(wsJsonObject.getString("country"));
			address.setProvince(wsJsonObject.getString("province"));
			address.setCity(wsJsonObject.getString("city"));
			address.setFullAddress(address.getCountry() + address.getProvince() + address.getCity());
		}
		//baidu
		if (address == null) {
			String baiduline = httpRequestProxy(BAIDU_URL + ip);
			if (baiduline.length() > 5) {
				JSONObject wsJsonObject = JSONObject.fromObject(baiduline);
				if (wsJsonObject.getInt("status") == 0) {
					address = new Address();
					address.setProvince(wsJsonObject.getJSONObject("content").getJSONObject("address_detail")
							.getString("province"));
					address.setCity(wsJsonObject.getJSONObject("content").getJSONObject("address_detail")
							.getString("city"));
					address.setFullAddress(address.getProvince() + address.getCity());
				}
			}
		}
		if (address == null) {
			address = new Address();
			address.setFullAddress(IPService.NOT_KNOW_AREA);
		}
		return address;
	}

	/**
	 * 
	 * 代理http请求
	 * 
	 * @param url
	 * @param ip
	 * @return
	 * 
	 */
	public static String httpRequestProxy(String url) {
		InputStream serverStream = null;
		BufferedReader serverReader = null;
		String returnline = "";
		try {
			URL requestUrl = new URL(url);
			URLConnection connection = requestUrl.openConnection();
			connection.setDoOutput(true);
			connection.setConnectTimeout(2000);
			serverStream = connection.getInputStream();
			serverReader = new BufferedReader(new InputStreamReader(serverStream, "UTF-8"));
			String line = "";
			while ((line = serverReader.readLine()) != null) {
				returnline += line;
			}

		} catch (MalformedURLException e) {
			logger.error("第三方service连接失败！" + url, e);
		} catch (IOException e) {
			logger.error("第三方service连接失败！" + url, e);
		} finally {
			if (serverReader != null) {
				try {
					serverReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (serverStream != null) {
				try {
					serverStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return returnline;
	}
}
