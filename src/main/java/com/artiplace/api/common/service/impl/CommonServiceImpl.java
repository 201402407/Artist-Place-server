package com.artiplace.api.common.service.impl;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artiplace.api.common.entity.LoginEntity;
import com.artiplace.api.common.entity.LoginLogEntity;
import com.artiplace.api.common.entity.RegistNicknameEntity;
import com.artiplace.api.common.pvo.LoginPVO;
import com.artiplace.api.common.pvo.RegistNicknamePVO;
import com.artiplace.api.common.repository.LoginLogRepository;
import com.artiplace.api.common.repository.LoginRepository;
import com.artiplace.api.common.repository.RegistNicknameRepository;
import com.artiplace.api.common.rvo.LoginRVO;
import com.artiplace.api.common.rvo.RegistNicknameRVO;
import com.artiplace.api.common.service.CommonService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CommonServiceImpl implements CommonService {

	@Autowired
	LoginRepository loginRepository;
	@Autowired
	LoginLogRepository loginLogRepository;
	@Autowired
	RegistNicknameRepository registNicknameRepository;
	
	@Override
	public LoginRVO chkLogin(HttpServletRequest request, LoginPVO pvo) throws Exception {
		LoginRVO rvo = new LoginRVO();
		LoginEntity loginEntity = loginRepository.findByEmailId(pvo.getEmailId());
		
		if(loginEntity == null) {
			rvo.setResult("0");
		}
		
		
		if(loginEntity.getPwd().equals(pvo.getPwd())) {
			rvo.setResult("1");	// 성공
			rvo.setNickname(loginEntity.getNickname());
			rvo.setEmailId(loginEntity.getEmailId());
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
			loginLogRepository.save(entity);
			return true;
		}
		catch(IllegalArgumentException e) {
			return false;
		}
		
	}


	@Override
	public RegistNicknameRVO registNickname(HttpServletRequest request, RegistNicknamePVO pvo) throws Exception {
		RegistNicknameRVO rvo = new RegistNicknameRVO();
		LoginLogEntity entity = new LoginLogEntity();

		LoginEntity loginEntity = loginRepository.findByEmailId(pvo.getEmailId());
		if(loginEntity != null) {
			throw new Exception("DB 조회 결과 NULL 발생!");
		}
		
		RegistNicknameEntity registNicknameEntity = new RegistNicknameEntity();
		registNicknameEntity.setEmailId(pvo.getEmailId());
		registNicknameEntity.setNickname(pvo.getNickname());
		
		
		try {
			registNicknameRepository.saveAndFlush(registNicknameEntity);
			rvo.setNickname(registNicknameEntity.getNickname());
		}
		catch(IllegalArgumentException e) {
			throw e;
		}
		
		return rvo;
	}

}
