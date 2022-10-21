package com.boritgogae.board.ask.domain;


public class AskSearchCriteria {
	private String searchType;
	private String searchWord;
	
	public AskSearchCriteria() {
		super();
	}

	public AskSearchCriteria(String searchType, String searchWord) {
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
		return "AskSearchCriteria [searchType=" + searchType + ", searchWord=" + searchWord + "]";
	}
	
	
}
