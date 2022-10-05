package com.boritgogae.board.ask.etc;

public class UploadFile {
	private String originFileName; // 원본 파일명
	private String thumbnailFileName; // 썸네일 파일의 이름
	private String notImageFileName; // 이미지가 아닐경우 파일의 이름
	private boolean isImage; // 이미지 파일인지 아닌지
	

	public UploadFile() {
		super();
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


	public String getNotImageFileName() {
		return notImageFileName;
	}


	public void setNotImageFileName(String notImageFileName) {
		this.notImageFileName = notImageFileName;
	}


	public boolean isImage() {
		return isImage;
	}


	public void setImage(boolean isImage) {
		this.isImage = isImage;
	}


	@Override
	public String toString() {
		return "UploadFile [originFileName=" + originFileName + ", thumbnailFileName=" + thumbnailFileName
				+ ", notImageFileName=" + notImageFileName + ", isImage=" + isImage + "]";
	}


	public UploadFile(String originFileName, String thumbnailFileName, String notImageFileName, boolean isImage) {
		super();
		this.originFileName = originFileName;
		this.thumbnailFileName = thumbnailFileName;
		this.notImageFileName = notImageFileName;
		this.isImage = isImage;
	}
	
	
	
	
	
	
	
}
