package cn.ustate.constraint;
/**
 * @author EricFu
 * @date 2017-7-17 下午4:33:09
 */
public class NoticeConst {
	/*
	 * 点赞发表生成的通知
	 */
	public static final int ACTION_LIKE_STMT = 1;
	/*
	 * 评论发表生成的通知
	 */
	public static final int ACTION_CMT_STMT = 2;
	/*
	 * 点赞评论生成的通知
	 */
	public static final int ACTION_LIKE_CMT = 3;
	/*
	 * 回复评论生成的通知
	 */
	public static final int ACTION_CMT_CMT = 4;
	/*
	 * 举报通知
	 */
	public static final int ACTION_REPORT = 5;
	/*
	 * 其他通知 
	 */
	public static final int ACTION_OTHER = 6;
	
	/*
	 * 通知未读
	 */
	public static final int STATUS_EFT = 1;
	/*
	 * 通知已读
	 */
	public static final int STATUS_VALID = 0;
}
