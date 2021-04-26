// Receive Value Object(수신 데이터. 가공 완료 후 전송하기 위한 데이터)
package com.artiplace.api.sample.pvo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@ApiModel(value="SamplePVo", description="샘플 PVo")
public class SamplePVo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String pwd;
}
