/**
 * Sample Controller
 * �깦�뵆 而⑦듃濡ㅻ윭
 */

package com.artiplace.api.common.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.artiplace.api.common.pvo.AddQuestionPVO;
import com.artiplace.api.common.pvo.GetQuestionListPVO;
import com.artiplace.api.common.pvo.LoginPVO;
import com.artiplace.api.common.pvo.RegistNicknamePVO;
import com.artiplace.api.common.rvo.GetQuestionListRVO;
import com.artiplace.api.common.rvo.LoginRVO;
import com.artiplace.api.common.rvo.RegistNicknameRVO;
import com.artiplace.api.common.service.CommonService;
import com.artiplace.api.comn.util.ApiUtils;
import com.artiplace.api.comn.util.MavUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import springfox.documentation.annotations.ApiIgnore;

@Slf4j
@Controller
@Api(value="CommonController", tags="공통 API")
@RequestMapping("/api/common")
public class CommonController {
	
	@Autowired
	CommonService commonService;
    
    @Autowired
    BasicDataSource dataSource;
    
	/**
	 * Login
	 * @param CreateCrewPVO
	 * @param HttpServletRequest
	 * @param bindingResult
	 * @return
	 */
    @ApiOperation(value = "아이디, 패스워드로 로그인", notes = "<p>ID, PWD로 로그인<p>", response = LoginRVO.class)
   	@RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(@RequestBody LoginPVO pvo, @ApiIgnore HttpServletRequest request, @ApiIgnore BindingResult bindingResult) {	// BindingResult : 데이터 바인딩 결과 담김
    	ModelAndView mav = new ModelAndView("jsonView");
    	String methodName = ApiUtils.getMethodName();
    	ApiUtils.startApiLog(methodName);
    	
    	if(!ApiUtils.validReqDataBindingResult("login", bindingResult)) {
    		return MavUtils.failModelAndView(mav, "binding error");
    	}
        log.debug("==============================================================");
        log.debug("[{}] pvo ::: {}", methodName, pvo.toString());
    	log.debug("==============================================================");
		try {
			LoginRVO rvo = commonService.chkLogin(request, pvo);
			log.debug("==============================================================");
	        log.debug("[{}] rvo ::: {}", methodName, rvo.toString());
	    	log.debug("==============================================================");
			mav.addObject("rvo", rvo);
			
			// 로그인 로그 저장
			ApiUtils.startApiLog("addLoginLog");
			if(commonService.addLoginLog(request, pvo, rvo)) {
				ApiUtils.successServiceLog("addLoginLog");
			}
			else {
				ApiUtils.failServiceLog("addLoginLog");
			}
			
			ApiUtils.endApiLog("addLoginLog");
		} catch (Exception e) {
			return MavUtils.failModelAndView(mav, e);
		} finally {
			ApiUtils.endApiLog(methodName);
		}
		
		return MavUtils.okModelAndView(mav);
    }
    
	/**
	 * 닉네임 설정(추가)
	 * @param request
	 * @param pvo
	 * @param bindingResult
	 * @return
	 */
    @ApiOperation(value = "닉네임 설정(추가)", notes = "<p>닉네임 설정(추가)<p>", response = LoginRVO.class)
   	@RequestMapping(value = "/registNickname", method = RequestMethod.POST)
    public ModelAndView registNickname(@RequestBody RegistNicknamePVO pvo, @ApiIgnore HttpServletRequest request, @ApiIgnore BindingResult bindingResult) {	// BindingResult : 데이터 바인딩 결과 담김
    	ModelAndView mav = new ModelAndView("jsonView");
    	String methodName = ApiUtils.getMethodName();
    	ApiUtils.startApiLog(methodName);
    	
    	if(!ApiUtils.validReqDataBindingResult("registNickname", bindingResult)) {
    		return MavUtils.failModelAndView(mav, "binding error");
    	}
    	
        log.debug("==============================================================");
        log.debug("[{}] pvo ::: {}", methodName, pvo.toString());
    	log.debug("==============================================================");
    	
		try {
			RegistNicknameRVO rvo = commonService.registNickname(request, pvo);
	        log.debug("==============================================================");
	        log.debug("[{}] rvo ::: {}", methodName, rvo.toString());
	    	log.debug("==============================================================");
	    	
			mav.addObject("nickname", rvo.getNickname());
		} catch (Exception e) {
			return MavUtils.failModelAndView(mav, e);
		}
		
		return MavUtils.okModelAndView(mav);
    }
    
