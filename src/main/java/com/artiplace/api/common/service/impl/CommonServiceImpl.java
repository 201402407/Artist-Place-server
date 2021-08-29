package com.artiplace.api.common.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artiplace.api.common.entity.LoginLogEntity;
import com.artiplace.api.common.entity.MembersEntity;
import com.artiplace.api.common.entity.TempProblemEntity;
import com.artiplace.api.common.pvo.AddQuestionPVO;
import com.artiplace.api.common.pvo.GetQuestionListPVO;
import com.artiplace.api.common.pvo.LoginPVO;
import com.artiplace.api.common.pvo.RegistNicknamePVO;
import com.artiplace.api.common.repository.LoginLogRepository;
import com.artiplace.api.common.repository.MembersRepository;
import com.artiplace.api.common.repository.TempProblemRepository;
import com.artiplace.api.common.rvo.GetQuestionListRVO;
import com.artiplace.api.common.rvo.LoginRVO;
import com.artiplace.api.common.rvo.RegistNicknameRVO;
import com.artiplace.api.common.service.CommonService;
import com.artiplace.api.common.vo.QuestionVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CommonServiceImpl implements CommonService {

	@Autowired
	MembersRepository membersRepository;
	@Autowired
	LoginLogRepository loginLogRepository;
	@Autowired
	TempProblemRepository tempProblemRepository;
	
	
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
	
	@Override
	public boolean addQuestion(HttpServletRequest request, AddQuestionPVO pvo) throws Exception {
		TempProblemEntity entity = new TempProblemEntity();
		entity.setState(pvo.getState());
		entity.setProblemName(pvo.getProblemName());
		entity.setType(pvo.getType());
		entity.setAnswer(pvo.getAnswer());
		try {
			tempProblemRepository.save(entity);
			return true;
		}
		catch(IllegalArgumentException e) {
			return false;
		}
	}
	


	@Override
	public GetQuestionListRVO getQuestionList(HttpServletRequest request, GetQuestionListPVO pvo) throws Exception {
		GetQuestionListRVO rvo = new GetQuestionListRVO();
		
		int type = pvo.getType();
		int state = pvo.getState();
		if(type == -1) {
			if(state == -1) {
				List<TempProblemEntity> list = tempProblemRepository.findAll();
				if(list.size() > 0) {
					List<QuestionVO> resultList = new ArrayList<QuestionVO>();
					for(TempProblemEntity ele : list) {
						QuestionVO vo = new QuestionVO();
						vo.setType(ele.getType());
						vo.setProblemName(ele.getProblemName());
						vo.setState(ele.getState());
						vo.setAnswer(ele.getAnswer());
						resultList.add(vo);
					}
					
					rvo.setQuestionList(resultList);
				}
			}
			else {
				List<TempProblemEntity> list = tempProblemRepository.findByState(state);
				if(list.size() > 0) {
					List<QuestionVO> resultList = new ArrayList<QuestionVO>();
					for(TempProblemEntity ele : list) {
						QuestionVO vo = new QuestionVO();
						vo.setType(ele.getType());
						vo.setProblemName(ele.getProblemName());
						vo.setState(ele.getState());
						vo.setAnswer(ele.getAnswer());
						resultList.add(vo);
					}
					
					rvo.setQuestionList(resultList);
				}
			}
		}
		else {
			if(state == -1) {
				List<TempProblemEntity> list = tempProblemRepository.findByType(type);
				if(list.size() > 0) {
					List<QuestionVO> resultList = new ArrayList<QuestionVO>();
					for(TempProblemEntity ele : list) {
						QuestionVO vo = new QuestionVO();
						vo.setType(ele.getType());
						vo.setProblemName(ele.getProblemName());
						vo.setState(ele.getState());
						vo.setAnswer(ele.getAnswer());
						resultList.add(vo);
					}
					
					rvo.setQuestionList(resultList);
				}
			}
			else {
				List<TempProblemEntity> list = tempProblemRepository.findByTypeAndState(type, state);
				if(list.size() > 0) {
					List<QuestionVO> resultList = new ArrayList<QuestionVO>();
					for(TempProblemEntity ele : list) {
						QuestionVO vo = new QuestionVO();
						vo.setType(ele.getType());
						vo.setProblemName(ele.getProblemName());
						vo.setState(ele.getState());
						vo.setAnswer(ele.getAnswer());
						resultList.add(vo);
					}
					
					rvo.setQuestionList(resultList);
				}
			}

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
