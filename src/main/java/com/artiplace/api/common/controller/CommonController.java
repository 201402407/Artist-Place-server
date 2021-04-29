/**
 * Sample Controller
 * �깦�뵆 而⑦듃濡ㅻ윭
 */

package com.artiplace.api.common.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.artiplace.api.common.pvo.LoginPVO;
import com.artiplace.api.common.rvo.LoginRVO;
import com.artiplace.api.comn.util.MavUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import springfox.documentation.annotations.ApiIgnore;

@Api(value="CommonController", tags="공통 API")
@RequestMapping("/api/common")
@Controller
@Slf4j
public class CommonController {
    
	/**
	 * Login
	 * @param request
	 * @param pvo
	 * @param bindingResult
	 * @return
	 */
    @ApiOperation(value = "아이디, 패스워드로 로그인", notes = "<p>ID, PWD로 로그인<p>", response = LoginRVO.class)
   	@RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(@RequestBody LoginPVO pvo, @ApiIgnore HttpServletRequest request, @ApiIgnore BindingResult bindingResult) {	// BindingResult : 데이터 바인딩 결과 담김
    	ModelAndView mav = new ModelAndView("jsonView");
        if(bindingResult.hasErrors()){
        	System.out.println("==================== [login] bindingResult error start ====================");
            bindingResult.getAllErrors().forEach(c ->{
                System.out.println(c);
            });
            System.out.println("==================== [login] bindingResult error end ====================");
        }
        
        log.debug("=======================================================");
        log.debug("pvo ::: {}", pvo.toString());
    	log.debug("=======================================================");
    	
		String shaPwd = "9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08"; // test sha256 결과값
		String inputPwd = pvo.getHashPwd();
		// String shaInputPwd = EncUtils.hashing(inputPwd, "sha256");
		LoginRVO rvo = new LoginRVO();
		rvo.setResult("");
		log.debug("=======================================================");
		log.debug("inputPwd ::: {}", inputPwd);
		log.debug("shaPwd ::: {}", shaPwd);
		log.debug("=======================================================");
		
		if(inputPwd.equals(shaPwd)) {
			MavUtils.okModelAndView(mav);
		}
		else {
			MavUtils.failModelAndView(mav);
		}
		
		mav.addObject(rvo);
		return mav;
    	
    }
}