	/**
	 * 문제 추가ㅣ
	 * @param request
	 * @param pvo
	 * @param bindingResult
	 * @return
	 */
    @CrossOrigin
    @ApiOperation(value = "문제 추가", notes = "문제 추가")
   	@RequestMapping(value = "/addQuestion", method = RequestMethod.POST)
    public ModelAndView addQuestion(@RequestBody AddQuestionPVO pvo, @ApiIgnore HttpServletRequest request, @ApiIgnore BindingResult bindingResult) {	// BindingResult : 데이터 바인딩 결과 담김
    	ModelAndView mav = new ModelAndView("jsonView");
    	String methodName = ApiUtils.getMethodName();
    	ApiUtils.startApiLog(methodName);
    	
    	if(!ApiUtils.validReqDataBindingResult("addQuestion", bindingResult)) {
    		return MavUtils.failModelAndView(mav, "binding error");
    	}
    	
        log.debug("==============================================================");
        log.debug("[{}] pvo ::: {}", methodName, pvo.toString());
    	log.debug("==============================================================");
    	
		try {
			boolean result = commonService.addQuestion(request, pvo);
	        log.debug("==============================================================");
	        log.debug("[{}] rvo ::: {}", methodName, result);
	    	log.debug("==============================================================");
	    	
		} catch (Exception e) {
			return MavUtils.failModelAndView(mav, e);
		}
		
		return MavUtils.okModelAndView(mav);
    }
    
	/**
	 * 문제 추가ㅣ
	 * @param request
	 * @param pvo
	 * @param bindingResult
	 * @return
	 */
    @CrossOrigin
    @ApiOperation(value = "문제 리스트 조회", notes = "문제 리스트 조회", response = GetQuestionListRVO.class)
   	@RequestMapping(value = "/getQuestionList", method = RequestMethod.POST)
    public ModelAndView getQuestionList(@RequestBody GetQuestionListPVO pvo, @ApiIgnore HttpServletRequest request, @ApiIgnore BindingResult bindingResult) {	// BindingResult : 데이터 바인딩 결과 담김
    	ModelAndView mav = new ModelAndView("jsonView");
    	String methodName = ApiUtils.getMethodName();
    	ApiUtils.startApiLog(methodName);
    	
    	if(!ApiUtils.validReqDataBindingResult("getQuestionList", bindingResult)) {
    		return MavUtils.failModelAndView(mav, "binding error");
    	}
    	
        log.debug("==============================================================");
        log.debug("[{}] pvo ::: {}", methodName, pvo.toString());
    	log.debug("==============================================================");
    	
		try {
			GetQuestionListRVO rvo = commonService.getQuestionList(request, pvo);
	        log.debug("==============================================================");
	        log.debug("[{}] rvo ::: {}", methodName, rvo);
	    	log.debug("==============================================================");
	    	mav.addObject("questionList", rvo.getQuestionList());
		} catch (Exception e) {
			return MavUtils.failModelAndView(mav, e);
		}
		
		return MavUtils.okModelAndView(mav);
    }
    
//    @RequestMapping("/dbConnTest")
//    public ModelAndView dbTest() {
//        Connection conn = null;
//        Statement st = null;
//        
//        ModelAndView mav = new ModelAndView("jsonView");
//        
//        try {
//            conn = dataSource.getConnection();
//            st = conn.createStatement();
//            ResultSet rs = st.executeQuery("select now() as now;");
//            
//            while(rs.next()) {
//            	mav.addObject("now", rs.getString("now"));
//            }
//            
//        } catch (Exception e) {
//        	MavUtils.failModelAndView(mav, e);
//        } finally {
//            try {
//                if(st != null) st.close();
//            } catch (SQLException e) {
//            	MavUtils.failModelAndView(mav, e);
//            }
//            
//            try {
//                if(conn != null) conn.close();
//            } catch (SQLException e) {
//            	MavUtils.failModelAndView(mav, e);
//            }                        
//        }
//        
//        return mav;
//    }
}
