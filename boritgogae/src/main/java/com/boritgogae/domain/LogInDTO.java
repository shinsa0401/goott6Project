package com.boritgogae.domain;

public class LogInDTO {
	private String memberId;
	private String memberPwd;
	private boolean remember;
	
	public LogInDTO() {
		super();
	}
	
	public LogInDTO(String memberId, String memberPwd, boolean remember) {
		super();
		this.memberId = memberId;
		this.memberPwd = memberPwd;
		this.remember = remember;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberPwd() {
		return memberPwd;
	}
	public void setMemberPwd(String memberPwd) {
		this.memberPwd = memberPwd;
	}
	public boolean isRemember() {
		return remember;
	}
	public void setRemember(boolean remember) {
		this.remember = remember;
	}
	
	@Override
	public String toString() {
		return "LogInDTO [memberId=" + memberId + ", memberPwd=" + memberPwd + ", remember=" + remember + "]";
	}
	
	
}
