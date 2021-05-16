/**
 * Sample Controller
 * �깦�뵆 而⑦듃濡ㅻ윭
 */

package com.artiplace.api.common.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.function.Consumer;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.artiplace.api.common.pvo.LoginPVO;
import com.artiplace.api.common.rvo.LoginRVO;
import com.artiplace.api.common.service.CommonService;
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
        	log.debug("==================== [login] bindingResult error start ====================");
            bindingResult.getAllErrors().forEach(new Consumer<ObjectError>() {
				@Override
				public void accept(ObjectError c) {
				    System.out.println(c);
				}
			});
            log.debug("==================== [login] bindingResult error end ====================");
        }
        
        log.debug("=======================================================");
        log.debug("pvo ::: {}", pvo.toString());
    	log.debug("=======================================================");
    	
		try {
			LoginRVO rvo = commonService.chkLogin(request, pvo);
			log.debug("=======================================================");
	        log.debug("rvo ::: {}", rvo.toString());
	    	log.debug("=======================================================");
			mav.addObject("result", rvo.getResult());
		} catch (Exception e) {
			return MavUtils.failModelAndView(mav, e);
		}
		
		return MavUtils.okModelAndView(mav);
    	
    }
    
    @RequestMapping("/dbConnTest")
    public ModelAndView dbTest() {
        Connection conn = null;
        Statement st = null;
        
        ModelAndView mav = new ModelAndView("jsonView");
        
        try {
            conn = dataSource.getConnection();
            st = conn.createStatement();
            ResultSet rs = st.executeQuery("select now() as now;");
            
            while(rs.next()) {
            	mav.addObject("now", rs.getString("now"));
            }
            
        } catch (Exception e) {
        	MavUtils.failModelAndView(mav, e);
        } finally {
            try {
                if(st != null) st.close();
            } catch (SQLException e) {
            	MavUtils.failModelAndView(mav, e);
            }
            
            try {
                if(conn != null) conn.close();
            } catch (SQLException e) {
            	MavUtils.failModelAndView(mav, e);
            }                        
        }
        
        return mav;
    }
}
