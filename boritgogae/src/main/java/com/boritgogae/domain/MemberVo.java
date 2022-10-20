package com.boritgogae.domain;

import java.sql.Timestamp;

public class MemberVo {
	private String memberId;
	private String memberName;
	private String memberPwd;
	private String nickName;
	private Timestamp birthDay;
	private String phoneNumber;
	private String MemberEmail;
	private String memberImg;
	private Timestamp joinDate;
	private Timestamp logOutDate;
	private Timestamp lastLoginDate;
	private String delStatus;
	private Timestamp lastPwdUpdate;
	private String isAdmin;
	private String sessionId;
	private Timestamp sessionLimit;
	private int memberPoint;
	private int purchaseAmount;
	private String grade;
	
	public MemberVo() {
		super();
	}

	public MemberVo(String memberId, String memberName, String memberPwd, String nickName, Timestamp birthDay,
			String phoneNumber, String memberEmail, String memberImg, Timestamp joinDate, Timestamp logOutDate,
			Timestamp lastLoginDate, String delStatus, Timestamp lastPwdUpdate, String isAdmin, String sessionId,
			Timestamp sessionLimit, int memberPoint, int purchaseAmount, String grade) {
		super();
		this.memberId = memberId;
		this.memberName = memberName;
		this.memberPwd = memberPwd;
		this.nickName = nickName;
		this.birthDay = birthDay;
		this.phoneNumber = phoneNumber;
		MemberEmail = memberEmail;
		this.memberImg = memberImg;
		this.joinDate = joinDate;
		this.logOutDate = logOutDate;
		this.lastLoginDate = lastLoginDate;
		this.delStatus = delStatus;
		this.lastPwdUpdate = lastPwdUpdate;
		this.isAdmin = isAdmin;
		this.sessionId = sessionId;
		this.sessionLimit = sessionLimit;
		this.memberPoint = memberPoint;
		this.purchaseAmount = purchaseAmount;
		this.grade = grade;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberPwd() {
		return memberPwd;
	}

	public void setMemberPwd(String memberPwd) {
		this.memberPwd = memberPwd;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Timestamp getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Timestamp birthDay) {
		this.birthDay = birthDay;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getMemberEmail() {
		return MemberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		MemberEmail = memberEmail;
	}

	public String getMemberImg() {
		return memberImg;
	}

	public void setMemberImg(String memberImg) {
		this.memberImg = memberImg;
	}

	public Timestamp getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Timestamp joinDate) {
		this.joinDate = joinDate;
	}

	public Timestamp getLogOutDate() {
		return logOutDate;
	}

	public void setLogOutDate(Timestamp logOutDate) {
		this.logOutDate = logOutDate;
	}

	public Timestamp getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Timestamp lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public String getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(String delStatus) {
		this.delStatus = delStatus;
	}

	public Timestamp getLastPwdUpdate() {
		return lastPwdUpdate;
	}

	public void setLastPwdUpdate(Timestamp lastPwdUpdate) {
		this.lastPwdUpdate = lastPwdUpdate;
	}

	public String getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public Timestamp getSessionLimit() {
		return sessionLimit;
	}

	public void setSessionLimit(Timestamp sessionLimit) {
		this.sessionLimit = sessionLimit;
	}

	public int getMemberPoint() {
		return memberPoint;
	}

	public void setMemberPoint(int memberPoint) {
		this.memberPoint = memberPoint;
	}

	public int getPurchaseAmount() {
		return purchaseAmount;
	}

	public void setPurchaseAmount(int purchaseAmount) {
		this.purchaseAmount = purchaseAmount;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		return "MemberVo [memberId=" + memberId + ", memberName=" + memberName + ", memberPwd=" + memberPwd
				+ ", nickName=" + nickName + ", birthDay=" + birthDay + ", phoneNumber=" + phoneNumber
				+ ", MemberEmail=" + MemberEmail + ", memberImg=" + memberImg + ", joinDate=" + joinDate
				+ ", logOutDate=" + logOutDate + ", lastLoginDate=" + lastLoginDate + ", delStatus=" + delStatus
				+ ", lastPwdUpdate=" + lastPwdUpdate + ", isAdmin=" + isAdmin + ", sessionId=" + sessionId
				+ ", sessionLimit=" + sessionLimit + ", memberPoint=" + memberPoint + ", purchaseAmount="
				+ purchaseAmount + ", grade=" + grade + "]";
	}
	
	
}
