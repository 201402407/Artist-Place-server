package com.artiplace.api.comn.util;

import java.util.function.Consumer;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.ModelAndView;

import com.artiplace.api.comn.Status;

import lombok.extern.slf4j.Slf4j;

/**
 * Api 관련 Utils
 * @author 이해원
 */

@Slf4j
public class ApiUtils {
	/* HTTP 통신 200 성공 시 */
	public static ModelAndView okModelAndView(ModelAndView mav) {
		Status status = Status.builder()
				.code(HttpStatus.OK.value())
				.codeMsg(HttpStatus.OK.name())
				.message("")
				.build();
		
		mav.setStatus(HttpStatus.OK);
		mav.addObject("Status", status);
		return mav;
	}
	
	/**
	 * 현재 실행중인 함수 이름 리턴
	 * @return String
	 */
	public static String getMethodName() {
		return Thread.currentThread().getStackTrace()[2].getMethodName();
	}
	
	
	/**
	 * API Request data binding 유효성 체크
	 * @param apiName
	 * @param bindingResult
	 */
	public static boolean validReqDataBindingResult(String apiName, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
        	log.debug("==================== [{}] bindingResult error start ====================", apiName);
            bindingResult.getAllErrors().forEach(new Consumer<ObjectError>() {
				@Override
				public void accept(ObjectError c) {
				    System.out.println(c);
				}
			});
            log.debug("==================== [{}] bindingResult error end ====================", apiName);
            return false;
        }
        
        return true;
	}
	
	/**
	 * API 시작 시 로그 생성
	 * @param apiName
	 * @param bindingResult
	 */
	public static void startApiLog(String methodName) {
		log.debug("==================== [START] {} API ====================", methodName);
	}
	
	/**
	 * API 종료 시 로그 생성
	 * @param apiName
	 * @param bindingResult
	 */
	public static void endApiLog(String methodName) {
		log.debug("==================== [END] {} API ====================", methodName);
	}
	
	/**
	 * Service 로직 성공 시 로그 생성
	 * @param apiName
	 * @param bindingResult
	 */
	public static void successServiceLog(String serviceName) {
		log.debug("==================== [SUCCESS] {} Service ====================", serviceName);
	}
	
	/**
	 * Service 로직 실패 시 로그 생성
	 * @param apiName
	 * @param bindingResult
	 */
	public static void failServiceLog(String serviceName) {
		log.debug("==================== [FAILED] {} Service ====================", serviceName);
	}
}
