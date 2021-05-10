package com.artiplace.api.common.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.RequestContext;

import com.artiplace.api.common.pvo.LoginPVO;
import com.artiplace.api.common.rvo.LoginRVO;
import com.artiplace.api.common.service.CommonService;

@Service
public class CommonServiceImpl implements CommonService {

	@Override
	public LoginRVO chkLogin(HttpServletRequest request, RequestContext requestContext, LoginPVO pvo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
