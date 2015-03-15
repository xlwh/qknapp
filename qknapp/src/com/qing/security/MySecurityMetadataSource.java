package com.qing.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;

import com.qing.common.exception.ServiceException;
import com.qing.common.exception.handle.ExceptionHandle;
import com.qing.common.util.ErrorCode;
import com.qing.right.dao.domain.BasePermit;
import com.qing.right.service.impl.BasePermitServiceImpl;

/**
 * 加载权限与资源URL的对应关系
 * 
 * @author huangqingjian/0050
 * @see [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since 深烟智能物流管理平台, 2013-1-15
 */
@Service("mySecurityMetadataSource")
public class MySecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Resource
    private BasePermitServiceImpl basePermitServiceImpl;

    public MySecurityMetadataSource() {
    }

    private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

    @Override
    public boolean supports(Class<?> clazz) {
	return true;
    }

    // 加载所有资源与权限的关系
    private void loadResourceDefine() {
	if (resourceMap == null) {
	    resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
	    List<BasePermit> permits;
	    try {
		permits = basePermitServiceImpl.findAllPermits();

		for (BasePermit permit : permits) {
		    Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
		    // 以权限名封装为Spring的security Object
		    ConfigAttribute configAttribute = new SecurityConfig(permit.getPermitName());
		    configAttributes.add(configAttribute);
		    resourceMap.put(permit.getPermitContent(), configAttributes);
		}
	    } catch (ServiceException e) {
		ExceptionHandle.getActionException(e, ErrorCode.DAO_QUERY_ERROR_004, "MySecurityMetadataSource.loadResourceDefine");
	    }

	}
    }

    // 返回所请求资源所需要的权限
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {

	FilterInvocation fi = (FilterInvocation) object;
	String contextPath = fi.getRequest().getContextPath();
	String requestUrl = fi.getRequest().getRequestURI();

	// 防止URL带参数无法过滤的情况
	requestUrl = requestUrl.replace(contextPath, "");
	if (resourceMap == null || resourceMap.isEmpty()) {
	    loadResourceDefine();
	}
	return resourceMap.get(requestUrl);
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
	return null;
    }

}