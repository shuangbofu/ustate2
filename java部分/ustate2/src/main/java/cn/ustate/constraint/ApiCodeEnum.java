package cn.ustate.constraint;
/**
 * @author EricFu
 * @date 2017-7-16 下午10:50:48
 */
public enum ApiCodeEnum {
	SUCCESS(200, "success"),
	FAILURE(500, "服务器运行异常"),
	NO_LOGIN(2015, "没有登录"),
	LOGIN_FAILURE(1003, "参数错误"),
	LIKE_SUCCESS(200, "yes"),
	LIKE_FAILURE(2015, "请先登录");
	
	private Integer code;
	private String message;
	
	private ApiCodeEnum(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	public Integer getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
	
}
