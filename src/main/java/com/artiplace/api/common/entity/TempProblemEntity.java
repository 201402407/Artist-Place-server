// Receive Value Object(수신 데이터. 가공 완료 후 전송하기 위한 데이터)
package com.artiplace.api.common.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Table(name="temp_problem")	// DB에서 해당 이름의 테이블과 매칭
@ApiModel(value="TempProblemEntity", description="임시 문제 테이블")
@DynamicUpdate  // 변경된 필드만 적용(세팅되지 않은 필드는 NULL로)
@DynamicInsert  // 변경된 필드만 적용(세팅되지 않은 필드는 NULL로)
public class TempProblemEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id	// Primary key
	@ApiModelProperty(value="코드 ID", required = true)
	private int codeId;
	@ApiModelProperty(value="문제 유형", required = true)
	private int type;
	@ApiModelProperty(value="문제내용", required = true)
	private String problemName;
	@ApiModelProperty(value="문제 풀이 여부", required = true)
	private int state;
}
