package com.boritgogae.domain;

public class DeleteReasonVo {
	private int deleteCode;
	private String deleteWhy;
	
	public DeleteReasonVo() {
		super();
	}

	public DeleteReasonVo(int deleteCode, String deleteWhy) {
		super();
		this.deleteCode = deleteCode;
		this.deleteWhy = deleteWhy;
	}

	public int getDeleteCode() {
		return deleteCode;
	}

	public void setDeleteCode(int deleteCode) {
		this.deleteCode = deleteCode;
	}

	public String getDeleteWhy() {
		return deleteWhy;
	}

	public void setDeleteWhy(String deleteWhy) {
		this.deleteWhy = deleteWhy;
	}

	@Override
	public String toString() {
		return "DeleteReasonVo [deleteCode=" + deleteCode + ", deleteWhy=" + deleteWhy + "]";
	}
	
	
}
