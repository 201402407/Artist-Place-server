// Receive Value Object(수신 데이터. 가공 완료 후 전송하기 위한 데이터)
package com.artiplace.api.common.rvo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@ApiModel(value="RegistNicknameRVO", description="닉네임 설정(추가) RVO")
public class RegistNicknameRVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="결과", required = true)
	private String result;
	@ApiModelProperty(value="닉네임", required = true)
	private String nickname;
}
