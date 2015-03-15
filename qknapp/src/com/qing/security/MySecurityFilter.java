package com.qing.security;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

/**
 * 拦截页面信息的过滤器
 * 
 * @author huangqingjian/0050
 * @see [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since 深烟智能物流管理平台, 2013-1-22
 */
public class MySecurityFilter extends AbstractSecurityInterceptor implements Filter {
    @Resource(name = "mySecurityMetadataSource")
    public FilterInvocationSecurityMetadataSource securityMetadataSource;

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
	return this.securityMetadataSource;
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	FilterInvocation fi = new FilterInvocation(request, response, chain);
	invoke(fi);
    }

    private void invoke(FilterInvocation fi) throws IOException, ServletException {
	InterceptorStatusToken token = super.beforeInvocation(fi);
	try {
	    fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
	} finally {
	    super.afterInvocation(token, null);
	}
    }

    @Override
    public Class<? extends Object> getSecureObjectClass() {
	return FilterInvocation.class;
    }

    @Override
    public void destroy() {

    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }

}