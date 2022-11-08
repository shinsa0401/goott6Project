package com.boritgogae.board.market.etc;

public class MarketUploadFile {
	private String savedOriginImageFileName; //원본파일이름
	private String thumbnailFileName; //이미지 파일의 경우 썸네일 이미지 파일의 이름
	private String notImageFileName; //이미지가 아닐 경우 파일의 이름
	private boolean isImage; //이미지파일 인지 아닌지 
	
	
	
	public MarketUploadFile() {
		super();
	}



	public MarketUploadFile(String savedOriginImageFileName, String thumbnailFileName, String notImageFileName,
			boolean isImage) {
		super();
		this.savedOriginImageFileName = savedOriginImageFileName;
		this.thumbnailFileName = thumbnailFileName;
		this.notImageFileName = notImageFileName;
		this.isImage = isImage;
	}



	public String getSavedOriginImageFileName() {
		return savedOriginImageFileName;
	}



	public void setSavedOriginImageFileName(String savedOriginImageFileName) {
		this.savedOriginImageFileName = savedOriginImageFileName;
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
		return "UploadFile [savedOriginImageFileName=" + savedOriginImageFileName + ", thumbnailFileName="
				+ thumbnailFileName + ", notImageFileName=" + notImageFileName + ", isImage=" + isImage + "]";
	}

	
	
}
