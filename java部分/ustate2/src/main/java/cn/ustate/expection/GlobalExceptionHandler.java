package cn.ustate.expection;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import cn.ustate.constraint.ApiCodeEnum;
import cn.ustate.dto.ApiDto;

/**
 * @author EricFu
 * @date 2017-8-15 上午11:29:06
 */
@ControllerAdvice
@EnableWebMvc
public class GlobalExceptionHandler {  
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({ServerException.class})
	@ResponseBody
	public ApiDto handleExceptioin(ServerException e) {
		// TODO 保存日志 e.getMessage()
		e.getEx().printStackTrace();
		return new ApiDto(e.getApiCodeEnum(), null);
	}
	
	@ExceptionHandler({NoLoginException.class})
	@ResponseBody
	public ApiDto handleExceptionApiDto(NoLoginException e) { 
//		e.printStackTrace();
		return new ApiDto(e.getApiCodeEnum(), null);
	}
	
	@ExceptionHandler({Exception.class})
	@ResponseBody
	public ApiDto handleOtherException(Exception e){ 
		e.printStackTrace();
		return new ApiDto(ApiCodeEnum.FAILURE, null);
	}
}
