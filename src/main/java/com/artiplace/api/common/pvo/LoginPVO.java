// Receive Value Object(수신 데이터. 가공 완료 후 전송하기 위한 데이터)
package com.artiplace.api.common.pvo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@ApiModel(value="LoginPVO", description="로그인 PVO")
public class LoginPVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="아이디", required = true)
	private String emailId;
	@ApiModelProperty(value="비밀번호(해시)", required = true)
	private String pwd;
}
