package com.boritgogae.board.ask.domain;

public class UploadAskFile {
	private String savedOriginImageFileName; // 저장된 원본 파일의 이름
	private String thumbnailFileName; // 썸네일 파일의 이름
	private String notImageFileName; // 이미지가 아닐경우 파일의 이름
	private boolean isImage; // 이미지 파일인지 아닌지	
	
	public UploadAskFile() {
		super();
	}

	public UploadAskFile(String savedOriginImageFileName, String thumbnailFileName, String notImageFileName,
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
		return "UploadAskFile [savedOriginImageFileName=" + savedOriginImageFileName + ", thumbnailFileName="
				+ thumbnailFileName + ", notImageFileName=" + notImageFileName + ", isImage=" + isImage + "]";
	}

	
	
}
