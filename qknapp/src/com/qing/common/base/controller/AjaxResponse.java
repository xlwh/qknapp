package com.qing.common.base.controller;

import java.util.Map;

/**
 * ajax返回消息结构体
 * 
 * @author luoqinglong
 * @date 2013-12-20 下午5:38:48
 * @since 1.0
 **/
public class AjaxResponse {
	/**
	 * 信息码
	 */
	private String code;
	/**
	 * 消息
	 */
	private String msg;
	/**
	 * 返回数据
	 */
	private Map<String, Object> data;

	public AjaxResponse() {
	}

	/**
	 * 
	 * @param code
	 * @param msg
	 * @param data
	 **/
	public AjaxResponse(String code, String msg, Map<String, Object> data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

}
