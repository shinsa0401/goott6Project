package com.boritgogae.board.question.etc;

public class QuestionSearchCriteria {
	private String searchType;
	private String searchWord;
	
	public QuestionSearchCriteria() {
		super();
	}
	
	public QuestionSearchCriteria(String searchType, String searchWord) {
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
