package com.artiplace.api.common.service.impl;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artiplace.api.common.entity.LoginLogEntity;
import com.artiplace.api.common.entity.MembersEntity;
import com.artiplace.api.common.pvo.LoginPVO;
import com.artiplace.api.common.pvo.RegistNicknamePVO;
import com.artiplace.api.common.repository.LoginLogRepository;
import com.artiplace.api.common.repository.MembersRepository;
import com.artiplace.api.common.rvo.LoginRVO;
import com.artiplace.api.common.rvo.RegistNicknameRVO;
import com.artiplace.api.common.service.CommonService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CommonServiceImpl implements CommonService {

	@Autowired
	MembersRepository membersRepository;
	@Autowired
	LoginLogRepository loginLogRepository;
	
	@Override
	public LoginRVO chkLogin(HttpServletRequest request, LoginPVO pvo) throws Exception {
		LoginRVO rvo = new LoginRVO();
		MembersEntity membersEntity = membersRepository.findByEmailId(pvo.getEmailId());
		
		if(membersEntity == null) {
			rvo.setResult("0");
		}
		
		
		if(membersEntity.getPwd().equals(pvo.getPwd())) {
			rvo.setResult("1");	// 성공
			rvo.setNickname(membersEntity.getNickname());
			rvo.setEmailId(membersEntity.getEmailId());
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
		
		try {
			MembersEntity resultEntity = updateNickname(pvo.getEmailId(), pvo.getNickname());
			if(resultEntity == null) {
				throw new Exception("DB 조회 결과 NULL 발생!");
			}
			
			rvo.setNickname(resultEntity.getNickname());
		}
		catch(IllegalArgumentException e) {
			throw e;
		}
		catch(Exception e) {
			throw e;
		}
		
		return rvo;
	}
	
	/**
	 * 닉네임 변경(추가)
	 * @param emailId
	 * @param nickname
	 * @return
	 */
	@Transactional
	public MembersEntity updateNickname(String emailId, String nickname) {
		MembersEntity membersEntity = membersRepository.getOneByEmailId(emailId);
		if(membersEntity == null) {
			return null;
		}
		
		membersEntity.setNickname(nickname);
		return membersRepository.save(membersEntity);
	}

}
