package model.dto;

import java.sql.Date;

public class TodoDTO {
	
	private int id;
	private String title;
	private String content;
	private Date ymd;
	private String priority;
	private int user_id;
	
	public TodoDTO() {}
	
	public TodoDTO(int id, String title, String content, Date ymd, String priority, int user_id) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.ymd = ymd;
		this.priority = priority;
		this.user_id = user_id;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public Date getYmd() {
		return ymd;
	}
	
	public void setYmd(Date ymd) {
		this.ymd = ymd;
	}
	
	public String getPriority() {
		return priority;
	}
	
	public void setPriority(String priority) {
		this.priority = priority;
	}
	
	public int getUserId() {
		return user_id;
	}
	
	public void setUserId(int user_id) {
		this.user_id = user_id;
	}
	
}