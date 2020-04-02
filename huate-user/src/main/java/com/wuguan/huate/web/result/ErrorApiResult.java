package com.wuguan.huate.web.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ErrorApiResult extends ApiResult {

	private String message;

	public ErrorApiResult(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

}
