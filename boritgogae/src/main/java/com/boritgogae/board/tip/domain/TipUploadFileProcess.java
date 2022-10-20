package com.boritgogae.board.tip.domain;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.springframework.util.FileCopyUtils;

public class TipUploadFileProcess {
	public static TipUploadFile uploadFileProcess(String upPath, String originalFileName, byte[] file, String contentType) throws IOException {
		UUID uuid = UUID.randomUUID();
		String saveFileName = uuid.toString() + "_" + originalFileName; // 중복되지 않는 파일 이름
		String savePath = calcSavePath(upPath); // 파일이 저장될 경로 계산하여 얻어옴 (최종 저장될 경로 = upPath + savePath)
		
		TipUploadFile upFile = new TipUploadFile(null, null, false);
		
		upFile.setSavedOriginImageFileName(savePath + File.separator + File.separator + saveFileName);
		
		File originTarget = new File(upPath + savePath, saveFileName);
		FileCopyUtils.copy(file, originTarget); // 원본 파일 저장
		
		if(TipImageMediaConfirm.getMediaType(contentType.toLowerCase()) != null) { // 이미지 파일이라면
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
		
		String yearPath = File.separator + File.separator + cal.get(Calendar.YEAR) + ""; //  \2022
		String monthPath = yearPath + File.separator + File.separator + new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1); // \2022\09
		String datePath = monthPath + File.separator + File.separator + new DecimalFormat("00").format(cal.get(Calendar.DATE)); // \2022\09\13
		
		System.out.println(upPath + datePath);
		
		makeDir(upPath, yearPath, monthPath, datePath); // 실제 저장될 폴더를 만듬
		
		return datePath;
	}

	private static void makeDir(String upPath, String... paths) {
		
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

