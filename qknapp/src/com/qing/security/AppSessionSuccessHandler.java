package com.qing.security;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.transaction.annotation.Transactional;

import com.qing.common.exception.ServiceException;
import com.qing.common.exception.handle.ExceptionHandle;
import com.qing.common.util.ErrorCode;
import com.qing.right.dao.domain.BaseOrgan;
import com.qing.right.dao.domain.BaseUser;
import com.qing.right.service.impl.BaseUserServiceImpl;

@Transactional
public class AppSessionSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Resource
    private BaseUserServiceImpl baseUserService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
	    throws ServletException, IOException {

	BaseUser user;
	try {
	    user = baseUserService.findUserByLoginCode(authentication.getName());
	    if (null != user) {
		// 如果本身为品牌用户或集体用户,则品牌ID就是当前用户所在组织,
		BaseOrgan baseOrgan = baseUserService.getLevBaseOrgByUserId(user.getUserId(), 6);
		if (null != baseOrgan) {
		    user.setBrandId(baseOrgan.getOrganId());
		}
	    }
	    request.getSession().setAttribute("user", user);
	    super.setDefaultTargetUrl("/s!login.shtml");
	    super.onAuthenticationSuccess(request, response, authentication);
	} catch (ServiceException e) {
	    ExceptionHandle.getActionException(e, ErrorCode.DAO_QUERY_ERROR_004, "AppSessionSuccessHandler.onAuthenticationSuccess");
	}

    }
}
