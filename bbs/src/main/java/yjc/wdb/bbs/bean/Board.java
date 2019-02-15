package yjc.wdb.bbs.bean;

import java.sql.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class Board {
	private int bno;
	
	@Size(min=1, message="required")
	private String title;
	
	@Size(min=1, message="required")
	private String writer;
	
	@Size(min=1, message="required")
	private String content;
	
	@Min(0)
	private int readcount;
	
	private Date regdate;
	private Date moddate;
	 
	public String toString() {
		return "title: [" + title + "], writer: [ "+writer+"], content: ["+content+"]";
	}
	
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getReadcount() {
		return readcount;
	}
	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public Date getModdate() {
		return moddate;
	}
	public void setModdate(Date moddate) {
		this.moddate = moddate;
	}
}
