package com.qing.common.base.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.util.FileCopyUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.qing.common.exception.ActionException;
import com.qing.common.exception.handle.ExceptionHandle;
import com.qing.common.util.ConfigUtil;
import com.qing.common.util.ErrorCode;
import com.qing.common.util.Escape;
import com.qing.common.util.Page;
import com.qing.common.util.StringUtil;
import com.qing.right.dao.domain.BaseUser;
import com.work.domain.MemberInfo;

/**
 * 基于Struts2的Action基类
 * (一句话功能简述)
 * (功能详细描述)
 * @author       huangqingjian/0050
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014年7月28日
 */
public abstract class BaseAction<T> extends ActionSupport implements SessionAware, RequestAware, ParameterAware {

	private static final long serialVersionUID = 3969174020787506280L;

	/**
	 * web应用中session对象
	 */
	protected Map<String, Object> session;
	/**
	 * web应用中params对象
	 */
	protected Map<String, Object> params;
	/**
	 * web应用中request对象
	 */
	protected Map<String, Object> request;

	public String errMsg;

	/**
	 * 分页对象
	 */
	public Page<T> page = new Page<T>();

	/**
	 * 页面操作标识
	 */
	private String operate;

	/**
	 * 操作人
	 */
	private String operator;
	/** 结果码: 成功 */
	protected static final int STATUS_OK = 1;
	/** 结果码: 失败 */
	protected static final int STATUS_FAILED = -1;
	/** 结果码: 未登录 */
	protected static final int NOT_LOGIN = 0;

	protected static final String STATUS_KEY = "status";
	protected static final String DATA_KEY = "data";
	protected static final String MSG_KEY = "msg";
	//当前城市
	private static final String CURRENTCITY = "currentCity";
	/**
	 * 返回JSON结果
	 */
	protected Map<String, Object> json = new HashMap<String, Object>();

	// 图片访问路径
	private String picUrl;

	@Resource
	protected ConfigUtil configUtil;

	/**
	 * 在构造方法中将operator从session中取出
	 */
	public BaseAction() {
	}

	/**
	 * @return the session
	 */
	public Map<String, Object> getSession() {
		return ActionContext.getContext().getSession();
	}

	/**
	 * @param session
	 *            the session to set
	 */
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	/**
	 * @return the request
	 */
	public Map<String, Object> getRequest() {

		return this.request;
	}

	/**
	 * @param request
	 *            the request to set
	 */
	@Override
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	/**
	 * 返回客户端json数据
	 * 
	 * @param json
	 * @throws ActionException
	 */
	public void fowardToJson(String json) {
		PrintWriter out = null;
		try {
			// 获取到HttpServletResponse
			HttpServletResponse response = ServletActionContext.getResponse();
			// 设置返回的字符格式, 注意这里是utf-8
			response.setContentType("text/html;charset=utf-8");

			out = response.getWriter();
			out.println(json);
			out.close();
		} catch (Exception e) {
			this.actionExceptionHandle(ExceptionHandle.getActionException(e, ErrorCode.ACTION_ERROR,
					"BaseAction.fowardToJson()"));
		} finally {
			if (null != out) {
				out.close();
			}
		}

	}

