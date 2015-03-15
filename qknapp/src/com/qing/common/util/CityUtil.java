/*
 * Title:        清清网系统 2014年8月23日
 * Description:  地理位置和城市名互换工具
 * Copyright:    Copyright (c) 2014
 * Company:      清清网
 * @author       张洪斌
 * @version      2.0  2014年8月23日
 */
package com.qing.common.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import net.sf.json.JSONObject;

/**
 * (一句话功能简述)
 * (功能详细描述)
 * @author       zhanghongbin
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014年8月23日
 */
public class CityUtil {
	private String ak;
	private String url;

	/**构造方法，负责初始化ak
	 * @since 清清网系统
	 */
	public CityUtil() {
		init();
	}

	/**
	 * 初始化，负责初始化ak
	 * @since 清清网系统
	 */
	private void init() {
		Properties proties = new Properties();
		try {
			FileInputStream in = new FileInputStream("config/spring/config.properties");
			try {
				proties.load(in);
				this.ak = proties.getProperty("baidu.ak");
				this.url = proties.getProperty("baidu.url");
			} catch (IOException e) {

				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 
	 * 通过经度和纬度获取返回的城市JSON数据
	 * 
	 * @param latitude 经度
	 * @param longitude 纬度
	 * @return  城市json数据
	 * @throws ServivceException
	 * 
	 */
	private String getCityByCoordinate(double latitude, double longitude) {

		if (ak != null && url != null) {
			url = url + "?ak=" + ak + "&callback=renderReverse&location=" + latitude + "," + longitude
					+ "&output=json&pois=0";
			return getDataByUrl(url);
		}

		return null;
	}

	/**
	 * 通过url获取远程的JSON数据
	 * @param url  要访问的url
	 * @return     从远程获取的数据
	 * 
	 */

	private String getDataByUrl(String url) {
		System.out.println(url);

		if (url != null) {
			try {
				URL path = new URL(url);
				try {
					HttpURLConnection conn = (HttpURLConnection) path.openConnection();
					//conn.setRequestMethod( "post" );

					InputStream in = conn.getInputStream();
					BufferedInputStream bin = new BufferedInputStream(in);
					StringBuffer result = new StringBuffer();
					byte buf[] = new byte[1024];
					while ((bin.read(buf)) != -1) {
						bin.read(buf, 0, 100);
						result.append(new String(buf));
					}

					bin.close();
					return result.toString();

				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}

		}
		return url;
	}

	/**
	 * 通过经度纬度返回位置信息
	 * @param latitude 经度
	 * @param longitude 纬度
	 * 
	 */

	public CityInfo getByCoordinate(double latitude, double longitude) {
		String result = getCityByCoordinate(latitude, longitude);
		if (result != null) {
			int i = result.indexOf('(');
			int j = result.indexOf(')');
			result = result.substring(i + 1, j);
			System.out.println(result);
			JSONObject obj = JSONObject.fromObject(result);
            CityInfo city = new CityInfo();
			int status = obj.getInt("status");
			
			JSONObject b = obj.getJSONObject("result");
			city.setFormatted_address(b.getString("formatted_address"));
			switch (status) {
			case 0: {
				JSONObject bj = b.getJSONObject("addressComponent");
				if (bj != null) {
                    city.setCity(bj.getString("city"));
					city.setDistrict(bj.getString("district"));
					city.setStreet(bj.getString("street"));
					city.setStreet_number(bj.getString("street_number"));
					return city;
				}

			}
				break;
			case 1: {
				System.out.println("服务器内部错误");
			}
				break;
			case 2: {
				System.out.println("请求参数非法");
			}
				break;
			case 3: {
				System.out.println("权限校验失败");
			}
				break;
			case 4: {
				System.out.println("配额校验失败");
			}
				break;
			case 5: {
				System.out.println("ak不存在或者非法");
			}
				break;
			case 101: {
				System.out.println("服务禁用");
			}
				break;
			case 102: {
				System.out.println("不通过白名单或者安全码不对");
			}
				break;
			}

		}
		return null;
	}

}
