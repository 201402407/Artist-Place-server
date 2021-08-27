// Receive Value Object(수신 데이터. 가공 완료 후 전송하기 위한 데이터)
package com.artiplace.api.common.rvo;

import java.io.Serializable;
import java.util.List;

import com.artiplace.api.common.vo.QuestionVO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@ApiModel(value="GetQuestionListRVO", description="문제 리스트 RVO")
public class GetQuestionListRVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="문제 유형", required = true)
	private List<QuestionVO> questionList;
}
