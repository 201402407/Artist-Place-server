package com.artiplace.api.common.service;

import javax.servlet.http.HttpServletRequest;

import com.artiplace.api.common.pvo.LoginPVO;
import com.artiplace.api.common.rvo.LoginRVO;

import springfox.documentation.annotations.ApiIgnore;

public interface CommonService {
	public LoginRVO chkLogin(@ApiIgnore HttpServletRequest request, LoginPVO pvo) throws Exception;
}
