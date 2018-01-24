package com.javaex.vo;

public class BoardVo {
	private int no;
	private String title;
	private String content;
	private String regDate;
	private int hit;
	private String userNo;
	
	public BoardVo() {
	}

	public BoardVo(int no, String title, String content, String regDate, int hit, String userNo) {
		this.no = no;
		this.title = title;
		this.content = content;
		this.regDate = regDate;
		this.hit = hit;
		this.userNo = userNo;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
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

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	@Override
	public String toString() {
		return "BoardVo [no=" + no + ", title=" + title + ", content=" + content + ", regDate=" + regDate + ", hit="
				+ hit + ", userNo=" + userNo + "]";
	}
	
}
