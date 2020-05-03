package com.wuguan.huate.web.result;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class ApiResult {
	
	protected Integer code;
	
	
	public static ApiResult success(Object data) {
		return new SuccessApiResult(data);
	}
	
	public static ApiResult success() {
		return new SuccessApiResult();
	}
	
	public static ApiResult error(Integer code,String message) {
		return new ErrorApiResult(code, message);
	}

}
