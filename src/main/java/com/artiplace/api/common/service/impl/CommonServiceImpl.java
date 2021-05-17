package com.artiplace.api.common.service.impl;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artiplace.api.common.entity.LoginEntity;
import com.artiplace.api.common.entity.LoginLogEntity;
import com.artiplace.api.common.pvo.LoginPVO;
import com.artiplace.api.common.repository.LoginLogRepository;
import com.artiplace.api.common.repository.LoginRepository;
import com.artiplace.api.common.rvo.LoginRVO;
import com.artiplace.api.common.service.CommonService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CommonServiceImpl implements CommonService {

	@Autowired
	LoginRepository loginRepository;
	@Autowired
	LoginLogRepository loginLogRepository;
	
	@Override
	public LoginRVO chkLogin(HttpServletRequest request, LoginPVO pvo) throws Exception {
		LoginRVO rvo = new LoginRVO();
		LoginEntity loginEntity = loginRepository.findByEmailId(pvo.getEmailId());
		
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
	

	@Override
	public boolean addLoginLog(HttpServletRequest request, LoginPVO pvo, LoginRVO rvo) {
		LoginLogEntity entity = new LoginLogEntity();
		entity.setEmailId(pvo.getEmailId());
		entity.setLoginResult(rvo.getResult());
		entity.setLoginTime(new Date());
		
		try {
			LoginLogEntity resultEntity = loginLogRepository.save(entity);
			return resultEntity == null ? false : true;
		}
		catch(IllegalArgumentException e) {
			return false;
		}
		
	}

}
