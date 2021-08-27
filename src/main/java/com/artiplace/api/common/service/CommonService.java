package com.artiplace.api.common.service;

import javax.servlet.http.HttpServletRequest;

import com.artiplace.api.common.pvo.AddQuestionPVO;
import com.artiplace.api.common.pvo.GetQuestionListPVO;
import com.artiplace.api.common.pvo.LoginPVO;
import com.artiplace.api.common.pvo.RegistNicknamePVO;
import com.artiplace.api.common.rvo.GetQuestionListRVO;
import com.artiplace.api.common.rvo.LoginRVO;
import com.artiplace.api.common.rvo.RegistNicknameRVO;

import springfox.documentation.annotations.ApiIgnore;

public interface CommonService {
	
	/*
     * 로그인
     */
	public LoginRVO chkLogin(@ApiIgnore HttpServletRequest request, LoginPVO pvo) throws Exception;
	
    /*
     * 로그인 로그 기록
     */
	public boolean addLoginLog(@ApiIgnore HttpServletRequest request, LoginPVO pvo, LoginRVO rvo);
	
	/*
     * 닉네임 설정(추가) 
     */
	public RegistNicknameRVO registNickname(@ApiIgnore HttpServletRequest request, RegistNicknamePVO pvo) throws Exception;
	
	/*
     * 문제 추가
     */
	public boolean addQuestion(@ApiIgnore HttpServletRequest request, AddQuestionPVO pvo) throws Exception;
	
	/*
     * 문제 리스트 조회
     */
	public GetQuestionListRVO getQuestionList(@ApiIgnore HttpServletRequest request, GetQuestionListPVO pvo) throws Exception;
	
}
