package cn.ustate.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import cn.ustate.constraint.PublicConst;
import cn.ustate.entity.Statement;

/**
 * @author EricFu
 * @date 2017-7-15 下午12:36:08
 */
@JsonInclude(Include.NON_NULL)
public class StatementDto extends Statement {
	
	
	private int author;
	
	private int more = PublicConst.TEXT_NO_MORE;
	
	public int getMore() {
		return more;
	}
	public void setMore(int more) {
		this.more = more;
	}
	public int getAuthor() {
		return author;
	}
	public void setAuthor(int author) {
		this.author = author;
	}
}