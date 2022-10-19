package com.boritgogae.board.prodReply.etc;

public class UploadImg {
	private int reviewNo;
	private String imgName;
	private String thumbnailName;
	
	public UploadImg(int reviewNo, String imgName, String thumbnailName) {
		super();
		this.reviewNo = reviewNo;
		this.imgName = imgName;
		this.thumbnailName = thumbnailName;
	}

	public UploadImg() {
		super();
	}

	public int getReviewNo() {
		return reviewNo;
	}

	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}

	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}

	public String getThumbnailName() {
		return thumbnailName;
	}

	public void setThumbnailName(String thumbnailName) {
		this.thumbnailName = thumbnailName;
	}

	@Override
	public String toString() {
		return "UploadImg [reviewNo=" + reviewNo + ", imgName=" + imgName + ", thumbnailName=" + thumbnailName + "]";
	}
}
