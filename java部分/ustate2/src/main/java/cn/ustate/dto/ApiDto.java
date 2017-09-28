package cn.ustate.dto;

import cn.ustate.constraint.ApiCodeEnum;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author EricFu
 * @date 2017-7-16 下午10:47:52
 */

@JsonInclude(Include.NON_NULL)
public class ApiDto{
	private String message;
	private int code;
	private Object data;
	
	public ApiDto(ApiCodeEnum apiCodeEnum, Object data) {
		this.message = apiCodeEnum.getMessage();
		this.code = apiCodeEnum.getCode();
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
