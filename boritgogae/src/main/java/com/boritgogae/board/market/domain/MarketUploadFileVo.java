package com.boritgogae.board.market.domain;


public class MarketUploadFileVo {
	private int no;
	private int bno;
	private String originFileName;
	private String thumbnailFileName;
	
	
	public MarketUploadFileVo() {
		super();
	}

	public MarketUploadFileVo(int no, int bno, String originFileName, String thumbnailFileName) {
		super();
		this.no = no;
		this.bno = bno;
		this.originFileName = originFileName;
		this.thumbnailFileName = thumbnailFileName;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getOriginFileName() {
		return originFileName;
	}

	public void setOriginFileName(String originFileName) {
		this.originFileName = originFileName;
	}

	public String getThumbnailFileName() {
		return thumbnailFileName;
	}

	public void setThumbnailFileName(String thumbnailFileName) {
		this.thumbnailFileName = thumbnailFileName;
	}

	@Override
	public String toString() {
		return "UploadFileVo [no=" + no + ", bno=" + bno + ", originFileName=" + originFileName + ", thumbnailFileName="
				+ thumbnailFileName + "]";
	}
	
	
	
}

