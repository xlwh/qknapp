package com.qing.common.util;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

/**
 * 天气
 * 
 * @author luoqinglong
 * @date 2014-2-19 下午4:59:27
 * @since 1.0
 **/
public class WeatherUtil {
	private static final Logger logger = Logger.getLogger(WeatherUtil.class);
	/** 中国天气获取城市代码接口 **/
	//	private static String serverUrl = "http://61.4.185.48:81/g/";
	///101280101
	private static String nowServerUrl = "http://www.weather.com.cn/data/sk/%s.html";
	private static String weatherServerUrl = "http://m.weather.com.cn/data/%s.html";

	public static Weather getWeather(String cityId) {
		Weather weather = new Weather();
		//1
		//String newJson = "{\"weatherinfo\":{\"city\":\"广州\",\"cityid\":\"101280101\",\"temp\":\"13\",\"WD\":\"东北风\",\"WS\":\"2级\",\"SD\":\"95%\",\"WSE\":\"2\",\"time\":\"16:25\",\"isRadar\":\"1\",\"Radar\":\"JC_RADAR_AZ9200_JB\"}}";//IPService.httpRequestProxy(String.format(nowServerUrl, cityId));
		String newJson = IPService.httpRequestProxy(String.format(nowServerUrl, cityId));
		try {
			JSONObject wsJsonObject = JSONObject.fromObject(newJson);
			weather.setNowTemp(Integer.valueOf(wsJsonObject.getJSONObject("weatherinfo").getString("temp")));
		} catch (Exception e) {
			logger.error("实时温度获取失败!,请求url和城市代码为:" + nowServerUrl + "," + cityId + ",返回值为：" + newJson, e);
		}
		//2
		//String weatherJson = "{\"weatherinfo\":{\"city\":\"广州\",\"city_en\":\"guangzhou\",\"weather1\":\"小雨\",\"temp1\":\"18℃~13℃\",\"img_title1\":\"小雨\"}}";//IPService.httpRequestProxy(String.format(weatherServerUrl, cityId));
		String weatherJson = IPService.httpRequestProxy(String.format(weatherServerUrl, cityId));
		try {
			JSONObject weatherInfoJsonObject = JSONObject.fromObject(weatherJson).getJSONObject("weatherinfo");
			weather.setTempRange(weatherInfoJsonObject.getString("temp1"));
			weather.setWeatherDes(weatherInfoJsonObject.getString("weather1"));
			weather.setWeatherImg(weatherInfoJsonObject.getString("img_title1"));
		} catch (Exception e) {
			logger.error("实时天气获取失败!,请求url和城市代码为:" + weatherServerUrl + "," + cityId + ",返回值为：" + weatherJson, e);
		}

		return weather;
	}

	public static void main(String[] arg) {
		Weather weather = WeatherUtil.getWeather("101280101");
		//http://m.weather.com.cn/data/101280101.html
		System.out.println(weather.toString());//http://www.weather.com.cn/data/sk/101280101.html,101280101
	}
}
