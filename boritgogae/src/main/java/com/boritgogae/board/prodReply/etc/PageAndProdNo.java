package com.boritgogae.board.prodReply.etc;

public class PageAndProdNo {
	private String prodNo;
	private int startNum;
	private int postPerPage;
	
	
	public PageAndProdNo(String prodNo, int startNum, int postPerPage) {
		super();
		this.prodNo = prodNo;
		this.startNum = startNum;
		this.postPerPage = postPerPage;
	}


	public String getProdNo() {
		return prodNo;
	}


	public void setProdNo(String prodNo) {
		this.prodNo = prodNo;
	}


	public int getStartNum() {
		return startNum;
	}


	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}


	public int getPostPerPage() {
		return postPerPage;
	}


	public void setPostPerPage(int postPerPage) {
		this.postPerPage = postPerPage;
	}


	@Override
	public String toString() {
		return "PageAndProdNo [prodNo=" + prodNo + ", startNum=" + startNum + ", postPerPage=" + postPerPage + "]";
	}
	
	
}
