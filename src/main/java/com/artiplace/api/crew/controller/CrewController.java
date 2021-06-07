/**
 * Sample Controller
 * �깦�뵆 而⑦듃濡ㅻ윭
 */

package com.artiplace.api.crew.controller;

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
import com.artiplace.api.crew.pvo.CreateCrewPVO;
import com.artiplace.api.crew.rvo.CreateCrewRVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import springfox.documentation.annotations.ApiIgnore;

@Slf4j
@Controller
@Api(value="CrewController", tags="크루 관련 API")
@RequestMapping("/api/crew")
public class CrewController {
	
	/**
	 * 크루 등록
	 * @param CreateCrewPVO
	 * @param HttpServletRequest
	 * @param bindingResult
	 * @return
	 */
    @ApiOperation(value = "크루 등록", notes = "<p>크루 등록하기<p>", response = CreateCrewRVO.class)
   	@RequestMapping(value = "/createCrew", method = RequestMethod.POST)
    public ModelAndView createCrew(@RequestBody CreateCrewPVO pvo, @ApiIgnore HttpServletRequest request, @ApiIgnore BindingResult bindingResult) {	// BindingResult : 데이터 바인딩 결과 담김
    	ModelAndView mav = new ModelAndView("jsonView");
    	String methodName = ApiUtils.getMethodName();
    	ApiUtils.startApiLog(methodName);
    	
    	if(ApiUtils.validReqDataBindingResult("createCrew", bindingResult)) {
    		return MavUtils.failModelAndView(mav, "binding error");
    	}

        log.debug("==============================================================");
        log.debug("[{}] pvo ::: {}", methodName, pvo.toString());
    	log.debug("==============================================================");
    	
		try {
//			LoginRVO rvo = commonService.chkLogin(request, pvo);
			
			log.debug("==============================================================");
//	        log.debug("[{}] rvo ::: {}", methodName, rvo.toString());
	    	log.debug("==============================================================");
//			mav.addObject("rvo", rvo);
	    	
		} catch (Exception e) {
			return MavUtils.failModelAndView(mav, e);
		} finally {
			ApiUtils.endApiLog(methodName);
		}
		
		return MavUtils.okModelAndView(mav);
    }
}
