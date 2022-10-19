package com.boritgogae.board.ask.domain;

public class AskCodeVo {
	private String askCode;
	private String askOption;
	
	public String getAskCode() {
		return askCode;
	}
	public void setAskCode(String askCode) {
		this.askCode = askCode;
	}
	public String getAskOption() {
		return askOption;
	}
	public void setAskOption(String askOption) {
		this.askOption = askOption;
	}
	@Override
	public String toString() {
		return "AskCodeVo [askCode=" + askCode + ", askOption=" + askOption + "]";
	}
	public AskCodeVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AskCodeVo(String askCode, String askOption) {
		super();
		this.askCode = askCode;
		this.askOption = askOption;
	}
	
	
	
	

}
