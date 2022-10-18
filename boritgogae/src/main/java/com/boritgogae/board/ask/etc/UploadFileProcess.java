package com.boritgogae.board.ask.etc;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.springframework.util.FileCopyUtils;

import com.boritgogae.board.ask.domain.UploadFile;

public class UploadFileProcess {

	public static UploadFile uploadFileProcess(String upPath, String originalFileName, byte[] file, String contentType) throws IOException {
		UUID uuid = UUID.randomUUID();
		String saveFileName = uuid.toString() + "_" + originalFileName; // 중복되지 않는 파일 이름
		String savePath = calcSavePath(upPath); // 파일이 저장될 경로 계산하여 얻어옴 (최종 저장될 경로 = upPath + savePath)
		
		UploadFile upFile = new UploadFile(null, null, null, false);
		
		upFile.setSavedOriginImageFileName(savePath + File.separator + saveFileName);

		File originTarget = new File(upPath + savePath, saveFileName);
		FileCopyUtils.copy(file, originTarget); // 원본 파일 저장
		
		if(ImageMediaConfirm.getMediaType(contentType.toLowerCase()) != null) { // 이미지 파일이라면
			System.out.println("이미지 파일!");
			upFile.setImage(true);
			
			String ext = originalFileName.substring(originalFileName.lastIndexOf(".") + 1); // 파일 확장자
			
			
			BufferedImage originImgFile = ImageIO.read(originTarget); // 업로드된 이미지 파일의 원본을 읽음
			int heightOfOriginImage = originImgFile.getHeight(); // 원본 이미지의 높이(픽셀)
			System.out.println("원본 이미지 : " + originImgFile);
			System.out.println("원본 이미지의 높이 : " + heightOfOriginImage);
			
			BufferedImage destFile = null;
			if(heightOfOriginImage > 100) { // 이미지의 높이가 100픽셀보다 클때 높이를 100픽셀로 맞춰 자른다.
				destFile = Scalr.resize(originImgFile, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, 100);
			}
			
			String thumbnailImageFileName = "thumb_" + saveFileName; // 저장될 썸네일 이미지 파일 이름
			
			File thumbnailFile = new File(upPath + savePath + File.separator + thumbnailImageFileName);
			
			ImageIO.write(destFile, ext, thumbnailFile); // 썸네일 이미지 저장
			
			upFile.setThumbnailFileName(savePath + File.separator + thumbnailImageFileName);
			
		} else {
			// 이미지 파일이 아님
			System.out.println("이미지 파일이 아님!");
			upFile.setImage(false);
			upFile.setNotImageFileName(savePath + File.separator + saveFileName); // 이미지가 아닌 경우의 파일 이름
			
		}
		
		
		return upFile; 
		
	}


	public static String calcSavePath(String upPath) {
		Calendar cal = Calendar.getInstance();
		
		String yearPath = File.separator + cal.get(Calendar.YEAR) + ""; //  \2022
		String monthPath = yearPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1); // \2022\09
		String datePath = monthPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.DATE)); // \2022\09\13
		
		System.out.println("calcSavePath : " + upPath + datePath);
		
		makeDir(upPath, yearPath, monthPath, datePath); // 실제 저장될 폴더를 만듬
		
		return datePath;
	}

	private static void makeDir(String upPath, String... paths) {
		// String... : 가변인자(배열로 처리됨), String 타입의 매개변수로 가변인자 배열로 받을것임을 컴파일러에게 알려줌.
		// yearPath, monthPath, datePath 값을 paths라는 이름의 배열로 넘겨주게 된다. 
		
		if(new File(upPath + paths[paths.length - 1]).exists()) {
			// 해당 경로가 존재한다... 폴더를 생성할 필요가 없다.
			return;
		}
		
		// 해당 경로의 폴더가 없으므로 폴더를 생성함
		for(String path : paths) {
			File dirPath = new File(upPath + path);
			
			if(!dirPath.exists()) {
				dirPath.mkdir(); // 실제 디렉토리(폴더) 생성
			}
		}
		
		
	}
}

