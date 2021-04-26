// Receive Value Object(수신 데이터. 가공 완료 후 전송하기 위한 데이터)
package com.artiplace.api.sample.rvo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import lombok.Data;

// @Builder -> NoArgsConstructor 안됨
@Data
@ApiModel(value="SampleRVo", description="샘플 RVo")
public class SampleRVo implements Serializable {

	private static final long serialVersionUID = 2575053120703824340L;

	
	private int resultCd;
	private String resultMsg;
	
}
