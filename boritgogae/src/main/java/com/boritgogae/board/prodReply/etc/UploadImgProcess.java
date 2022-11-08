package com.boritgogae.board.prodReply.etc;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.springframework.util.FileCopyUtils;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Scalar;

public class UploadImgProcess {
	public static UploadImg uploadImgProcess(String upPath, String originImgName, byte[] file, String prodNo) throws IOException {
		UUID uuid = UUID.randomUUID();
		String saveImgName = uuid.toString() + "_" + originImgName;
		
		String savePath = savePath(upPath, prodNo);
		System.out.println(upPath + savePath);
		
		System.out.println(savePath);
		
		UploadImg upImg = new UploadImg();
		
		upImg.setImgName(savePath + File.separator +saveImgName);
		
		File originTarget = new File(upPath + savePath, saveImgName);
		FileCopyUtils.copy(file, originTarget);
		
		String ext = originImgName.substring(originImgName.lastIndexOf(".")+1);
		
		BufferedImage bi = ImageIO.read(originTarget);
		BufferedImage thumbDest = null;
		int heightBi = bi.getHeight();
		if (heightBi > 80) {
			thumbDest = Scalr.resize(bi, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, 80);
			
		}
		String thumbnailName = "thumb_" + saveImgName;
		
		File thumbnailFile = new File(upPath+savePath+ File.separator + thumbnailName);
		
		ImageIO.write(thumbDest, ext, thumbnailFile);
		
		upImg.setThumbnailName(savePath+File.separator+thumbnailName);
		
		System.out.println(upImg.toString());
		
		return upImg;
	}

	private static String savePath(String upPath, String prodNo) {
		Calendar cal = Calendar.getInstance();
		String prodNoPath = File.separator + prodNo;
		String year = prodNoPath+File.separator + cal.get(Calendar.YEAR);
		String month = year + File.separator + new DecimalFormat("00").format(cal.get(Calendar.MONTH));
		String date = month + File.separator + new DecimalFormat("00").format(cal.get(Calendar.DATE));
		
		makeDir(upPath, prodNoPath, year, month, date);
		
		return date;
	}

	private static void makeDir(String upPath, String... paths) {
		if(new File(upPath+paths[paths.length-1]).exists()) {
			return;
		}
		
		for (String s : paths) {
			File dirPath = new File(upPath+s);
			if(!dirPath.exists()) {
				dirPath.mkdir();
			}
		}
		
	}
	

}
