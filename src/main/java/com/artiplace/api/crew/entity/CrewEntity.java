// Receive Value Object(수신 데이터. 가공 완료 후 전송하기 위한 데이터)
package com.artiplace.api.crew.entity;

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
import org.hibernate.annotations.DynamicUpdate;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name="crew")	// DB에서 해당 이름의 테이블과 매칭
@ApiModel(value="CrewEntity", description="크루 테이블")
@DynamicUpdate  // 변경된 필드만 적용(세팅되지 않은 필드는 NULL로)
@DynamicInsert  // 변경된 필드만 적용(세팅되지 않은 필드는 NULL로)
public class CrewEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id	// Primary key
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@ApiModelProperty(value="index(PK)", required = true)
	private BigInteger id;
	@ApiModelProperty(value="크루명", required = true)
	private String name;
	@ApiModelProperty(value="크루 제한 인원수", required = true)
	private int limitMemberNum;
	@ApiModelProperty(value="크루 마스터 ID", required = true)
	private String masterId;
	@ApiModelProperty(value="크루 한줄소개", required = true)
	private String shortIntroduction;
	@ApiModelProperty(value="크루 소개", required = true)
	private String description;
	@Temporal(TemporalType.TIMESTAMP)
	@ApiModelProperty(value="로그인 접속 시간", required = true)
	private Date createTime;
}
