/**
 * Sample Controller
 * �깦�뵆 而⑦듃濡ㅻ윭
 */

package com.artiplace.api.sample.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.artiplace.api.comn.util.EncUtils;
import com.artiplace.api.sample.pvo.SamplePVo;
import com.artiplace.api.sample.rvo.SampleRVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api("Sample API")
@RequestMapping("/api")
@Controller
@Slf4j
public class SampleController {
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
    // 메서드 설명, 메서드 상세 설명, html 적용도 가능. 
    @ApiOperation(value = "해시 ID 값 리턴", notes = "특정 문자열의 <big>해시 ID 값</big>을 반환한다.")
	@RequestMapping(value = "/main", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView getHashId(HttpServletRequest request, @RequestParam(value="word", required=true) String word) {
		// log.info("Welcome home! The client locale is {}.", locale);
		ModelAndView mav = new ModelAndView("jsonView");
		System.out.println(request.getLocalName() +  ", " + request.getServerPort());
		int hashWord = word.hashCode();
		mav.addObject("hashWord", hashWord);
		return mav;
	}
	
	@RequestMapping(value = "/getCount", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView getCount(HttpServletRequest request, @RequestParam(value="count", required=false) String cnt) {
		// log.info("Welcome home! The client locale is {}.", locale);
		// Log.debug(String.valueOf(cnt));
		ModelAndView mav = new ModelAndView("jsonView");
		int count = 256;
		mav.addObject("count", count);
		return mav;
	}
	
    @ApiOperation(value = "해시 ID(SHA-256, MD5) 리턴", notes = "특정 문자열의 <big>해시 ID(SHA-256, MD5) 값</big>을 반환한다.")
	@RequestMapping(value = "/hash", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView temp(HttpServletRequest request, @RequestParam(value="word", required=true) String word) {
		ModelAndView mav = new ModelAndView("jsonView");
		
		String encSha256Word = EncUtils.hashing(word, "sha256");
		String encMd5Word = EncUtils.hashing(word, "md5");
		System.out.println("request.getHeader : " + request.getHeader("User-Agent"));
		
		mav.addObject("word", word);
		mav.addObject("sha256", encSha256Word);
		mav.addObject("md5", encMd5Word);
		
		return mav;
	}
    
    @ApiOperation(value = "ID, PWD로 로그인", notes = "<p>ID, PWD로 로그인<p> (ID=test, pwd=test)", response = SampleRVo.class)
   	@RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(HttpServletRequest request, @RequestBody SamplePVo pvo, BindingResult bindingResult) {	// BindingResult : 데이터 바인딩 결과 담김
    	ModelAndView mav = new ModelAndView("jsonView");
        if(bindingResult.hasErrors()){
        	System.out.println("--------------------- bindingResult error start ---------------------");
            bindingResult.getAllErrors().forEach(c ->{
                System.out.println(c);
            });
            System.out.println("--------------------- bindingResult error end ---------------------");
        }
    	System.out.println(pvo.toString());
		String shaPwd = "9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08"; // test sha256 결과값
		String inputPwd = pvo.getPwd();
		String shaInputPwd = EncUtils.hashing(inputPwd, "sha256");
		SampleRVo rvo = new SampleRVo();
		
		log.debug(shaInputPwd);
		System.out.println(shaInputPwd);
		log.debug(shaPwd);
		System.out.println(shaPwd);
		
		if(shaInputPwd.equals(shaPwd)) {
			rvo.setResultCd(1);
			rvo.setResultMsg("로그인 성공!");
//			rvo = SampleRVo.builder()
//			.resultCd(1)
//			.resultMsg("로그인 성공!")
//			.build();
		}
		else {
			rvo.setResultCd(0);
			rvo.setResultMsg("로그인 실패!");
//			rvo = SampleRVo.builder()
//			.resultCd(0)
//			.resultMsg("로그인 실패!")
//			.build();
		}
		
		mav.addObject(rvo);
		return mav;
    	
    }
    
    @RequestMapping(value = "/greeting", method = {RequestMethod.GET, RequestMethod.POST})
    public String get(ModelMap model) {
        model.addAttribute("message", "Hello, World!");
        return "greeting";
    }
}
