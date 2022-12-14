package com.boritgogae.board.tip.domain;

public class TipUploadFile {
	private String savedOriginImageFileName; // 저장된 원본 파일의 이름
	private String notImageFileName; // 이미지가 아닐경우 파일의 이름
	private boolean isImage; // 이미지 파일인지 아닌지
	
	public TipUploadFile() {
		super(); 
	}

	public TipUploadFile(String savedOriginImageFileName, String notImageFileName,
			boolean isImage) {
		super();
		this.savedOriginImageFileName = savedOriginImageFileName;
		this.notImageFileName = notImageFileName;
		this.isImage = isImage;
	}


	public String getSavedOriginImageFileName() {
		return savedOriginImageFileName;
	}

	public void setSavedOriginImageFileName(String savedOriginImageFileName) {
		this.savedOriginImageFileName = savedOriginImageFileName;
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
		return "UploadAskFile [savedOriginImageFileName=" + savedOriginImageFileName + ","
				+ " notImageFileName=" + notImageFileName + ", isImage=" + isImage + "]";
	}

	
}
