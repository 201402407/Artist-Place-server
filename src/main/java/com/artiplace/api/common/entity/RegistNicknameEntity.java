// Receive Value Object(수신 데이터. 가공 완료 후 전송하기 위한 데이터)
package com.artiplace.api.common.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name="members")	// DB에서 해당 이름의 테이블과 매칭
@ApiModel(value="RegistNicknameEntity", description="회원 테이블")
public class RegistNicknameEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id	// Primary key
	@ApiModelProperty(value="이메일 ID", required = true)
	private String emailId;
	@ApiModelProperty(value="비밀번호(해시)", required = true)
	private String pwd;
	@ApiModelProperty(value="닉네임", required = true)
	private String nickname;
}