	/**
	 * 
	 * Description:把对象转换为json
	 * 
	 * @param 参数Object可以为
	 *            Object、String、map
	 * @throws IOException
	 */
	public void fowardToJSON(Object object) {
		PrintWriter out = null;
		// 获取到HttpServletResponse
		HttpServletResponse response = ServletActionContext.getResponse();
		// 设置返回的字符格式
		response.setContentType("text/html;charset=utf-8");
		try {
			out = response.getWriter();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		String json;
		if (object instanceof Collection) {
			JSONArray obj = JSONArray.fromObject(object);
			json = obj.toString();
		} else {
			try {
				JSONObject obj = JSONObject.fromObject(object);// 
				json = obj.toString();
			} catch (JSONException e) {
				json = object.toString();
			}
		}
		out.write(json);
		out.flush();
		out.close();
	}

	/**
	 * 返回失败json信息
	 * @author       luoqinglong
	 * @since        清清网系统, 2014-8-31
	 */
	public void jsonFailed(String msg) {
		this.json.put(BaseAction.STATUS_KEY, BaseAction.STATUS_FAILED);
		this.json.put(BaseAction.MSG_KEY, msg);
		this.fowardToJSON(this.json);
	}

	/**
	 * 
	 * 返回成功json信息
	 * @author       luoqinglong
	 * @since        清清网系统, 2014-8-31
	 */
	public void jsonSucc(String msg) {
		this.json.put(BaseAction.STATUS_KEY, BaseAction.STATUS_OK);
		this.json.put(BaseAction.MSG_KEY, msg);
		this.fowardToJSON(this.json);
	}

	/**
	 * 
	 * Description:获取jboss部署目录WebRoot的物理路径, 如F:\jboss-as-7.1.1.Final\standalone\
	 * deployments\LS.war\
	 * 
	 * @param request
	 * @return
	 * 
	 */
	protected String getWebRootPhysicalPath(HttpServletRequest request) {
		String realPath = request.getSession().getServletContext().getRealPath("/");
		return realPath;
	}

	/**
	 * 将异常信息添加到actionError中
	 * 
	 * @param e
	 *            异常的处理，包括上层Dao,Service抛出的异常及Action本层抛出的异常处理
	 */
	public void actionExceptionHandle(Exception e) {
		this.addActionMessage(ExceptionHandle.getExceptionValue(e));
	}

	public Page<T> getPage() {
		return this.page;
	}

	public void setPage(Page<T> page) {
		this.page = page;
	}

	public Map<String, Object> getParams() {
		return this.params;
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setParameters(Map paramsMap) {
		this.params = new HashMap<String, Object>();
		Set<String> keySet = paramsMap.keySet();
		for (String key : keySet) {
			Object[] values = (Object[]) paramsMap.get(key);
			if (0 < values.length && 1 == values.length) {
				this.params.put(key, values[0]);
			} else {
				this.params.put(key, values);
			}
		}
	}

	/**
	 * get cookieValue by cookieName
	 * 
	 * @param cookieName
	 * @return cookieValue
	 */
	public String getCookie(String cookieName) {
		String cookieValue = null;
		Cookie[] cookies = ServletActionContext.getRequest().getCookies();
		if (null != cookies && cookies.length > 0) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals(cookieName)) {
					cookieValue = cookies[i].getValue();
					break;
				}
			}
		}
		return cookieValue;
	}

	/**
	 * add cookie by cookieValue and cookieName
	 * 
	 * @param cookieName
	 * @param cookieValue
	 */
	public void setCookie(String cookieName, String cookieValue, int maxAge, String path) {
		//把会员ID放进COOKIE里
		Cookie cookie = new Cookie(cookieName, cookieValue);
		cookie.setMaxAge(maxAge);
		if ("/".equals(path)) {
			path = ServletActionContext.getRequest().getContextPath();
		}
		cookie.setPath(path);
		ServletActionContext.getResponse().addCookie(cookie);
	}

	/**
	 * ADD NEW cookie
	 * 
	 * @param cookieName
	 * @param cookieValue
	 */
	public void addCookie(String cookieName, String cookieValue) {
		Cookie cookie = new Cookie(cookieName, cookieValue);
		cookie.setMaxAge(31536000);// 一年
		ServletActionContext.getResponse().addCookie(cookie);
	}

	/**
	 * Clear Cookie
	 * 
	 * @param cartID
	 */
	public void clearCookie(String cookieName) {
		Cookie[] cookies = ServletActionContext.getRequest().getCookies();
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(cookieName)) {
				this.addCookie(cookieName, "0");
				break;
			}
		}
	}

	/**
	 * 获取当前城市
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年9月15日
	 * @return
	 */
	protected String getCurrentCity() {
		String currentCity = null;
		if (StringUtil.isValidStr(getCookie(CURRENTCITY))) {
			currentCity = Escape.unescape(getCookie(CURRENTCITY));
		} else {
			currentCity = "深圳";
		}
		return currentCity;
	}

	/**
	 * 
	 * @param filePic
	 *            要上传的图片文件
	 * @param fileFileName
	 *            要上传的图片文件名称
	 * @param fileDirctoryName
	 *            上传至服务器的image下面的哪个文件夹
	 * @return 文件上传是否成功
	 * @throws IOException
	 */
	protected boolean upload(File filePic, String fileFileName, String fileDirctoryName) throws IOException {
		boolean flag = false;

		// 判断文件大小是否大于2M
		if (filePic.length() <= 1024 * 1024 * 2) {
			long currentTime = System.currentTimeMillis();// 取得系统当前时间
			Random random = new Random();
			int ranNum = random.nextInt(1000000);// 产生一个随机数

			String fileName = currentTime + "_" + ranNum + fileFileName.substring(fileFileName.lastIndexOf('.'));// 图片名称
			String preUrl = this.configUtil.getResourceContext();
			String absFilePath = this.configUtil.getAbsUploadPath();
			String filePath = absFilePath + fileDirctoryName;// 图片文件夹物理路径

			this.picUrl = preUrl + fileDirctoryName + "/" + fileName;// 图片url路径
			File fileDirctory = new File(filePath);
			File file = new File(filePath + "/" + fileName);

			if (!fileDirctory.exists())// 判断文件夹是否存在
			{
				if (fileDirctory.mkdirs())// 创建文件夹
				{
					file.createNewFile();// 创建文件
				}
			}
			FileCopyUtils.copy(filePic, file);
			flag = true;
		}
		return flag;
	}

	/**
	 * Description:获取系统登录的session用户信息
	 * 
	 * @return
	 * 
	 */
	protected BaseUser getBaseUser() {
		return (BaseUser) this.getSession().get("user");
	}

	/**
	 * 获取登录的会员信息
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年9月17日
	 * @return
	 */
	protected MemberInfo getMemberInfoSession() {
		return (MemberInfo) this.getSession().get("memberInfo");
	}

	/**
	 * 获取会员ID
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年9月18日
	 * @return
	 */
	protected String getMemberInfoIdSession() {
		MemberInfo memberInfo = getMemberInfoSession();
		if (null != memberInfo) {
			return memberInfo.getMemberId();
		}
		return null;
	}

	/**
	 * 获取项目名称
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月12日
	 * @return
	 */
	public String getHttpContextPath() {
		HttpServletRequest request = ServletActionContext.getRequest();
		return request.getContextPath();
	}

	public String getOperate() {
		return this.operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}

	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getErrMsg() {
		return this.errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public String getPicUrl() {
		return this.picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public ConfigUtil getConfigUtil() {
		return this.configUtil;
	}

	public void setConfigUtil(ConfigUtil configUtil) {
		this.configUtil = configUtil;
	}

}