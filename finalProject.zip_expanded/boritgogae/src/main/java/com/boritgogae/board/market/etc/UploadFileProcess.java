package com.boritgogae.board.market.etc;

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
	 * @methodName : uploadFileProcess
	 * @author : hsy
	 * @Date : 2022. 10. 12. :
	 * @입력 : param :
	 *  upPath : 실제 파일이 저장되는 경로
	 * originalFileName : 원본 파일의 이름
	 * file : 업로드 된 파일의 2진 배열
	 * contentType : 업로드된 파일의 타입
	 * 
	 * @returnType : UploadFile
	 */
	public static UploadFile uploadFileProcess(String upPath, String originalFileName, byte[] file, String contentType) throws IOException {
		
		//upload된 파일의 이름을 중복되지 않도록 하여 저장한다.
		UUID uuid = UUID.randomUUID();
		String saveFileName = uuid.toString()+ "_" + originalFileName; //중복되지 않는 파일 이름
		String savePath = calcSavePath(upPath);  //파일이 저장될 경로 계산하여 얻어옴(최종 저장될 경로 = upPath +savePath)
		
		UploadFile upFile = new UploadFile();
		
		upFile.setSavedOriginImageFileName(savePath + File.separator + saveFileName);
		
		File originTarget = new File(upPath +savePath, saveFileName);
		FileCopyUtils.copy(file, originTarget); //원본 파일 저장
		
		if(ImgMediaConfirm.getMediaType(contentType.toLowerCase()) !=null){//이미지 파일이면....
			System.out.println("이미지 파일");
			upFile.setImage(true);
			
			String ext = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);  //파일 확장자
			
			//BufferedImage(이미지 랩핑)
			BufferedImage originImgFile = ImageIO.read(originTarget);// 업로드된 이미지 파일의 원본을 읽음
			int heightOfOriginImg = originImgFile.getHeight();  //원본 이미지 높이(픽셀)
			
			BufferedImage destFile = null;
			if(heightOfOriginImg >100) {  //이미지의 높이가 100픽셀보다 클때 높이를 100픽셀로 맞춰 자른다
				destFile =  Scalr.resize(originImgFile, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, 100);
				
			}
			
			String thumbnailImageFileName = "thumb_"+saveFileName; //저장될 썸네일 이미지 파일 이름
			File thumbnailFile = new File(upPath + savePath + File.separator + thumbnailImageFileName);
			
			ImageIO.write(destFile, ext, thumbnailFile); //썸네일 이미지 저장
			
			// 이름에 저장경로 넣는 이유 : 뷰단에서 띄워줘야 무슨 파일인지 알기 때문에
			upFile.setThumbnailFileName(savePath + File.separator + thumbnailImageFileName);
			
		}else {
			//이미지 파일 아님
			System.out.println("이미지 파일 아님");
			upFile.setImage(false);
			
			upFile.setNotImageFileName(savePath +File.separator+saveFileName);  //이미지가 아닌 경우의 파일 이름
			
		}
		
		return upFile;
		
	}

	/**
	 * @methodName : calcSavePath
	 * @author : hsy
	 * @Date : 2022. 10. 12. :
	 * @입력 : param : 날짜 계산해서 파일 생성
	 * @returnType : String
	 */
	public static String calcSavePath(String upPath) {
		Calendar cal = Calendar.getInstance();
		
		String yearPath = File.separator + cal.get(Calendar.YEAR) + ""; //  \2022
		String monthPath = yearPath+ File.separator + new DecimalFormat("00").format(cal.get(Calendar.MONTH)+1); // \2022\09
		String datePath = monthPath+ File.separator + new DecimalFormat("00").format(cal.get(Calendar.DATE)); // \2022\09\13
		
		System.out.println(upPath+datePath);
		
		makeDir (upPath, yearPath, monthPath, datePath);  // 실제 저장될 폴더를 만듦
		
		return datePath;
	}

	/**
	 * @methodName : makeDir
	 * @author : hsy
	 * @Date : 2022. 10. 12. :
	 * @입력 : param : upPath경로 밑에 날짜별 디렉토리 생성
	 * @returnType : void
	 */
	private static void makeDir(String upPath, String...paths) {  
		// String... : string타입의 매개변수로 가변인자 배열로 받을 것임을 컴파일러에게 알려줌. 
		// yearPath,monthPath,datePath 값을 paths라는 이름의 배열로 넘겨준다
		if(new File(upPath +paths[paths.length-1]).exists()){ //upPath밑에 \2022\09\13폴더가 있는지 확인
			// 해당 경로가 존재한다... 폴더를 생성할 필요가 없다
			return;
		}
		//해당경로의 폴더가 없으므로 폴더를 생성함
		for(String path : paths) {
			File dirPath = new File(upPath +path);
			
			if(!dirPath.exists()) { //paths[0] : yearPath가 없다면, 배열
				dirPath.mkdir(); //실제 디렉토리(폴더) 생성
			}
			
		}
	}
}
