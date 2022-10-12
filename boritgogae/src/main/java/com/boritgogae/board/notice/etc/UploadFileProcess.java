package com.boritgogae.board.notice.etc;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.springframework.util.FileCopyUtils;

public class UploadFileProcess {
	/**
	 * @methodName : uploadFileRename
	 * @author : shh
	 * @param file 
	 * @param originalFileName 
	 * @param upPath 
	 * @param contentType 
	 * @throws IOException 
	 * @date : 2022. 9. 13.
	 * 
	 * @입력 param :
	 * upPath : 실제 파일이 저장되는 경로
	 * originalFileName : 원본 파일의 이름
	 * file : 업로드 된 파일의 이진 배열
	 * contentType : 업로드된 파일의 타입
	 * 
	 * @returnType : void
	 * @upload된 파일의 이름을 중복되지 않도록 한다.
	 * 파일을 업로드 된 날짜의 폴더에 저장
	 * 이미지 파일이라면 썸네일 이미지를 만들어 썸네일 이미지와 원본 파일을 함께 저장
	 * 이미지 파일이 아니라면 원본 파일만 저장
	 * 
	 */
	public static UploadFile uploadFileProcess(String upPath, String originalFileName, byte[] file, String contentType) throws IOException {
		UUID uuid = UUID.randomUUID();
		String saveFileName = uuid.toString() + "_" + originalFileName; // 중복되지 않는 파일 이름
		String savePath = calcSavePath(upPath); // 파일이 저장될 경로 계산하여 얻어옴 (최종 저장될 경로 = upPath + savePath)
		
		UploadFile upFile = new UploadFile(null, null, false);
		
		upFile.setSavedOriginImageFileName(savePath + File.separator + File.separator + saveFileName);
		
		File originTarget = new File(upPath + savePath, saveFileName);
		
		
		if(ImageMediaConfirm.getMediaType(contentType.toLowerCase()) != null) { // 이미지 파일이라면
			System.out.println("이미지 파일!");
			FileCopyUtils.copy(file, originTarget); // 원본 파일 저장
			upFile.setImage(true);
			
			String ext = originalFileName.substring(originalFileName.lastIndexOf(".") + 1); // 파일 확장자
			
			
		} else {
			// 이미지 파일이 아님
			System.out.println("이미지 파일이 아님!");
			upFile.setImage(false);
			
		}
		return upFile; 
		
	}

	/**
	 * @methodName : calcSavePath
	 * @author : shh
	 * @date : 2022. 9. 13.
	 * @입력 param : 
	 * upPath : 실제 파일이 저장되는 경로
	 * 
	 * @returnType : 파일이 저장되는 날짜 폴더 경로
	 * 실제 파일이 저장되는 경로 하위에 업로드된 년/월/일 을 얻어와 해당 날짜의 폴더를 만들 수 있도록 함
	 */
	public static String calcSavePath(String upPath) {
		Calendar cal = Calendar.getInstance();
		
		String yearPath = File.separator + File.separator + cal.get(Calendar.YEAR) + ""; //  \2022
		String monthPath = yearPath + File.separator + File.separator + new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1); // \2022\09
		String datePath = monthPath + File.separator + File.separator + new DecimalFormat("00").format(cal.get(Calendar.DATE)); // \2022\09\13
		
		System.out.println(upPath + datePath);
		
		makeDir(upPath, yearPath, monthPath, datePath); // 실제 저장될 폴더를 만듬
		
		return datePath;
	}

	/**
	 * @methodName : makeDir
	 * @author : shh
	 * @date : 2022. 9. 13.
	 * @입력 param : upPath 경로 밑에 yearPath, monthPath, datePath의 폴더를 각각 만든다
	 * @returnType : void
	 */
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
