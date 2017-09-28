package cn.ustate.expection;

import cn.ustate.constraint.ApiCodeEnum;

/**
 * @author EricFu
 * @date 2017-8-15 下午1:05:45
 */
public class NoLoginException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ApiCodeEnum apiCodeEnum = ApiCodeEnum.NO_LOGIN;
	public ApiCodeEnum getApiCodeEnum() {
		return apiCodeEnum;
	}
}
