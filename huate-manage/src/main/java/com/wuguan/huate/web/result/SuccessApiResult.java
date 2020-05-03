package com.wuguan.huate.web.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SuccessApiResult extends ApiResult {

	private Object data;

	public SuccessApiResult(Object data) {
		this.code = ResultEnums.SUCCESS.getCode();
		this.data = data;
	}

	public SuccessApiResult() {
		this.code = ResultEnums.SUCCESS.getCode();
	}

}
