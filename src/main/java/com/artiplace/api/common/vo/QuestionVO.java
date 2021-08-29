// Receive Value Object(수신 데이터. 가공 완료 후 전송하기 위한 데이터)
package com.artiplace.api.common.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@ApiModel(value="QuestionVO", description="문제 VO")
public class QuestionVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="문제 유형", required = true)
	private int type;
	@ApiModelProperty(value="문제내용", required = true)
	private String problemName;
	@ApiModelProperty(value="문제 풀이 여부", required = true)
	private int state;
	@ApiModelProperty(value="정답", required = false)
	private String answer;
}
