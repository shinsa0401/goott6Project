package com.boritgogae.board.market.domain;

public class Paging {
	
	private int postperPage = 10;
	private int pageCntPerBlock = 5; //페이지 목록 수
	
	private int totalPage; //전체페이지
	private int totalPostCnt; //전체 글의 개수
	
	private int startNum; //출력 시작할 인덱스번호
	
	private int totalPagingBlock; //전체 페이징 블럭 수
	private int currentPagingBlock; //현재 페이지가 속한 페이징 블럭 번호
	private int startNumOfCurPagingBlock; //출력할 블럭의 시작 페이지 번호
	private int endNumOfCurPagingBlock; //출력할 블럭의 끝 페이지 번호
	
	public int getPostperPage() {
		return postperPage;
	}
	
	public void setPostperPage(int postperPage) {
		this.postperPage = postperPage;
	}
	
	public int getPageCntPerBlock() {
		return pageCntPerBlock;
	}
	
	public void setPageCntPerBlock(int pageCntPerBlock) {
		this.pageCntPerBlock = pageCntPerBlock;
	}
	
	public int getTotalPage() {
		return totalPage;
	}
	
	public void setTotalPage(int totalPostCnt) {
		
		if(totalPostCnt % this.postperPage !=0) {
			this.totalPostCnt = (totalPostCnt/this.postperPage) +1;
		}else{
			this.totalPostCnt = totalPostCnt/this.postperPage;
		}
		
	}
	public int getTotalPostCnt() {
		return totalPostCnt;
	}
	
	//전체 페이지 수 구하기
	public void setTotalPostCnt(int totalPostCnt) {
		this.totalPostCnt = totalPostCnt;
		
	}
	public int getStartNum() {
		return startNum;
	}
	
	//페이징 시작 인덱스 번호 = 페이징당 보여줄 글 갯수 *(현재페이지 -1)
	public void setStartNum(int pageNo) {
		this.startNum = this.postperPage * (pageNo-1);
	}
	public int getTotalPagingBlock() {
		return totalPagingBlock;
	}
	
	//전체 페이징 블럭 수
	public void setTotalPagingBlock(int totalPage) {
		if(totalPage % this.pageCntPerBlock !=0) {
			this.totalPagingBlock = (totalPage/this.pageCntPerBlock)+1;
		}else {
			this.totalPagingBlock=totalPage / this.pageCntPerBlock;
		}
		
	}
	
	public int getCurrentPagingBlock() {
		return currentPagingBlock;
	}
	
	public void setCurrentPagingBlock(int pageNo) {
		this.currentPagingBlock = (int)(Math.ceil((double)pageNo/this.pageCntPerBlock));
	}
	
	public int getStartNumOfCurPagingBlock() {
		return startNumOfCurPagingBlock;
	}
	
	//현재 페이지 시작번호
	public void setStartNumOfCurPagingBlock(int currentPagingBlock) {
		this.startNumOfCurPagingBlock = ((currentPagingBlock-1)*this.pageCntPerBlock)+1;
	}
	
	public int getEndNumOfCurPagingBlock() {
		return endNumOfCurPagingBlock;
	}
	
	//현재 페이지 블럭 끝 번호
	public void setEndNumOfCurPagingBlock(int startNumOfCurPagingBlock) {
		this.endNumOfCurPagingBlock = (startNumOfCurPagingBlock+this.pageCntPerBlock)-1;
	}
	
	
	@Override
	public String toString() {
		return "Paging [postperPage=" + postperPage + ", pageCntPerBlock=" + pageCntPerBlock + ", totalPage="
				+ totalPage + ", totalPostCnt=" + totalPostCnt + ", startNum=" + startNum + ", totalPagingBlock="
				+ totalPagingBlock + ", currentPagingBlock=" + currentPagingBlock + ", startNumOfCurPagingBlock="
				+ startNumOfCurPagingBlock + ", endNumOfCurPagingBlock=" + endNumOfCurPagingBlock + "]";
	}
	
	
	

}
