package com.wuguan.huate.comm;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.wuguan.huate.web.result.ApiResult;
import com.wuguan.huate.web.result.ResultEnums;
@RestControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ResponseBody
	@ExceptionHandler(Exception.class)
	public ApiResult exceptionHandler(Exception e) {
		String uuid = UUID.randomUUID().toString();
		if (e instanceof CustomException) {
			CustomException exc = (CustomException) e;
			log.error("业务异常—" + uuid + ":" + exc.getCode() + ":" + exc.getMsg());
			log.error("业务异常—" + uuid, e);
			return ApiResult.error(exc.getCode(), exc.getMsg());
		}
		log.error("系统异常—" + uuid, e);
		return ApiResult.error(ResultEnums.ERROR.getCode(), ResultEnums.ERROR.getMsg());
	}

}
