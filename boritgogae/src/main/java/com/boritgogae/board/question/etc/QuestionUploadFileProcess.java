package com.boritgogae.board.question.etc;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.springframework.util.FileCopyUtils;

public class QuestionUploadFileProcess {
	
	/**
	 * @methodName : uploadFileRename
	 * @author : 신태호
	 * @param file
	 * @param originalFileName 
	 * @param upPath 
	 * @param contentType 
	 * @throws IOException 
	 * @date : 2022. 10. 10.
	 * @입력 param : 
	 * upPath : 실제 파일이 저장되는 경로
	 * originalFileName : 원본 파일의 이름
	 * file : 업로드 된 파일의 이진 배열
	 * contentType : 업로드된 파일의 타입
	 * 
	 * @returnType : void
	 * upload된 파일의 이름을 중복되지 않도록 한다
	 * 파일을 업로드 된 날짜의 폴더에 저장
	 * 이미지 파일이라면 썸네일 이미지를 만들어 썸네일이미지와 원본파일을 함께 저장
	 * 이미지 파일이 아니라면 원본파일만 저장
	 */
	public static QuestionUploadFile uploadFileProcess(String upPath, String originalFileName, byte[] file, String contentType) throws IOException {
		UUID uuid = UUID.randomUUID();
		String saveFileName = uuid.toString() + "_" + originalFileName; // 중복되지 않는 파일 이름
		String savePath = calcSavePath(upPath); // 파일이 저장될 경로를 계산하여 얻어옴
		// 최종 저장될 경로 = upPath + savePath
		
		QuestionUploadFile upFile = new QuestionUploadFile();
		upFile.setSavedOriginImageFileName(savePath + File.separator + saveFileName);
		
		File orginTarget = new File(upPath + savePath, saveFileName);
		FileCopyUtils.copy(file, orginTarget); // 원본 파일 저장
		
		if (QuestionImgMediaConfirm.getMediaType(contentType.toLowerCase()) != null) { // 이미지 파일이면..
			System.out.println("이미지파일이 맞음");
			upFile.setImage(true);
			
			String ext = originalFileName.substring(originalFileName.lastIndexOf(".") + 1); // 파일확장자
			
			BufferedImage originImgFile = ImageIO.read(orginTarget); // 업로드된 이미지 파일의 원본을 읽음
			int heightOfOriginImg = originImgFile.getHeight(); // 원본 이미지의 높이(픽셀)
			System.out.println("원본 이미지의 높이 : " + heightOfOriginImg);
			
			BufferedImage destFile = null;
			if (heightOfOriginImg > 100) { // 이미지의 높이가 100픽셀 보다 클 때 높이 100픽셀 비율에 맞춰 자름
				destFile = Scalr.resize(originImgFile, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, 100);
			}
			
			String thumbnailImageFileName = "thumb_" + saveFileName; // 저장될 썸네일 이미지 파일 이름
			File thumbnailFile = new File(upPath + savePath + File.separator + thumbnailImageFileName);
			
			ImageIO.write(destFile, ext, thumbnailFile); // 썸네일 이미지 저장
			
			upFile.setThumbnailFileName(savePath + File.separator + thumbnailImageFileName);
			
		} else { // 이미지 파일이 아님
			System.out.println("이미지파일이 아님");
			upFile.setImage(false);
			upFile.setNotImageFileName(savePath + File.separator + saveFileName); // 이미지가 아닌 경우의 파일 이름
		}
		
		return upFile;
	}

	/**
	 * @methodName : calcSavePath
	 * @author : 신태호
	 * @return 
	 * @date : 2022. 10. 10.
	 * @입력 param :
	 * upPath : 실제 파일이 저장되는 경로
	 * 
	 * @returnType : 파일이 저장되는 날짜 폴더 경로
	 * 실제 파일이 저장되는 경로 하위에 업로드된 년/월/일을 얻어와 해당 날짜의 폴더를 만들 수 있도록 한다
	 */
	public static String calcSavePath(String upPath) {
		Calendar cal = Calendar.getInstance();
		
		String yearPath = File.separator + cal.get(Calendar.YEAR) + ""; // \2022
		String monthPath = yearPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1); // \2022\09
		String datePath = monthPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.DATE)); // \2022\09\13
		
		System.out.println(upPath + datePath);
		
		makeDir(upPath, yearPath, monthPath, datePath); // 실제 저장될 폴더를 만듦
		
		return datePath;
	}

	
	/**
	 * @methodName : makeDir (파일이 저장될 경로(폴더)를 만드는 메서드)
	 * @author : 신태호
	 * @date : 2022. 10. 10.
	 * @입력 param : upPath경로 밑에 yearPath, monthPath, datePath의 폴더를 각각 만든다
	 * @returnType : void
	 */
	private static void makeDir(String upPath, String... paths) { 
		// String... : String 타입의 매개변수로 가변인자 배열로 받을 것임을 컴파일러에게 알려줌
		// yearPath, monthPath, datePath 값을 paths라는 이름의 배열로 넘겨주게 된다
		
		if (new File(upPath + paths[paths.length - 1]).exists()) {
			// 해당 경로가 존재한다... 폴더를 생성할 필요가 없다
			return;
		}
		
		// 해당경로의 폴더가 없으므로 폴더를 생성함
		for (String path : paths) {
			File dirPath = new File(upPath + path);
			
			if (!dirPath.exists()) {
				dirPath.mkdir(); // 실제 디렉토리(폴더) 생성
			}
		}
	}
	
}
