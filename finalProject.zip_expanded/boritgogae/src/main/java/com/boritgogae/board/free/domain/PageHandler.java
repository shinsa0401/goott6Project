package com.boritgogae.board.free.domain;

public class PageHandler {

	private int totalCnt; // 총 개시물 갯수
	private int pageSize; // 한 페이지의 크기
	private int naviSize =10; // 페이지내비게이션 크기
	private int totalPage; // 전체 페이지 갯수
	private int page; //현재페이지
	private int beginPage; // 네비게이션 첫번째 페이지
	private int endPage; // 네비게이션 마지막 페이지
	private boolean showPrev; // 이전 페이지 이동하는 링크 부여
	private boolean showNext; // 다음 페이지 이동하는 링크 부
	
	
	public PageHandler(int totalCnt, int page) {
		this(totalCnt,page,3);
	}
	
	public PageHandler(int totalCnt, int page,int pageSize) {
	
		this.totalCnt = totalCnt;
		this.pageSize = pageSize;
		this.page = page;
		
		
		
		totalPage = (int)Math.ceil(totalCnt/(double)pageSize);
		
		beginPage= page/naviSize*naviSize+1; // 페이지 앞부분 1~5(1), 6~10(6)
		endPage =Math.min(beginPage+naviSize-1 , totalPage);//Math.min 둘중 작은값을 써
		showPrev = beginPage!=1;
		showNext = endPage!= totalPage;
		
		
		
		
	
	}




	

	@Override
	public String toString() {
		return "PageHandler [totalCnt=" + totalCnt + ", pageSize=" + pageSize + ", naviSize=" + naviSize
				+ ", totalPage=" + totalPage + ", page=" + page + ", beginPage=" + beginPage + ", endPage=" + endPage
				+ ", showerPrev=" + showPrev + ", showerNext=" + showNext + "]";
	}




	public int getTotalCnt() {
		return totalCnt;
	}




	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}




	public int getPageSize() {
		return pageSize;
	}




	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}




	public int getNaviSize() {
		return naviSize;
	}




	public void setNaviSize(int naviSize) {
		this.naviSize = naviSize;
	}




	public int getTotalPage() {
		return totalPage;
	}




	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}




	public int getPage() {
		return page;
	}




	public void setPage(int page) {
		this.page = page;
	}




	public int getBeginPage() {
		return beginPage;
	}




	public void setBeginPage(int beginPage) {
		this.beginPage = beginPage;
	}




	public int getEndPage() {
		return endPage;
	}




	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}




	public boolean isShowPrev() {
		return showPrev;
	}




	public void setShowPrev(boolean showPrev) {
		this.showPrev = showPrev;
	}




	public boolean isShowNext() {
		return showNext;
	}




	public void setShowNext(boolean showerNext) {
		this.showNext = showerNext;
	}
	
	
	
	
	
	











	
	
	
	


}
