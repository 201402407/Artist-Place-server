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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.artiplace.api.common.pvo.LoginPVO;
import com.artiplace.api.common.pvo.RegistNicknamePVO;
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
    	if(!ApiUtils.validReqDataBindingResult("login", bindingResult)) {
    		return MavUtils.failModelAndView(mav, "binding error");
    	}
    	String methodName = ApiUtils.getMethodName();
        
        log.debug("==============================================================");
        log.debug("[{}] pvo ::: {}", methodName, pvo.toString());
    	log.debug("==============================================================");
    	
		try {
			log.debug("==================== [{}] chkLogin start ====================", methodName);
			LoginRVO rvo = commonService.chkLogin(request, pvo);
			log.debug("==================== [{}] chkLogin end ====================", methodName);
			log.debug("==============================================================");
	        log.debug("[{}] rvo ::: {}", methodName, rvo.toString());
	    	log.debug("==============================================================");
			mav.addObject("rvo", rvo);
			
			// 로그 저장
			log.debug("==================== [{}] addLoginLog start ====================", methodName);
			if(commonService.addLoginLog(request, pvo, rvo)) {
				log.debug("==================== [{}] addLoginLog success!! ====================", methodName);
			}
			else {
				log.debug("==================== [{}] addLoginLog failed!! ====================", methodName);
			}
			log.debug("==================== [{}] addLoginLog end ====================", methodName);
		} catch (Exception e) {
			return MavUtils.failModelAndView(mav, e);
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
    	ApiUtils.validReqDataBindingResult("registNickname", bindingResult);
    	String methodName = ApiUtils.getMethodName();
    	
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
