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
@ApiModel(value="GetQuestionListPVO", description="문제 리스트 PVO")
public class GetQuestionListPVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="문제 유형", required = true)
	private int type;
	@ApiModelProperty(value="문제 풀이 여부", required = true)
	private int state;
}
