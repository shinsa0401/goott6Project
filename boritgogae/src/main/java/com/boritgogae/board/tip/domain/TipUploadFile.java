package com.boritgogae.board.tip.domain;

<<<<<<<< HEAD:boritgogae/src/main/java/com/boritgogae/board/tip/domain/TipUploadFile.java

public class TipUploadFile {
========
public class NoticeUploadFile {
>>>>>>>> 4fa2a232e3412b34a9a041673698b6cc00e18b30:boritgogae/src/main/java/com/boritgogae/board/notice/etc/NoticeUploadFile.java
	private String savedOriginImageFileName; // 저장된 원본 파일의 이름
	private String notImageFileName; // 이미지가 아닐경우 파일의 이름
	private boolean isImage; // 이미지 파일인지 아닌지
	
	
<<<<<<<< HEAD:boritgogae/src/main/java/com/boritgogae/board/tip/domain/TipUploadFile.java
	public TipUploadFile() {
========
	public NoticeUploadFile() {
>>>>>>>> 4fa2a232e3412b34a9a041673698b6cc00e18b30:boritgogae/src/main/java/com/boritgogae/board/notice/etc/NoticeUploadFile.java
		super(); 
	}


<<<<<<<< HEAD:boritgogae/src/main/java/com/boritgogae/board/tip/domain/TipUploadFile.java
	public TipUploadFile(String savedOriginImageFileName, String notImageFileName,
========
	public NoticeUploadFile(String savedOriginImageFileName, String notImageFileName,
>>>>>>>> 4fa2a232e3412b34a9a041673698b6cc00e18b30:boritgogae/src/main/java/com/boritgogae/board/notice/etc/NoticeUploadFile.java
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
