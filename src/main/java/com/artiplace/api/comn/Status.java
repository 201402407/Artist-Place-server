package com.artiplace.api.comn;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Status {
	int code;
	String codeMsg;
	String message;
}
