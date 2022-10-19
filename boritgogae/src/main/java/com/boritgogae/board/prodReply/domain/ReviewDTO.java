package com.boritgogae.board.prodReply.domain;

public class ReviewDTO {
	private String userId;
	private String nickName;
	private String prodName;
	private String prodNo;
	private String title;
	private String reviewContent;
	private int assess;
	
	
	
	public ReviewDTO(String userId, String nickName, String prodName, String prodNo, String title, String reviewContent,
			int assess) {
		super();
		this.userId = userId;
		this.nickName = nickName;
		this.prodName = prodName;
		this.prodNo = prodNo;
		this.title = title;
		this.reviewContent = reviewContent;
		this.assess = assess;
	}



	public ReviewDTO() {
		super();
	}



	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getProdName() {
		return prodName;
	}


	public void setProdName(String prodName) {
		this.prodName = prodName;
	}


	public String getProdNo() {
		return prodNo;
	}


	public void setProdNo(String prodNo) {
		this.prodNo = prodNo;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getReviewContent() {
		return reviewContent;
	}


	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}


	public int getAssess() {
		return assess;
	}


	public void setAssess(int assess) {
		this.assess = assess;
	}


	public String getNickName() {
		return nickName;
	}



	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@Override
	public String toString() {
		return "ReviewDTO [userId=" + userId + ", nickName=" + nickName + ", prodName=" + prodName + ", prodNo="
				+ prodNo + ", title=" + title + ", reviewContent=" + reviewContent + ", assess=" + assess + "]";
	}

}
