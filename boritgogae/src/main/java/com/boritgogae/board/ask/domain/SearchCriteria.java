package com.boritgogae.board.ask.domain;


public class SearchCriteria {
	private String searchType;
	private String searchWord;
	
	public SearchCriteria() {
		super();
	}

	public SearchCriteria(String searchType, String searchWord) {
		super();
		this.searchType = searchType;
		this.searchWord = searchWord;
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

	@Override
	public String toString() {
		return "SearchCriteria [searchType=" + searchType + ", searchWord=" + searchWord + "]";
	}
	
	
}
