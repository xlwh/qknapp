package com.qing.common.util;

/**
 * 第三方天气
 * 
 * @author luoqinglong
 * @date 2014-2-19 上午11:50:19
 * @since 1.0
 **/
public class Weather {
	/**
	 * 实时气温
	 */
	private Integer nowTemp;
	/**
	 * 气温范围
	 */
	private String tempRange;
	/**
	 * 天气图片
	 */
	private String weatherImg;
	/**
	 * 天气描述
	 */
	private String weatherDes;

	public Weather() {
	}

	public Weather(Integer nowTemp, String tempRange, String weatherImg, String weatherDes) {
		super();
		this.nowTemp = nowTemp;
		this.tempRange = tempRange;
		this.weatherImg = weatherImg;
		this.weatherDes = weatherDes;
	}

	/**
	 * @return the nowTemp
	 **/
	public Integer getNowTemp() {
		return nowTemp;
	}

	/**
	 * @param nowTemp
	 *            the nowTemp to set
	 **/
	public void setNowTemp(Integer nowTemp) {
		this.nowTemp = nowTemp;
	}

	/**
	 * @return the tempRange
	 **/
	public String getTempRange() {
		return tempRange;
	}

	/**
	 * @param tempRange
	 *            the tempRange to set
	 **/
	public void setTempRange(String tempRange) {
		this.tempRange = tempRange;
	}

	/**
	 * @return the weatherImg
	 **/
	public String getWeatherImg() {
		return weatherImg;
	}

	/**
	 * @param weatherImg
	 *            the weatherImg to set
	 **/
	public void setWeatherImg(String weatherImg) {
		this.weatherImg = weatherImg;
	}

	/**
	 * @return the weatherDes
	 **/
	public String getWeatherDes() {
		return weatherDes;
	}

	/**
	 * @param weatherDes
	 *            the weatherDes to set
	 **/
	public void setWeatherDes(String weatherDes) {
		this.weatherDes = weatherDes;
	}

	/**
	 * 
	 * @return
	 * @see java.lang.Object#toString()
	 **/
	@Override
	public String toString() {
		return "Weather [nowTemp=" + nowTemp + ", tempRange=" + tempRange + ", weatherImg=" + weatherImg
				+ ", weatherDes=" + weatherDes + "]";
	}

}
