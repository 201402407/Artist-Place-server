package com.artiplace.api.common.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artiplace.api.common.entity.LoginEntity;
import com.artiplace.api.common.pvo.LoginPVO;
import com.artiplace.api.common.repository.LoginRepository;
import com.artiplace.api.common.rvo.LoginRVO;
import com.artiplace.api.common.service.CommonService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CommonServiceImpl implements CommonService {

	@Autowired
	LoginRepository LoginRepository;
	
	@Override
	public LoginRVO chkLogin(HttpServletRequest request, LoginPVO pvo) throws Exception {
		LoginRVO rvo = new LoginRVO();
		LoginEntity loginEntity = LoginRepository.findByEmailId(pvo.getEmailId());
		
		if(loginEntity == null) {
			rvo.setResult("0");
		}
		
		
		if(loginEntity.getPwd().equals(pvo.getPwd())) {
			rvo.setResult("1");	// 성공
		}
		else {
			rvo.setResult("0");
		}
		
		return rvo;
	}

}
