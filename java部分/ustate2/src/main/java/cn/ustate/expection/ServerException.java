package cn.ustate.expection;

import cn.ustate.constraint.ApiCodeEnum;

/**
 * @author EricFu
 * @date 2017-8-15 下午3:44:47
 */
public class ServerException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ApiCodeEnum apiCodeEnum = ApiCodeEnum.FAILURE;
//	private String message;
	private Exception ex;

//	public ServerException(String message) {
//		super();
//		this.message = message;
//	}
	public ServerException(Exception ex) {
		this.ex = ex;
	}
//	public String getMessage() {
//		return message;
//	}
//
//	public void setMessage(String message) {
//		this.message = message;
//	}

	public ApiCodeEnum getApiCodeEnum() {
		return apiCodeEnum;
	}

	public Exception getEx() {
		return ex;
	}

	public void setEx(Exception ex) {
		this.ex = ex;
	}
}
