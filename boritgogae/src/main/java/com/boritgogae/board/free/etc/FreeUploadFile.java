package com.boritgogae.board.free.etc;

public class FreeUploadFile {

	@Override
	public String toString() {
		return "UploadFile [SavedOriginImageFileName=" + SavedOriginImageFileName + ", Image=" + Image
				+ ", NotImageFileName=" + NotImageFileName + "]";
	}
	public String getSavedOriginImageFileName() {
		return SavedOriginImageFileName;
	}
	public void setSavedOriginImageFileName(String savedOriginImageFileName) {
		SavedOriginImageFileName = savedOriginImageFileName;
	}
	public boolean isImage() {
		return Image;
	}
	public void setImage(boolean image) {
		Image = image;
	}
	public String getNotImageFileName() {
		return NotImageFileName;
	}
	public void setNotImageFileName(String notImageFileName) {
		NotImageFileName = notImageFileName;
	}

	private String SavedOriginImageFileName;
	private boolean Image;
	private String NotImageFileName;
	
	public FreeUploadFile(String savedOriginImageFileName, boolean image, String notImageFileName
			) {
		super();
		SavedOriginImageFileName = savedOriginImageFileName;
		Image = image;
		NotImageFileName = notImageFileName;
		
	}
	public FreeUploadFile() {
		super();
	}
	
	



	
	
}
