package com.artiplace.api.comn.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;

import com.artiplace.api.comn.Status;

/**
 * ModelAndView Utils
 * HTTP Status 주입
 * @author 이해원
 */
public class MavUtils {
	/* HTTP 통신 200 성공 시 */
	public static ModelAndView okModelAndView(ModelAndView mav) {
		Status status = Status.builder()
				.code(HttpStatus.OK.value())
				.codeMsg(HttpStatus.OK.name())
				.message("")
				.build();
		
		// mav.setStatus(HttpStatus.OK);
		mav.addObject("Status", status);
		return mav;
	}
	
	public static ModelAndView okModelAndView(ModelAndView mav, String message) {
		Status status = Status.builder()
				.code(HttpStatus.OK.value())
				.codeMsg(HttpStatus.OK.name())
				.message(message)
				.build();
		
		// mav.setStatus(HttpStatus.OK);
		mav.addObject("Status", status);
		return mav;
	}
	
	
	/* HTTP 실패 시 */
	public static ModelAndView failModelAndView(ModelAndView mav) {
		Status status = Status.builder()
				.code(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.codeMsg(HttpStatus.INTERNAL_SERVER_ERROR.name())
				.message("")
				.build();
		
		// mav.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		mav.addObject("Status", status);
		return mav;
	}
	
	public static ModelAndView failModelAndView(ModelAndView mav, Exception e) {
		Status status = Status.builder()
				.code(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.codeMsg(HttpStatus.INTERNAL_SERVER_ERROR.name())
				.message(e.getMessage())
				.build();
		
		// mav.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		mav.addObject("Status", status);
		return mav;
	}
}
