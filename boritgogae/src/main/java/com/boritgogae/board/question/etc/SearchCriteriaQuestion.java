package com.boritgogae.board.question.etc;

public class SearchCriteriaQuestion {
	private String searchType;
	private String searchWord;
	
	public SearchCriteriaQuestion() {
		super();
	}
	
	public SearchCriteriaQuestion(String searchType, String searchWord) {
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
