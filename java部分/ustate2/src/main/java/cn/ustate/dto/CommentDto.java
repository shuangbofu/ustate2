package cn.ustate.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import cn.ustate.entity.Comment;

/**
 * @author EricFu
 * @date 2017-7-16 下午10:00:55
 */
@JsonInclude(Include.NON_NULL)
public class CommentDto extends Comment {
	
	private int author;
	
	public int getAuthor() {
		return author;
	}

	public void setAuthor(int author) {
		this.author = author;
	}	
}
