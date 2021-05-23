// Receive Value Object(수신 데이터. 가공 완료 후 전송하기 위한 데이터)
package com.artiplace.api.common.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@DynamicInsert  // 변경된 필드만 적용(세팅되지 않은 필드는 NULL로)
@Table(name="logs_login")	// DB에서 해당 이름의 테이블과 매칭
@ApiModel(value="LoginLogEntity", description="로그인 접속 로그 테이블")
public class LoginLogEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id	// Primary key
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@ApiModelProperty(value="index(PK)", required = true)
	private BigInteger id;
	@ApiModelProperty(value="이메일 ID", required = true)
	private String emailId;
	
	@ApiModelProperty(value="로그인 접속 결과", required = true)
	private String loginResult;
	@Temporal(TemporalType.TIMESTAMP)
	@ApiModelProperty(value="로그인 접속 시간", required = true)
	private Date loginTime;
}
