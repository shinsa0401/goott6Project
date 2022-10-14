package com.boritgogae.board.free.domain;

public class SearchCriterria {
	
	@Override
	public String toString() {
		return "SearchCriterria [page=" + page + ", pageSize=" + pageSize + ", searchType=" + searchType
				+ ", searchWord=" + searchWord + "]";
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getSearchWord() {
		return searchWord;
	}
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
	private int page;
	private int pageSize;
	private String searchType; 
	private String searchWord;
	public SearchCriterria(int page, int pageSize, String searchType, String searchWord) {
		super();
		this.page = page;
		this.pageSize = pageSize;
		this.searchType = searchType;
		this.searchWord = searchWord;
	}
	public SearchCriterria() {
		super();
	}
	
	
	
	
	
	
	
	
}
