// Receive Value Object(수신 데이터. 가공 완료 후 전송하기 위한 데이터)
package com.artiplace.api.common.entity;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@Entity
@Table(name="member")	// DB에서 해당 이름의 테이블과 매칭
@ApiModel(value="LoginPVO", description="로그인 PVO")
public class LoginEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id	// Primary key
	@ApiModelProperty(value="아이디", required = true)
	private String emailId;
	@ApiModelProperty(value="비밀번호(해시)", required = true)
	private String pwd;
}
